package com.example.User_Management.Client;

import com.example.User_Management.Config.UserManagementConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "user-service", url = "http://localhost:55113/v1/user", configuration = UserManagementConfig.class)
public interface UserServiceClient {
    @GetMapping("/getAll")
    ResponseEntity<List<Map<String, Object>>> getAllWorkers();

    @PutMapping("/updateUserRole")
    ResponseEntity<Void> updateRoleByEmail(@RequestBody Map<String, String> request);
}
