package pl.someday.rest_api_fishing_log.dto.AuthDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewPasswordOTP(

@Valid

@NotBlank(message = "username cannot be blank")
String username,

@NotBlank(message = "OTP cannot be blank")
String token,

@NotBlank
@Size(min = 8, max = 30, message = "Password must be between 6 and 30 characters long")
String newPassword
) {
    
}
