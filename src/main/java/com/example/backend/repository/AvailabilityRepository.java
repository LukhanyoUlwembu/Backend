package com.example.backend.repository;

import com.example.backend.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    boolean existsByNameIgnoreCase(String name);
}
