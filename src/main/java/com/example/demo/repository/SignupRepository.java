package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SignupEntity;

@Repository
public interface SignupRepository extends JpaRepository<SignupEntity, Long> {
	
	  public boolean existsByemailAddress(String emailAddress);
	
}