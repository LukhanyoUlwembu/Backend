package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.ApplicantFile;

public interface ApplicantFileRepository extends JpaRepository<ApplicantFile,Long>{
    
}
