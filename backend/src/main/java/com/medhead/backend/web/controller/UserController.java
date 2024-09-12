package com.medhead.backend.web.controller;

import com.medhead.backend.model.User;
import com.medhead.backend.web.dao.UserDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    /**
     * Retrieves a list of all users from the database.
     * @return a list of User objects representing all users in the system
     */
    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping("/Users")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    /**
     * Retrieves a user by their unique ID from the database.
     * @param id the unique identifier of the user
     * @return an Optional containing the User object if found, or an empty Optional if not found
     */
    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping(value = "/Users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userDao.findById(id);
    }

    /**
     * Adds a new user to the database.
     * @param user the User object to be added to the database
     */
    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @PostMapping(value = "/Users")
    public void addUser(@RequestBody User user) {
        userDao.save(user);
    }

}
