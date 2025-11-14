package com.example.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.AdditionalFile;

public interface AdditionalFileRepository extends JpaRepository<AdditionalFile,Long>{

}
