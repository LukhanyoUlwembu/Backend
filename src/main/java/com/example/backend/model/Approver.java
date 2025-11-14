package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Approver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String signature;
    private boolean decision;
    @ManyToOne
    @JsonBackReference
    private Approval approval;
@Version 
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Approval getApproval() {
        return this.approval;
    }

    public void setApproval(Approval approval) {
        this.approval = approval;
    }

    public boolean isDecision() {
        return this.decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
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
        public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
