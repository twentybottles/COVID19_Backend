package com.example.demo.controller;

import static com.example.demo.common.WebConst.LOGOUT_URL;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
	
   @RequestMapping(path = LOGOUT_URL, method = RequestMethod.POST)
   public void logout() {
	   
   }

}