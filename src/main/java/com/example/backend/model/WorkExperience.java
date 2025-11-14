package com.example.backend.model;
import java.util.Date;
import jakarta.persistence.*;

@Entity
public class WorkExperience {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long workExperienceId;
private String companyName;
private String jobTitle;
private Date endDate;
private Date startDate;
private String duties;
private String referenceName;
private String phoneNumber;
private Boolean current;
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

public String getDuties() {
    return this.duties;
}

public void setDuties(String duties) {
    this.duties = duties;
}

public String getReferenceName() {
    return this.referenceName;
}

public void setReferenceName(String referenceName) {
    this.referenceName = referenceName;
}

public String getPhoneNumber() {
    return this.phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

public Boolean getCurrent() {
    return this.current;
}

public void setCurrent(Boolean current) {
    this.current = current;
}



public long getWorkExperienceId() {
    return this.workExperienceId;
}

public void setWorkExperienceId(long workExperienceId) {
    this.workExperienceId = workExperienceId;
}

public String getCompanyName() {
    return this.companyName;
}

public void setCompanyName(String companyName) {
    this.companyName = companyName;
}

public String getJobTitle() {
    return this.jobTitle;
}

public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
}

public Date getEndDate() {
    return this.endDate;
}

public void setEndDate(Date endDate) {
    this.endDate = endDate;
}

public Date getStartDate() {
    return this.startDate;
}

public void setStartDate(Date startDate) {
    this.startDate = startDate;
}

}
