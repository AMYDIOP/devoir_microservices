package com.example.userservice.controller;

import com.example.userservice.dto.*;
import com.example.userservice.model.User;
import com.example.userservice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }

    @PostMapping("/refresh-token")
    public AuthResponse refreshToken(@RequestHeader("Authorization") String token) {
        return authService.refreshToken(token.replace("Bearer ", ""));
    }

    @GetMapping("/me")
    public User me(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return authService.getCurrentUser(token);
    }
}
