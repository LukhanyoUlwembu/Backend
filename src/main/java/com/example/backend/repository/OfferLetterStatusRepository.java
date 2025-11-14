package com.example.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.model.OfferLetterStatus;

@Repository
public interface OfferLetterStatusRepository extends JpaRepository<OfferLetterStatus, Long> {
    Optional<OfferLetterStatus> findByName(String name);
}
