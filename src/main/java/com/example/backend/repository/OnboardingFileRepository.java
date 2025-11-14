package com.example.backend.repository;

import com.example.backend.model.OnboardingFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingFileRepository extends JpaRepository<OnboardingFile, Long> {
    // Add custom queries if needed
}
