package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.backend.model.JobPosting;
import com.example.backend.service.HrServiceImplementation;

@Controller
public class ShareController {

    @Autowired
    private HrServiceImplementation hr;

    private final String frontendUrl = "https://ulwemburecruite.netlify.app";

    @GetMapping("/share/job/{id}")
    public String shareJob(@PathVariable long id, Model model) {

        JobPosting job = hr.getJobPosting(id);

        if (job == null) {
            model.addAttribute("title", "Job Not Found");
            model.addAttribute("description", "This job posting does not exist.");
            model.addAttribute("image", "https://yourdomain.com/default-job.png");
            model.addAttribute("url", frontendUrl + "/jobDetail/" + id);
            return "job-share";
        }

        // Prepare description
        String description = job.getJobDescription() != null
                ? job.getJobDescription()
                : "View job details.";

        if (description.length() > 180) {
            description = description.substring(0, 180) + "...";
        }

        // Sample default image
        String imageUrl = "https://i.pinimg.com/1200x/2c/5d/9b/2c5d9b70a83fe9a1ef64475b34186cff.jpg";

        // Pass template values
        model.addAttribute("title", job.getJobTitle());
        model.addAttribute("description", description);
        model.addAttribute("image", imageUrl);
        model.addAttribute("url", frontendUrl + "/jobDetail/" + id);

        return "job-share";
    }
}
