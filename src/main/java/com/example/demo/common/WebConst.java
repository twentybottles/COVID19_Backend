package com.example.demo.common;

public interface WebConst {
	
    /** ---- Function URLs ---- **/

	String AUTHENTICATION_URL = "/authentication";

	String LOGIN_SEARCH_INFO_URL = "/login/search/userInfo";

    String SIGNUP_SEARCH_USERNAME_URL = "/signup/search/username";

    String SIGNUP_REGISTER_URL = "/signup/register";

    String SIGNUP_UPDATE_URL = "/signup/update";

    String COVID_SEARCH_SUMMARY_URL = "/covid/search/summary";

    String COVID_SEARCH_COUNTRY_URL = "/covid/search/country";

    String SENDMAIL_PASSWORD_URL = "/sendMail/password";

    String PASSWORD_REGISTER_URL = "/password/register";

    /** ---- Function PATH WILDCARD ---- **/

    String LOGIN_WILDCARD_PATH = "/login/**";

    String SIGNUP_WILDCARD_PATH = "/signup/**";
 
    String COVID_WILDCARD_PATH = "/covid/**";
    
    /** ---- api.covid19api.com ---- **/

    String COVID19_API_COM_URL = "https://api.covid19api.com";
    
    String COVID19_API_SUMMARY_URL = COVID19_API_COM_URL + "/summary";
    
    String COVID19_API_TOTAL_COUNTRY_URL = COVID19_API_COM_URL + "/total/country/";
    
    /** ---- Defined Host ---- **/

    String LOCAL_HOST_3000 = "http://localhost:3000";

    /** ---- Defined Integer ---- **/

    int BCRYPT_LENGTH = 60;

    /** ---- Defined String ---- **/

    String USERNAME = "username";

    String PASSWORD = "password"; 
    
    /** ---- Defined ROLE ---- **/
    
    String ROLE_ADMIN = "ROLE_ADMIN";
    
    String ROLE_USER = "ROLE_USER";
    
    String ADMIN = "ADMIN";
    
    String USER = "USER";

    /** ---- Defined REST ---- **/

    String GET = "GET"; 

    String POST = "POST"; 

    /** ---- Defined Cookie ---- **/
    
    String CONTENT_TYPE = "Content-Type"; 
    
    
    

}
