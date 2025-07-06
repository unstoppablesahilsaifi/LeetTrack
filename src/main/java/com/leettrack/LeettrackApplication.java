package com.leettrack;

import com.leettrack.entity.Role;
import com.leettrack.entity.User;
import com.leettrack.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication(
		exclude = {
				org.springframework.ai.vectorstore.redis.autoconfigure.RedisVectorStoreAutoConfiguration.class
		}
)
public class LeettrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeettrackApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			if (!userRepository.existsByEmail("sahiladmin@example.com")) {
				User admin = new User();
				admin.setEmail("sahiladmin@example.com");
				admin.setPassword(encoder.encode("admin123"));
				admin.setFullName("Admin User");
				admin.setRoles(Set.of(Role.ADMIN));
				userRepository.save(admin);
			}
		};
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logStartup() {
		System.out.println("✅ Application is running successfully");
	}
}
