package com.medhead.backend.web.dao;
import com.medhead.backend.model.AuthUser;

public interface AuthUserDao {
    AuthUser login(String username, String password);
    AuthUser signup(String username, String password);
}
