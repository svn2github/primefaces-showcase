package com.prime.showcase.integration.calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CalendarLocalizationIntegrationTest extends AbstractIntegrationTest {

	private CalendarTestingHelper calendarTestingHelper = new CalendarTestingHelper();

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("calendarLocalization.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldLocalizeToTurkish() {
		testOn("trCal_input", toShortDateFormat(new Locale("tr")));
	}

	@Test
	public void shouldLocalizeToGerman() {
		testOn("deCal_input", toShortDateFormat(Locale.GERMAN));
	}

	@Test
	public void shouldLocalizeToPortugese() {
		testOn("ptCal_input", toShortDateFormat(new Locale("pt")));
	}

	@Test
	public void shouldLocalizeToCustomLocalization() {
		testOn("customCal_input", new SimpleDateFormat("EEE, dd MMM, yyyy"));
	}

	private DateFormat toShortDateFormat(Locale locale) {
		return SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, locale);
	}

	private void testOn(String inputFieldName, DateFormat dateFormat) {
		WebElement trInput = findElementById(inputFieldName);

		trInput.click();

		WebElement calendar = findElementById("ui-datepicker-div");

		WebElement firstCellOfSecondRow = calendarTestingHelper.getCell(calendar);

		Date clickedDate = calendarTestingHelper.getClickedDate(firstCellOfSecondRow);

		firstCellOfSecondRow.click();

		trInput = findElementById(inputFieldName);

		try {
			assertThat(dateFormat.parse(trInput.getAttribute("value")), equalTo(clickedDate));
		} catch (ParseException e) {
			throw new RuntimeException("date parsing failed");
		}
	}
}
