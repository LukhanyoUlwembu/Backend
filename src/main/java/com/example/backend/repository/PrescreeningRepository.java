package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Prescreening;

public interface PrescreeningRepository extends JpaRepository<Prescreening,Long>{
    
}
