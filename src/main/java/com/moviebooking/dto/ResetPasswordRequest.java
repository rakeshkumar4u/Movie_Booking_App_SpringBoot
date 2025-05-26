package com.moviebooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @NotBlank(message = "Login ID is required")
    private String loginId;

    @NotBlank(message = "New password is required")
    private String newPassword;
}
