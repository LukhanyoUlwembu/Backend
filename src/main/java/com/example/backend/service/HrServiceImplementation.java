package com.example.backend.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import com.example.backend.model.*;
import com.example.backend.repository.*;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


    @Service
    public class HrServiceImplementation implements HrServiceInterface{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PassRepository passRepository;
    @Autowired
    ApplicantRepository applicantRepository;
    @Autowired
    JobAlertRepository jobAlertRepository;
    @Autowired
    JobPostingRepository jobPostingRepository;
    @Autowired
    ApplicantSkillRepository appskill;
    @Autowired
    JobSkillRepository jobskill;
    @Autowired
    LocationRepository locrep;
    @Autowired
    WorkExperienceRepository workrep;
    @Autowired
    JobAppliedRepository appliedRepository;
    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    JobRequisitionRepository jobRequisitionRepository;
    @Autowired
    PrescreeningRepository pRep;
    @Autowired
    OfferLetterRepository offerLetterRepository;
    @Autowired
    OnboardingRepository onboardingRepository;
    @Autowired
    OnboardingFileRepository onboardingFileRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    RemFormRepository remFormRepository;
    @Autowired
    ApprovalRepository approvalRepository;
    @Autowired
    AdditionalFileRepository additionalFileRepository;
    @Autowired
    ApplicantFileRepository applicantFileRepository;
    @Autowired
    ApplicantEducationRepository applicantEducationRepository;
    @Autowired
    RequisitionFileRepository reFiles;
    @Autowired
    JobEducationRepository jobEduRep;
    @Autowired
    ApproverRepository approverRepository;
    @Autowired
    ConflictOfInterestRepository conflictOfInterestRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private ExperienceLevelRepository experienceLevelRepository;
    @Autowired
    private JobSectorRepository jobSectorRepository;
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private FileTypeRepository fileTypeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CertificationRepository certificationRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;
    @Autowired
    private JobStatusRepository jobStatusRepository;
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private OfferLetterStatusRepository offerLetterStatusRepository;
    @Autowired
    private RemFormStatusRepository remFormStatusRepository;




    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Scheduled(cron = "0 0 0 * * ?")
@Override
public void processExpiredJobs() {
    List<JobPosting> expiredJobs = jobPostingRepository.findByJobDeadlineBeforeAndActiveTrue(new Date());

    JobStatus application = getJobStatusByName("Application");
    JobStatus rejected = getJobStatusByName("Rejected");
    JobStatus screening = getJobStatusByName("Screening");

    for (JobPosting job : expiredJobs) {
        List<JobApplied> apps = appliedRepository.findByJobPosting(job);

        for (JobApplied app : apps) {
            if (app.getStatus() == null || app.getStatus().equals(application)) {
                if (app.getScore() < 2.0f) {
                    app.setStatus(rejected);
                } else {
                    app.setStatus(screening);
                }
                appliedRepository.save(app);
            }
        }

        job.setActive(false);
        jobPostingRepository.save(job);
    }
}
     //Role Management

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public List<String> getRoleNames(){
        List<Role> roles= roleRepository.findAll();
        List<String> store= new ArrayList<String>();
        //loop --> roles & store roleNames & return stored names
        for(Role role:roles){
            
            store.add(role.getName());
        }
        return store;
    }
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    @Override
    public Role createRole(Role role) {
        if(roleRepository.existsByName(role.getName())==false){
            return roleRepository.save(role);
        }
        return null;
    }


    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }
//Permission Management
    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
     @Override
     public Set<Permission> getPermissionsByRole(String role){
        Role rolee = roleRepository.findByName(role);
        Set<Permission> yho = rolee.getPermissions();
          return yho;
     }
    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Permission permission) {
        if (!permissionRepository.existsById(permission.getId())) {
            throw new RuntimeException("Permission not found with id: " + permission.getId());
        }
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new RuntimeException("Permission not found with id: " + id);
        }
        permissionRepository.deleteById(id);
    }

    @Override
public void assignPermissionsToRole(Long roleId, List<Long> permissionIds) {
    Role role = roleRepository.findById(roleId)
        .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));

    List<Permission> permissions = permissionRepository.findAllById(permissionIds);

    if (permissions.size() != permissionIds.size()) {
        throw new RuntimeException("Some permissions were not found");
    }

    role.setPermissions(new HashSet<>(permissions)); // Overwrite current permissions
    roleRepository.save(role);
}


    @Override
    public List<Applicant> getAllApplicant(){
    return applicantRepository.findAll();
    }
@Override
public Employee getEmployee(String email) {
    return employeeRepository.findByEmail(email);
}


    @Override
public Employee createEmployee(Employee employee) {
    if (employee.getRole() != null && employee.getRole().getId() != null) {
        Long roleId = employee.getRole().getId();
        Role role = roleRepository.findById(roleId).orElse(null);
        employee.setRole(role);
    } else {
        throw new RuntimeException("Role is required");
    }
    return employeeRepository.save(employee);
}



@Override
public Employee updateEmployee(Employee employee) {
    if (employee.getId() == 0) {
        throw new RuntimeException("Invalid employee ID");
    }

    Employee existing = employeeRepository.findById(employee.getId())
        .orElseThrow(() -> new RuntimeException("Employee not found"));

    Long roleId = employee.getRole() != null ? employee.getRole().getId() : null;
    if (roleId == null) {
        throw new RuntimeException("Role ID is missing");
    }

    Role role = roleRepository.findById(roleId)
        .orElseThrow(() -> new RuntimeException("Role not found"));

    existing.setRole(role);

    return employeeRepository.save(existing);
}


    @Override
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployees(){
    return employeeRepository.findAll();
    }



    @Override
    public Applicant createApplicant(Applicant applicant){
    if(!applicantRepository.existsByEmail(applicant.getEmail())){
    String hashedPassword = encoder.encode(applicant.getPassword());
    applicant.setPassword(hashedPassword);
    applicant.setCreated(new Date());
    applicant.setConflictOfInterest(conflictOfInterestRepository.save(applicant.getConflictOfInterest()));
    return applicantRepository.save(applicant);

    }else{
    return null;
    }

    }

    @Override
    public void resetPassword(String email,String password){
        Applicant applicant = applicantRepository.findByEmail(email);
        String hashedPassword = encoder.encode(password);
        applicant.setPassword(hashedPassword);
        applicantRepository.save(applicant);
    }
    @Override
    public Applicant login(String email, String rawPassword) {
    Applicant applicant = applicantRepository.findByEmail(email);
    if (applicant != null && encoder.matches(rawPassword, applicant.getPassword())) {
    return applicant;
    } else {
    return null;
    }
    }
