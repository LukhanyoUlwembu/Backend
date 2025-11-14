package com.example.backend.controller;

import com.example.backend.model.*;
import com.example.backend.repository.JobAppliedRepository;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.OfferLetterStatusRepository;
import com.example.backend.repository.RemFormStatusRepository;
import com.example.backend.service.HrServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offer-letters")
public class OfferLetterController {

    @Autowired
    private HrServiceImplementation hr;

    @Autowired
    private JobAppliedRepository appliedRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OfferLetterStatusRepository offerLetterStatusRepository;

    @Autowired
    private RemFormStatusRepository remFormStatusRepository;

    // ------------------- Notifications -------------------
    @GetMapping("/notifications")
    public List<JobApplied> getNotifications(@RequestParam(required = false) String roleName) {
        if (roleName == null) {
            return hr.getRecruiterNotifications();
        }

        Role userRole = roleRepository.findByName(roleName);
        if (userRole == null) {
            return List.of();
        }

        switch (userRole.getName()) {
            case "Line Manager":
                return appliedRepository.findByOfferLetter_Status(
                        offerLetterStatusRepository.findByName("MANAGER_APPROVAL_PENDING")
                                .orElseThrow(() -> new RuntimeException("Status not found"))
                );

            case "Human Resource":
                return appliedRepository.findByOfferLetter_Status(
                        offerLetterStatusRepository.findByName("HR_APPROVAL_PENDING")
                                .orElseThrow(() -> new RuntimeException("Status not found"))
                );

            case "Admin":
                return appliedRepository.findByOfferLetter_Status(
                        offerLetterStatusRepository.findByName("ADMIN_APPROVAL_PENDING")
                                .orElseThrow(() -> new RuntimeException("Status not found"))
                );

            case "CFO":
                RemFormStatus approved = remFormStatusRepository.findByName("CFO_APPROVED")
                        .orElseThrow(() -> new RuntimeException("Status not found"));
                return appliedRepository.findAll().stream()
                        .filter(app -> app.getRemForm() != null &&
                                app.getRemForm().getStatus().equals(approved))
                        .collect(Collectors.toList());

            default:
                return List.of();
        }
    }

    // ------------------- Get Offer Letter by ID -------------------
    @GetMapping("/{id}")
    public OfferLetter getOfferLetter(@PathVariable Long id) {
        return hr.getOfferLetterById(id);
    }

    // ------------------- Approve Offer Letter -------------------
    @PutMapping("/{id}/approve/{approverEmail}")
    public OfferLetter approveOfferLetter(@PathVariable Long id, @PathVariable String approverEmail) {
        return hr.approveOfferLetter(id, approverEmail);
    }

    // ------------------- Reject Offer Letter -------------------
    @PutMapping("/{id}/reject")
    public OfferLetter rejectOfferLetter(@PathVariable Long id, @RequestParam String approverEmail) {
        return hr.rejectOfferLetter(id, approverEmail);
    }

}

