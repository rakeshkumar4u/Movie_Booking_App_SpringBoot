package com.moviebooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserUpdateDto {

    @NotBlank(message = "Login ID is required for identification")
    private String loginId;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Contact Number must be 10 digits")
    private String contactNumber;
}
