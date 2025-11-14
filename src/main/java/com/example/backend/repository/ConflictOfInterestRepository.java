package com.example.backend.repository;

import com.example.backend.model.ConflictOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConflictOfInterestRepository extends JpaRepository<ConflictOfInterest, Long> {
    // You can define custom queries here if needed
}