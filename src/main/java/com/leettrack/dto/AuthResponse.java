package com.leettrack.dto;

public class AuthResponse {

    private String token;
    private String message;

    public AuthResponse() {}

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Getters and Setters
}
