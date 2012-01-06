package com.prime.showcase.integration.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CalendarTestingHelper {

	public static final String DATE_FORMAT = "d/M/yyyy";
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	
	public WebElement getCell(WebElement calendar) {
		return calendar.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(1).findElement(By.tagName("td"));
	}
	
	public Date getClickedDate(WebElement element) {
		String jsCall = element.getAttribute("onclick");
		StringTokenizer jsCallTokenizer = new StringTokenizer(jsCall, ",");

		jsCallTokenizer.nextToken();
		String monthString = jsCallTokenizer.nextToken();
		String yearString = jsCallTokenizer.nextToken();
		String dayString = element.findElement(By.tagName("a")).getText();
		String dateAsString = toFormattedDateString(dayString, monthString, yearString);

		try {
			return dateFormat.parse(dateAsString);
		} catch (ParseException e) {
			throw new RuntimeException("date parsing failed " + dateAsString);
		}
	}
	
	public Date getDateOnCell(WebElement element) {
		return getClickedDate(getCell(element));
	}
	
	public String toFormattedDateString(String dayString, String monthString, String yearString) {
		return dayString + "/" + (Integer.valueOf(monthString) + 1) + "/" + yearString;
 	}
	
	public Date parseDateString(String dateAsString) {
		try {
			return dateFormat.parse(dateAsString);
		} catch (ParseException e) {
			throw new RuntimeException("date parsing failed " + dateAsString);
		}
	}
	
	public Calendar toCalendar(Date date) {
		Calendar selected = Calendar.getInstance();
		selected.setTime(date);
		return selected;
	}
}
