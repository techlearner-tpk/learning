package com.tushar.learning.camel.bindy;

import org.apache.camel.Exchange;
import org.apache.camel.NoTypeConversionAvailableException;
import org.apache.camel.TypeConversionException;

public class LastElementRemovalBean {
		public String removeLastElement(Exchange exchange) throws TypeConversionException, NoTypeConversionAvailableException {
		String body = exchange.getContext().getTypeConverter().mandatoryConvertTo(String.class, exchange.getIn().getBody());
		body = body.substring(0, body.lastIndexOf(","))+System.getProperty("line.separator");
		return body;
	}
}
