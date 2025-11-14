package com.example.backend.controller;

import com.example.backend.service.HrServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
public class HRController {

    @Autowired
    private HrServiceImplementation hrService;

    @PostMapping("/processExpiredJobs")
    public String manuallyProcessExpiredJobs() {
        hrService.processExpiredJobs();
        return "✅ Manually triggered job processing complete.";
    }
}
