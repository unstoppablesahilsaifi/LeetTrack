package com.leettrack.repository;

import com.leettrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (for login)
    Optional<User> findByEmail(String email);

    // Check if user exists by email (for signup validation)
    boolean existsByEmail(String email);
}
