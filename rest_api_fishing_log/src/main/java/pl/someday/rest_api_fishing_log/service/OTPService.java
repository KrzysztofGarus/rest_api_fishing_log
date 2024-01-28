package pl.someday.rest_api_fishing_log.service;

import pl.someday.rest_api_fishing_log.model.OTP;

public interface OTPService {

    OTP generateOTP();
    boolean isValidOTP(int userToken, String username);

}