@Override
public Applicant getApplicant(long id) {
    return applicantRepository.findByApplicantId(id);
}

@Override
public Applicant updateApplicantWithFiles(Applicant applicant, List<MultipartFile> files, List<FileType> fileTypes) throws IOException {
    if (applicant.getLocation() != null) {
        Location savedLocation = locrep.save(applicant.getLocation());
        applicant.setLocation(savedLocation);
    }

    if (applicant.getApplicantEducations() != null) {
        applicant.setApplicantEducations(applicantEducationRepository.saveAll(applicant.getApplicantEducations()));
    }

    // if (applicant.getApplicantSkill() != null) {
    //     applicant.setApplicantSkill(appskill.saveAll(applicant.getApplicantSkill()));
    // }
    
if (applicant.getApplicantSkill() != null && !applicant.getApplicantSkill().isEmpty()) {
    List<ApplicantSkill> skills = applicant.getApplicantSkill().stream()
        .map(js -> {
            // Fetch the Skill entity
            Skill skill = js.getSkillName() != null && js.getSkillName().getId() != null
                    ? skillRepository.findById(js.getSkillName().getId())
                        .orElseThrow(() -> new RuntimeException(
                            "Skill not found: " + js.getSkillName().getId()))
                    : null;

            js.setSkillName(skill);

            // Optionally set created date
            if (js.getCreated() == null) {
                js.setCreated(new Date());
            }
            return appskill.save(js);
        })
        .toList();

     applicant.setApplicantSkill(skills);
}

    if (applicant.getWorkExperiences() != null) {
        applicant.setWorkExperiences(workrep.saveAll(applicant.getWorkExperiences()));
    }


    if (files != null && !files.isEmpty()) {
        String uploadDir = new File(".").getCanonicalPath() + File.separator + "uploads" + File.separator;
        new File(uploadDir).mkdirs();

        List<ApplicantFile> savedFiles = new ArrayList<>();
for (int i = 0; i < files.size(); i++) {
    MultipartFile file = files.get(i);
    if (file.isEmpty()) continue;

    String cleanName = sanitizeFileName(file.getOriginalFilename());
    String fullPath = uploadDir + cleanName;
    file.transferTo(new File(fullPath));

    ApplicantFile af = new ApplicantFile();
    af.setFileName(cleanName);
    af.setFilePath(fullPath);

    // ✅ Safe fileType assignment
    if (fileTypes != null && i < fileTypes.size()) {
        af.setFileType(fileTypes.get(i));
    } else {
        af.setFileType(null); // or assign a default type
    }

    savedFiles.add(af);
}

        if (applicant.getApplicantFile() != null) {
            savedFiles.addAll(applicant.getApplicantFile());
        }

        applicant.setApplicantFile(applicantFileRepository.saveAll(savedFiles));
    }

    // 🔑 Reattach reference entities before saving
    reattachReferences(applicant);

    return applicantRepository.save(applicant);
}

/**
 * Ensures applicant’s reference entities are managed (Province, Gender, Race).
 */
private void reattachReferences(Applicant applicant) {
    

    if (applicant.getGender() != null && applicant.getGender().getId() != null) {
        Gender gender = genderRepository.findById(applicant.getGender().getId())
            .orElseThrow(() -> new RuntimeException("Gender not found"));
        applicant.setGender(gender);
    }

    if (applicant.getRace() != null && applicant.getRace().getId() != null) {
        Race race = raceRepository.findById(applicant.getRace().getId())
            .orElseThrow(() -> new RuntimeException("Race not found"));
        applicant.setRace(race);
    }
}


    @Override
    public void deleteApplicantEducation(Long id){
            applicantEducationRepository.deleteById(id);
    }
    @Override
    public void deleteApplicantWorkExperience(Long id){
            workrep.deleteById(id);
    }
    @Override
    public void deleteApplicantFile(Long id){
            applicantFileRepository.deleteById(id);
    }
    @Override
    public void deleteApplicantSkill(Long id){
            appskill.deleteById(id);
    }

    @Override
    public String createJobAppliedold(int applicantid, int Jobid){

    JobApplied job = new JobApplied();
    job.setApplicant(getApplicant(applicantid));
    job.setJobPosting(getJobPosting(Jobid));
    job.setStatus(getJobStatusByName("Application"));
    job.setCreated(new Date());
    appliedRepository.save(job);
    return"Job Applied Created";
    }


@Override
public String createJobPosting(JobPosting jobPosting) {

    // Handle Location + nested Province
    if (jobPosting.getLocation() != null) {
        Location loc = jobPosting.getLocation();

        // Save Province first if it is new
        if (loc.getProvince() != null && loc.getProvince().getId() == null) {
            Province savedProvince = provinceRepository.findById(loc.getProvince().getId())
            .orElseThrow(() -> new RuntimeException("Location not found"));
            loc.setProvince(savedProvince);
        }

        Location savedLocation = locrep.save(loc);
        jobPosting.setLocation(savedLocation);
    }
    

    // Save PreScreening questions
    if (jobPosting.getPreScreening() != null) {
        List<Prescreening> questions = pRep.saveAll(jobPosting.getPreScreening());
        jobPosting.setPreScreening(questions);
    }

    // Save Approval
    if (jobPosting.getApproval() != null) {
        Approval approval = jobPosting.getApproval();

        // Ensure nested objects in Approval are persisted if needed
        // (e.g., approver, workflow steps, etc.)

        approval.setActive(false); 
        Approval savedApproval = approvalRepository.save(approval);
        jobPosting.setApproval(savedApproval);
    }

    // Finalize JobPosting
    jobPosting.setActive(true);
    jobPosting.setCreated(new Date());
    jobPostingRepository.save(jobPosting);

    return "Job Created";
}




    @Override
    public JobAlert createJobAlert(JobAlert alert,long id) {
            Applicant applicant = applicantRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Applicant not found"));
        alert.setApplicant(applicant);
    return jobAlertRepository.save(alert);
    }

