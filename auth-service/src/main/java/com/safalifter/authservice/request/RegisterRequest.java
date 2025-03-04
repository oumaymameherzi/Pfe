package com.safalifter.authservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
