package com.example.backend.model;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class RemForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String name;
    private String position;
    private Date commencementDate;
    private String previousSalary;
    private String proposedSalary;
    private String billingRate;
    private String clientName;
    private String description;
    private String recommendedBy;
    private String hrComment;
    private String HrEmp;
    private boolean active;
     @OneToOne
    @JsonBackReference
    private JobApplied jobApplied;
        @Transient // optional if not persisted
    private Boolean decision;
  

    public String getHrEmp() {
        return this.HrEmp;
    }

    public void setHrEmp(String HrEmp) {
        this.HrEmp = HrEmp;
    }

@ManyToOne
@JoinColumn(name = "status_id", nullable = false)
private RemFormStatus status;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(Date commencementDate) {
        this.commencementDate = commencementDate;
    }

    public String getPreviousSalary() {
        return previousSalary;
    }

    public void setPreviousSalary(String previousSalary) {
        this.previousSalary = previousSalary;
    }

    public String getProposedSalary() {
        return proposedSalary;
    }

    public void setProposedSalary(String proposedSalary) {
        this.proposedSalary = proposedSalary;
    }

    public String getBillingRate() {
        return billingRate;
    }

    public void setBillingRate(String billingRate) {
        this.billingRate = billingRate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public String getHrComment() {
        return hrComment;
    }

    public void setHrComment(String hrComment) {
        this.hrComment = hrComment;
    }

    public RemFormStatus getStatus() {
        return status;
    }

    public void setStatus(RemFormStatus status) {
        this.status = status;
    }

    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

        public Boolean getDecision() {
        return decision;
    }

    public void setDecision(Boolean decision) {
        this.decision = decision;
    }



}