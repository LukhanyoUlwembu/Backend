package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otpCode) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setFrom("noreply@ulwembubs.com");
            helper.setSubject("Your Verification OTP Code");

            String htmlContent = "<p>Your OTP code is: <strong>" + otpCode + "</strong></p>" +
                "<p>It will expire in 10 minutes.</p>" +
                "<br/>" +
                "<p style='font-style: italic; font-size: 0.9em; color: #555;'>Confidentiality Notice: This email and any attachments are confidential and intended solely for the use of the individual or entity to whom they are addressed. If you have received this email in error, please notify the sender immediately and delete it from your system. Unauthorized use, disclosure, or copying of this email or any part thereof is strictly prohibited.</p>" +
                "<p style='font-style: italic; font-size: 0.9em; color: #555;'>Legal Disclaimer: The views and opinions expressed in this email are those of the author and do not necessarily reflect the official policy or position of Ulwembu. No employee or agent is authorized to conclude any binding agreement on behalf of Ulwembu with another party by email without express written confirmation.</p>" +
                "<p style='font-style: italic; font-size: 0.9em; color: #555;'>Privacy Notice: Ulwembu is committed to protecting your privacy. For more information on how we handle personal data, please refer to our privacy policy available on our website.</p>";

            helper.setText(htmlContent, true); 

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }

    public void sendOtpEmailResetPassword(String toEmail, String otpCode) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setFrom("noreply@ulwembubs.com");
            helper.setSubject("Password Reset");

            String htmlContent = "<p>Your OTP code is: <strong>" + otpCode + "</strong></p>" +
                "<p>It will expire in 10 minutes.</p>" +
                "<br/>" +
                "<p style='font-style: italic; font-size: 0.9em; color: #555;'>Confidentiality Notice: This email and any attachments are confidential and intended solely for the use of the individual or entity to whom they are addressed. If you have received this email in error, please notify the sender immediately and delete it from your system. Unauthorized use, disclosure, or copying of this email or any part thereof is strictly prohibited.</p>" +
                "<p style='font-style: italic; font-size: 0.9em; color: #555;'>Legal Disclaimer: The views and opinions expressed in this email are those of the author and do not necessarily reflect the official policy or position of Ulwembu. No employee or agent is authorized to conclude any binding agreement on behalf of Ulwembu with another party by email without express written confirmation.</p>" +
                "<p style='font-style: italic; font-size: 0.9em; color: #555;'>Privacy Notice: Ulwembu is committed to protecting your privacy. For more information on how we handle personal data, please refer to our privacy policy available on our website.</p>";

            helper.setText(htmlContent, true); 

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }
}
