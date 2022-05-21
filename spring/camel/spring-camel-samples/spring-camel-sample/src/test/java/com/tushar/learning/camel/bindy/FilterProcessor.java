package com.tushar.learning.camel.bindy;

import java.util.List;

import org.apache.camel.Exchange;

public class FilterProcessor {

	public boolean isStudentsNotEmpty(Exchange exchange) {
		@SuppressWarnings("unchecked")
		List<Student> records = (List<Student>) exchange.getProperty("students");
		if (records != null) {
			return !records.isEmpty();
		}
		return false;

	}

	public boolean isTeachersNotEmpty(Exchange exchange) {
		@SuppressWarnings("unchecked")
		List<Teacher> records = (List<Teacher>) exchange.getProperty("teachers");
		if (records != null) {
			return !records.isEmpty();
		}
		return false;

	}

}
