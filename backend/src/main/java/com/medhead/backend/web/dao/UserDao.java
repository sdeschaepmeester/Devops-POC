package com.medhead.backend.web.dao;
import com.medhead.backend.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(int id);
}