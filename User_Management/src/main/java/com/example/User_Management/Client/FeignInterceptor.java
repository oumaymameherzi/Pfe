package com.example.User_Management.Client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = getTokenFromContext();  // Retrieve token from the current security context

        if (token != null && !token.isEmpty()) {
            template.header("Authorization", "Bearer " + token);
        }
    }

    private String getTokenFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() != null) {
            return (String) authentication.getCredentials();
        }
        return null;
    }
}
