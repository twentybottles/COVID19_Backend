package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.LoginService;

@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {
		   
	@Autowired
	private LoginService login;
	
    @Autowired
    PasswordEncoder passwordEncoder;
	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
				
		UserEntity user = login.authentication(username);
		if (user == null) {
			throw new AuthenticationCredentialsNotFoundException("User is not Authenticated");
		}
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new UsernameNotFoundException("Username:" + username + " not found");
		}
				
		return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), grantedAuths);

	}

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}