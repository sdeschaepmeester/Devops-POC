package com.medhead.backend.web.controller;
import com.medhead.backend.model.User;
import com.medhead.backend.web.dao.AuthDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class AuthController {
    private final AuthDao authDao;

    public AuthController(AuthDao authDao){
        this.authDao = authDao;
    }

    /**
     * Handles user login requests by validating credentials and returning a JWT token upon successful authentication.
     * @param user the User object containing login credentials (username and password)
     * @return a ResponseEntity containing either the JWT token or an error message with an unauthorized status
     */
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

