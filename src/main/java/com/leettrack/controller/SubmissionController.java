package com.leettrack.controller;

import com.leettrack.entity.Submission;
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
    public Submission submitChallenge(@RequestBody Submission submission) {
        submission.setSubmittedAt(java.time.LocalDateTime.now());
        return submissionService.submitChallenge(submission);
    }
}
