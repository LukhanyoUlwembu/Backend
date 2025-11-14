package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.backend.model.FileType;
import com.example.backend.model.FileOwnerType;
import com.example.backend.service.HrServiceImplementation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/file-types") // optional base path
public class FileTypeController {

    @Autowired
    private HrServiceImplementation hrService;

    @GetMapping("/applicant")
    public List<FileType> getApplicantFileTypes() {
        return hrService.getApplicantFileTypes();
    }

    @PostMapping("/applicant")
    public ResponseEntity<FileType> addApplicantFileType(@RequestBody FileType fileType) {
    // Ensure the owner type is set to APPLICANT
    fileType.setOwnerType(FileOwnerType.APPLICANT);
    FileType created = hrService.createFileType(fileType);
    return ResponseEntity.ok(created);
}

    @GetMapping("/recruiter")
    public List<FileType> getRecruiterFileTypes() {
        return hrService.getRecruiterFileTypes();
    }
}

