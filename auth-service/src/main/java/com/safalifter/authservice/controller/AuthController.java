package com.safalifter.authservice.controller;

import com.safalifter.authservice.dto.RegisterDto;
import com.safalifter.authservice.dto.TokenDto;
import com.safalifter.authservice.request.LoginRequest;
import com.safalifter.authservice.request.RegisterRequest;
import com.safalifter.authservice.service.AuthService;
import com.safalifter.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;
private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/validate-token")
    public String validateToken(@RequestHeader("Authorization") String token) {
        String email = jwtService.extractEmail(token.replace("Bearer ", ""));
        String role = jwtService.extractRole(token.replace("Bearer ", ""));

        // Create a minimal UserDetails object from the email
        UserDetails userDetails = User.withUsername(email)
                .password("") // Placeholder (not used)
                .roles(role) // Use the extracted role
                .build();

        if (jwtService.validateToken(token.replace("Bearer ", ""), userDetails)) {
            return role;
        } else {
            throw new RuntimeException("Invalid or expired token");
        }
    }
    @GetMapping("/user-role")
    public String validateRole(@RequestHeader("Authorization") String token) {
        String email = jwtService.extractEmail(token.replace("Bearer ", ""));

        // Extraire le rôle après authentification
        String role = authService.getUserRole(email);

        return role;
    }
}
