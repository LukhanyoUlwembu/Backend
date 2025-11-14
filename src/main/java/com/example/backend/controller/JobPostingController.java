package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.JobPosting;
import com.example.backend.service.HrServiceImplementation;

@RestController
@RequestMapping("/postings")
public class JobPostingController {

    @Autowired
    private HrServiceImplementation hr;

    // GET /postings
    @GetMapping
    public List<JobPosting> getAllJobPostings() {
        return hr.getAllJobPostings();
    }

    // GET /postings/{id}
    @GetMapping("/{id}")
    public JobPosting getJobPostingById(@PathVariable long id) {
        return hr.getJobPosting(id);
    }

    // POST /postings
    @PostMapping
    public String createJobPosting(@RequestBody JobPosting jobPosting) {
        return hr.createJobPosting(jobPosting);
    }
}
