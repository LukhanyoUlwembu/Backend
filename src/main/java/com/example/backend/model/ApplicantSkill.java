package com.example.backend.model;


import java.util.Date;

import jakarta.persistence.*;

@Entity
public class ApplicantSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicantSkiillId;

    @ManyToOne
    @JoinColumn(referencedColumnName  = "id")
    private Skill skillName;
    private Date created;
    private String ownSkill;

        public String getOwnSkill() {
            return this.ownSkill;
        }

        public void setOwnSkill(String ownSkill) {
            this.ownSkill = ownSkill;
        }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    public Skill getSkillName() {
        return this.skillName;
    }

    public void setSkillName(Skill skillName) {
        this.skillName = skillName;
    }

    public long getApplicantSkiillId() {
        return this.applicantSkiillId;
    }

    public void setApplicantSkiillId(long applicantSkiillId) {
        this.applicantSkiillId = applicantSkiillId;
    }
    
}
