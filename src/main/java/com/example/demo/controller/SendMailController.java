package com.example.demo.controller;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.EmailServiceImpl;
import com.example.demo.service.PasswordRegisterService;
import com.example.demo.service.SignupRegisterService;
import static com.example.demo.common.WebConst.SENDMAIL_PASSWORD_URL;
import static com.example.demo.common.WebConst.LOCAL_HOST_3000;

@RestController
public class SendMailController {
   
   @Autowired
   private PasswordRegisterService passwordRegisterService; 
   
   @Autowired
   private SignupRegisterService signupRegisterService;
      
	@Autowired
	private EmailServiceImpl emailServiceImpl;
   
   @RequestMapping(path = SENDMAIL_PASSWORD_URL, method = RequestMethod.POST)
   public boolean signupSearchUsername(@RequestBody String emailAddress, HttpServletRequest request){
	   
	   Optional<UserEntity> optional = passwordRegisterService.findUserInfo(emailAddress);
	   
	   if(optional.isPresent()) {
		   
		   UserEntity userEntity = optional.get();
		   userEntity.setResetToken(UUID.randomUUID().toString());
		   
		   signupRegisterService.register(userEntity);
		   
//		   String appUrl = request.getScheme() + "://" + request.getServerName();
		   String appUrl = LOCAL_HOST_3000 + "/setting-password";
		   
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@demo.com");
			passwordResetEmail.setTo(userEntity.getUsername());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl + "/reset?token=" + userEntity.getResetToken());
			
			emailServiceImpl.sendEmail(passwordResetEmail);		   
			
			return true;

	   }
	   
	   return false;

   }

}