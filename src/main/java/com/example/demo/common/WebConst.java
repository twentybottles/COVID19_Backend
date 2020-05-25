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
    
    String COVID19_API_COUNTRY_URL = COVID19_API_COM_URL + "/country/";
    
	/** ---- Date Words ---- **/

	String FROM = "from";

	String TO = "to";

	int COVID19_START_YEAR = 2020;

	int COVID19_START_MONTH = 1;

	int COVID19_START_DAY = 1;
	
	/** ---- URL Parameter Words ---- **/

	String EQUAL_MARK = "=";

	String QUESION_MARK = "?";

	String AND_MARK = "&";
    
}
