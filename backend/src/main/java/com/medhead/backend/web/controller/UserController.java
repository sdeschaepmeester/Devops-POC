package com.medhead.backend.web.controller;

import com.medhead.backend.model.User;
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
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping("/Users")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping(value = "/Users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userDao.findById(id);
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @PostMapping(value = "/Users")
    public void addUser(@RequestBody User user) {
        userDao.save(user);
    }

//    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
//    @PostMapping(value = "/Users/Login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try {
//            String token = userDao.login(user);
//            return ResponseEntity.ok().body(Collections.singletonMap("token", token));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
//        }
//    }

}
