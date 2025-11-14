package com.example.backend.model;


import java.util.Date;

import jakarta.persistence.*;

@Entity
public abstract class Education{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationId;
    private String educationName;
    @ManyToOne
    @JoinColumn(referencedColumnName  = "id")
    private Grade educationGrade;

    @ManyToOne
    @JoinColumn(referencedColumnName  = "id")
    private Certification certificate;
    private Date created;
    @Version
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Certification getCertifiate() {
        return this.certificate;
    }

    public void setCertifiate(Certification certifiate) {
        this.certificate = certifiate;
    }

    public Long getEducationId() {
        return this.educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    public String getEducationName() {
        return this.educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public Grade getEducationGrade() {
        return this.educationGrade;
    }

    public void setEducationGrade(Grade educationGrade) {
        this.educationGrade = educationGrade;
    }


    
}
