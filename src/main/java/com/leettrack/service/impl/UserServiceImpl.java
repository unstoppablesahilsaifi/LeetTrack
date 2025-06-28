package com.leettrack.service.impl;

import com.leettrack.dto.RegisterRequest;
import com.leettrack.dto.AuthResponse;
import com.leettrack.entity.Role;
import com.leettrack.entity.User;
import com.leettrack.repository.UserRepository;
import com.leettrack.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, "Email already in use");
        }

        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFullName(),
                Set.of(Role.USER)
        );

        userRepository.save(user);

        return new AuthResponse(null, "User registered successfully");
    }
}
