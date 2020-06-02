package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.SignupRegisterService;
import com.example.demo.service.SignupSearchService;
import static com.example.demo.common.WebConst.SIGNUP_SEARCH_USERNAME_URL;
import static com.example.demo.common.WebConst.SIGNUP_REGISTER_URL;
import static com.example.demo.common.WebConst.SIGNUP_UPDATE_URL;

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

	   return signupRegister.register(userEntity);

   }
   
   @RequestMapping(path = SIGNUP_UPDATE_URL, method = RequestMethod.POST)
   public boolean signupUpdate(@RequestBody UserEntity userEntity){
	   
	   return signupSearch.existsByUsernameNotId(userEntity)? false : signupRegister.register(userEntity);

   }

}