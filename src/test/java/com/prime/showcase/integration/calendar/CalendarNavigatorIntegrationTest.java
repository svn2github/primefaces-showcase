package com.prime.showcase.integration.calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CalendarNavigatorIntegrationTest extends AbstractIntegrationTest {

	private static final int FEBRUARY = 1;

	private CalendarTestingHelper calendarTestingHelper = new CalendarTestingHelper();

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("calendarNavigator.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldNavigateBetweenMonths() {

		WebElement calendarElement = findElementById("cal_inline");

		WebElement monthElement = findElementByClass("ui-datepicker-month");

		int selectedIndex = FEBRUARY;

		Select monthSelectionElement = new Select(monthElement);

		monthSelectionElement.selectByIndex(selectedIndex);

		Date clickedDate = calendarTestingHelper.getDateOnCell(calendarElement);

		Calendar selectedDate = calendarTestingHelper.toCalendar(clickedDate);

		assertThat(selectedDate.get(Calendar.MONTH), equalTo(selectedIndex));

	}

	@Test
	public void shouldNavigateBetweenYears() {
		
		WebElement calendarElement = findElementById("cal_inline");

		WebElement yearElement = findElementByClass("ui-datepicker-year");

		String selectedYear = "2013";

		Select yearSelectionElement = new Select(yearElement);

		yearSelectionElement.selectByVisibleText(selectedYear);

		Date clickedDate = calendarTestingHelper.getDateOnCell(calendarElement);

		Calendar selectedDate = calendarTestingHelper.toCalendar(clickedDate);

		assertThat(selectedDate.get(Calendar.YEAR), equalTo(Integer.valueOf(selectedYear)));

	}

	
}
