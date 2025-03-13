package com.example.User_Management.Client;

import com.example.User_Management.dto.UserTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "auth-service", url = "http://localhost:55115/v1/auth") // Replace with the actual URL of the Auth Service Microservice
public interface AuthServiceClient {
    @PostMapping("/register")
    void createUser(@RequestBody Map<String, String> user);
    @GetMapping("/validate-token")
    String validateToken(@RequestHeader("Authorization") String token);
}
