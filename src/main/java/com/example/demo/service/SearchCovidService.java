package com.example.demo.service;

import static com.example.demo.common.WebConst.COVID19_API_SUMMARY_URL;
import static com.example.demo.common.WebConst.COVID19_API_TOTAL_COUNTRY_URL;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.CovidCountryEntity;
import com.example.demo.entity.CovidSummaryEntity;

@Service
public class SearchCovidService {

    public CovidSummaryEntity searchCovidSummary() {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	return restTemplate.getForObject(COVID19_API_SUMMARY_URL, CovidSummaryEntity.class);

    }

    public CovidCountryEntity[] searchCovidCountry(String countrySlug) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	return restTemplate.getForObject(COVID19_API_TOTAL_COUNTRY_URL + countrySlug, CovidCountryEntity[].class);

    }
    
}