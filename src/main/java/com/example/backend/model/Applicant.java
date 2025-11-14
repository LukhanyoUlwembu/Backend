package com.example.backend.model;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

 import jakarta.persistence.Version;

@Entity
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicantId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    @ManyToOne
    @JoinColumn(referencedColumnName  = "id")
    private Race race;

    private boolean hasDisability;
    
    private String disabilityDescription;
    private String disabilityAccomodation;

    public boolean isHasDisability() {
        return this.hasDisability;
    }

    public void setHasDisability(boolean hasDisability) {
        this.hasDisability = hasDisability;
    }

    public String getDisabilityDescription() {
        return this.disabilityDescription;
    }

    public void setDisabilityDescription(String disabilityDescription) {
        this.disabilityDescription = disabilityDescription;
    }

    public String getDisabilityAccomodation() {
        return this.disabilityAccomodation;
    }

    public void setDisabilityAccomodation(String disabilityAccomodation) {
        this.disabilityAccomodation = disabilityAccomodation;
    }


    @Version 
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Gender gender;
    private Date created;
    @OneToOne
    @JoinColumn(referencedColumnName =   "id")
  private ConflictOfInterest conflictOfInterest;

  public ConflictOfInterest getConflictOfInterest() {
      return this.conflictOfInterest;
  }

  public void setConflictOfInterest(ConflictOfInterest conflictOfInterest) {
      this.conflictOfInterest = conflictOfInterest;
  }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private String phone;
    @OneToMany
    @JoinColumn(referencedColumnName = "applicantId")
    private List<JobApplied> jobsApplied;

    private String idNumber;
    private String passport;
    
    @OneToMany
    @JoinColumn(referencedColumnName = "applicantId")
    private List<ApplicantEducation> applicantEducations;

    public List<ApplicantEducation> getApplicantEducations() {
        return this.applicantEducations;
    }

    public void setApplicantEducations(List<ApplicantEducation> applicantEducations) {
        this.applicantEducations = applicantEducations;
    }

    public List<JobApplied> getJobsApplied() {
        return this.jobsApplied;
    }

    public void setJobsApplied(List<JobApplied> jobsApplied) {
        this.jobsApplied = jobsApplied;
    }

    @OneToMany
    @JoinColumn(referencedColumnName = "applicantId")
    private List<ApplicantFile> applicantFile;

    public List<ApplicantFile> getApplicantFile() {
        return this.applicantFile;
    }

    public void setApplicantFile(List<ApplicantFile> applicantFile) {
        this.applicantFile = applicantFile;
    }
    
    @OneToMany
    @JoinColumn(referencedColumnName = "applicantId")
    private List<ApplicantSkill> applicantSkill;


    @OneToMany
    @JoinColumn(referencedColumnName = "applicantId")
    private List<WorkExperience> workExperiences;

    public List<WorkExperience> getWorkExperiences() {
        return this.workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
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

        public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Race getRace() {
        return this.race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
	public List<ApplicantSkill> getApplicantSkill() {
		return this.applicantSkill;
	}

	public void setApplicantSkill(List<ApplicantSkill> applicantSkill) {
		this.applicantSkill = applicantSkill;
	}


    public Applicant() {
   
      }
    public long getApplicantId() {
        return this.applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassport() {
        return this.passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

}