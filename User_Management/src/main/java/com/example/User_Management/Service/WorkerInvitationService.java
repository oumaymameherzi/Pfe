package com.safalifter.usermanagement.service;

import com.example.User_Management.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WorkerInvitationService {

    @Autowired
    private EmailService emailService;

    public void inviteWorkers(List<String> emails) {
        for (String email : emails) {
            String password = generateRandomPassword();
            sendInvitationEmail(email, password);
        }
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    private void sendInvitationEmail(String email, String password) {
        String subject = "Your Account Credentials";
        String body = "Hello,\n\nYour password is: " + password + "\n\nPlease log in and change your password.";
        emailService.sendEmail(email, subject, body);
    }
}