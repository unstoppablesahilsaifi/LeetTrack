package com.leettrack.dto;

import lombok.Data;

@Data
public class SubmissionRequest {
    private Long challengeId;
    private String email;
    private String code;
}
