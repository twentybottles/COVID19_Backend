package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SignupEntity;

@Repository
public interface SignupRepository extends JpaRepository<SignupEntity, Long> {
	
	List<SignupEntity> findById(String emailAddress);
	
}