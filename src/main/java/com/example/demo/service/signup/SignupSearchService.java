package com.example.demo.service.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.SignupRepository;

@Service
public class SignupSearchService {

    @Autowired
    private SignupRepository repository;
    
	public boolean existsByemailAddress(String emailAddress) {
		
		return repository.existsByemailAddress(emailAddress);
			
	}
	
}