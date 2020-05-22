package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SignupEntity;
import com.example.demo.service.SignupRegisterService;
import com.example.demo.service.SignupSearchService;
import static com.example.demo.common.WebConst.SIGNUP_SEARCH_URL;
import static com.example.demo.common.WebConst.SIGNUP_REGISTER_URL;

@RestController
public class SignupController {
	
   @Autowired
   private SignupRegisterService signupRegister;
   
   @Autowired
   private SignupSearchService signupSearch;
   
   
   @RequestMapping(path = SIGNUP_SEARCH_URL, method = RequestMethod.POST)
   public boolean signupSearch(@RequestBody String emailAddress){
       	   
	   return signupSearch.existsByemailAddress(emailAddress);

   }
   
   @RequestMapping(path = SIGNUP_REGISTER_URL, method = RequestMethod.POST)
   public boolean signupRegister(@RequestBody SignupEntity signupEntity){
       
	   return signupRegister.save(signupEntity);

   }

}