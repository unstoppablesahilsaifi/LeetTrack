package com.leettrack.repository;

import com.leettrack.entity.Submission;
import com.leettrack.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    // Add findByUserId or ChallengeId if needed later
}
