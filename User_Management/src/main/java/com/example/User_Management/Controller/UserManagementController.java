package com.example.User_Management.Controller;


import com.example.User_Management.Service.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/user-management")
public class UserManagementController {
    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String id) {
        Map<String, Object> userData = userManagementService.getUserById(id);
        return ResponseEntity.ok(userData);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@PathVariable String email) {
        Map<String, Object> userData = userManagementService.getUserByEmail(email);
        return ResponseEntity.ok(userData);
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<Map<String, Object>> getUserByUsername(@PathVariable String username) {
        Map<String, Object> userData = userManagementService.getUserByUsername(username);
        return ResponseEntity.ok(userData);
    }
}