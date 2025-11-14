package com.example.backend.repository;

import com.example.backend.model.JobStatus;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {
    Optional<JobStatus> findByName(String name);
    Optional<JobStatus> findByOrderIndex(int orderIndex);
}

