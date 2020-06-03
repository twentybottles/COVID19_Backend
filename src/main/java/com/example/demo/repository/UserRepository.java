package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public Optional<UserEntity> findByUsername(String username);
	
	public Optional<UserEntity> findByResetToken(String resetToken);
	
	public boolean existsByUsername(String username);
	
	@Query("select count(u) from UserEntity u where u.username = :username and u.id != :id")
	int countByUsernameAndNotId(@Param("username") String username, @Param("id") long id);
	
}