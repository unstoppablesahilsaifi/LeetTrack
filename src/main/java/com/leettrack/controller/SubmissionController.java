package com.leettrack.controller;

import com.leettrack.dto.SubmissionRequest;
import com.leettrack.entity.Submission;
import com.leettrack.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping
    public Submission submitChallenge(@RequestBody SubmissionRequest request) {
        return submissionService.submitSolution(
                request.getChallengeId(),
                request.getCode(),
                request.getEmail()
        );
    }
}
