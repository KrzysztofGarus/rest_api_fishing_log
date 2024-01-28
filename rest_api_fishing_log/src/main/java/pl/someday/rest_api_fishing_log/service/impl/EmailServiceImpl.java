package pl.someday.rest_api_fishing_log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.someday.rest_api_fishing_log.service.EmailService;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to);
        message.setFrom("springdemoemailsender@gmail.com");
        message.setSubject(subject); 
        message.setText(content);
        javaMailSender.send(message);
    }
    
}
