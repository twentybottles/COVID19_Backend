package com.example.demo.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SearchDate {

	public String searchTargetDate(int year, int month, int day, String format) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	
    	return sdf.format(calendar.getTime());
		
	}
	
	public String searchCurrentDate(String format) {
		
    	Calendar calendar = Calendar.getInstance();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	
    	return sdf.format(calendar.getTime());
		
	}
	
}
