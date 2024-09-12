package com.medhead.backend.web.dao;
import com.medhead.backend.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    //String login(User user) throws Exception;
}
