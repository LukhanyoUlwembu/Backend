package com.example.backend.model;

import java.util.Date;

import jakarta.persistence.*;
@Entity
public class JobSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobSkiillId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Skill skillName;
    private Date created;

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

    public long getJobSkiillId() {
        return this.jobSkiillId;
    }

    public void setJobSkiillId(long jobSkiillId) {
        this.jobSkiillId = jobSkiillId;
    }

}
