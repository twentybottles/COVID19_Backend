package com.example.demo.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Global {

	@JsonProperty("NewConfirmed")
    private int newConfirmed;
    
	@JsonProperty("TotalConfirmed")
    private int totalConfirmed;
    
	@JsonProperty("NewDeaths")
    private int newDeaths;
    
	@JsonProperty("TotalDeaths")
    private int totalDeaths;
    
	@JsonProperty("NewRecovered")
    private int newRecovered;
    
	@JsonProperty("TotalRecovered")
    private int totalRecovered;
    
}