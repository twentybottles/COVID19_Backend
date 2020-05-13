package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MEMBER_INFORMATION")
@Data
public class LoginEntity {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "email_address")
    private String myUsername;
	@Column(name = "password")
    private String myPassword;
	
}