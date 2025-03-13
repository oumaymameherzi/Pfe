package com.example.User_Management.jwt;

import com.example.User_Management.Client.AuthServiceClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenValidationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(TokenValidationFilter.class);

    private final AuthServiceClient authServiceClient;

    public TokenValidationFilter(AuthServiceClient authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            logger.debug("JWT token extracted: {}", token);

            try {
                // Call Auth Service to validate the token and get the role
                String role = authServiceClient.validateToken("Bearer " + token);
                logger.debug("Role extracted from token: {}", role);

                // Create a UserDetails object
                UserDetails userDetails = createUserDetails(role);

                // Set the authentication in the SecurityContext
                setAuthentication(userDetails);

                logger.debug("Authentication set in SecurityContext for role: {}", role);
            } catch (Exception e) {
                logger.error("Token validation failed: {}", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                return;
            }
        } else {
            logger.warn("No JWT token found in the request");
        }

        filterChain.doFilter(request, response);
    }

    private UserDetails createUserDetails(String role) {
        return User.withUsername("user") // Use a placeholder username
                .password("") // Not used for JWT
                .roles(role)
                .build();
    }

    private void setAuthentication(UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}