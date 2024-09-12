package com.medhead.backend.web.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenFilterConfigured extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilterConfigured(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Configures HTTP security for the application, including custom filters.
     *
     * This method sets up security configurations by adding a custom JWT token filter to the Spring Security filter chain.
     * The custom filter is added before the `UsernamePasswordAuthenticationFilter` to ensure it processes requests
     * before the standard authentication filter.
     *
     * @param http the HttpSecurity object used to configure security settings
     * @throws Exception if an error occurs during security configuration
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