@Override
public List<JobAlert> getJobAlertsByApplicantId(Long applicantId) {
    Applicant applicant = applicantRepository.findByApplicantId(applicantId);
    return jobAlertRepository.findByApplicant(applicant);
}


    @Override
    public JobPosting getJobPosting(long id) {
    return jobPostingRepository.findByJobPostingId(id);
    }

    @Override
    public List<JobPosting> getAllJobPostings() {
    return jobPostingRepository.findAll();
    }


    @Override
    public List<JobApplied> getApplicationsByApplicant(long applicantId) {
    return appliedRepository.findByApplicantApplicantId(applicantId);
    }
    @Override
    public List<JobApplied> getApplicationsByJobPosting(long jobPostingId) {
    return appliedRepository.findByJobPostingJobPostingId(jobPostingId);
    }

    @Override
    public JobApplied updateApplication( Long id,  JobApplied updatedApp){

    JobApplied existing = getJobId(id);
    JobStatus js = getNextStatus(updatedApp.getStatus());
    existing.setStatus(js);
    existing.setNotes(updatedApp.getNotes());
    if(updatedApp.getInterviews()!=null){
    List<Interview> interviews = interviewRepository.saveAll(updatedApp.getInterviews());
    existing.setInterviews(interviews);
    }

    if(updatedApp.getRemForm()!=null){
    RemForm rem = remFormRepository.save(updatedApp.getRemForm());
    existing.setRemForm(rem);
    }



    appliedRepository.save(existing);

    return existing;
    }
    @Override
    public boolean applicationExists(long appid, long jobid) {
    return appliedRepository.existsByApplicantApplicantIdAndJobPostingJobPostingId(appid,jobid);
    }

    @Override
    public JobApplied castPassVote(Long jobAppliedId, Pass incomingPass) {
    JobApplied jobApplied = appliedRepository.findByJobAppliedId(jobAppliedId);

    JobStatus currentStatus = jobApplied.getStatus();

    boolean alreadyVoted = passRepository.existsBySignatureAndStatusAndJobAppliedJobAppliedId(
    incomingPass.getSignature(), currentStatus, jobAppliedId
    );

    if (alreadyVoted) {
    throw new IllegalStateException("This approver has already voted for this status.");
    }

    incomingPass.setJobApplied(jobApplied);
    incomingPass.setCreated(new Date());
    incomingPass.setStatus(currentStatus);
    passRepository.save(incomingPass);

    // ✅ Refetch passes for this application and current status from DB
    List<Pass> currentVotes = passRepository.findAll().stream()
    .filter(p -> p.getJobApplied().getJobAppliedId() == jobAppliedId)
    .filter(p -> p.getStatus() == currentStatus)
    .toList();

    if (currentVotes.size() == 3) {
    long approvals = currentVotes.stream().filter(Pass::isDecision).count();

    if (approvals > 1) {
    jobApplied.setStatus(getNextStatus(currentStatus));
    } else {
    jobApplied.setStatus(getJobStatusByName("Rejected"));
    }

    jobApplied.setNotes("Final decision made by panel of 3.");
    jobApplied = appliedRepository.save(jobApplied);
    }

    return jobApplied;
    }


   private JobStatus getNextStatus(JobStatus current) {
        return jobStatusRepository.findByOrderIndex(current.getOrderIndex()+1).
        orElseThrow(()->new IllegalStateException("error"));
    }

    public List<JobApplied> moveApplicantsToLonglist(List<Long> jobAppliedIds) {
    return updateStatus(jobAppliedIds, "Application", "Longlist");
}


    // Move applicants from Longlist → Shortlist
    @Override
    public JobApplied moveApplicantsToShortlist(JobApplied jobApplied) {
        
        
    JobStatus shortList = getJobStatusByName("Shortlist");
            jobApplied.setStatus(shortList);
        

        return appliedRepository.save(jobApplied);
    }

    // Move applicants from Shortlist → Interview
    public List<JobApplied> moveApplicantsToInterview(List<Long> jobAppliedIds) {
        return updateStatus(jobAppliedIds, "Shortlist", "Interview");
     }

    // Helper method to update status with validation
   private List<JobApplied> updateStatus(List<Long> ids, String requiredCurrentName, String nextName) {
    JobStatus requiredCurrent = jobStatusRepository.findByName(requiredCurrentName)
            .orElseThrow(() -> new RuntimeException("Required status not found: " + requiredCurrentName));

    JobStatus next = jobStatusRepository.findByName(nextName)
            .orElseThrow(() -> new RuntimeException("Next status not found: " + nextName));

    List<JobApplied> updated = new ArrayList<>();
    for (Long id : ids) {
        JobApplied applicant = appliedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found: " + id));

        if (!applicant.getStatus().equals(requiredCurrent)) {
            throw new IllegalStateException("Applicant " + id + " must be in status " + requiredCurrent.getName());
        }

        applicant.setStatus(next);
        updated.add(appliedRepository.save(applicant));
    }
    return updated;
}


    @Override
    public String createJobApplied(JobApplied job, MultipartFile cv, MultipartFile coverLetter) { 
    job.setCreated(new Date());

    List<ApplicantFile> attachedFiles = new ArrayList<>();

    try {
    Applicant applicant = getApplicant(job.getApplicant().getApplicantId());

    // 1. Reuse existing files selected by ID
    if (job.getApplicantFile() != null) {
    for (ApplicantFile fileRef : job.getApplicantFile()) {
    if (fileRef.getAddId() > 0) {
    applicantFileRepository.findById(fileRef.getAddId()).ifPresent(attachedFiles::add);
    }
    }
    }

    // 2. Upload new files (cv / cover letter) and attach to applicant
    String uploadDir = new File(".").getCanonicalPath() + File.separator + "uploads" + File.separator;
    new File(uploadDir).mkdirs();

    List<ApplicantFile> newlyUploaded = new ArrayList<>();

    if (cv != null && !cv.isEmpty()) {
    String cleanCvName = sanitizeFileName(cv.getOriginalFilename());
    String uniqueCvName = UUID.randomUUID() + "_" + cleanCvName;
    File dest = new File(uploadDir + uniqueCvName);
    cv.transferTo(dest);

    ApplicantFile cvFile = new ApplicantFile();
cvFile.setFileName(uniqueCvName);
cvFile.setFilePath(dest.getAbsolutePath());
cvFile.setFileType(getFileTypeByNameOrThrow("CV"));
newlyUploaded.add(cvFile);
attachedFiles.add(cvFile);
}

if (coverLetter != null && !coverLetter.isEmpty()) {
    String cleanCoverLetterName = sanitizeFileName(coverLetter.getOriginalFilename());
    String uniqueCoverLetterName = UUID.randomUUID() + "_" + cleanCoverLetterName;
    File dest = new File(uploadDir + uniqueCoverLetterName);
    coverLetter.transferTo(dest);

    ApplicantFile coverLetterFile = new ApplicantFile();
    coverLetterFile.setFileName(uniqueCoverLetterName);
    coverLetterFile.setFilePath(dest.getAbsolutePath());
    coverLetterFile.setFileType(getFileTypeByNameOrThrow("Cover Letter"));
    newlyUploaded.add(coverLetterFile);
    attachedFiles.add(coverLetterFile);
}


    // Save and append new files to existing applicant list (without overwriting)
    if (!newlyUploaded.isEmpty()) {
    List<ApplicantFile> saved = applicantFileRepository.saveAll(newlyUploaded);

    List<ApplicantFile> existing = applicant.getApplicantFile() != null
    ? new ArrayList<>(applicant.getApplicantFile())
    : new ArrayList<>();

    existing.addAll(saved);
    applicant.setApplicantFile(existing);
    applicantRepository.save(applicant); 
    }

    // 3. Finalize application
    job.setApplicant(applicant); 
    job.setApplicantFile(attachedFiles);
    job.setJobPosting(getJobPosting(job.getJobPosting().getJobPostingId()));
    job.setStatus(getJobStatusByName("Application"));
    if (job.getAvailability() != null && job.getAvailability().getId() != null) {
    Availability availability = availabilityRepository.findById(job.getAvailability().getId())
        .orElseThrow(() -> new RuntimeException("Availability not found: " + job.getAvailability().getId()));
    job.setAvailability(availability);
}

    ScoreService score = new ScoreService();
    job.setScore((float) score.calculateApplicationScore(applicant, job.getJobPosting().getApproval().getJobRequisition(), null));

    appliedRepository.save(job);

    return "created";

    } catch (IOException e) {
    throw new RuntimeException("Failed to save files", e);
    }
    }

    @Override
    public void withdrawApplication(Long jobAppliedId) {
    JobApplied jobApplied = appliedRepository.findByJobAppliedId(jobAppliedId);

    jobApplied.setStatus(getJobStatusByName("Withdrawn"));
    appliedRepository.save(jobApplied);
    }

    @Override
    public JobApplied getJobId(long id) {
    return appliedRepository.findByJobAppliedId(id);
    }

    @Override
    public JobApplied application(long applicantId, long jobId){
    return appliedRepository.findByApplicantApplicantIdAndJobPostingJobPostingId(applicantId,jobId);
    }
    @Override
    public List<JobApplied> getJobApplieds() {
    return appliedRepository.findAll();
    }
    @Override
    public List<Interview> getInterviews(String email){
    return interviewRepository.findByInterviewer(email);
    }

    @Override
    public String createLocation(Location location) {
    locrep.save(location);
    location.setCreated(new Date());
    return "created";
    }
    @Override
    public Location getLocation(long id) {
    return locrep.findBylocationId(id);
    }
    @Override
    public List<Location> getLocations() {
    return locrep.findAll();
    }
    @Override
    public boolean emailExists(String email) {
    return applicantRepository.existsByEmail(email);
    }
   
