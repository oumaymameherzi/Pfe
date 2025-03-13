package com.safalifter.userservice.request;

import com.safalifter.userservice.enums.Active;
import com.safalifter.userservice.enums.Role;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 6, message = "Username must be at least 6 characters")
    private String username;
    @NotNull(message = "Password is required")
    private String password;
    @Email(message = "Email should be valid")
    private String email;
    private Role role;
    private Active active;
}
