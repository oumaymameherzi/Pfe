package com.example.QuoteValidation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


    @Configuration
    @EnableWebSecurity
    public class QuoteValidationConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .cors().and()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ✅ Autoriser les requêtes preflight CORS
                    .antMatchers("/api/quoteValidation/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .build();
        }

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(@NonNull CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOriginPatterns("http://localhost:*", "http://127.0.0.1:*")  // ✅ Accepte localhost ET 127.0.0.1
                            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                            .allowedHeaders("*")
                            .exposedHeaders("*") // ✅ Expose les headers pour éviter les blocages
                            .allowCredentials(true); // ✅ Permet les requêtes avec cookies/sessions
                }
            };
        }
    }


