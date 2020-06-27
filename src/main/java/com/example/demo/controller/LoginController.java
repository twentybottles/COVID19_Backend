package com.example.demo.controller;

import static com.example.demo.common.WebConst.AUTHENTICATION_URL;
import static com.example.demo.common.WebConst.LOGIN_SEARCH_INFO_URL;
import static com.example.demo.common.WebConst.PRE_LOGIN_URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;

@RestController
public class LoginController {
	
	@RequestMapping(path = PRE_LOGIN_URL, method = RequestMethod.POST)
	public boolean CsrfTokenController(HttpServletRequest request) {
	
		CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		
		String ddd = "ccc"
	    
		return "".equals(token.getToken()) ? false : true;
		
		// aaa
	
	}

	@RequestMapping(path = AUTHENTICATION_URL, method = RequestMethod.POST)
	public void authentication() {
		
		// write your code
	
	}

	@RequestMapping(path = LOGIN_SEARCH_INFO_URL, method = RequestMethod.GET)
	public UserEntity loginSearchId(@AuthenticationPrincipal UserEntity userEntity){

		return userEntity;

	}

}