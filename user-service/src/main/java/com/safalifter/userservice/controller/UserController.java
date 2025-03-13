package com.safalifter.userservice.controller;

import com.safalifter.userservice.dto.AuthUserDto;
import com.safalifter.userservice.dto.UserDto;
import com.safalifter.userservice.model.User;
import com.safalifter.userservice.request.RegisterRequest;
import com.safalifter.userservice.request.UserUpdateRequest;
import com.safalifter.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(modelMapper.map(userService.saveUser(request), UserDto.class));
    }

    @GetMapping("/getAll")
  // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class)).toList());
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserById(id), UserDto.class));
    }

    // In UserController.java (user-service)
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        // Explicitly set fields if ModelMapper isn't mapping them
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<AuthUserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserByUsername(username), AuthUserDto.class));
    }



    @DeleteMapping("/deleteUserById/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userService.getUserById(#id).username == principal")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/updateUserRole")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateUserRole(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newRole = request.get("role");

        if (email == null || newRole == null || newRole.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        userService.updateUserRole(email, newRole);
        return ResponseEntity.ok().build();
    }

}
