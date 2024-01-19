package pl.someday.rest_api_fishing_log.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUsernameNotFoundException extends UsernameNotFoundException {

    public CustomUsernameNotFoundException(String msg) {
        super("Username not found");
    }
}
