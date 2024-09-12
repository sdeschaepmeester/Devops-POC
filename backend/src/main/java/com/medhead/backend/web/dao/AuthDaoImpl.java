package com.medhead.backend.web.dao;

import com.medhead.backend.model.User;
import com.medhead.backend.web.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthDaoImpl implements AuthDao {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  // Use this for password hashing

    @Override
    public String login(User user) throws Exception {
        User storedUser = findByUsername(user.getUsername());

        if (storedUser == null) {
            throw new Exception("User not found");
        }

        // Compare stored hashed password with the password provided by the user
        if (!passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
            throw new Exception("Invalid password");
        }

        // Generate token
        Authentication authentication = new UsernamePasswordAuthenticationToken(storedUser, null, new ArrayList<>());
        return jwtTokenProvider.generateToken(authentication);
    }

    // Example method to retrieve user details by username
    private User findByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}