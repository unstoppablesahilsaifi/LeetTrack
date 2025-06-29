package com.leettrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = {
		org.springframework.ai.vectorstore.redis.autoconfigure.RedisVectorStoreAutoConfiguration.class
})
public class LeettrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeettrackApplication.class, args);
	}
	// Log message after full startup
	@EventListener(ApplicationReadyEvent.class)
	public void logStartup() {
		System.out.println("✅ Application is running successfully");
	}
}





