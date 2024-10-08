package com.medhead.backend.web.controller;

import com.medhead.backend.model.User;
import com.medhead.backend.web.dao.AuthDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthDao authDao;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        // New user with correct logins
        User user = new User(5L, "andrew", "andrew");

        when(authDao.login(user)).thenReturn("valid_token");

        ResponseEntity<?> response = authController.login(user);

        // Verify result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof java.util.Map);
        assertEquals("valid_token", ((java.util.Map)response.getBody()).get("token"));

        // Verify query has been called once with right credentials
        verify(authDao, times(1)).login(user);
    }

    @Test
    public void testLoginFailure() throws Exception {
        // New user with wrong logins
        User user = new User(5L, "andrew", "andrew");
        user.setUsername("wrongusername");
        user.setPassword("wrongpassword");

        // Wrong login
        when(authDao.login(user)).thenThrow(new Exception("Invalid credentials"));

        ResponseEntity<?> response = authController.login(user);

        // Verify answer's status
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Login failed: Invalid credentials", response.getBody());

        // Verify login method has been called once with the wrong credentials
        verify(authDao, times(1)).login(user);
    }
}