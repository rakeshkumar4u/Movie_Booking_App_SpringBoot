package com.moviebooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Login ID is mandatory")
    @Size(min = 4, max = 20, message = "Login ID must be between 4 and 20 characters")
    private String loginId;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Confirm Password is mandatory")
    private String confirmPassword;

    @NotBlank(message = "Contact Number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Contact Number must be a 10-digit number")
    private String contactNumber;
}
