package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.entity.CovidSummaryEntity;
import static com.example.demo.common.WebConst.COVID19_API_SUMMARY_URL;

@Service
public class SearchCovidService {

    public CovidSummaryEntity searchCovidSummary() {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	return restTemplate.getForObject(COVID19_API_SUMMARY_URL, CovidSummaryEntity.class);

    }
}