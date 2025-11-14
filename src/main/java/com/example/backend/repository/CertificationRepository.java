package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Certification;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    boolean existsByName(String name);
}
