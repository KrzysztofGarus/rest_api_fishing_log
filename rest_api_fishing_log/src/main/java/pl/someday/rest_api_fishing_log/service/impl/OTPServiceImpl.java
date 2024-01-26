package pl.someday.rest_api_fishing_log.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.someday.rest_api_fishing_log.service.OTPService;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService{

    private final PasswordEncoder passwordEncoder;

    @Override
    public String generateOTP(String email) {
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(30);
        String otp = passwordEncoder.encode(email + expirationDate.toString());
        return otp;
    }

    @Override
    public boolean validateOTP(String email, String otp) {
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(30);
        String encodedEmailAndExpirationDate = email + expirationDate.toString();
        return passwordEncoder.matches(encodedEmailAndExpirationDate, otp);
    }
    
}
