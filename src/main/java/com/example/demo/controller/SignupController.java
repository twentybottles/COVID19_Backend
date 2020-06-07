package com.example.demo.controller;

import static com.example.demo.common.WebConst.SIGNUP_REGISTER_URL;
import static com.example.demo.common.WebConst.SIGNUP_SEARCH_USERNAME_URL;
import static com.example.demo.common.WebConst.SIGNUP_UPDATE_URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
   
   @Autowired
   private AuthenticationManager authenticationProvider;
   
   @RequestMapping(path = SIGNUP_SEARCH_USERNAME_URL, method = RequestMethod.POST)
   public boolean signupSearchUsername(@RequestBody String username){
       	   
	   return signupSearch.existsByUsername(username);

   }
   
   @RequestMapping(path = SIGNUP_REGISTER_URL, method = RequestMethod.POST)
   public boolean signupRegister(@RequestBody UserEntity userEntity, HttpServletResponse response){

	   if (signupRegister.register(userEntity)) {
		   
		   
		   return true;
  
	   }
	   
	   return false;

   }
   
   @RequestMapping(path = SIGNUP_UPDATE_URL, method = RequestMethod.POST)
   public boolean signupUpdate(@RequestBody UserEntity userEntity, HttpServletRequest request){
	   
	   if(signupSearch.existsByUsernameNotId(userEntity)) {return false;}
	   
			signupRegister.register(userEntity);
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword());
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authentication = authenticationProvider.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
	
			return true;

   }

}