public JobRequisition createJobRequisitionWithFiles(JobRequisition jobData, MultipartFile organogramFile) {
    try {
        String uploadDir = new File(".").getCanonicalPath() + File.separator + "uploads" + File.separator;
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        List<RequisitionFile> filesToSave = new ArrayList<>();

        if (organogramFile != null && !organogramFile.isEmpty()) {
            String cleanOrganogramName = sanitizeFileName(organogramFile.getOriginalFilename());
            RequisitionFile organogram = new RequisitionFile();
            organogram.setFileName(cleanOrganogramName);
            organogram.setFilePath(uploadDir + cleanOrganogramName);
            organogram.setFileType(getFileTypeByNameOrThrow("Organogram File"));
            organogramFile.transferTo(new File(organogram.getFilePath()));
            filesToSave.add(organogram);
        }
        
if (jobData.getJobSkills() != null && !jobData.getJobSkills().isEmpty()) {
    List<JobSkill> skills = jobData.getJobSkills().stream()
        .map(js -> {
            // Fetch the Skill entity
            Skill skill = js.getSkillName() != null && js.getSkillName().getId() != null
                    ? skillRepository.findById(js.getSkillName().getId())
                        .orElseThrow(() -> new RuntimeException(
                            "Skill not found: " + js.getSkillName().getId()))
                    : null;

            // Set the fetched Skill into JobSkill
            js.setSkillName(skill);

            // Optionally set created date
            if (js.getCreated() == null) {
                js.setCreated(new Date());
            }

            // Save JobSkill (persist link to Skill)
            return jobskill.save(js);
        })
        .toList();

    jobData.setJobSkills(skills);
}

if (jobData.getJobEducation() != null && !jobData.getJobEducation().isEmpty()) {
    List<JobEducation> eduList = jobData.getJobEducation().stream()
        .map(e -> {
            // Link existing Grade
            if (e.getEducationGrade() != null && e.getEducationGrade().getId() != null) {
                Grade grade = gradeRepository.findById(e.getEducationGrade().getId())
                        .orElseThrow(() -> new RuntimeException(
                                "Grade not found: " + e.getEducationGrade().getId()));
                e.setEducationGrade(grade);
            }

            // Link existing Certification
            if (e.getCertifiate() != null && e.getCertifiate().getId() != null) {
                Certification cert = certificationRepository.findById(e.getCertifiate().getId())
                        .orElseThrow(() -> new RuntimeException(
                                "Certification not found: " + e.getCertifiate().getId()));
                e.setCertifiate(cert);
            }

            // Save JobEducation
            return jobEduRep.save(e);
        })
        .toList();

    jobData.setJobEducation(eduList);
}


if (jobData.getCurrency() != null && jobData.getCurrency().getId() != null) {
    Currency curr = currencyRepository.findById(jobData.getCurrency().getId())
        .orElseThrow(() -> new RuntimeException("Currency not found: " + jobData.getCurrency().getId()));
    jobData.setCurrency(curr);
}

if (jobData.getLevel() != null && jobData.getLevel().getId() != null) {
    ExperienceLevel level = experienceLevelRepository.findById(jobData.getLevel().getId())
        .orElseThrow(() -> new RuntimeException("Experience level not found: " + jobData.getLevel().getId()));
    jobData.setLevel(level);
}

        



        jobData.setRequisitionFile(reFiles.saveAll(filesToSave));
        jobData.setActive(true);
        return jobRequisitionRepository.save(jobData);

    } catch (IOException e) {
        throw new RuntimeException("Failed to save files", e);
    }
}



    @Override
    public List<JobRequisition> getAllRequisitions(){
    return  jobRequisitionRepository.findAll();
    }
    @Override
    public JobRequisition updateRequisition( JobRequisition job){
    return  jobRequisitionRepository.save(job);
    }
    @Override
    public boolean hasApproverAlreadyVoted(long jobRequisitionId, String signature) {
    return approvalRepository.existsByJobRequisitionJobRequisitionIdAndApproverSignature(jobRequisitionId, signature);
    }

    private String sanitizeFileName(String fileName) {
    return fileName.replaceAll("[^a-zA-Z0-9\\.\\-\\_ ]", "_");
    }


    @Override
    public JobRequisition getRequisition(long id) {
    return jobRequisitionRepository.findByJobRequisitionId(id);
    }
    @Autowired
    public void ApprovalServiceImpl(ApprovalRepository approvalRepository) {
    this.approvalRepository = approvalRepository;
    }

    @Override
    public List<Approval> getAllApprovals() {
    return approvalRepository.findAll();
    }
    @Override
    public Approval getApprovalByJobId( long id){
    return approvalRepository.findByJobRequisitionJobRequisitionId(id);
    }
    @Override
    public Approval getApproval(long id) {
    return approvalRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Approval not found with id " + id));
    }

    @Override
    public Approval addOrUpdateApprovalVote(long jobReqId, Approver incomingApprover) {
    Approval approval = approvalRepository.existsByJobRequisitionJobRequisitionId(jobReqId)
    ? approvalRepository.findByJobRequisitionJobRequisitionId(jobReqId)
    : null;

    if (approval == null) {
    approval = new Approval();
    approval.setActive(true);
    approval.setDecision(false);
    approval.setJobRequisition(
    jobRequisitionRepository.findById(jobReqId)
    .orElseThrow(() -> new RuntimeException("Job requisition not found"))
    );
    approval = approvalRepository.save(approval);
    }

    if (approval.getApprover() == null) {
    approval.setApprover(new ArrayList<>());
    }

    boolean alreadyVoted = approval.getApprover().stream()
    .anyMatch(a -> a.getSignature().equals(incomingApprover.getSignature()));
    if (alreadyVoted) {
    throw new IllegalStateException("This approver already voted.");
    }

    incomingApprover.setApproval(approval);
    approverRepository.save(incomingApprover);
    approval.getApprover().add(incomingApprover);

    long totalVotes = approval.getApprover().size();

    if (totalVotes == 3) {
    long approvals = approval.getApprover().stream().filter(Approver::isDecision).count();
    long rejections = 3 - approvals;

    approval.setDecision(approvals > rejections);
    approval.setApprovalDate(new Date());
    JobRequisition jobReq = approval.getJobRequisition();
    jobReq.setActive(false);
    jobRequisitionRepository.save(jobReq);
    }

    return approvalRepository.save(approval);
    }
    @Override 
    public boolean approvalExists(long id){
    return approvalRepository.existsByJobRequisitionJobRequisitionId(id);
    }

    @Override
    public void deleteApproval(long id) {
    approvalRepository.deleteById(id);
    }



    // ------------------- Offer Letter -------------------
    @Override
    public List<OfferLetter> getAllOfferLetters() {
    return offerLetterRepository.findAll();
    }

    @Override
    public OfferLetter getOfferLetterById(Long id) {
    return offerLetterRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("OfferLetter not found with id " + id));
    }

