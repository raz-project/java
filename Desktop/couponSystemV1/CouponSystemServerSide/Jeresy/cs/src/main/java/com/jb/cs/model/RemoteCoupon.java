package com.jb.cs.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonSetter;

//RemoteCoupon extends Coupon to use setStartDate and setEndDate from coupon model.
public class RemoteCoupon extends Coupon {

	// Took String from user for the field 'startDate'.

	@JsonSetter( "startDate")
	public void setStartDate(String startDate) {

		// take a format as i want for date
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// put the String with the formatter and use parse function to put him in the
		// formatter
		LocalDate parseStartDate = LocalDate.parse(startDate, dateFormater);
		// put the date in the filed startDate same with endDate
		setStartDate(parseStartDate);

	}

	@JsonSetter( "endDate")
	public void setEndDate(String endDate) {
		System.out.println(endDate);

		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate parseEndDate = LocalDate.parse(endDate, dateFormater);

		setEndDate(parseEndDate);

	}

}
