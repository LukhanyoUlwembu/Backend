package com.example.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.JobRequisition;


public interface JobRequisitionRepository extends JpaRepository<JobRequisition,Long>{
public JobRequisition  findByJobRequisitionId(long jobRequisitionId);
}
