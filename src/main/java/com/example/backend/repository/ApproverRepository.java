package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Approver;

public interface ApproverRepository extends JpaRepository<Approver,Long>{
   public boolean existsBySignature(String signature);
}
