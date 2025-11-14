package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.model.OfferLetter;

@Repository
public interface OfferLetterRepository extends JpaRepository<OfferLetter, Long> {
    // Additional custom queries if needed
}



