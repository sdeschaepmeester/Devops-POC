package com.medhead.backend.web.controller;
import com.medhead.backend.model.User;
import com.medhead.backend.web.config.JwtTokenProvider;
import com.medhead.backend.web.dao.AuthDao;
import com.medhead.backend.web.dao.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthController {
    private final AuthDao authDao;
    private JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthDao authDao, JwtTokenProvider jwtTokenProvider){
        this.authDao = authDao;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @PostMapping(value = "/Auth/Login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String token = authDao.login(user);
            return ResponseEntity.ok().body(Collections.singletonMap("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

}

