package com.medhead.backend.web.config;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.medhead.backend.model.User;
import com.medhead.backend.web.controller.UserController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    private String jwtSecret = "test";
    private SecretKey key = Jwts.SIG.HS512.key().build();

    @Autowired
    private UserController userController;

    private int jwtExpirationInMs = 864_000_000;

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

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(authToken);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Signature JWT invalide");
        } catch (MalformedJwtException ex) {
            System.out.println("Token JWT malformé");
        } catch (ExpiredJwtException ex) {
            System.out.println("Token JWT expiré");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Token JWT non pris en charge");
        } catch (IllegalArgumentException ex) {
            System.out.println("Token JWT vide");
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public Authentication getAuthentication(String token) throws Exception {
        Long userId = getUserIdFromJWT(token);

        Optional<User> userDetails = userController.getUserById(userId);
        User user = userDetails.orElseThrow(() -> new Exception("User cannot be found"));

        return new UsernamePasswordAuthenticationToken(user, "", null);
    }

}