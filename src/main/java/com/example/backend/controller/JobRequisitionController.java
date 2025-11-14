package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.model.Approval;
import com.example.backend.model.Approver;
import com.example.backend.model.JobRequisition;
import com.example.backend.service.HrServiceImplementation;

import java.util.List;

@RestController
@RequestMapping("/job-requisitions")
public class JobRequisitionController {

    @Autowired
    private HrServiceImplementation hr;

    // GET /job-requisitions
    @GetMapping
    public List<JobRequisition> getAllJobRequisitions() {
        return hr.getAllRequisitions();
    }

    // GET /job-requisitions/{id}
    @GetMapping("/{id}")
    public JobRequisition getJobRequisitionById(@PathVariable long id) {
        return hr.getRequisition(id);
    }

    // POST /job-requisitions (multipart)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JobRequisition> createJobRequisition(
        @RequestPart("jobRequisition") JobRequisition jobRequisition,
        @RequestParam("organogramFile") MultipartFile organogramFile
        
    ) {
        return ResponseEntity.ok(hr.createJobRequisitionWithFiles(jobRequisition, organogramFile));
    }

    // PUT /job-requisitions/{id}
    @PutMapping("/{id}")
    public JobRequisition updateRequisition(@PathVariable long id, @RequestBody JobRequisition job) {
        job.setJobRequisitionId(id); // assuming this is the field name
        return hr.updateRequisition(job);
    }

    // GET /approvals
    @GetMapping("/approvals")
    public List<Approval> getAllApprovals() {
        return hr.getAllApprovals();
    }

    // GET /approvals/{id}
    @GetMapping("/approvals/{id}")
    public Approval getApprovalById(@PathVariable long id) {
        return hr.getApproval(id);
    }

     // GET /approvals/{id}/job-id
    @GetMapping("/approvals/{id}/job-id")
    public Approval getApprovalByJobId(@PathVariable long id) {
        return hr.getApprovalByJobId(id);
    }

    @PostMapping("/approvals/vote")
public Approval submitApproverVote(@RequestBody ApprovalVoteRequest vote) {
    Approver approver = new Approver();
    approver.setName(vote.getApproverName());
    approver.setSignature(vote.getApproverSignature());
    approver.setDecision(vote.isDecision());

    return hr.addOrUpdateApprovalVote(vote.getJobRequisitionId(), approver);
}

    
    //Get /approvals/{id}/exist
    @GetMapping("/approvals/{id}/exist")
    public boolean approvalExists(@PathVariable long id){
        return hr.approvalExists(id);
    }

    @GetMapping("/approvals/{jobRequisitionId}/exist/{signature}")
public ResponseEntity<Boolean> hasApproverVoted(
        @PathVariable long jobRequisitionId,
        @PathVariable String signature) {
    boolean exists = hr.hasApproverAlreadyVoted(jobRequisitionId, signature);
    return ResponseEntity.ok(exists);
}


 public static class ApprovalVoteRequest {
     private long jobRequisitionId;
     private String approverName;
     private String approverSignature;
     private boolean decision;

     public long getJobRequisitionId() {
         return this.jobRequisitionId;
     }

     public void setJobRequisitionId(long jobRequisitionId) {
         this.jobRequisitionId = jobRequisitionId;
     }

     public String getApproverName() {
         return this.approverName;
     }

     public void setApproverName(String approverName) {
         this.approverName = approverName;
     }

     public String getApproverSignature() {
         return this.approverSignature;
     }

     public void setApproverSignature(String approverSignature) {
         this.approverSignature = approverSignature;
     }

     public boolean isDecision() {
         return this.decision;
     }

     public void setDecision(boolean decision) {
         this.decision = decision;
     }

}

}
