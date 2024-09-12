package com.medhead.backend.web.dao;
import com.medhead.backend.model.User;

public interface AuthDao {
    String login(User user) throws Exception;
}
