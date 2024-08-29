package com.medhead.backend.web.dao;
import com.medhead.backend.model.User;
import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(int id);
    boolean createUser(String username, String rawPassword);
}