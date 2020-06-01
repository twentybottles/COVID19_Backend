package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.LoginRepository;

@Service
public class SignupSearchService {

    @Autowired
    private LoginRepository repository;
    
    @Transactional
	public boolean existsByEmail(String email) {
		
		return repository.existsByEmail(email);
			
	}
	
}