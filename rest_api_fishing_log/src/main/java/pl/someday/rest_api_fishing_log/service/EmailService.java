package pl.someday.rest_api_fishing_log.service;

public interface EmailService {

    void sendEmail(String to, String subject, String content);
    
}
