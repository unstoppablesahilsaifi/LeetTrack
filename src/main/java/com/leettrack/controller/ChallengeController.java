package com.leettrack.controller;

import com.leettrack.entity.Challenge;
import com.leettrack.entity.Challenge;
import com.leettrack.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public List<Challenge> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @PostMapping
    public Challenge createChallenge(@RequestBody Challenge challenge) {
        return challengeService.createChallenge(challenge);
    }

    @GetMapping("/{id}")
    public Challenge getChallenge(@PathVariable Long id) {
        return challengeService.getChallengeById(id);
    }
}