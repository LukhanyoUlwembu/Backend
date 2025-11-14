package com.example.backend.repository;

import com.example.backend.model.PreScreeningResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreScreeningResultRepository extends JpaRepository<PreScreeningResult, Long> {
    PreScreeningResult findByApplicantIdAndJobPostingId(Long applicantId, Long jobPostingId);
}

