package com.example.backend.model;
import java.util.Date;
import java.util.List;


import jakarta.persistence.*;

@Entity
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobPostingId;
    private String jobSector;
    private String jobTitle;
    @Column(length = 1000)
    private String jobDescription;
    private float jobSalary;
    private String type;
    @Column(length = 1000)
    private String contract;
    private boolean active;

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Version 
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    private Date jobDeadline;
        private Date created;

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToOne
    @JoinColumn(name = "locationId")
    private Location location;

    @OneToOne
    @JoinColumn(referencedColumnName  = "id")
    private Approval approval;

    public Approval getApproval() {
        return this.approval;
    }

    public void setApproval(Approval approval) {
        this.approval = approval;
    }

    
    @OneToMany
    @JoinColumn(referencedColumnName = "jobPostingId")
    private List<JobApplied> jobsApplied;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(referencedColumnName = "jobPostingId")
    private List<Prescreening> preScreening;

    public String getContract() {
        return this.contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Prescreening> getPreScreening() {
        return this.preScreening;
    }

    public void setPreScreening(List<Prescreening> preScreening) {
        this.preScreening = preScreening;
    }
 
    public long getJobPostingId() {
        return this.jobPostingId;
    }

    public void setJobPostingId(long jobPostingId) {
        this.jobPostingId = jobPostingId;
    }

    public String getJobSector() {
        return this.jobSector;
    }

    public void setJobSector(String jobSector) {
        this.jobSector = jobSector;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return this.jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public float getJobSalary() {
        return this.jobSalary;
    }

    public void setJobSalary(float jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Date getJobDeadline() {
        return this.jobDeadline;
    }

    public void setJobDeadline(Date jobDeadline) {
        this.jobDeadline = jobDeadline;
    }

}
