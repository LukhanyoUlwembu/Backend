package com.example.backend.controller;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.model.Applicant;
import com.example.backend.model.FileType;
import com.example.backend.service.HrServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/applicants") 
public class ApplicantController {


    @Autowired
    private HrServiceImplementation hrservice;

    // GET /applicants
    @GetMapping
    public List<Applicant> getAllApplicants() {
    return hrservice.getAllApplicant();
    }

    // POST /applicants
    @PostMapping
    public Applicant createApplicant(@RequestBody Applicant applicant) {
    return hrservice.createApplicant(applicant);
    }

    // GET /applicants/{id}
    @GetMapping("/{id}")
    public Applicant getApplicant(@PathVariable long id) {
    return hrservice.getApplicant(id);
    }
    
@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Applicant> updateApplicantWithFiles(
    @PathVariable Long id,
    @RequestPart("payload") String payload,
    @RequestPart(required = false) List<MultipartFile> files,
    @RequestParam Map<String, String> requestParams) {

    try {
        ObjectMapper mapper = new ObjectMapper();
        Applicant applicant = mapper.readValue(payload, Applicant.class);
        applicant.setApplicantId(id);

        List<FileType> fileTypes = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            for (int i = 0; i < files.size(); i++) {
                String typeKey = "fileType_" + i;
                String typeValue = requestParams.get(typeKey);

                if (typeValue == null || typeValue.trim().isEmpty()) {
                    return ResponseEntity.badRequest().body(null);
                }

                try {
                    FileType fileType = hrservice.getFileTypeByNameOrThrow(typeValue);
                    fileTypes.add(fileType);
                } catch (RuntimeException ex) {
                    return ResponseEntity.badRequest().body(null); // Invalid fileType name
                }
            }
        }

        Applicant updated = hrservice.updateApplicantWithFiles(
            applicant,
            files != null ? files : new ArrayList<>(),
            fileTypes
        );

        return ResponseEntity.ok(updated);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


    // POST /applicants/login
    @PostMapping("/login")
    public Applicant login(@RequestBody LoginRequest loginRequest) {
    return hrservice.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @GetMapping("/applicant/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
    try {
    Path file = Paths.get("uploads").resolve(filename).normalize();
    Resource resource = new UrlResource(file.toUri());

    if (resource.exists()) {
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    } else {
    return ResponseEntity.notFound().build();
    }
    } catch (MalformedURLException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    }

    //Delete /applicants/education/{id}
    @DeleteMapping("/education/{id}")
    public void deleteEducation(@PathVariable Long id){
        hrservice.deleteApplicantEducation(id);
    }

    //Delete /applicants/experience/{id}
    @DeleteMapping("/experience/{id}")
    public void deleteExperience(@PathVariable Long id){
        hrservice.deleteApplicantWorkExperience(id);
    }
    //Delete /applicants/skill/{id}
    @DeleteMapping("/skill/{id}")
    public void deleteSkill(@PathVariable Long id){
        hrservice.deleteApplicantSkill(id);
    }
    //Delete /applicants/file/{id}
    @DeleteMapping("/file/{id}")
    public void deleteApplicantFile(@PathVariable Long id){
        hrservice.deleteApplicantFile(id);
    }
    public static class LoginRequest {
    private String email;
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    }

}
