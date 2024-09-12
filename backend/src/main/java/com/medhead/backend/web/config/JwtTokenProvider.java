package com.medhead.backend.web.config;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.medhead.backend.model.User;
import com.medhead.backend.web.controller.UserController;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    private String jwtSecret = "WhtdaD8N9ACDzKF6djJMf7p4sPKqfDYkN48tADKubkU=";
    private SecretKey key = Jwts.SIG.HS512.key().build();

    @Autowired
    private UserController userController;

    private int jwtExpirationInMs = 86_400_000; // Expiration time set to 1 day

    /**
     * Generates a JWT token for the given authentication.
     *
     * @param authentication the authentication object containing user details
     * @return the generated JWT token
     */
    public String generateToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        // claims
        Map<String, Object> claims = new HashMap<>();

        claims.put("username", userPrincipal.getUsername());
        claims.put("role", "user");

        return Jwts.builder().claim("username", userPrincipal.getUsername()).claims().add(claims).and()
                .subject(userPrincipal.getId().toString())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)).signWith(key).compact();
    }

    /**
     * Extracts the user ID from the JWT token.
     *
     * @param token the JWT token
     * @return the user ID extracted from the token
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * Validates the JWT token by checking its signature and expiration.
     *
     * @param authToken the JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(authToken);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Malformed JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("Empty JWT token");
        } catch (PrematureJwtException ex) {
            System.out.println("JWT token used before its allowed time");
        } catch (JwtException ex) {
            System.out.println("General error related to the JWT token");
        }
        return false;
    }

    /**
     * Extracts the JWT token from the HTTP request's Authorization header.
     *
     * @param request the HTTP request
     * @return the JWT token if present and properly formatted, null otherwise
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    /**
     * Retrieves the authentication object for the given JWT token.
     *
     * @param token the JWT token
     * @return an Authentication object containing the user details
     * @throws Exception if the user cannot be found
     */
    public Authentication getAuthentication(String token) throws Exception {
        Long userId = getUserIdFromJWT(token);

        Optional<User> userDetails = userController.getUserById(userId);
        User user = userDetails.orElseThrow(() -> new Exception("User cannot be found"));

        return new UsernamePasswordAuthenticationToken(user, "", null);
    }

}