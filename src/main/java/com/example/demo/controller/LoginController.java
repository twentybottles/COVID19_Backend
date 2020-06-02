package com.example.demo.controller;

import static com.example.demo.common.WebConst.LOGIN_SEARCH_ID_URL;
import static com.example.demo.common.WebConst.LOGIN_SEARCH_INFO_URL;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.LoginService;

@RestController
public class LoginController {
	
   @Autowired
   private LoginService login;
   
   @RequestMapping(path = LOGIN_SEARCH_INFO_URL, method = RequestMethod.GET)
   public Optional<UserEntity> searchUserInfo(@RequestParam(value = "id") long id){

	   return login.searchUserInfo(id);
	   
   }

   @RequestMapping(path = LOGIN_SEARCH_ID_URL, method = RequestMethod.GET)
   public UserEntity loginSearchId(@AuthenticationPrincipal UserEntity userEntity){

	   return userEntity;

   }

}