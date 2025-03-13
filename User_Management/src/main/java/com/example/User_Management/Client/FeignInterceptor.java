package com.example.User_Management.Client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class FeignInterceptor implements RequestInterceptor {



    @Override
    public void apply(RequestTemplate template) {
        System.out.println("hellloooooooooooooooooo");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getCredentials() != null) {
            String token = authentication.getCredentials().toString();
            template.header("Authorization", "Bearer " + token);
           System.out.println("Feign request with Authorization header: Bearer " + token); // Debug log
        } else {
            System.out.println("No authentication found in SecurityContext"); // Debug log
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
