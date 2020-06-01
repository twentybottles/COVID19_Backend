package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {
	
	public UserEntity findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
}