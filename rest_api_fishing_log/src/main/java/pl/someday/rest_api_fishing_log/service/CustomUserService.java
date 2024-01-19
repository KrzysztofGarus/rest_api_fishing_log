package pl.someday.rest_api_fishing_log.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserService {

    UserDetailsService userDetailsService();

    boolean isUsernameAlreadySigned(String username);

    boolean doesUserExist(String username);

}
