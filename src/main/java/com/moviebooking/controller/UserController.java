package com.moviebooking.controller;

import com.moviebooking.dto.LoginRequest;
import com.moviebooking.dto.ResetPasswordRequest;
import com.moviebooking.dto.UserDto;
import com.moviebooking.dto.UserUpdateDto;
import com.moviebooking.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto registeredUser = userService.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserDto user = userService.login(loginRequest.getLoginId(), loginRequest.getPassword());
        return ResponseEntity.ok(user);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest resetRequest) {
        userService.resetPassword(resetRequest.getLoginId(), resetRequest.getNewPassword());
        return ResponseEntity.ok("Password updated successfully.");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestBody LoginRequest loginRequest) {
        userService.logout(loginRequest.getLoginId());
        return ResponseEntity.ok("User logged out successfully.");
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> updateUserDetails(@Valid @RequestBody UserUpdateDto dto) {
        userService.updateEmailAndContact(dto);
        return ResponseEntity.ok("User details updated successfully");
    }
    
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestParam String loginId) {
        UserDto userDto = userService.getUserProfile(loginId);
        return ResponseEntity.ok(userDto);
    }

}

