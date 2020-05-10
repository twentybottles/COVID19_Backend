package com.example.demo.service.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SignupEntity;

@Service
public class SignupService {

    @Autowired
    private SignupRegisterService register;
    
    @Autowired
    private SignupSearchService search;

    public boolean register(SignupEntity entity) {
    	
    	entity.setEmailAddress("riki.nakajima3@gmail.com");
    	entity.setFirstname("riki");
    	entity.setLastname("nakajima");
    	entity.setPassword("riki0033");
    	
    	register.save(entity);
    	return true;
    }
    
    public boolean find(String emailAddress) {
    	return search.find(emailAddress);
    }
    

}