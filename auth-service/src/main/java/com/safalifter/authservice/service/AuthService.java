package com.safalifter.authservice.service;

import com.safalifter.authservice.client.UserServiceClient;
import com.safalifter.authservice.dto.RegisterDto;
import com.safalifter.authservice.dto.TokenDto;
import com.safalifter.authservice.dto.UserDto;
import com.safalifter.authservice.exc.WrongCredentialsException;
import com.safalifter.authservice.request.LoginRequest;
import com.safalifter.authservice.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
@RequiredArgsConstructor
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public TokenDto login(LoginRequest request) {
        try {
            request.setEmail(request.getEmail().toLowerCase()); // Normalize email
            logger.info("Attempting to authenticate user: {}", request.getEmail());

            // Authenticate the user
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            if (authenticate.isAuthenticated()) {
                // Fetch the user's role
                String role = getUserRole(request.getEmail());

                // Generate the token with the role
                String token = jwtService.generateToken(request.getEmail(), role);

                logger.info("Generated token: {}", token);
                return TokenDto.builder()
                        .token(token)
                        .build();
            } else {
                throw new WrongCredentialsException("Wrong credentials");
            }
        } catch (Exception e) {
            logger.error("Login failed: {}", e.getMessage());
            throw new WrongCredentialsException("Wrong credentials");
        }
    }

    public String getUserRole(String email) {
        ResponseEntity<UserDto> user = userServiceClient.getUserByEmail(email);
        return Objects.requireNonNull(user.getBody()).getRole();
    }

    public RegisterDto register(RegisterRequest request) {
        request.setEmail(request.getEmail().toLowerCase()); // Normalize email
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        logger.info("Hashed password during registration: {}", encodedPassword);
        request.setPassword(encodedPassword);
        RegisterDto registeredUser = userServiceClient.save(request).getBody();
        logger.info("Registered User: {}", registeredUser);
        return registeredUser;
    }
}