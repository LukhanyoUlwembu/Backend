package com.example.backend.service;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.example.backend.model.*;

public interface HrServiceInterface {

    // Role Management
    public List<Role> getAllRoles();
    public List<String> getRoleNames();
    public Role getRoleById(Long id);
    public Role createRole(Role role);
    public void deleteRole(Long id);

    //Permission Management
    public List<Permission> getAllPermissions();
    public Set<Permission> getPermissionsByRole(String role);
    public Permission getPermissionById(Long id);
    public Permission createPermission(Permission permission);
    public Permission updatePermission(Permission permission);
    public void deletePermission(Long id);
    void assignPermissionsToRole(Long roleId, List<Long> permissionIds);

    //AutoCutApplicants
    void processExpiredJobs();
    
    //Employee
    public Employee getEmployee(String email);
    public Employee createEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public List<Employee> getEmployees();
    public void deleteEmployee(Long id);

    //Applicant
    public List<Applicant> getAllApplicant();
    public Applicant createApplicant(Applicant applicant);
    public Applicant getApplicant(long id);
    public Applicant updateApplicantWithFiles(Applicant applicant, List<MultipartFile> files, List<FileType> fileTypes)throws IOException;
    public Applicant login(String email,String password);
    public boolean emailExists(String email);
    public void resetPassword(String email,String password);
    public void deleteApplicantEducation(Long id);
    public void deleteApplicantWorkExperience(Long id);
    public void deleteApplicantFile(Long id);
    public void deleteApplicantSkill(Long id);


    //JobPosting
    public String createJobPosting(JobPosting jobPosting);
    public JobPosting getJobPosting(long id);
    public List<JobPosting> getAllJobPostings();
    
    //JobApplied
    public void withdrawApplication(Long jobAppliedId);
    public String createJobApplied(JobApplied job, MultipartFile cv, MultipartFile coverLetter);
    public String createJobAppliedold(int applicantid, int Jobid);
    public JobApplied updateApplication( Long id,  JobApplied updatedApp);
    public JobApplied getJobId(long id);
    public List<JobApplied> getJobApplieds();
    public List<JobApplied> getApplicationsByApplicant(long applicantId);
    public List<JobApplied> getApplicationsByJobPosting(long jobPostingId);
    public boolean applicationExists(long appid,long jobid);
    public JobApplied application(long applicantId, long jobId);
    public JobApplied castPassVote(Long jobAppliedId, Pass incomingPass);
    public JobApplied moveApplicantsToShortlist(JobApplied jobApplied);
    public List<JobApplied> moveApplicantsToLonglist(List<Long> jobAppliedIds);

    //Location
    public String createLocation(Location location);
    public Location getLocation(long id);
    public List<Location> getLocations();

     //Job Requisition
     public JobRequisition createJobRequisitionWithFiles(JobRequisition jobData,MultipartFile organogramFile);
     public JobRequisition getRequisition(long id);
    public List<JobRequisition> getAllRequisitions();
    public JobRequisition updateRequisition( JobRequisition job);


    // Approval
    public List<Approval> getAllApprovals();
    public Approval getApproval(long id);
    public Approval addOrUpdateApprovalVote(long jobReqId, Approver incomingApprover);
    public void deleteApproval(long id);
    public boolean approvalExists(long id);
    public boolean hasApproverAlreadyVoted(long jobRequisitionId, String signature);
     public Approval getApprovalByJobId( long id);

    //Interview
    public List<Interview> getInterviews(String email);
    
    //JobAlert
    public JobAlert createJobAlert(JobAlert alert,long id);
    public List<JobAlert> getJobAlertsByApplicantId(Long applicantId);
    
 // ---------------------- Onboarding Checklist ----------------------
   public List<Onboarding> getAllOnboarding();
   public Onboarding getOnboardingById(Long id);
   public Onboarding createOnboarding(Long applicationId,Onboarding onboarding,MultipartFile id,MultipartFile qualification, MultipartFile cv,MultipartFile proofOfAccount,MultipartFile proofOfTax,
   MultipartFile takeOnForm,MultipartFile ea1, MultipartFile consent) ;
   public Onboarding updateOnboarding(Onboarding onboarding,Long id);

