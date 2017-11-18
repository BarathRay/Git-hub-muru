package com.intellect.validators;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateAndTimeDeserialize extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        String date = arg0.getText();
        try {
            return new Date(format.parse(date).getTime());
        } catch (ParseException e) {
        	try {
				return new Date(format.parse(date).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
		return null;
	}

}
