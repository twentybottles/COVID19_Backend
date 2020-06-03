package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    
    private final JavaMailSender javaMailSender;
    
    @Autowired
    EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
      }
 
	@Async
	public void sendEmail(SimpleMailMessage email) {
		
		javaMailSender.send(email);
	
	}

}