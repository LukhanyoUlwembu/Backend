package com.example.backend.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class OfferLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    
    private String candidateName;
    private String candidateSignature;
    private String empName;
    private String empSignature;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OfferLetterStatus status;


    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpSignature() {
        return this.empSignature;
    }

    public void setEmpSignature(String empSignature) {
        this.empSignature = empSignature;
    }

    private String position;
    private String summary;
    private float remunerations;
    private Date commencementDate;
    private Date endDate;
    private Date created;
    private boolean decision;
    @ManyToOne
@JoinColumn(name = "approved_by")
private Employee approvedBy;

@Temporal(TemporalType.TIMESTAMP)
private Date approvedAt;

@ManyToOne
@JoinColumn(name = "rejected_by")
private Employee rejectedBy;

@Temporal(TemporalType.TIMESTAMP)
private Date rejectedAt;



// --- getters and setters ---
public Employee getRejectedBy() {
    return rejectedBy;
}

public void setRejectedBy(Employee rejectedBy) {
    this.rejectedBy = rejectedBy;
}

public Date getRejectedAt() {
    return rejectedAt;
}

public void setRejectedAt(Date rejectedAt) {
    this.rejectedAt = rejectedAt;
}

    
    public String getCandidateSignature() {
        return this.candidateSignature;
    }

    public void setCandidateSignature(String candidateSignature) {
        this.candidateSignature = candidateSignature;
    }

    public boolean isDecision() {
        return this.decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public float getRemunerations() {
        return this.remunerations;
    }

    public void setRemunerations(float remunerations) {
        this.remunerations = remunerations;
    }

    public Date getCommencementDate() {
        return this.commencementDate;
    }

    public void setCommencementDate(Date commencementDate) {
        this.commencementDate = commencementDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public OfferLetterStatus getStatus() {
    return status;
}

public void setStatus(OfferLetterStatus status) {
    this.status = status;
}

// getters/setters
public Employee getApprovedBy() {
    return approvedBy;
}

public void setApprovedBy(Employee approvedBy) {
    this.approvedBy = approvedBy;
}

public Date getApprovedAt() {
    return approvedAt;
}

public void setApprovedAt(Date approvedAt) {
    this.approvedAt = approvedAt;
}

   
}



