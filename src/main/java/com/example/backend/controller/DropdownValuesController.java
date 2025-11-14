package com.example.backend.controller;

import java.util.List;

import com.example.backend.model.*;
import com.example.backend.service.HrServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dropdown")

public class DropdownValuesController {

    @Autowired
    private HrServiceInterface hrService;

    // ------------------ GENDERS ------------------
    @GetMapping("/genders")
    public ResponseEntity<List<Gender>> getAllGenders() {
        return ResponseEntity.ok(hrService.getAllGenders());
    }

    @PostMapping("/genders")
    public ResponseEntity<Gender> addGender(@RequestBody Gender gender) {
        return ResponseEntity.ok(hrService.saveGender(gender));
    }

    @PutMapping("/genders/{id}")
    public ResponseEntity<Gender> updateGender(@PathVariable Long id, @RequestBody Gender gender) {
        return ResponseEntity.ok(hrService.updateGender(id, gender));
    }

    @DeleteMapping("/genders/{id}")
    public ResponseEntity<Void> deleteGender(@PathVariable Long id) {
        hrService.deleteGender(id);
        return ResponseEntity.noContent().build();
    }

    // ------------------ RACES ------------------
    @GetMapping("/races")
    public ResponseEntity<List<Race>> getAllRaces() {
        return ResponseEntity.ok(hrService.getAllRaces());
    }

    @PostMapping("/races")
    public ResponseEntity<Race> addRace(@RequestBody Race race) {
        return ResponseEntity.ok(hrService.saveRace(race));
    }

    @PutMapping("/races/{id}")
    public ResponseEntity<Race> updateRace(@PathVariable Long id, @RequestBody Race race) {
        return ResponseEntity.ok(hrService.updateRace(id, race));
    }

    @DeleteMapping("/races/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        hrService.deleteRace(id);
        return ResponseEntity.noContent().build();
    }

 // CREATE
    @PostMapping("/job-statuses")
    public ResponseEntity<JobStatus> createJobStatus(@RequestBody JobStatus jobStatus) {
        JobStatus created = hrService.createJobStatus(jobStatus);
        return ResponseEntity.ok(created);
    }

    // READ (all)
    @GetMapping("/job-statuses")
    public ResponseEntity<List<JobStatus>> getAllJobStatuses() {
        return ResponseEntity.ok(hrService.getAllJobStatuses());
    }

