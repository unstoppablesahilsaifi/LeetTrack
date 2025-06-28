package com.leettrack.service.impl;

import com.leettrack.dto.AuthResponse;
import com.leettrack.dto.RegisterRequest;
import com.leettrack.entity.Role;
import com.leettrack.entity.User;
import com.leettrack.repository.UserRepository;
import com.leettrack.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

        // ✅ FIX: Avoid Set.of(...) to prevent type inference issue
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);

        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFullName(),
                roles
        );

        userRepository.save(user);

        return new AuthResponse(null, "User registered successfully");
    }
}
