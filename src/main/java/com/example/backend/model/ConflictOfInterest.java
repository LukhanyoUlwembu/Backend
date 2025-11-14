package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class ConflictOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean workCurrent;
    private boolean workBefore;
    private boolean relatives;
    
    private String relativesInfo; // CHANGED
    private boolean friends;
    private String friendsInfo;   // CHANGED
    private boolean businessRelation;
    private String businessInfo;  // CHANGED
@Version 
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public boolean getWorkCurrent() {
        return workCurrent;
    }

    public void setWorkCurrent(boolean workCurrent) {
        this.workCurrent = workCurrent;
    }

    public boolean getWorkBefore() {
        return workBefore;
    }

    public void setWorkBefore(boolean workBefore) {
        this.workBefore = workBefore;
    }

    public boolean getRelatives() {
        return relatives;
    }

    public void setRelatives(boolean relatives) {
        this.relatives = relatives;
    }

    public String getRelativesInfo() {
        return relativesInfo;
    }

    public void setRelativesInfo(String relativesInfo) {
        this.relativesInfo = relativesInfo;
    }

    public boolean getFriends() {
        return friends;
    }

    public void setFriends(boolean friends) {
        this.friends = friends;
    }

    public String getFriendsInfo() {
        return friendsInfo;
    }

    public void setFriendsInfo(String friendsInfo) {
        this.friendsInfo = friendsInfo;
    }

    public boolean getBusinessRelation() {
        return businessRelation;
    }

    public void setBusinessRelation(boolean businessRelation) {
        this.businessRelation = businessRelation;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }
}