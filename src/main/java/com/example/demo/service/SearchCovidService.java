package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.common.CreateUrl;
import com.example.demo.common.SearchDate;
import com.example.demo.entity.CovidCountryEntity;
import com.example.demo.entity.CovidSummaryEntity;
import static com.example.demo.common.WebConst.COVID19_API_COUNTRY_URL;
import static com.example.demo.common.WebConst.COVID19_API_SUMMARY_URL;
import static com.example.demo.common.WebConst.FROM;
import static com.example.demo.common.WebConst.TO;
import static com.example.demo.common.WebConst.COVID19_START_YEAR;
import static com.example.demo.common.WebConst.COVID19_START_MONTH;
import static com.example.demo.common.WebConst.COVID19_START_DAY;

@Service
public class SearchCovidService {

    public CovidSummaryEntity searchCovidSummary() {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	return restTemplate.getForObject(COVID19_API_SUMMARY_URL, CovidSummaryEntity.class);

    }
    

    public CovidCountryEntity[] searchCovidCountry(String countrySlug) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	return restTemplate.getForObject(createCovid19ApiCounrtyUrl(countrySlug), CovidCountryEntity[].class);

    }
    
    private String createCovid19ApiCounrtyUrl(String countrySlug) {
    	
    	CreateUrl createUrl = new CreateUrl();
    	SearchDate searchDate = new SearchDate();
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(COVID19_API_COUNTRY_URL);
    	sb.append(countrySlug);
    	sb.append(createUrl.createUrlParameter(
    			FROM,
    			searchDate.searchTargetDate(COVID19_START_YEAR, COVID19_START_MONTH, COVID19_START_DAY, "yyyy-MM-dd'T'HH:mm:ss.Z"),
    			TO,
    			searchDate.searchCurrentDate("yyyy-MM-dd'T'HH:mm:ss.Z")));

    	return new String(sb);
    	
    }
    
}