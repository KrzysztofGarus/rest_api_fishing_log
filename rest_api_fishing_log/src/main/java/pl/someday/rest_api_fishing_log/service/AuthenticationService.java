package pl.someday.rest_api_fishing_log.service;

import pl.someday.rest_api_fishing_log.dto.AuthDTO.JWTAuthenticationResponse;
import pl.someday.rest_api_fishing_log.dto.AuthDTO.SignUpRequest;
import pl.someday.rest_api_fishing_log.dto.AuthDTO.SingInRequest;

public interface AuthenticationService {

    void signUp(SignUpRequest signUpRequest);

    JWTAuthenticationResponse signIn(SingInRequest singInRequest);
}
