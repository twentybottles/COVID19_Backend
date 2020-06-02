package com.example.demo.common;

public interface WebConst {
	
    /** ---- Function URLs ---- **/

	String AUTHENTICATION_URL = "/authentication";
	
	String LOGIN_SEARCH_ID_URL = "/login/search/id";

	String LOGIN_SEARCH_INFO_URL = "/login/search/userInfo";

    String SIGNUP_SEARCH_USERNAME_URL = "/signup/search/username";

    String SIGNUP_REGISTER_URL = "/signup/register";

    String COVID_SEARCH_SUMMARY_URL = "/covid/search/summary";

    String COVID_SEARCH_COUNTRY_URL = "/covid/search/country";
    
    /** ---- api.covid19api.com ---- **/

    String COVID19_API_COM_URL = "https://api.covid19api.com";
    
    String COVID19_API_SUMMARY_URL = COVID19_API_COM_URL + "/summary";
    
    String COVID19_API_TOTAL_COUNTRY_URL = COVID19_API_COM_URL + "/total/country/";

    /** ---- Defined Integer ---- **/

    int BCRYPT_LENGTH = 60;

    /** ---- Defined ROLE ---- **/
    
    String ROLE_ADMIN = "ROLE_ADMIN";
    
    String ROLE_USER = "ROLE_USER";

}
