package com.leettrack.controller;

import com.leettrack.dto.RegisterRequest;
import com.leettrack.dto.AuthResponse;
import com.leettrack.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
