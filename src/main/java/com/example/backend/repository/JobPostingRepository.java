package com.example.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.JobPosting;


public interface JobPostingRepository extends JpaRepository<JobPosting,Long>{
    public JobPosting findByJobPostingId(long jobPostingId); 
    List<JobPosting> findByJobDeadlineBeforeAndActiveTrue(Date now);
}
