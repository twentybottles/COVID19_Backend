package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class CsrfCookieFilter extends OncePerRequestFilter {

    private static final String CSRF_COOKIE_NAME = "_ctkn";

    private static final String CSRF_COOKIE_PATH = "/";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    	
        final CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (csrf != null) {
        	
            final String token = csrf.getToken();
            Cookie cookieToken = WebUtils.getCookie(request, CSRF_COOKIE_NAME);
            
            if (cookieToken == null || token != null && !token.equals(cookieToken.getValue())) {
            	
            	cookieToken = new Cookie(CSRF_COOKIE_NAME, token);
            	cookieToken.setPath(CSRF_COOKIE_PATH);
                response.addCookie(cookieToken);
                
            }

        }

        filterChain.doFilter(request, response);
    }
}
