package com.leettrack.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

    public String getUsername() {
        return email;
    }
}
