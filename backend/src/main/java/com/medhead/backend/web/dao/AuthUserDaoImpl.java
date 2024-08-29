
package com.medhead.backend.web.dao;

import com.medhead.backend.model.AuthUser;
import com.medhead.backend.model.Speciality;
import org.springframework.stereotype.Repository;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class AuthUserDaoImpl implements AuthUserDao {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthUser login(String username, String rawPassword) {
        String sql = "SELECT id, username, password FROM users WHERE username = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (passwordEncoder.matches(rawPassword, storedPassword)) {
                        // Exclude the password from the response for security reasons
                        return new AuthUser(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("password")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            // Handle the exception more robustly
            throw new RuntimeException("Error while logging in", e);
        }
        return null;
    }


    @Override
    public AuthUser signup(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            String hashedPassword = passwordEncoder.encode(password);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return new AuthUser(
                                generatedKeys.getInt(1),
                                username,
                                password
                        );
                    }
                }
            }
        } catch (SQLException e) {
            // Handle the exception more robustly
            throw new RuntimeException("Error while signing up", e);
        }
        return null;
    }


}