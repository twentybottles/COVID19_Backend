package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class PasswordRegisterService {

    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public Optional<UserEntity> findUserInfo(String emailAddress) {
    	
    	return userRepository.findByUsername(emailAddress);
    	
    }
    
    @Transactional
    public Optional<UserEntity> findUserByResetToken(String token) {
    	
    	return userRepository.findByResetToken(token);
    	
    }

    @Transactional
    public boolean register(String password, String token) {
    	
    	Optional<UserEntity> userEntity = userRepository.findByResetToken(token);
    	
    	if (userEntity.isPresent()) {
    		
    		UserEntity resetUser = userEntity.get(); 
    		
        	PasswordEncoder encoder = new BCryptPasswordEncoder(); 
        	
        	resetUser.setPassword(encoder.encode(password));
      
        	userRepository.save(resetUser);
        	
        	return true;    		

    	}
    	
    	return false;
    	
    }
    
}