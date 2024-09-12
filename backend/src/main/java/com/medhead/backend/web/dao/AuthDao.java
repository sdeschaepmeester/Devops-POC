package com.medhead.backend.web.dao;
import com.medhead.backend.model.User;
import java.util.List;
import java.util.Optional;

public interface AuthDao {
    String login(User user) throws Exception;
}
