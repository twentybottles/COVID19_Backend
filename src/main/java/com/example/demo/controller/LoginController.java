package com.example.demo.controller;

import static com.example.demo.common.WebConst.LOGIN_SEARCH_NAME_URL;
import static com.example.demo.common.WebConst.LOGIN_AUTHENTICATION_URL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoginEntity;
import com.example.demo.service.LoginService;

@RestController
public class LoginController {
	
   @Autowired
   private LoginService login;   
   
   
   @RequestMapping(path = LOGIN_SEARCH_NAME_URL, method = RequestMethod.GET)
   public Optional<LoginEntity> loginSearchName(@RequestParam(value = "id") long id){

	   return login.searchName(id);

   }
   
   @RequestMapping(path = LOGIN_AUTHENTICATION_URL, method = RequestMethod.POST)
   public List<LoginEntity> authentication(@RequestBody LoginEntity loginEntity){
       	   
	   return login.authentication(loginEntity);

   }

}