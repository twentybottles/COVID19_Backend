package com.example.demo.security;

import static com.example.demo.common.WebConst.ROLE_ADMIN;
import static com.example.demo.common.WebConst.ROLE_USER;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.example.demo.repository.UserRepository;

@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {
		   
  private final UserRepository userRepository;

  public CustomAuthenticationProvider(UserRepository userRepository) {

	  this.userRepository = userRepository;
  
  }
	
    @Autowired
    PasswordEncoder passwordEncoder;
	
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		grantedAuths.add(new SimpleGrantedAuthority(ROLE_ADMIN));
		grantedAuths.add(new SimpleGrantedAuthority(ROLE_USER));
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
				
		Optional<UserEntity> userEntity = userRepository.findByUsername(username);
		
    	if (!userEntity.isPresent()) {
    		
			throw new AuthenticationCredentialsNotFoundException("User is not Authenticated");
    		
    	}
    		
		if (password.equals(userEntity.get().getPassword()) || passwordEncoder.matches(password, userEntity.get().getPassword())) {
			
			return new UsernamePasswordAuthenticationToken(userEntity.get(), userEntity.get().getPassword(), grantedAuths);
			
		}
		
		throw new UsernameNotFoundException("Username:" + username + " not found");

	}

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}