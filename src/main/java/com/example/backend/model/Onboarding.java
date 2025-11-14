package com.example.backend.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Onboarding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCreated;
    private boolean isComplete;
    private Date dateSigned;
    private boolean contactSigned;
    private String inductionType;
    private String officeTourType;
    private boolean workAccount;
    private boolean systemAccess;
    private boolean securityAccess;
    private boolean active;
@Version 
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private List<OnboardingFile> OnboardingFiles;

    public List<OnboardingFile> getOnboardingFiles() {
        return this.OnboardingFiles;
    }

    public void setOnboardingFiles(List<OnboardingFile> OnboardingFiles) {
        this.OnboardingFiles = OnboardingFiles;
    }


    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Date getDateSigned() {
        return this.dateSigned;
    }

    public void setDateSigned(Date dateSigned) {
        this.dateSigned = dateSigned;
    }

    public boolean isContactSigned() {
        return this.contactSigned;
    }

    public void setContactSigned(boolean contactSigned) {
        this.contactSigned = contactSigned;
    }

    public String getInductionType() {
        return this.inductionType;
    }

    public void setInductionType(String inductionType) {
        this.inductionType = inductionType;
    }

    public String getOfficeTourType() {
        return this.officeTourType;
    }

    public void setOfficeTourType(String officeTourType) {
        this.officeTourType = officeTourType;
    }

    public boolean isWorkAccount() {
        return this.workAccount;
    }

    public void setWorkAccount(boolean workAccount) {
        this.workAccount = workAccount;
    }

    public boolean isSystemAccess() {
        return this.systemAccess;
    }

    public void setSystemAccess(boolean systemAccess) {
        this.systemAccess = systemAccess;
    }

    public boolean isSecurityAccess() {
        return this.securityAccess;
    }

    public void setSecurityAccess(boolean securityAccess) {
        this.securityAccess = securityAccess;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offer_letter_id", referencedColumnName = "id")
    private OfferLetter offerLetter;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public OfferLetter getOfferLetter() {
        return offerLetter;
    }

    public void setOfferLetter(OfferLetter offerLetter) {
        this.offerLetter = offerLetter;
    }
}
