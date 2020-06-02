package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.UserRepository;

@Service
public class SignupSearchService {

    @Autowired
    private UserRepository repository;
    
    @Transactional
	public boolean existsByUsername(String username) {
		
		return repository.existsByUsername(username);
			
	}

}