@Override
public OfferLetter createOfferLetter(Long id, OfferLetter offerLetter) {
    // fetch the initial status from table
    OfferLetterStatus managerStatus = offerLetterStatusRepository
        .findByName("MANAGER_APPROVAL_PENDING")
        .orElseThrow(() -> new RuntimeException("Status not found"));

    offerLetter.setStatus(managerStatus);

    OfferLetter offer = offerLetterRepository.save(offerLetter);

    JobApplied application = getJobId(id);
    application.setOfferLetter(offer);
    OfferLetterStatus managerPending = offerLetterStatusRepository
        .findByName("MANAGER_APPROVAL_PENDING")
        .orElseThrow(() -> new RuntimeException("OfferLetterStatus not found"));

        offerLetter.setStatus(managerPending);


    appliedRepository.save(application);

    return offer;
}


@Override
public List<JobApplied> getRecruiterNotifications() {
    return appliedRepository.findAll().stream()
        .filter(app -> {
            boolean createOfferLetter = app.getRemForm() != null &&
                                        "CFO_APPROVED".equals(app.getRemForm().getStatus().getName()) &&
                                        app.getOfferLetter() == null;

            boolean managerApprovalPending = app.getOfferLetter() != null &&
                                            "MANAGER_APPROVAL_PENDING".equals(app.getOfferLetter().getStatus().getName());

            boolean hrApprovalPending = app.getOfferLetter() != null &&
                                        "HR_APPROVAL_PENDING".equals(app.getOfferLetter().getStatus().getName());

            boolean adminApprovalPending = app.getOfferLetter() != null &&
                                           "ADMIN_APPROVAL_PENDING".equals(app.getOfferLetter().getStatus().getName());

            return createOfferLetter || managerApprovalPending || hrApprovalPending || adminApprovalPending;
        })
        .collect(Collectors.toList());
}

