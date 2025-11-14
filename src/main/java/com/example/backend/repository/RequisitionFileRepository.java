package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.RequisitionFile;

public interface RequisitionFileRepository extends JpaRepository<RequisitionFile,Long>{
    
}
