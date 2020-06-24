package com.example.demo.security;

import static com.example.demo.common.WebConst.AUTHENTICATION_URL;
import static com.example.demo.common.WebConst.COVID_WILDCARD_PATH;
import static com.example.demo.common.WebConst.GET;
import static com.example.demo.common.WebConst.LOCAL_HOST_3000;
import static com.example.demo.common.WebConst.LOGIN_WILDCARD_PATH;
import static com.example.demo.common.WebConst.LOGOUT_URL;
import static com.example.demo.common.WebConst.PASSWORD;
import static com.example.demo.common.WebConst.PASSWORD_REGISTER_URL;
import static com.example.demo.common.WebConst.POST;
import static com.example.demo.common.WebConst.PRE_LOGIN_URL;
import static com.example.demo.common.WebConst.SENDMAIL_PASSWORD_URL;
import static com.example.demo.common.WebConst.SIGNUP_WILDCARD_PATH;
import static com.example.demo.common.WebConst.USER;
import static com.example.demo.common.WebConst.USERNAME;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private CustomLogoutHandler customLogoutHandler;
	
    @Override
    public void configure(WebSecurity web) throws Exception {
    	
        web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "/images/**", "/fonts/**", "/shutdown" /* for Demo */);
    
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http
        .authorizeRequests()
    		.mvcMatchers(AUTHENTICATION_URL).hasRole(USER)
        	.mvcMatchers(LOGIN_WILDCARD_PATH).hasRole(USER)
        	.mvcMatchers(COVID_WILDCARD_PATH).hasRole(USER)
        	.mvcMatchers(PRE_LOGIN_URL).permitAll()
        	.mvcMatchers(SIGNUP_WILDCARD_PATH).permitAll()
        	.mvcMatchers(SENDMAIL_PASSWORD_URL).permitAll()
        	.mvcMatchers(PASSWORD_REGISTER_URL).permitAll()
            .anyRequest().authenticated()
            .and()
        .csrf()
        	.ignoringAntMatchers(PRE_LOGIN_URL)
        	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        	.and()
          .logout()
            .logoutUrl(LOGOUT_URL)
            .addLogoutHandler(customLogoutHandler)
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID", "SESSION", "remember-me")
            .permitAll()
            .and()
        .cors()
            .configurationSource(this.corsConfigurationSource())
        ;
    	
        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter =
            new JsonUsernamePasswordAuthenticationFilter(authenticationManager());
        jsonUsernamePasswordAuthenticationFilter.setUsernameParameter(USERNAME);
        jsonUsernamePasswordAuthenticationFilter.setPasswordParameter(PASSWORD);
        jsonUsernamePasswordAuthenticationFilter
        	.setAuthenticationSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK));
        jsonUsernamePasswordAuthenticationFilter
            .setAuthenticationFailureHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED));
        http.addFilterAt(jsonUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        http.exceptionHandling().accessDeniedHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_FORBIDDEN));
  
    	http.addFilterBefore(new CsrfCookieFilter(), CsrfFilter.class);

    }
    
    private CorsConfigurationSource corsConfigurationSource() {
    	
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://3.20.220.91:3000",LOCAL_HOST_3000));
		corsConfiguration.setAllowedMethods(Arrays.asList(GET,POST));
		corsConfiguration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
		corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsSource;

    }
    
    @Bean     
    public AuthenticationManager authenticationProvider() throws Exception {

    	return super.authenticationManagerBean();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
    	
        return new BCryptPasswordEncoder();
    
    }
    
}