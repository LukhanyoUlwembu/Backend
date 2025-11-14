package com.example.backend.model;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class JobRequisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobRequisitionId;
    private LocalDate created;
    @Version
    private int version;
    private String jobTitle;
    private String department;
    private Date dateOfRequest;
    private Date expectedStartDate;
    private String client;
    private boolean annexure; 
    private String shortListingMethod; 
    private String employmentType;
    private float billingRate;
    private String positionType;
    private float salaryBenchmark;
    private float salary;
    private boolean active;
    @Column(length = 1000)
    private String responsibilities;
    @ManyToOne
    @JoinColumn(referencedColumnName= "id" ) 
    private Term fixedTermMonths;
   
     @ManyToOne
    @JoinColumn(referencedColumnName = "id")
     private ExperienceLevel level;


    @OneToMany
    @JoinColumn(referencedColumnName = "jobRequisitionId")
    private List<JobEducation> jobEducation;


    @OneToMany
    @JoinColumn(referencedColumnName = "jobRequisitionId")
    private List<JobSkill> jobSkills;

   
    private String lineManager;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Currency currency;

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getLineManager() {
        return this.lineManager;
    }

    public void setLineManager(String lineManager) {
        this.lineManager = lineManager;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany
    @JoinColumn(referencedColumnName = "jobRequisitionId")
    private List<RequisitionFile> requisitionFile;

    public List<RequisitionFile> getRequisitionFile() {
        return this.requisitionFile;
    }

    public void setRequisitionFile(List<RequisitionFile> requisitionFile) {
        this.requisitionFile = requisitionFile;
    }

    
    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<JobEducation> getJobEducation() {
        return this.jobEducation;
    }

    public void setJobEducation(List<JobEducation> jobEducation) {
        this.jobEducation = jobEducation;
    }
    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDateOfRequest() {
        return this.dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }
public String getResponsibilities() {
        return this.responsibilities;
    }
 public List<JobSkill> getJobSkills() {
        return this.jobSkills;
    }

    public void setJobSkills(List<JobSkill> jobSkills) {
        this.jobSkills = jobSkills;
    }
public LocalDate getCreated() {
        return this.created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public long getJobRequisitionId() {
        return this.jobRequisitionId;
    }

    public void setJobRequisitionId(long jobRequisitionId) {
        this.jobRequisitionId = jobRequisitionId;
    }
    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
    public Date getExpectedStartDate() {
        return this.expectedStartDate;
    }

    public void setExpectedStartDate(Date expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public String getClient() {
        return this.client;
    }

     public ExperienceLevel getLevel() {
         return this.level;
     }

     public void setLevel(ExperienceLevel level) {
         this.level = level;
     }
    public void setClient(String client) {
        this.client = client;
    }

    public boolean isAnnexure() {
        return this.annexure;
    }

    public void setAnnexure(boolean annexure) {
        this.annexure = annexure;
    }

    public String getShortListingMethod() {
        return this.shortListingMethod;
    }

    public void setShortListingMethod(String shortListingMethod) {
        this.shortListingMethod = shortListingMethod;
    }

    public String getEmploymentType() {
        return this.employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public Term getFixedTermMonths() {
        return this.fixedTermMonths;
    }

    public void setFixedTermMonths(Term fixedTermMonths) {
        this.fixedTermMonths = fixedTermMonths;
    }

    public float getBillingRate() {
        return this.billingRate;
    }

    public void setBillingRate(float billingRate) {
        this.billingRate = billingRate;
    }

    public String getPositionType() {
        return this.positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public float getSalaryBenchmark() {
        return this.salaryBenchmark;
    }

    public void setSalaryBenchmark(float salaryBenchmark) {
        this.salaryBenchmark = salaryBenchmark;
    }

    public float getSalary() {
        return this.salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

   


  

}