@Override
public OfferLetter approveOfferLetter(Long id, String approverEmail) {
    OfferLetter offerLetter = offerLetterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Offer letter not found"));

    Employee approver = employeeRepository.findByEmail(approverEmail);

    String currentStatus = offerLetter.getStatus().getName();
    OfferLetterStatus nextStatus = new OfferLetterStatus();
      
    switch (currentStatus) {
        case "MANAGER_APPROVAL_PENDING":
        if(approver.getRole().getName().equals("Line Manager")){
            nextStatus = offerLetterStatusRepository.findByName("HR_APPROVAL_PENDING")
                        .orElseThrow(() -> new RuntimeException("Status not found"));
            break;
        }
            throw new RuntimeException("Cannot Approve");
        case "HR_APPROVAL_PENDING":
            if(approver.getRole().getName().equals("HR Executive")){
                nextStatus = offerLetterStatusRepository.findByName("CFO_APPROVAL_PENDING")
                            .orElseThrow(() -> new RuntimeException("Status not found"));
                break;
            }
            throw new RuntimeException("Cannot Approve");
        case "CFO_APPROVAL_PENDING":
        if(approver.getRole().getName().equals("CFO")){
            nextStatus = offerLetterStatusRepository.findByName("APPROVED")
                        .orElseThrow(() -> new RuntimeException("Status not found"));
            break;
        }
        throw new RuntimeException("Cannot Approve");
        default:
            throw new RuntimeException("Invalid state for approval");
    }

    offerLetter.setStatus(nextStatus);
    offerLetter.setApprovedBy(approver);
    offerLetter.setApprovedAt(new Date());
    return offerLetterRepository.save(offerLetter);
}

@Override
public OfferLetter rejectOfferLetter(Long id, String approverEmail) {
    OfferLetter offerLetter = offerLetterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Offer letter not found"));

    Employee approver = employeeRepository.findByEmail(approverEmail);

    OfferLetterStatus rejectedStatus = offerLetterStatusRepository.findByName("REJECTED")
            .orElseThrow(() -> new RuntimeException("Status not found"));

    offerLetter.setStatus(rejectedStatus);
    offerLetter.setRejectedBy(approver);
    offerLetter.setRejectedAt(new Date());
    return offerLetterRepository.save(offerLetter);
}


    @Override
    public OfferLetter updateOfferLetter( long id,OfferLetter offerLetter) {
    JobApplied application = getJobId(id);
    if(offerLetter.isDecision()){
    application.setStatus(getJobStatusByName("Onboarding"));
    }else{
    application.setStatus(getJobStatusByName("Withdrawn"));
    }

    appliedRepository.save(application);

    return offerLetterRepository.save(offerLetter);
    }


    @Override
    public void deleteOfferLetter(Long id) {
    offerLetterRepository.deleteById(id);
    }

    // ------------------- Onboarding -------------------
    @Override
    public List<Onboarding> getAllOnboarding(){
    return onboardingRepository.findAll();
    }
    @Override
    public Onboarding getOnboardingById(Long id){
    return onboardingRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Onboarding not found with id " + id));
    }

    @Override
    public Onboarding createOnboarding(Long applicationId, Onboarding onboarding,
                                       MultipartFile id, MultipartFile qualification, MultipartFile cv,
                                       MultipartFile proofOfAccount, MultipartFile proofOfTax,
                                       MultipartFile takeOnForm, MultipartFile ea1, MultipartFile consent) {
        try {
            String uploadDir = new File(".").getCanonicalPath() + File.separator + "uploads" + File.separator;
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            List<OnboardingFile> onboardingFiles = new ArrayList<>();

             onboardingFiles.add(saveFile(id, getFileTypeByNameOrThrow("Id"), uploadDir));
             if (qualification != null) {
                 onboardingFiles.add(saveFile(qualification, getFileTypeByNameOrThrow("Certificate"), uploadDir));
             }
             onboardingFiles.add(saveFile(cv, getFileTypeByNameOrThrow("CV"), uploadDir));
             onboardingFiles.add(saveFile(proofOfAccount, getFileTypeByNameOrThrow("Proof of Account"), uploadDir));
             onboardingFiles.add(saveFile(proofOfTax, getFileTypeByNameOrThrow("Proof of Tax"), uploadDir));
             onboardingFiles.add(saveFile(takeOnForm, getFileTypeByNameOrThrow("Take on Form"), uploadDir));
             onboardingFiles.add(saveFile(ea1, getFileTypeByNameOrThrow("EA1"), uploadDir));
             onboardingFiles.add(saveFile(consent, getFileTypeByNameOrThrow("Consent"), uploadDir));

            // Save file metadata
            List<OnboardingFile> savedFiles = onboardingFileRepository.saveAll(onboardingFiles);
            onboarding.setOnboardingFiles(savedFiles);
            onboarding.setActive(true);
            onboarding.setDateCreated(new Date());
            Onboarding savedOnboarding = onboardingRepository.save(onboarding);

            JobApplied application = getJobId(applicationId);
            application.setOnboarding(savedOnboarding);
            appliedRepository.save(application);

            return savedOnboarding;

        } catch (IOException e) {
            throw new RuntimeException("Failed to save files", e);
        }
    }

private OnboardingFile saveFile(MultipartFile multipartFile, FileType fileType, String uploadDir) throws IOException {
    String cleanFileName = sanitizeFileName(multipartFile.getOriginalFilename());
    String filePath = uploadDir + cleanFileName;

    multipartFile.transferTo(new File(filePath));

    OnboardingFile file = new OnboardingFile();
    file.setFileName(cleanFileName);
    file.setCreated(new Date());
    file.setFilePath(filePath);
    file.setFileType(fileType);

    return file;
}

    @Override
    public Onboarding updateOnboarding(Onboarding onboarding, Long id){
    if(onboarding.getId()!=null){
        Onboarding onboardingOriginal = onboardingRepository.findById(id).orElseThrow(() -> new RuntimeException("onboarding not found with id " + id));
        onboarding.setOnboardingFiles(onboardingOriginal.getOnboardingFiles());
        JobApplied application = appliedRepository.findByOnboardingId(id);
        application.setStatus(getJobStatusByName("Accepted"));
        appliedRepository.save(application);
    return onboardingRepository.save(onboarding);
    }
    return null;
    }


   // ------------------- REM Form -------------------
