package pl.someday.rest_api_fishing_log.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pl.someday.rest_api_fishing_log.dto.JWTAuthenticationResponse;
import pl.someday.rest_api_fishing_log.dto.SignUpRequest;
import pl.someday.rest_api_fishing_log.dto.SingInRequest;
import pl.someday.rest_api_fishing_log.service.AuthenticationService;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        authenticationService.signUp(signUpRequest);
        return ResponseEntity.created(null).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody @Valid SingInRequest singInRequest){
        return ResponseEntity.ok(authenticationService.signIn(singInRequest));
    }
}
