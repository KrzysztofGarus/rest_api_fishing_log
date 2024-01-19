package pl.someday.rest_api_fishing_log.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

}
