package com.example.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Countries {


	@JsonProperty("Country")
    private String Country;
    
	@JsonProperty("CountryCode")
    private String CountryCode;
    
	@JsonProperty("Slug")
    private String Slug;
    
	@JsonProperty("NewConfirmed")
    private String NewConfirmed;
    
	@JsonProperty("TotalConfirmed")
    private String TotalConfirmed;
    
	@JsonProperty("NewDeaths")
    private String NewDeaths;
    
	@JsonProperty("TotalDeaths")
    private String TotalDeaths;
    
	@JsonProperty("NewRecovered")
    private String NewRecovered;
    
	@JsonProperty("TotalRecovered")
    private String TotalRecovered;
    
	@JsonProperty("Date")
    private Date date;
    
}