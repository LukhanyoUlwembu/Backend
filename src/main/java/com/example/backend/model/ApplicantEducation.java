package com.example.backend.model;
import java.util.Date;
import jakarta.persistence.Entity;

@Entity
public class ApplicantEducation extends Education{
    private Date dateObtained;
    private String institution;
  
    public Date getDateObtained() {
        return this.dateObtained;
    }

    public void setDateObtained(Date dateObtained) {
        this.dateObtained = dateObtained;
    }

    public String getInstitution() {
        return this.institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

}
