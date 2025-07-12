package com.leettrack.service;

import com.leettrack.entity.User;
import com.leettrack.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StreakResetService {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * *") // Runs daily at midnight
    public void resetStreaks() {
        log.info("Starting streak reset job at midnight");

        List<User> users = userRepository.findAll();
        LocalDate today = LocalDate.now();

        for (User user : users) {
            Date lastSubmission = user.getLastSubmissionDate();

            if (lastSubmission == null ||
                    !lastSubmission.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(today)) {
                user.setStreak(0);
            }
        }

        userRepository.saveAll(users);
        log.info("Streak reset complete");
    }
}
