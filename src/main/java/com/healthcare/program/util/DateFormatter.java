package com.healthcare.program.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class DateFormatter {

	private static DatatypeFactory dataType =null;
	
	
	static {
		try {
			dataType = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException ignored) {
		}
	}
	
	public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException{
		XMLGregorianCalendar xmlGregorianCalendar=null;
		if(date != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			xmlGregorianCalendar=dataType.newXMLGregorianCalendar(format.format(date));
		}
		return xmlGregorianCalendar;
	}

}