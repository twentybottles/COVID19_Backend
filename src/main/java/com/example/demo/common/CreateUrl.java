package com.example.demo.common;

import static com.example.demo.common.WebConst.AND_MARK;
import static com.example.demo.common.WebConst.EQUAL_MARK;
import static com.example.demo.common.WebConst.QUESION_MARK;


public class CreateUrl {
	
	public String createUrlParameter(String key1, String value1) {
		
		return QUESION_MARK + key1 + EQUAL_MARK + value1;
		
	}
	
	public String createUrlParameter(String key1, String value1, String key2, String value2) {
		
		return QUESION_MARK + key1 + EQUAL_MARK + value1 + AND_MARK + key2 + EQUAL_MARK + value2;
		
	}
	
}
