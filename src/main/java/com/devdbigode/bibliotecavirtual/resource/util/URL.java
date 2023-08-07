package com.devdbigode.bibliotecavirtual.resource.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
    public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static Double convertPrice(String price, Double defaultPrice){

		Double value = Double.parseDouble(price); 

		if (value == null){
			return defaultPrice;
		} else {
			return value; 
		}
	}
}