    //RemForm
  RemForm getRemFormById(long id);
  RemForm createRemForm(RemForm remForm, long jobId);
  List<RemForm> getAllRemForms();
  RemForm approveRemFormByManager(Long id, String userEmail);
  RemForm approveRemFormByHr(Long id, String userEmail);
  RemForm approveRemFormByCfo(Long id, String userEmail);
  RemForm rejectRemForm(Long id, String userEmail);


   // ---------------------- Offer Letter ----------------------
  public List<OfferLetter> getAllOfferLetters();
   public OfferLetter getOfferLetterById(Long id);
   public OfferLetter createOfferLetter(Long id, OfferLetter offerLetter);
   public void deleteOfferLetter(Long id);
    public OfferLetter updateOfferLetter( long id,OfferLetter offerLetter);
    OfferLetter approveOfferLetter(Long id, String approverEmail);
    OfferLetter rejectOfferLetter(Long id, String approverEmail);
     List<JobApplied> getRecruiterNotifications();

      // GENDERS
    List<Gender> getAllGenders();
    Gender saveGender(Gender gender);
    Gender updateGender(Long id, Gender gender);
    void deleteGender(Long id);

    // RACES
    List<Race> getAllRaces();
    Race saveRace(Race race);
    Race updateRace(Long id, Race race);
    void deleteRace(Long id);

  //------------------------ JobStatus  --------------------
    // CREATE
    JobStatus createJobStatus(JobStatus jobStatus);

    // READ
    List<JobStatus> getAllJobStatuses();
    JobStatus getJobStatusById(Long id);
    JobStatus getJobStatusByName(String name);

    // UPDATE
    JobStatus updateJobStatus(Long id, JobStatus jobStatus);

    // DELETE
    void deleteJobStatus(Long id);


  //------------------ Availability -------------------------
  public List<Availability> getAllAvailabilitys();
    Availability addAvailability(Availability availability);
    Availability updateAvailability(Long id, Availability availability);
    void deleteAvailability(Long id);

  // --------------- Certification ---------------------------
      Certification createCertification(Certification certification);
      List<Certification> getAllCertifications();
      Certification updateCertification(Long id, Certification certification);
      void deleteCertification(Long id);

  // --------------- Province ---------------------------
      Province createProvince(Province province);
      List<Province> getAllProvinces();
      List<Province> createProvinces(List<Province> provinces);
      Province updateProvince(Long id, Province province);
      void deleteProvince(Long id);

      // --------------- File Type ---------------------------
      FileType createFileType(FileType fileType);
      List<FileType> getAllFileTypes();
      FileType updateFileType(Long id, FileType fileType);
      FileType getFileTypeByName(String name);
      List<FileType> getApplicantFileTypes();
      List<FileType> getRecruiterFileTypes();
      void deleteFileType(Long id);
      public FileType getFileTypeByNameOrThrow(String typeValue);


      // --------------- Grade ---------------------------
      Grade createGrade(Grade grade);
      List<Grade> getAllGrades();
      Grade updateGrade(Long id, Grade grade);
      void deleteGrade(Long id);

      // --------------- Skill ---------------------------
      Skill createSkill(Skill skill);
      List<Skill> getAllSkills();
      Skill updateSkill(Long id, Skill skill);
      void deleteSkill(Long id);

      // --------------- Term ---------------------------
      Term createTerm(Term term);
      List<Term> getAllTerms();
      Term updateTerm(Long id, Term term);
      void deleteTerm(Long id);

      // --------------- Job Sector ---------------------------
      JobSector createJobSector(JobSector jobSector);
      List<JobSector> getAllJobSectors();
      JobSector updateJobSector(Long id, JobSector jobSector);
      void deleteJobSector(Long id);

      // --------------- Experience Level ---------------------------
      ExperienceLevel createExperienceLevel(ExperienceLevel experienceLevel);
      List<ExperienceLevel> getAllExperienceLevels();
      ExperienceLevel updateExperienceLevel(Long id, ExperienceLevel experienceLevel);
      void deleteExperienceLevel(Long id);

// --------------- Currency ---------------------------
Currency createCurrency(Currency currency);
List<Currency> getAllCurrencies();
List<Currency> setAllCurrencies(List<Currency> currencies);  // <-- add parameter here
Currency updateCurrency(Long id, Currency currency);
void deleteCurrency(Long id);

}