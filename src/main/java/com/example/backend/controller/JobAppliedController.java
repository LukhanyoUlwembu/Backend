package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.model.JobApplied;
import com.example.backend.model.OfferLetter;
import com.example.backend.model.Pass;
import com.example.backend.service.HrServiceImplementation;

@RestController
@RequestMapping("/applications")
public class JobAppliedController {

    @Autowired
    private HrServiceImplementation hrservice;

    // GET /applications
    @GetMapping
    public List<JobApplied> getAllApplications() {
        return hrservice.getJobApplieds();
    }

    // POST /applications (with multipart for CV and cover letter)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createApplication(
            @RequestPart("application") JobApplied job,
            @RequestParam(value = "cv", required = false) MultipartFile cv,
            @RequestParam(value = "coverLetter", required = false) MultipartFile coverLetter) {

        return ResponseEntity.ok(hrservice.createJobApplied(job, cv, coverLetter));
    }

    // POST /applications/apply/{applicantid}/{Jobid}
    @PostMapping("/apply/{applicantid}/{Jobid}")
    public String apply(@PathVariable int applicantid, @PathVariable int Jobid) {
        return hrservice.createJobAppliedold(applicantid, Jobid);
    }

    // POST /applications/pass-vote/{jobAppliedId}
    @PostMapping("/pass-vote/{jobAppliedId}")
    public JobApplied voteOnApplication(@PathVariable long jobAppliedId, @RequestBody Pass pass) {
        return hrservice.castPassVote(jobAppliedId, pass);
    }

    @PutMapping("/move-to-longlist")
    public ResponseEntity<?> moveToLonglist(@RequestBody List<Long> jobAppliedIds) {
        try {
            List<JobApplied> updated = hrservice.moveApplicantsToLonglist(jobAppliedIds);
            return ResponseEntity.ok(updated);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

        @PutMapping("/move-to-shortlist")
    public ResponseEntity<?> moveToShortlist(@RequestBody JobApplied jobApplied) {
        try {
            
            return ResponseEntity.ok( hrservice.moveApplicantsToShortlist(jobApplied));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/move-to-interview")
    public ResponseEntity<?> moveToInterview(@RequestBody List<Long> jobAppliedIds) {
        try {
            List<JobApplied> updated = hrservice.moveApplicantsToInterview(jobAppliedIds);
            return ResponseEntity.ok(updated);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());}
        }
     
    // GET /applications/{id}
    @GetMapping("/{id}")
    public JobApplied getApplicationById(@PathVariable long id) {
        return hrservice.getJobId(id);
    }

    // GET /applications/by-applicant/{applicantId}
    @GetMapping("/by-applicant/{applicantId}")
    public List<JobApplied> getByApplicant(@PathVariable long applicantId) {
        return hrservice.getApplicationsByApplicant(applicantId);
    }

    // GET /applications/by-job-posting/{jobId}
    @GetMapping("/by-job-posting/{jobId}")
    public List<JobApplied> getByJobPosting(@PathVariable long jobId) {
        return hrservice.getApplicationsByJobPosting(jobId);
    }

    // GET /applications/exists/{applicantId}/{jobId}
    @GetMapping("/exists/{applicantId}/{jobId}")
    public boolean applicationExists(@PathVariable long applicantId, @PathVariable long jobId) {
        return hrservice.applicationExists(applicantId, jobId);
    }

    // GET /applications/get-by-applicant-job/{applicantId}/{jobId}
    @GetMapping("/get-by-applicant-job/{applicantId}/{jobId}")
    public JobApplied application(@PathVariable long applicantId, @PathVariable long jobId) {
        return hrservice.application(applicantId, jobId);
    }

    // PUT /applications/{id}
    @PutMapping("/{id}")
    public JobApplied updateApplication(@PathVariable Long id, @RequestBody JobApplied updatedApp) {
        return hrservice.updateApplication(id, updatedApp);
    }

    // POST /applications/offer-letter/{id}
    @PostMapping("/offer-letter/{id}")
    public OfferLetter createOffer(@PathVariable Long id, @RequestBody OfferLetter offerLetter) {
        return hrservice.createOfferLetter(id, offerLetter);
    }

    // PUT /applications/offer-letter/{id}
    @PutMapping("/offer-letter/{id}")
    public OfferLetter updateOffer(@PathVariable Long id, @RequestBody OfferLetter offerLetter) {
        return hrservice.updateOfferLetter(id, offerLetter);
    }

    // PUT /applications/withdraw/{jobAppliedId}
    @PutMapping("/withdraw/{jobAppliedId}")
    public ResponseEntity<?> withdrawApplication(@PathVariable Long jobAppliedId) {
        try {
            hrservice.withdrawApplication(jobAppliedId);
            return ResponseEntity.ok("Application withdrawn successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error withdrawing application.");
        }
    }
}
