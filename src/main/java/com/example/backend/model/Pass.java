package com.example.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;

@Entity
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String signature;
    private boolean decision;
    @ManyToOne
    @JoinColumn(name = "job_status_id")
    private JobStatus status;
    private Date created;
    @ManyToOne
    @JsonBackReference
    private JobApplied jobApplied;
    private String Notes;
@Version 
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    public String getNotes() {
        return this.Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isDecision() {
        return this.decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public JobStatus getStatus() {
        return this.status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public JobApplied getJobApplied() {
        return this.jobApplied;
    }

    public void setJobApplied(JobApplied jobApplied) {
        this.jobApplied = jobApplied;
    }

}
