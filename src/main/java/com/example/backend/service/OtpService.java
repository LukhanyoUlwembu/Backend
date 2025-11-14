package com.example.backend.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    private final ConcurrentHashMap<String, OtpEntry> otpCache = new ConcurrentHashMap<>();

    private static class OtpEntry {
        String otp;
        Instant expiresAt;
        OtpEntry(String otp, Instant expiresAt) {
            this.otp = otp;
            this.expiresAt = expiresAt;
        }
    }

    public String generateOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(900000) + 100000);
        Instant expiresAt = Instant.now().plusSeconds(600); // 10 minutes expiry
        otpCache.put(email, new OtpEntry(otp, expiresAt));
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        OtpEntry entry = otpCache.get(email);
        if (entry == null) return false;
        if (Instant.now().isAfter(entry.expiresAt)) {
            otpCache.remove(email);
            return false; // expired
        }
        boolean isValid = entry.otp.equals(otp);
        if (isValid) {
            otpCache.remove(email); // OTP used, remove from cache
        }
        return isValid;
    }
}
