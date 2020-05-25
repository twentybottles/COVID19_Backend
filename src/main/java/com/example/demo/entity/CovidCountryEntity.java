package com.example.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CovidCountryEntity {
    
	@JsonProperty("Confirmed")
    private String confirmed;
    
	@JsonProperty("Deaths")
    private String deaths;
    
	@JsonProperty("Recovered")
    private String recovered;
    
	@JsonProperty("Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    
}