package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;

@Service
public class SendMailService {
	
//	@Autowired
//	private EmailService emailService;
//	
//	@Async
//	public boolean sendPassword(String appUrl, UserEntity userEntity) {
//		
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setTo(userEntity.getUsername());
//		msg.setSubject("COVID19.com：Setting password URL");
//		msg.setText("To reset your password, click the link below:\n" + appUrl + "/reset?token=" + userEntity.getResetToken());
//		
//		emailService.sendEmail(msg);
//
//		return true;
//
//	}

}