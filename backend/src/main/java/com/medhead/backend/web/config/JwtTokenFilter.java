package com.medhead.backend.web.config;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Filters HTTP requests to extract and validate JWT tokens, and sets the authentication context.
     *
     * This method intercepts incoming HTTP requests, extracts the JWT token from the Authorization header,
     * validates the token, and sets the authentication context if the token is valid. The filter chain
     * is then continued with the request and response.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain for continuing request processing
     * @throws ServletException if an error occurs during request processing
     * @throws IOException if an I/O error occurs during request processing
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = null;
            try {
                authentication = jwtTokenProvider.getAuthentication(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}