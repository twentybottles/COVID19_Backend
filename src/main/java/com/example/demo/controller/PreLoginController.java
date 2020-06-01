package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreLoginController {

	@RequestMapping(path = "/prelogin", method = RequestMethod.GET)
    public String preLogin(HttpServletRequest request) {
//        DefaultCsrfToken token = (DefaultCsrfToken) request.getAttribute("_csrf");
//        if (token == null) {
//            throw new RuntimeException("could not get a token.");
//        }
//        return token.getToken();
		return "";
    }
	
}