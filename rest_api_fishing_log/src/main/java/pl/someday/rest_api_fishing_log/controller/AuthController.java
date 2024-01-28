package pl.someday.rest_api_fishing_log.controller;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pl.someday.rest_api_fishing_log.dto.AuthDTO.JWTAuthenticationResponse;
import pl.someday.rest_api_fishing_log.dto.AuthDTO.NewPasswordOTP;
import pl.someday.rest_api_fishing_log.dto.AuthDTO.SignUpRequest;
import pl.someday.rest_api_fishing_log.dto.AuthDTO.SingInRequest;
import pl.someday.rest_api_fishing_log.model.OTP;
import pl.someday.rest_api_fishing_log.model.User;
import pl.someday.rest_api_fishing_log.repository.UserRepository;
import pl.someday.rest_api_fishing_log.service.impl.AuthenticationServiceImpl;
import pl.someday.rest_api_fishing_log.service.impl.EmailServiceImpl;
import pl.someday.rest_api_fishing_log.service.impl.OTPServiceImpl;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationServiceImpl authenticationService;
    private final EmailServiceImpl emailService;
    private final OTPServiceImpl otpService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        authenticationService.signUp(signUpRequest);
        return ResponseEntity.ok().body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody @Valid SingInRequest singInRequest){
        return ResponseEntity.ok(authenticationService.signIn(singInRequest));
    }

    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email) throws Exception {
        try {
            User user = userRepository.findByUsername(email).orElseThrow();
            OTP otp = otpService.generateOTP();
            user.setOtp(otp);
            String subject = "FishigLog - password reset request";
            String content = "Your password reset code is: " + otp.getOneTimePassword();
            userRepository.save(user);
            emailService.sendEmail(email, subject, content);
            return ResponseEntity.ok().body("Password reset email sent!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error in sending password reset email");
        }
}

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid NewPasswordOTP request) throws Exception{
        try {
            User user = userRepository.findByUsername(request.username()).orElseThrow();
            int userToken = Integer.parseInt(request.token());
            String newPassword = request.newPassword();
            if (otpService.isValidOTP(userToken, request.username())){
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
            } 
            else {
                return ResponseEntity.badRequest().body("Password reset code is incorrect");
            }
            return ResponseEntity.ok().body("Password changed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Password reset failed");
        }
    }

    @GetMapping("/email-test") 
    public ResponseEntity<String> emailTest() throws Exception{
        try {
            emailService.sendEmail("krzysztof.garus@gmail.com", "Test", "Testowy email - prosze nie odpowiadać");
    }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error in sending email");
        }
        return ResponseEntity.ok().body("Email sent");
    }
}
