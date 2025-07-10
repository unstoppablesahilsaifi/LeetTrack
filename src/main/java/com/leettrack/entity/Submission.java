package com.leettrack.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Challenge challenge;

    private String submittedCode;
    private boolean isCorrect;
    private LocalDateTime submittedAt = LocalDateTime.now();




    public void setIsCorrect(boolean isCorrect) {
    }
}

