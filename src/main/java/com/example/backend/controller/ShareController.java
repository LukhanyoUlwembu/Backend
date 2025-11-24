package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.backend.model.JobPosting;
import com.example.backend.service.HrServiceImplementation;

@Controller  // <-- very important! Returns HTML, not JSON
public class ShareController {

    @Autowired
    private HrServiceImplementation hr;

    @GetMapping("/share/job/{id}")
    public String shareJob(@PathVariable long id, Model model) {

        JobPosting job = hr.getJobPosting(id);

        if (job == null) {
            // fallback metadata
            model.addAttribute("title", "Job not found");
            model.addAttribute("description", "This job posting does not exist.");
            model.addAttribute("image", "https://yourdomain.com/default-job.png");
            model.addAttribute("url", "https://yourdomain.com");
            model.addAttribute("id", id);
            return "job-share";
        }

        // --------- Build metadata dynamically ---------
        String title = job.getJobTitle();
        String description = job.getJobDescription() != null
                ? job.getJobDescription()
                : "View job details.";

        // shorten description for LinkedIn/Facebook
        if (description.length() > 180) {
            description = description.substring(0, 180) + "...";
        }

        // You can later change to job-specific image
        String imageUrl = "https://i.pinimg.com/1200x/2c/5d/9b/2c5d9b70a83fe9a1ef64475b34186cff.jpg";

        String url = "https://yourdomain.com/share/job/" + id;

        // -------- Add attributes --------
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("image", imageUrl);
        model.addAttribute("url", url);
        model.addAttribute("id", id);

        return "job-share"; // return HTML page
    }
}
