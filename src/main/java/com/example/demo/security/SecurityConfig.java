package com.example.demo.security;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "/images/**", "/fonts/**", "/shutdown" /* for Demo */);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();

    	http
        // AUTHORIZE
        .authorizeRequests()
        	.mvcMatchers("/authentication").hasRole("USER")
        	.mvcMatchers("/login/search/**").permitAll()
        	.mvcMatchers("/signup/**").permitAll()
        	.mvcMatchers("/covid/search/**").permitAll()
            .anyRequest().authenticated()
//        .csrf().ignoringAntMatchers("/login").csrfTokenRepository(new CookieCsrfTokenRepository())
            .and() 
        .cors()
            .configurationSource(this.corsConfigurationSource())
        ;
    	
    	SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    	successHandler.setTargetUrlParameter("/login/search/id");
    	successHandler.setDefaultTargetUrl("/login/search/id");
    	successHandler.setAlwaysUseDefaultTargetUrl(true);

        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter =
            new JsonUsernamePasswordAuthenticationFilter(authenticationManager());
        jsonUsernamePasswordAuthenticationFilter.setUsernameParameter("username");
        jsonUsernamePasswordAuthenticationFilter.setPasswordParameter("password");
        jsonUsernamePasswordAuthenticationFilter
        	.setAuthenticationSuccessHandler((req, res, auth) -> {res.setStatus(HttpServletResponse.SC_OK);});
        jsonUsernamePasswordAuthenticationFilter
        	.setAuthenticationSuccessHandler(successHandler);
        jsonUsernamePasswordAuthenticationFilter
            .setAuthenticationFailureHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED));
        http.addFilterAt(jsonUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        http.exceptionHandling().accessDeniedHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_FORBIDDEN));

    }
    
    private CorsConfigurationSource corsConfigurationSource() {
    	
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Content-Type"));
		corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsSource;

    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}