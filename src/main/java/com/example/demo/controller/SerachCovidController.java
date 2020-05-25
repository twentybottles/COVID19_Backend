package com.example.demo.controller;

import static com.example.demo.common.WebConst.COVID_SEARCH_SUMMARY_URL;
import static com.example.demo.common.WebConst.COVID_SEARCH_COUNTRY_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CovidCountryEntity;
import com.example.demo.entity.CovidSummaryEntity;
import com.example.demo.service.SearchCovidService;

@RestController
public class SerachCovidController {
	
   @Autowired
   private SearchCovidService searchCovid;
   
   @RequestMapping(path = COVID_SEARCH_SUMMARY_URL, method = RequestMethod.GET)
   public CovidSummaryEntity searchCovidSummary(){
       
	   return searchCovid.searchCovidSummary();

   }
   
   @RequestMapping(path = COVID_SEARCH_COUNTRY_URL, method = RequestMethod.GET)
   public CovidCountryEntity[] searchCovidCountry(@RequestParam(value = "countrySlug") String countrySlug){
       
	   return searchCovid.searchCovidCountry(countrySlug);

   }
   
}