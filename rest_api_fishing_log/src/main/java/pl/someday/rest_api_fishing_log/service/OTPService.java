package pl.someday.rest_api_fishing_log.service;

public interface OTPService {

    String generateOTP(String email);

    boolean validateOTP(String email, String otp);
    
}