@Override
public List<RemForm> getAllRemForms() {
    return remFormRepository.findAll();
}

@Override
public RemForm getRemFormById(long id) {
    return remFormRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("REM form not found with id " + id));
}

@Override
public RemForm createRemForm(RemForm remForm, long jobId) {
    // fetch status from DB instead of enum
    RemFormStatus pendingStatus = remFormStatusRepository.findByName("MANAGER_APPROVAL_PENDING")
            .orElseThrow(() -> new RuntimeException("Status MANAGER_APPROVAL_PENDING not found"));
    remForm.setStatus(pendingStatus);

    RemForm savedRemForm = remFormRepository.save(remForm);

    JobApplied application = getJobId(jobId);
    application.setRemForm(savedRemForm);
    appliedRepository.save(application);

    return savedRemForm;
}

@Override
public RemForm approveRemFormByManager(Long id, String userEmail) {
    ensureUserHasRole(userEmail, "Line Manager"); // role by string

    RemForm rem = getRemFormById(id);
    if (!rem.getStatus().getName().equals("MANAGER_APPROVAL_PENDING")) {
        throw new RuntimeException("Form is not pending manager approval");
    }

    RemFormStatus nextStatus = remFormStatusRepository.findByName("HR_APPROVAL_PENDING")
            .orElseThrow(() -> new RuntimeException("Status HR_APPROVAL_PENDING not found"));
    rem.setStatus(nextStatus);

    return remFormRepository.save(rem);
}

@Override
public RemForm approveRemFormByHr(Long id, String userEmail) {
    ensureUserHasRole(userEmail, "HR Executive");

    RemForm rem = getRemFormById(id);
    if (!rem.getStatus().getName().equals("HR_APPROVAL_PENDING")) {
        throw new RuntimeException("Form is not pending HR approval");
    }

    RemFormStatus nextStatus = remFormStatusRepository.findByName("CFO_APPROVAL_PENDING")
            .orElseThrow(() -> new RuntimeException("Status CFO_APPROVAL_PENDING not found"));
    rem.setStatus(nextStatus);

    return remFormRepository.save(rem);
}

@Override
public RemForm approveRemFormByCfo(Long id, String userEmail) {
    ensureUserHasRole(userEmail, "CFO");

    RemForm rem = getRemFormById(id);
    if (!rem.getStatus().getName().equals("CFO_APPROVAL_PENDING")) {
        throw new RuntimeException("Form is not pending CFO approval");
    }

    RemFormStatus nextStatus = remFormStatusRepository.findByName("CFO_APPROVED")
            .orElseThrow(() -> new RuntimeException("Status CFO_APPROVED not found"));
    rem.setStatus(nextStatus);

    RemFormStatus approvedStatus = remFormStatusRepository.findByName("APPROVED")
            .orElseThrow(() -> new RuntimeException("Status REJECTED not found"));
    rem.setStatus(approvedStatus);
    return remFormRepository.save(rem);
}

@Override
public RemForm rejectRemForm(Long id, String userEmail) {
    employeeRepository.findByEmail(userEmail);

    RemForm rem = getRemFormById(id);

    RemFormStatus rejectedStatus = remFormStatusRepository.findByName("REJECTED")
            .orElseThrow(() -> new RuntimeException("Status REJECTED not found"));
    rem.setStatus(rejectedStatus);

    return remFormRepository.save(rem);
}

// Updated ensureUserHasRole method
private void ensureUserHasRole(String email, String requiredRoleName) {
    Employee emp = employeeRepository.findByEmail(email);

    if (!emp.getRole().getName().equals(requiredRoleName)) {
        throw new SecurityException("User does not have the required role");
    }
}
@Override
public List<Gender> getAllGenders() {
    return genderRepository.findAll();
}

@Override
public Gender saveGender(Gender gender) {
    return genderRepository.save(gender);
}

@Override
public Gender updateGender(Long id, Gender gender) {
    Gender existing = genderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Gender not found"));
    existing.setName(gender.getName());
    return genderRepository.save(existing);
}

@Override
public void deleteGender(Long id) {
    genderRepository.deleteById(id);
}


@Override
public List<Race> getAllRaces() {
    return raceRepository.findAll();
}

@Override
public Race saveRace(Race race) {
    return raceRepository.save(race);
}

@Override
public Race updateRace(Long id, Race race) {
    Race existing = raceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Race not found"));
    existing.setName(race.getName());
    return raceRepository.save(existing);
}

@Override
public void deleteRace(Long id) {
    raceRepository.deleteById(id);
}

    // CREATE
    @Override
    public JobStatus createJobStatus(JobStatus jobStatus) {
        return jobStatusRepository.save(jobStatus);
    }

    // READ
    @Override
    public List<JobStatus> getAllJobStatuses() {
        return jobStatusRepository.findAll();
    }

    @Override
    public JobStatus getJobStatusById(Long id) {
        return jobStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobStatus with id " + id + " not found"));
    }

    @Override
    public JobStatus getJobStatusByName(String name) {
        return jobStatusRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("JobStatus '" + name + "' not found"));
    }

    // UPDATE
    @Override
    public JobStatus updateJobStatus(Long id, JobStatus updatedStatus) {
        JobStatus existing = jobStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobStatus with id " + id + " not found"));

        existing.setName(updatedStatus.getName());
        return jobStatusRepository.save(existing);
    }

    // DELETE
    @Override
    public void deleteJobStatus(Long id) {
        if (!jobStatusRepository.existsById(id)) {
            throw new RuntimeException("JobStatus with id " + id + " not found");
        }
        jobStatusRepository.deleteById(id);
    }






 // ✅ Get all
    @Override
    public List<Availability> getAllAvailabilitys() {
        return availabilityRepository.findAll();
    }

    // ✅ Add
    @Override
    public Availability addAvailability(Availability availability) {
        if (availabilityRepository.existsByNameIgnoreCase(availability.getName())) {
            throw new IllegalArgumentException("Availability already exists");
        }
        return availabilityRepository.save(availability);
    }

    // ✅ Update
    @Override
    public Availability updateAvailability(Long id, Availability availability) {
        Availability existing = availabilityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Availability not found"));
        existing.setName(availability.getName());
        return availabilityRepository.save(existing);
    }

    // ✅ Delete
    @Override
    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }


