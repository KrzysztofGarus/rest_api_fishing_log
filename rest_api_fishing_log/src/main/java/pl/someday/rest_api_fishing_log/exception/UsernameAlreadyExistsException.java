package pl.someday.rest_api_fishing_log.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UsernameAlreadyExistsException extends DataIntegrityViolationException {

    public UsernameAlreadyExistsException() {
        super("Username already exists");
    }
}