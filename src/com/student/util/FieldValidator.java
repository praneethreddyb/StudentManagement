package com.student.util;

import java.util.Map;

public class FieldValidator {
	public static String required(Map<String, Object> req, String msg,String... keys) {
		if (req == null || keys == null || keys.length == 0)
			return null;
		for (String k : keys) {
			Object _v = req.get(k);
			if (_v == null || _v.toString().trim().equals(""))
				return msg == null || msg.trim().equals("") ? "Required fields missing" : msg.trim();
		}
		return null;
	}
	public static String dateValidation(String date) {
	String msg = null;
	if(!date.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
		return "Incorrect Date Format";
	return msg;
	
	}
}