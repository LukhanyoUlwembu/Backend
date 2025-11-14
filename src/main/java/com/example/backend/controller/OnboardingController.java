package com.example.backend.controller;

import com.example.backend.model.Onboarding;
import com.example.backend.service.HrServiceImplementation;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    @Autowired
    private HrServiceImplementation hrServiceImplementation;

    @GetMapping()
    public List<Onboarding> getAllChecklists() {
        return hrServiceImplementation.getAllOnboarding();
    }

    @GetMapping("/{id}")
    public Onboarding getOnboardingById(@PathVariable Long id) {
        return hrServiceImplementation.getOnboardingById(id);
    }
   
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Onboarding createOnboarding(@RequestPart("onboarding")  Onboarding onboarding,
        @RequestParam(value = "id", required = false) MultipartFile id,
        @RequestParam(value = "qualification", required = false) MultipartFile qualification,
        @RequestParam(value = "cv", required = false) MultipartFile cv,
        @RequestParam(value = "proofOfAccount", required = false) MultipartFile proofOfAccount,
        @RequestParam(value = "proofOfTax", required = false) MultipartFile proofOfTax,
        @RequestParam(value = "takeOnForm", required = false) MultipartFile takeOnForm,
        @RequestParam(value = "ea1", required = false) MultipartFile ea1,
        @RequestParam(value = "consent", required = false) MultipartFile consent,
        @RequestParam("applicationId") Long applicationId
    ) {
        return hrServiceImplementation.createOnboarding(applicationId,onboarding,id,qualification,cv,proofOfAccount,proofOfTax,takeOnForm,ea1,consent);
    }

    @PutMapping("/{id}")
    public Onboarding updateChecklist( @RequestBody Onboarding onboarding, @PathVariable Long id) {
       return hrServiceImplementation.updateOnboarding(onboarding,id);
    }

   
}