package com.safalifter.authservice.dto;

import com.safalifter.authservice.enums.Role;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserDto {

    private String email;
    private String password;
    private String role;

}
