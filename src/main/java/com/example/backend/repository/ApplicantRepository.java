package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Applicant;


public interface ApplicantRepository extends JpaRepository<Applicant,Long>{

    public Applicant findByEmail(String email);
    public Applicant findByApplicantId(long applicantId);
    public boolean existsByEmail(String email);
}