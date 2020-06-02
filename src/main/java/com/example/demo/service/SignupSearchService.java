package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class SignupSearchService {

    @Autowired
    private UserRepository repository;
    
    @Transactional
	public boolean existsByUsername(String username) {
		
		return repository.existsByUsername(username);
			
	}
        
    @Transactional
	public boolean existsByUsernameNotId(UserEntity userEntity) {
		
		return repository.countByUsernameAndNotId(userEntity.getUsername(), userEntity.getId()) > 0? true : false;
			
	}


}