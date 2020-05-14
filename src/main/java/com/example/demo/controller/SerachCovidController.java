package com.example.demo.controller;

import static com.example.demo.common.WebConst.SEARCH_COVID_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CovidSummaryEntity;
import com.example.demo.service.SearchCovidService;

@RestController
public class SerachCovidController {
	
   @Autowired
   private SearchCovidService searchCovid;
   
   @RequestMapping(path = SEARCH_COVID_URL, method = RequestMethod.GET)
   public CovidSummaryEntity searchCovidSummary(){
       
	   return searchCovid.searchCovidSummary();

   }
   

}