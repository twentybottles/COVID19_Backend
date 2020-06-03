package com.example.demo.controller;

import static com.example.demo.common.WebConst.LOGIN_SEARCH_INFO_URL;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;

@RestController
public class LoginController {

   @RequestMapping(path = LOGIN_SEARCH_INFO_URL, method = RequestMethod.GET)
   public UserEntity loginSearchId(@AuthenticationPrincipal UserEntity userEntity){

	   return userEntity;

   }

}