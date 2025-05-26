package com.moviebooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Login ID is required")
    private String loginId;

    @NotBlank(message = "Password is required")
    private String password;
}

