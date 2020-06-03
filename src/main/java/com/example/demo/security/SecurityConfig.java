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
import static com.example.demo.common.WebConst.LOCAL_HOST_3000;
import static com.example.demo.common.WebConst.LOGIN_SEARCH_INFO_URL;
import static com.example.demo.common.WebConst.AUTHENTICATION_URL;
import static com.example.demo.common.WebConst.LOGIN_WILDCARD_PATH;
import static com.example.demo.common.WebConst.SIGNUP_WILDCARD_PATH;
import static com.example.demo.common.WebConst.COVID_WILDCARD_PATH;
import static com.example.demo.common.WebConst.USERNAME;
import static com.example.demo.common.WebConst.PASSWORD;
import static com.example.demo.common.WebConst.GET;
import static com.example.demo.common.WebConst.POST;
import static com.example.demo.common.WebConst.CONTENT_TYPE;
import static com.example.demo.common.WebConst.USER;
import static com.example.demo.common.WebConst.SENDMAIL_PASSWORD_URL;
import static com.example.demo.common.WebConst.PASSWORD_REGISTER_URL;

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
        	.mvcMatchers(AUTHENTICATION_URL).hasRole(USER)
        	.mvcMatchers(LOGIN_WILDCARD_PATH).permitAll()
        	.mvcMatchers(SIGNUP_WILDCARD_PATH).permitAll()
        	.mvcMatchers(COVID_WILDCARD_PATH).permitAll()
        	.mvcMatchers(SENDMAIL_PASSWORD_URL).permitAll()
        	.mvcMatchers(PASSWORD_REGISTER_URL).permitAll()
            .anyRequest().authenticated()
//        .csrf().ignoringAntMatchers("/login").csrfTokenRepository(new CookieCsrfTokenRepository())
            .and() 
        .cors()
            .configurationSource(this.corsConfigurationSource())
        ;
    	
    	SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    	successHandler.setTargetUrlParameter(LOGIN_SEARCH_INFO_URL);
    	successHandler.setDefaultTargetUrl(LOGIN_SEARCH_INFO_URL);
    	successHandler.setAlwaysUseDefaultTargetUrl(true);

        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter =
            new JsonUsernamePasswordAuthenticationFilter(authenticationManager());
        jsonUsernamePasswordAuthenticationFilter.setUsernameParameter(USERNAME);
        jsonUsernamePasswordAuthenticationFilter.setPasswordParameter(PASSWORD);
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
        corsConfiguration.setAllowedOrigins(Arrays.asList(LOCAL_HOST_3000));
		corsConfiguration.setAllowedMethods(Arrays.asList(GET,POST));
		corsConfiguration.setAllowedHeaders(Arrays.asList(CONTENT_TYPE));
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