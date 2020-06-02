package com.example.demo.controller;

import static com.example.demo.common.WebConst.SIGNUP_REGISTER_URL;
import static com.example.demo.common.WebConst.SIGNUP_SEARCH_USERNAME_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.SignupRegisterService;
import com.example.demo.service.SignupSearchService;

@RestController
public class SignupController {

   @Autowired
   private SignupRegisterService signupRegister;
   
   @Autowired
   private SignupSearchService signupSearch;   
   
   @RequestMapping(path = SIGNUP_SEARCH_USERNAME_URL, method = RequestMethod.POST)
   public boolean signupSearchUsername(@RequestBody String username){
       	   
	   return signupSearch.existsByUsername(username);

   }
   
   @RequestMapping(path = SIGNUP_REGISTER_URL, method = RequestMethod.POST)
   public boolean signupRegister(@RequestBody UserEntity userEntity){

	   signupRegister.register(userEntity);

	   return true;

   }

}