package com.safalifter.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.safalifter.userservice.enums.Role;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String email;
    private String password; // Ensure this is included
    private Role role;       // Ensure this is included

    // Getters and setters
}