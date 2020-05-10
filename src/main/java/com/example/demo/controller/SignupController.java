package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SignupEntity;
import com.example.demo.service.signup.SignupService;

@RestController
@RequestMapping("/signup")
public class SignupController {
	
   @Autowired
   private SignupService signupService;
   
   @RequestMapping(method = RequestMethod.POST)
   public String addAuthenticatable(SignupEntity signupEntity){
       
	   signupService.register(signupEntity);
	   
	   return "OK";

   }

}