package com.example.demo.common;

/**
 * Constant definition
 */
public interface WebConst {

    /** ---- URLs ---- **/

	String LOGIN_AUTHENTICATION_URL = "/loginAuthentication";

    String SIGNUP_SEARCH_URL = "/signupSearch";

    String SIGNUP_REGISTER_URL = "/signupRegister";

    String SEARCH_COVID_URL = "/searchCovid";
    
    /** ---- api.covid19api.com ---- **/

    String COVID19_API_COM_URL = "https://api.covid19api.com";
    
    String SEARCH_COVID_SUMMARY_URL = COVID19_API_COM_URL + "/summary";
    

}
