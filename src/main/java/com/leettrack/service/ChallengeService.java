package com.leettrack.service;

import com.leettrack.entity.Challenge;
import com.leettrack.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }
    @Cacheable(value = "hotChallenges", key = "#id")
    public Challenge getChallengeById(Long id) {
        System.out.println("🔥 DB Call for Challenge ID: " + id);
        return challengeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));
    }

    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }
}