package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CovidSummaryEntity {

	@JsonProperty("Global")
    private Global global;
    
	@JsonProperty("Countries")
    private List<Countries> countries;

	@JsonProperty("Date")
    private Date date;
	
}