@Override
public Certification createCertification(Certification certification) {
    return certificationRepository.save(certification);
}

@Override
public List<Certification> getAllCertifications() {
    return certificationRepository.findAll();
}

@Override
public Certification updateCertification(Long id, Certification updatedCertification) {
    Certification existing = certificationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Certification not found"));
    existing.setName(updatedCertification.getName());
    return certificationRepository.save(existing);
}

@Override
public void deleteCertification(Long id) {
    certificationRepository.deleteById(id);
}

@Override
public Province createProvince(Province province) {
    return provinceRepository.save(province);
}

@Override
public List<Province> getAllProvinces() {
    return provinceRepository.findAll();
}

@Override
public List<Province> createProvinces(List<Province> provinces) {
    return provinceRepository.saveAll(provinces);
}


@Override
public Province updateProvince(Long id, Province updatedProvince) {
    Province existing = provinceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Province not found"));
    existing.setName(updatedProvince.getName());
    return provinceRepository.save(existing);
}

@Override
public void deleteProvince(Long id) {
    provinceRepository.deleteById(id);
}

@Override
public FileType createFileType(FileType fileType) {
    return fileTypeRepository.save(fileType);
}

@Override
public List<FileType> getAllFileTypes() {
    return fileTypeRepository.findAll();
}

@Override
public FileType updateFileType(Long id, FileType updatedFileType) {
    FileType existing = fileTypeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("FileType not found"));
    existing.setName(updatedFileType.getName());
    existing.setOwnerType(updatedFileType.getOwnerType()); // ✅ update owner
    return fileTypeRepository.save(existing);
}

@Override
public FileType getFileTypeByName(String name) {
    return fileTypeRepository.findByName(name);
}

@Override
public List<FileType> getApplicantFileTypes() {
    return fileTypeRepository.findByOwnerType(FileOwnerType.APPLICANT);
}

@Override
public List<FileType> getRecruiterFileTypes() {
    return fileTypeRepository.findByOwnerType(FileOwnerType.RECRUITER);
}

@Override
public void deleteFileType(Long id) {
    fileTypeRepository.deleteById(id);
}

@Override
public Grade createGrade(Grade grade) {
    return gradeRepository.save(grade);
}

@Override
public List<Grade> getAllGrades() {
    return gradeRepository.findAll();
}

@Override
public Grade updateGrade(Long id, Grade updatedGrade) {
    Grade existing = gradeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Grade not found"));
    existing.setName(updatedGrade.getName());
    return gradeRepository.save(existing);
}

@Override
public void deleteGrade(Long id) {
    gradeRepository.deleteById(id);
}

@Override
public Skill createSkill(Skill skill) {
    return skillRepository.save(skill);
}

@Override
public List<Skill> getAllSkills() {
    return skillRepository.findAll();
}

@Override
public Skill updateSkill(Long id, Skill updatedSkill) {
    Skill existing = skillRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Skill not found"));
    existing.setName(updatedSkill.getName());
    return skillRepository.save(existing);
}

@Override
public void deleteSkill(Long id) {
    skillRepository.deleteById(id);
}

@Override
public Term createTerm(Term term) {
    return termRepository.save(term);
}

@Override
public List<Term> getAllTerms() {
    return termRepository.findAll();
}

@Override
public Term updateTerm(Long id, Term updatedTerm) {
    Term existing = termRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Term not found"));
    existing.setName(updatedTerm.getName());
    return termRepository.save(existing);
}

@Override
public void deleteTerm(Long id) {
    termRepository.deleteById(id);
}

@Override
public JobSector createJobSector(JobSector jobSector) {
    return jobSectorRepository.save(jobSector);
}

@Override
public List<JobSector> getAllJobSectors() {
    return jobSectorRepository.findAll();
}

@Override
public JobSector updateJobSector(Long id, JobSector updatedJobSector) {
    JobSector existing = jobSectorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("JobSector not found"));
    existing.setName(updatedJobSector.getName());
    return jobSectorRepository.save(existing);
}

@Override
public void deleteJobSector(Long id) {
    jobSectorRepository.deleteById(id);
}

@Override
public ExperienceLevel createExperienceLevel(ExperienceLevel experienceLevel) {
    return experienceLevelRepository.save(experienceLevel);
}

@Override
public List<ExperienceLevel> getAllExperienceLevels() {
    return experienceLevelRepository.findAll();
}

@Override
public ExperienceLevel updateExperienceLevel(Long id, ExperienceLevel updated) {
    ExperienceLevel existing = experienceLevelRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("ExperienceLevel not found"));
    existing.setName(updated.getName());
    return experienceLevelRepository.save(existing);
}

@Override
public void deleteExperienceLevel(Long id) {
    experienceLevelRepository.deleteById(id);
}

@Transactional
@Override
public Currency createCurrency(Currency currency) {
    return currencyRepository.save(currency);
}

@Override
public List<Currency> getAllCurrencies() {
    return currencyRepository.findAll();
}

@Override
@Transactional
public List<Currency> setAllCurrencies(List<Currency> currencies) {
    for (Currency currency : currencies) {
        if (currency.getId() != null && currencyRepository.existsById(currency.getId())) {
            // Update existing currency
            Currency existing = currencyRepository.findById(currency.getId())
                .orElseThrow(() -> new RuntimeException("Currency not found"));
            existing.setName(currency.getName());
            currencyRepository.save(existing);
        } else {
            // Insert new currency
            currencyRepository.save(currency);
        }
    }
    return currencyRepository.findAll(); // Return all currencies after changes
}

@Override
public Currency updateCurrency(Long id, Currency currency) {
    Currency existing = currencyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Currency not found"));
    existing.setName(currency.getName());
    return currencyRepository.save(existing);
}

@Override
public void deleteCurrency(Long id) {
    currencyRepository.deleteById(id);
}

public FileType getFileTypeByNameOrThrow(String typeValue) {
    return fileTypeRepository.findByName(typeValue);
}



}
