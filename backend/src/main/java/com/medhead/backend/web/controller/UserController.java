package com.medhead.backend.web.controller;

import com.medhead.backend.model.User;
import com.medhead.backend.web.dao.UserDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping("/Users")
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @GetMapping(value = "/Users/{id}")
    public User getUserById(@PathVariable int id) {
        return userDao.findById(id);
    }

}