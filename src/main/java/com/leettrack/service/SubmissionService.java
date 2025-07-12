package com.leettrack.service;

import com.leettrack.entity.Challenge;
import com.leettrack.entity.Submission;
import com.leettrack.entity.User;
import com.leettrack.repository.ChallengeRepository;
import com.leettrack.repository.SubmissionRepository;
import com.leettrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.time.DateUtils.isSameDay;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserRepository userRepository;

    public Submission submitSolution(Long challengeId, String code, String email) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isCorrect = evaluateCode(code); // placeholder for logic

        Submission submission = new Submission();
        submission.setChallenge(challenge);
        submission.setUser(user);
        submission.setSubmittedCode(code);
        submission.setIsCorrect(isCorrect);

        // 🔥 STREAK LOGIC STARTS HERE
        java.util.Date today = new java.util.Date();
        if (user.getLastSubmissionDate() == null || !isSameDay(user.getLastSubmissionDate(), today)) {
            user.setStreak(user.getStreak() + 1);
        }
        user.setLastSubmissionDate(today);
        userRepository.save(user);
        // 🔥 STREAK LOGIC ENDS HERE
        return submissionRepository.save(submission);
    }

    private boolean evaluateCode(String code) {
        // You can implement actual code evaluation here
        return code.contains("return"); // Dummy check
    }

    public Submission submitChallenge(Submission submission) {
        return submissionRepository.save(submission);
    }

}

