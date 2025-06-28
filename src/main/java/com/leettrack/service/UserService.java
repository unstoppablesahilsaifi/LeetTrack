package com.leettrack.service;

import com.leettrack.dto.RegisterRequest;
import com.leettrack.dto.AuthResponse;

public interface UserService {

    AuthResponse registerUser(RegisterRequest request);
}
