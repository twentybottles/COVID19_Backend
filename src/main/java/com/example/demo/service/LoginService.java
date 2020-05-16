package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginEntity;
import com.example.demo.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repository;
    
	public Optional<LoginEntity> searchName(long id) {
		
		return repository.findById(id);
		
	}
      
	public List<LoginEntity> authentication(LoginEntity loginEntity) {
				
		return repository.findByMyUsernameAndMyPassword(loginEntity.getMyUsername(), loginEntity.getMyPassword());
		
	}
	
}