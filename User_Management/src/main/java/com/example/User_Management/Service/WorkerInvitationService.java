package com.example.User_Management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class WorkerInvitationService {

    @Autowired
    private EmailService emailService;

    public Map<String, String> inviteWorkers(String email) {
        // Step 1: Generate a random username
        String username = generateRandomUsername();

        // Step 2: Generate a random password
        String password = generateRandomPassword();

        // Step 3: Send the email with the username and password
        sendInvitationEmail(email, username, password);

        // Step 4: Return the email, username, and password
        Map<String, String> result = new HashMap<>();
        result.put("email", email);
        result.put("username", username);
        result.put("password", password);
        return result;
    }

    private String generateRandomUsername() {
        String prefix = "user_";
        int randomNum = new Random().nextInt(10000); // Generates a random number between 0 and 9999
        return prefix + randomNum; // Example: user_4532
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

    private void sendInvitationEmail(String email, String username, String password) {
        String subject = "Your Account Credentials";
        String body = "Hello,\n\nYour username is: " + username +
                "\nYour password is: " + password +
                "\n please change it once you log in " +
                "\n\nPlease log in and change your password.";
        emailService.sendEmail(email, subject, body);
    }
}
