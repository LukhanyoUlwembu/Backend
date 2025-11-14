package com.example.backend.repository;

import com.example.backend.model.Interview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview,Long>{
    public List<Interview> findByInterviewer(String interviewer);
}
