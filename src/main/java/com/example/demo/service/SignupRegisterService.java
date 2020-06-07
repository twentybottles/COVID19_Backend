package com.example.demo.service;

import static com.example.demo.common.WebConst.BCRYPT_LENGTH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class SignupRegisterService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean register(UserEntity userEntity) {
    	
    	PasswordEncoder encoder = new BCryptPasswordEncoder();   	
    	
    	if (userEntity.getPassword().length() < BCRYPT_LENGTH) {
    		
        	userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        	
    	}

    	userRepository.save(userEntity);
    	
    	return true;
    	
    }
    
}