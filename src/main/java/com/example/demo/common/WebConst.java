package com.example.demo.common;

/**
 * Constant definition
 */
public interface WebConst {
	
    /** ---- Function URLs ---- **/

	String LOGIN_AUTHENTICATION_URL = "/loginAuthentication";

	String LOGIN_SEARCH_NAME_URL = "/loginSearchName";

    String SIGNUP_SEARCH_URL = "/signupSearch";

    String SIGNUP_REGISTER_URL = "/signupRegister";

    String COVID_SEARCH_SUMMARY_URL = "/covidSearchSummary";

    String COVID_SEARCH_COUNTRY_URL = "/covidSearchCountry";
    
    /** ---- api.covid19api.com ---- **/

    String COVID19_API_COM_URL = "https://api.covid19api.com";
    
    String COVID19_API_SUMMARY_URL = COVID19_API_COM_URL + "/summary";
    
    String COVID19_API_TOTAL_COUNTRY_URL = COVID19_API_COM_URL + "/total/country/";
    
}
