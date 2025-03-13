package com.example.User_Management.Controller;


import com.example.User_Management.Client.AuthServiceClient;
import com.example.User_Management.Client.UserServiceClient;
import com.example.User_Management.Service.WorkerInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/user-management")
public class UserManagementController {
    @Autowired
    private WorkerInvitationService workerInvitationService;
    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private UserServiceClient  userServiceClient;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<?> getAllWorkers() {
        try {
            ResponseEntity<List<Map<String, Object>>> response = userServiceClient.getAllWorkers();
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch users: " + e.getMessage());
        }
    }
    @PostMapping("/invite-workers")
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> inviteWorkers(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        System.out.println("Received request to invite workers with email: " + email);
        if (email == null || email.isEmpty()) {
            System.out.println("Email is required!");
            return ResponseEntity.badRequest().body("Email is required!");
        }
        try {
            Map<String, String> workerData = workerInvitationService.inviteWorkers(email);
            // Step 3: Send the email and hashed password to the Auth Service Microservice
            authServiceClient.createUser(workerData);

            // Step 4: Return a success response
            return ResponseEntity.ok("Invitation sent and user created successfully!");
        } catch (Exception e) {
            System.out.println("Failed to send invitations for email: " + email + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send invitations: " + e.getMessage());
        }
    }
    @PutMapping("/update-role")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> updateUserRole(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newRole = request.get("role");

        if (email == null || newRole == null || newRole.isEmpty()) {
            return ResponseEntity.badRequest().body("Email and role are required.");
        }

        try {
            // Call the user service to update the role, passing the full request map
            ResponseEntity<Void> response = userServiceClient.updateRoleByEmail(request);

            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok("User role updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to update user role.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user role: " + e.getMessage());
        }
    }


}

