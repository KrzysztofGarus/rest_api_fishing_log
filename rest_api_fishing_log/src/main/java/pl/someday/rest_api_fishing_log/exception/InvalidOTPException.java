package pl.someday.rest_api_fishing_log.exception;

public class InvalidOTPException extends Exception{
    
        public InvalidOTPException() {
            super("OTP is not valid");
        }

}
