package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SignupEntity;
import com.example.demo.service.signup.SignupRegisterService;
import com.example.demo.service.signup.SignupSearchService;
import static com.example.demo.common.WebConst.SIGNUP_SEARCH_URL;
import static com.example.demo.common.WebConst.SIGNUP_REGISTER_URL;

@RestController
public class SignupController {
	
   @Autowired
   private SignupRegisterService signupRegister;
   
   @Autowired
   private SignupSearchService signupSearch;
   
   
   @RequestMapping(path = SIGNUP_SEARCH_URL, method = RequestMethod.POST)
   public boolean signupSearch(String emailAddress){
       
	   return signupSearch.select(emailAddress);
	   
   }
   
   @RequestMapping(path = SIGNUP_REGISTER_URL, method = RequestMethod.POST)
   public boolean signupRegister(SignupEntity signupEntity){
       
	   // 画面から値を受け取ったことを想定して一旦は値をセット
	   signupEntity.setEmailAddress("riki.nakajima4@gmail.com");
	   signupEntity.setFirstname("riki");
	   signupEntity.setLastname("nakajima");
	   signupEntity.setPassword("aaaabbbb");
	   
	   return signupRegister.save(signupEntity);

   }
   

}