package com.example.demo.controller;

import static com.example.demo.common.WebConst.PASSWORD_REGISTER_URL;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.PasswordRegisterService;

@RestController
public class PasswordController {
   
   @Autowired
   private PasswordRegisterService passwordRegisterService;
   
   @RequestMapping(path = PASSWORD_REGISTER_URL, method = RequestMethod.POST)
   public boolean signupSearchUsername(@RequestBody UserEntity user){
	   
	   Optional<UserEntity> userEntity = passwordRegisterService.findUserByResetToken(user.getResetToken());
	   
	   if (userEntity.isPresent()) {
		   
		   return passwordRegisterService.register(user.getPassword(), user.getResetToken());
	   
	   }
	   
	   return false;
	   
   }

}