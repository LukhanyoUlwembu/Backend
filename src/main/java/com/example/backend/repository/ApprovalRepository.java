package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.backend.model.Approval;
import com.example.backend.model.RemForm;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {
     public boolean existsByJobRequisitionJobRequisitionId(long id);
     public Approval findByJobRequisitionJobRequisitionId(long id);
     public boolean existsByJobRequisitionJobRequisitionIdAndApproverSignature(long jobRequisitionId, String signature);
     // REM Form Approvals
    Optional<Approval> findByRemForm_Id(Long remFormId);
    boolean existsByRemForm_Id(Long remFormId);

     Optional<Approval> findByRemForm(RemForm remForm);
    boolean existsByRemForm(RemForm remForm);
}
