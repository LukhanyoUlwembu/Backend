package com.example.backend.controller;
import com.example.backend.model.JobAlert;
import com.example.backend.service.HrServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobAlerts")
public class JobAlertController {

    @Autowired
    private HrServiceImplementation hrService;


    @PostMapping("/{id}")
    public JobAlert createAlert(@RequestBody JobAlert alert,@PathVariable long id) {
        return hrService.createJobAlert(alert,id);
    }

    @GetMapping("/applicant/{id}")
    public List<JobAlert> getAlertsForApplicant(@PathVariable Long id) {
        return hrService.getJobAlertsByApplicantId(id);
    }
}
