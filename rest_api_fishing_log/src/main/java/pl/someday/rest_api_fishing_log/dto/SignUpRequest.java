package pl.someday.rest_api_fishing_log.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record SignUpRequest(
    @Valid

    @NotBlank(message = "First name cannot be blank")
    String firstName,
    @NotBlank(message = "Last name cannot be blank")
    String lastName,
    @Email(message = "Email should be valid")
    String username,
    @NotBlank
    @Size(min = 8, max = 30, message = "Password must be between 6 and 30 characters long")
    String password) {
} 
