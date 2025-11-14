package com.example.backend.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class JobApplied {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobAppliedId;
    private float score;
    private String notes;
    
    @OneToMany
    @JoinColumn(referencedColumnName = "jobAppliedId")
    private List<Interview> interviews;

      
    @OneToMany
    @JoinColumn(referencedColumnName = "jobAppliedId")
    private List<Pass> pass;

    public List<Pass> getPass() {
        return this.pass;
    }

    public void setPass(List<Pass> pass) {
        this.pass = pass;
    }

@Version 
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    @ManyToOne
    private Applicant applicant;
        private Date created;

  
    @ManyToOne
    private JobPosting jobPosting;
    
    
    @ManyToMany
    @JoinTable(
        joinColumns = @JoinColumn(referencedColumnName  = "jobAppliedId"),
        inverseJoinColumns = @JoinColumn(referencedColumnName  = "addId")
    )
    private List<ApplicantFile> applicantFiles;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private JobStatus status;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Availability availability;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private RemForm RemForm;
    
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private OfferLetter offerLetter;

     @OneToOne
    @JoinColumn(referencedColumnName = "id")
     private Onboarding onboarding;

     public Onboarding getOnboarding() {
         return this.onboarding;
     }

     public void setOnboarding(Onboarding onboarding) {
         this.onboarding = onboarding;
     }

    public OfferLetter getOfferLetter() {
        return this.offerLetter;
    }

    public void setOfferLetter(OfferLetter offerLetter) {
        this.offerLetter = offerLetter;
    }

    // Getters and setters
    
    public RemForm getRemForm() {
        return this.RemForm;
    }

    public void setRemForm(RemForm RemForm) {
        this.RemForm = RemForm;
    }
    public List<Interview> getInterviews() {
        return this.interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getScore() {
        return this.score;
    }

    public void setScore(float score) {
        this.score = score;
    }
      public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

      public JobStatus getStatus() {
        return this.status;
    }

    public void setStatus(JobStatus rejected) {
        this.status = rejected;
    }

     public Availability getAvailability() {
        return this.availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public List<ApplicantFile> getApplicantFile() {
        return this.applicantFiles;
    }

    public void setApplicantFile(List<ApplicantFile> applicantFile) {
        this.applicantFiles = applicantFile;
    }
    


    public Applicant getApplicant() {
        return this.applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public JobPosting getJobPosting() {
        return this.jobPosting;
    }

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }
    public long getJobAppliedId() {
        return jobAppliedId;
    }

    public void setJobAppliedId(long jobAppliedId) {
        this.jobAppliedId = jobAppliedId;
    }

}
