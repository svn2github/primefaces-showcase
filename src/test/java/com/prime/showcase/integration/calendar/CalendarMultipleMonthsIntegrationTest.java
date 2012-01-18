package com.prime.showcase.integration.calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CalendarMultipleMonthsIntegrationTest extends AbstractIntegrationTest {

	private CalendarTestingHelper calendarTestingHelper = new CalendarTestingHelper();

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("calendarMultipleMonths.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldDisplayMultipleMonths() {

		int firstDisplayedMonth = getMonth("ui-datepicker-group-first");
		int secondDisplayedMonth = getMonth("ui-datepicker-group-middle");
		int thirdDisplayedMonth = getMonth("ui-datepicker-group-last");
		
		assertThat((firstDisplayedMonth + 1) % 12, equalTo(secondDisplayedMonth));
		assertThat((secondDisplayedMonth + 1) % 12, equalTo(thirdDisplayedMonth));

		int first = firstDisplayedMonth;
		WebElement next = findElementByClass("ui-datepicker-next");
		next.click();
		
		firstDisplayedMonth = getMonth("ui-datepicker-group-first");
		secondDisplayedMonth = getMonth("ui-datepicker-group-middle");
		thirdDisplayedMonth = getMonth("ui-datepicker-group-last");

		assertThat((first + 1) % 12, equalTo(firstDisplayedMonth));
		assertThat((first + 2) % 12, equalTo(secondDisplayedMonth));
		assertThat((first + 3) % 12, equalTo(thirdDisplayedMonth));
	}

	private int getMonth(String calendarElementId) {
		WebElement calendar = findElementByClass(calendarElementId);
		Date dateOnCell = calendarTestingHelper.getDateOnCell(calendar);
		Calendar calendarOfDate = calendarTestingHelper.toCalendar(dateOnCell);
		return calendarOfDate.get(Calendar.MONTH);
	}

}
