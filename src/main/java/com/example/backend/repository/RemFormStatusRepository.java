package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.RemFormStatus;
import java.util.Optional;

public interface RemFormStatusRepository extends JpaRepository<RemFormStatus, Long> {
    Optional<RemFormStatus> findByName(String name);
}
