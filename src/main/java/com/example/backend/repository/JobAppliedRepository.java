package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.JobApplied;
import com.example.backend.model.JobPosting;
import com.example.backend.model.OfferLetterStatus;

public interface JobAppliedRepository extends JpaRepository<JobApplied,Long>{
public JobApplied findByJobAppliedId(long jobAppliedId);
public  List<JobApplied> findByApplicantApplicantId(long applicantId);
  public  List<JobApplied> findByJobPostingJobPostingId(long jobPostingId);
  public boolean existsByApplicantApplicantIdAndJobPostingJobPostingId(long applicantId,long jobPostingId);
   public JobApplied findByApplicantApplicantIdAndJobPostingJobPostingId(long applicantId,long jobPostingId);
 public List<JobApplied> findByJobPosting(JobPosting posting);
 public JobApplied findByOnboardingId(long id);
 List<JobApplied> findByOfferLetter_Status(OfferLetterStatus status);
}
