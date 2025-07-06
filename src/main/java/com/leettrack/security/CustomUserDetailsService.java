    package com.leettrack.security;

    import com.leettrack.entity.User;
    import com.leettrack.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.*;
    import org.springframework.stereotype.Service;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;

    import java.util.Arrays;
    import java.util.stream.Collectors;


    @Service
    public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        // Load user from DB by username (in our case: email)


        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    Arrays.stream(user.getRoles()
                                    .replaceAll("[\\[\\]]", "")  // ✅ remove square brackets
                                    .split(","))
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                            .collect(Collectors.toList())
            );

        }


    }
