package com.example.backend.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date created;
    private Date interviewDate;
    private String name;
     private String categoryType;

    public String getCategoryType() {
        return this.categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
@Version 
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    private String feedBack;
    private String interviewer;
    private String Panel;
    private String link;

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    @OneToOne
    @JoinColumn(name = "locationId") 
    private Location location;

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getInterviewer() {
        return this.interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getPanel() {
        return this.Panel;
    }

    public void setPanel(String Panel) {
        this.Panel = Panel;
    }


    public Date getCreated() {
        return this.created;
    }

    public void setCreated() {
        this.created = new Date();
    }

    public Date getInterviewDate() {
        return this.interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getFeedBack() {
        return this.feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
    
}

