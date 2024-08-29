package com.medhead.backend.web.controller;
import com.medhead.backend.web.dao.AuthUserDao;
import com.medhead.backend.model.AuthUser;
import com.medhead.backend.web.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final AuthUserDao authUserDao;
    private final JwtService jwtService;

    public AuthController(AuthUserDao authUserDao, JwtService jwtService){
        this.authUserDao = authUserDao;
        this.jwtService = jwtService;
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @PostMapping("/Signup")
    public ResponseEntity<?> signup(@RequestBody AuthUser authUser) {
        AuthUser user = authUserDao.signup(authUser.getUsername(), authUser.getPassword());
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "https://medhead-frontend-9f97491cebce.herokuapp.com")
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody AuthUser authUser) {
        AuthUser user = authUserDao.login(authUser.getUsername(), authUser.getPassword());
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(token);
    }
}
