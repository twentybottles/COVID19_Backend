package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginEntity;
import com.example.demo.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repository;
    
	public List<LoginEntity> authentication(LoginEntity loginEntity) {
				
		return repository.findByMyUsernameAndMyPassword(loginEntity.getMyUsername(), loginEntity.getMyPassword());
		
	}
	
}