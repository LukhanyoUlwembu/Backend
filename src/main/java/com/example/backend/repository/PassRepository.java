package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.JobStatus;
import com.example.backend.model.Pass;

public interface PassRepository extends JpaRepository<Pass,Long>{
   boolean existsBySignatureAndStatusAndJobAppliedJobAppliedId(String signature, JobStatus status, Long jobAppliedId);

}
