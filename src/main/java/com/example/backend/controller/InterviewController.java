package com.example.backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backend.model.*;
import com.example.backend.service.HrServiceImplementation;

@RestController
@RequestMapping("/interviews")  
public class InterviewController {
     @Autowired
    private HrServiceImplementation hrservice;

    // GET /interviews/{email}
    @GetMapping("/{email}")
    public List<Interview> getInterviews(@PathVariable String email) {
        return hrservice.getInterviews(email);
    }
}
