package com.example.backend.controller;

import com.example.backend.service.EmailService;
import com.example.backend.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    // POST /auth/otp/send
    @PostMapping("/otp/send")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequest request) {
        String otp = otpService.generateOtp(request.getEmail());
        emailService.sendOtpEmail(request.getEmail(), otp);
        return ResponseEntity.ok("OTP sent successfully to " + request.getEmail());
    }
    
     // POST /auth/otp/send
    @PostMapping("/otp/reset/send")
    public ResponseEntity<String> resetSendOtp(@RequestBody OtpRequest request) {
        String otp = otpService.generateOtp(request.getEmail());
        emailService.sendOtpEmailResetPassword(request.getEmail(), otp);
        return ResponseEntity.ok("OTP sent successfully to " + request.getEmail());
    }

    // POST /auth/otp/verify
    @PostMapping("/otp/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest request) {
        boolean valid = otpService.verifyOtp(request.getEmail(), request.getOtp());
        if (valid) {
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            return ResponseEntity.status(400).body("Invalid or expired OTP.");
        }
    }

    // DTO for sending OTP
    public static class OtpRequest {
        private String email;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    // DTO for verifying OTP
    public static class OtpVerificationRequest {
        private String email;
        private String otp;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getOtp() { return otp; }
        public void setOtp(String otp) { this.otp = otp; }
    }
}