    // READ (by id)
    @GetMapping("/job-statuses/{id}")
    public ResponseEntity<JobStatus> getJobStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(hrService.getJobStatusById(id));
    }

    // READ (by name)
    @GetMapping("/job-statuses/name/{name}")
    public ResponseEntity<JobStatus> getJobStatusByName(@PathVariable String name) {
        return ResponseEntity.ok(hrService.getJobStatusByName(name));
    }

    // UPDATE
    @PutMapping("/job-statuses/{id}")
    public ResponseEntity<JobStatus> updateJobStatus(
            @PathVariable Long id,
            @RequestBody JobStatus jobStatus
    ) {
        return ResponseEntity.ok(hrService.updateJobStatus(id, jobStatus));
    }

    // DELETE
    @DeleteMapping("/job-statuses/{id}")
    public ResponseEntity<Void> deleteJobStatus(@PathVariable Long id) {
        hrService.deleteJobStatus(id);
        return ResponseEntity.noContent().build();
    }

 // ✅ GET all
    @GetMapping("/availability-options")
    public ResponseEntity<List<Availability>> getAllAvailabilitys() {
        return ResponseEntity.ok(hrService.getAllAvailabilitys());
    }

    // ✅ POST add
    @PostMapping("/availability-options")
    public ResponseEntity<Availability> addAvailability(@RequestBody Availability availability) {
        return ResponseEntity.ok(hrService.addAvailability(availability));
    }

    // ✅ PUT update
    @PutMapping("/availability-options/{id}")
    public ResponseEntity<Availability> updateAvailability(
            @PathVariable Long id,
            @RequestBody Availability availability) {
        return ResponseEntity.ok(hrService.updateAvailability(id, availability));
    }

    // ✅ DELETE
    @DeleteMapping("/availability-options/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        hrService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/certifications")
    public Certification createCertification(@RequestBody Certification certification) {
        return hrService.createCertification(certification);
    }

    @GetMapping("/certifications")
    public List<Certification> getAllCertifications() {
        return hrService.getAllCertifications();
    }

    @PutMapping("/certifications/{id}")
    public Certification updateCertification(@PathVariable Long id, @RequestBody Certification certification) {
        return hrService.updateCertification(id, certification);
    }

    @DeleteMapping("/certifications/{id}")
    public void deleteCertification(@PathVariable Long id) {
        hrService.deleteCertification(id);
    }

    @PostMapping("/provinces")
    public Province createProvince(@RequestBody Province province) {
        return hrService.createProvince(province);
    }

    @PostMapping("/provinces/bulk")
    public List<Province> createProvinces(@RequestBody List<Province> provinces) {
        return hrService.createProvinces(provinces);
    }


    @GetMapping("/provinces")
    public List<Province> getAllProvinces() {
        return hrService.getAllProvinces();
    }

    @PutMapping("/provinces/{id}")
    public Province updateProvince(@PathVariable Long id, @RequestBody Province province) {
        return hrService.updateProvince(id, province);
    }

    @DeleteMapping("/provinces/{id}")
    public void deleteProvince(@PathVariable Long id) {
        hrService.deleteProvince(id);
    }

    @PostMapping("/file-types")
    public FileType createFileType(@RequestBody FileType fileType) {
        return hrService.createFileType(fileType);
    }

    @GetMapping("/file-types")
    public List<FileType> getAllFileTypes() {
        return hrService.getAllFileTypes();
    }

    @PutMapping("/file-types/{id}")
    public FileType updateFileType(@PathVariable Long id, @RequestBody FileType fileType) {
        return hrService.updateFileType(id, fileType);
    }

    @DeleteMapping("/file-types/{id}")
    public void deleteFileType(@PathVariable Long id) {
        hrService.deleteFileType(id);
    }

    @PostMapping("/grades")
    public Grade createGrade(@RequestBody Grade grade) {
        return hrService.createGrade(grade);
    }

    @GetMapping("/grades")
    public List<Grade> getAllGrades() {
        return hrService.getAllGrades();
    }

    @PutMapping("/grades/{id}")
    public Grade updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        return hrService.updateGrade(id, grade);
    }

    @DeleteMapping("/grades/{id}")
    public void deleteGrade(@PathVariable Long id) {
        hrService.deleteGrade(id);
    }

    @PostMapping("/skills")
    public Skill createSkill(@RequestBody Skill skill) {
        return hrService.createSkill(skill);
    }

    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return hrService.getAllSkills();
    }

    @PutMapping("/skills/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        return hrService.updateSkill(id, skill);
    }

    @DeleteMapping("/skills/{id}")
    public void deleteSkill(@PathVariable Long id) {
        hrService.deleteSkill(id);
    }

    @PostMapping("/terms")
    public Term createTerm(@RequestBody Term term) {
        return hrService.createTerm(term);
    }

    @GetMapping("/terms")
    public List<Term> getAllTerms() {
        return hrService.getAllTerms();
    }

    @PutMapping("/terms/{id}")
    public Term updateTerm(@PathVariable Long id, @RequestBody Term term) {
        return hrService.updateTerm(id, term);
    }

    @DeleteMapping("/terms/{id}")
    public void deleteTerm(@PathVariable Long id) {
        hrService.deleteTerm(id);
    }

    @PostMapping("/sectors")
    public JobSector createJobSector(@RequestBody JobSector jobSector) {
        return hrService.createJobSector(jobSector);
    }

    @GetMapping("/sectors")
    public List<JobSector> getAllJobSectors() {
        return hrService.getAllJobSectors();
    }

    @PutMapping("/sectors/{id}")
    public JobSector updateJobSector(@PathVariable Long id, @RequestBody JobSector jobSector) {
        return hrService.updateJobSector(id, jobSector);
    }

    @DeleteMapping("/sectors/{id}")
    public void deleteJobSector(@PathVariable Long id) {
        hrService.deleteJobSector(id);
    }

    @PostMapping("/experience-levels")
    public ExperienceLevel createExperienceLevel(@RequestBody ExperienceLevel experienceLevel) {
        return hrService.createExperienceLevel(experienceLevel);
    }

    @GetMapping("/experience-levels")
    public List<ExperienceLevel> getAllExperienceLevels() {
        return hrService.getAllExperienceLevels();
    }

    @PutMapping("/experience-levels/{id}")
    public ExperienceLevel updateExperienceLevel(@PathVariable Long id, @RequestBody ExperienceLevel experienceLevel) {
        return hrService.updateExperienceLevel(id, experienceLevel);
    }

    @DeleteMapping("/experience-levels/{id}")
    public void deleteExperienceLevel(@PathVariable Long id) {
        hrService.deleteExperienceLevel(id);
    }

    @PostMapping("/currencies")
    public Currency createCurrency(@RequestBody Currency currency) {
        return hrService.createCurrency(currency);
    }

    @GetMapping("/currencies")
    public List<Currency> getAllCurrencies() {
        return hrService.getAllCurrencies();
    }

    @PutMapping("/currencies/{id}")
    public Currency updateCurrency(@PathVariable Long id, @RequestBody Currency currency) {
        return hrService.updateCurrency(id, currency);
    }

    
@PostMapping("/currencies/bulk")
public List<Currency> setAllCurrencies(@RequestBody List<Currency> currencies) {
    return hrService.setAllCurrencies(currencies);
}


    @DeleteMapping("/currencies/{id}")
    public void deleteCurrency(@PathVariable Long id) {
        hrService.deleteCurrency(id);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return hrService.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return hrService.getRoleById(id);
    }

    @GetMapping("/roles/role-names")
    public List<String> getRoleNames() {
        return hrService.getRoleNames();
    }

    @PostMapping("/roles")
    public Role addRole(@RequestBody Role role) {
        return hrService.createRole(role);
    }

    @DeleteMapping("/roles/{id}")
    public void deleteRole(@PathVariable Long id) {
        hrService.deleteRole(id);
    }
} 
