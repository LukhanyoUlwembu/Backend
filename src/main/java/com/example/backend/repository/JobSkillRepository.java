package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.JobSkill;


public interface JobSkillRepository extends JpaRepository<JobSkill,Long>{
 public JobSkill findByJobSkiillId(long jobSkiillId);
}
