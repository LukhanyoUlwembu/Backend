package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.WorkExperience;


public interface WorkExperienceRepository extends JpaRepository<WorkExperience,Long>{
public WorkExperience findByWorkExperienceId(long workExperienceId);
}
