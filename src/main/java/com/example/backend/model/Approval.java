package com.example.backend.model;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.*;

@Entity
public class Approval {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private boolean decision;
private Date approvalDate;
private boolean active;
@Version 
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
   @OneToOne
    @JoinColumn(referencedColumnName = "id") // fixed
    private RemForm remForm;

   public RemForm getRemForm() {
       return this.remForm;
   }

   public void setRemForm(RemForm remForm) {
       this.remForm = remForm;
   }

@OneToOne
@JoinColumn(referencedColumnName = "jobRequisitionId")
private JobRequisition jobRequisition;

@OneToMany(mappedBy = "approval", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonManagedReference
private List<Approver> approver;

public List<Approver> getApprover() {
    return this.approver;
}

public void setApprover(List<Approver> approver) {
    this.approver = approver;
}

public JobRequisition getJobRequisition() {
    return this.jobRequisition;
}

public void setJobRequisition(JobRequisition jobRequisition) {
    this.jobRequisition = jobRequisition;
}


public boolean isActive() {
    return this.active;
}

public void setActive(boolean active) {
    this.active = active;
}

public long getId() {
    return this.id;
}

public void setId(long id) {
    this.id = id;
}

    private Date created;

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


public boolean getDecision() {
    return this.decision;
}

public void setDecision(boolean decision) {
    this.decision = decision;
}


public Date getApprovalDate() {
    return this.approvalDate;
} 

public void setApprovalDate(Date ApprovalDate) {
    this.approvalDate = ApprovalDate;
}


}
