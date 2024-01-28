package pl.someday.rest_api_fishing_log.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.someday.rest_api_fishing_log.model.OTP;
import pl.someday.rest_api_fishing_log.repository.UserRepository;
import pl.someday.rest_api_fishing_log.service.OTPService;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService{

    private final UserRepository userRepository;



    @Override
    public OTP generateOTP() {
        SecureRandom random = new SecureRandom();
        OTP otp = new OTP();
        otp.setOneTimePassword(100000 + random.nextInt(900000));
        otp.setExpirationDate(LocalDateTime.now().plusMinutes(30));
        return otp;
    }

    @Override
    public boolean isValidOTP(int userToken, String username) {
        OTP userOTP = userRepository.findByUsername(username).get().getOtp();
        if(userOTP.getExpirationDate().isBefore(LocalDateTime.now())) {
            return false;
        }
        if(userToken == userOTP.getOneTimePassword()) { 
            return true;
        } else {
            return false;
        }
    }
}
