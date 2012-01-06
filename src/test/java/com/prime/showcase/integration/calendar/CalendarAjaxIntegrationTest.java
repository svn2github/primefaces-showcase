package com.prime.showcase.integration.calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CalendarAjaxIntegrationTest extends AbstractIntegrationTest {
	
	private CalendarTestingHelper calendarTestingHelper = new CalendarTestingHelper();

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("calendarAjax.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldCalendarMakeAjaxCallback()  {
		
		WebElement inlineCalendar = findElementById("form:inlineCal_inline");

		WebElement firtCellOfSecondRow = calendarTestingHelper.getCell(inlineCalendar);

		Date expectedDate = calendarTestingHelper.getClickedDate(firtCellOfSecondRow);

		firtCellOfSecondRow.click();

		waitUntilAjaxRequestCompletes();
		
		WebElement growlMessage = driver.findElement(By.className("ui-growl-message"));
		
		String dateString = growlMessage.findElement(By.tagName("p")).getText();
		
		assertThat(calendarTestingHelper.parseDateString(dateString), equalTo(expectedDate));
		
	}
	

}
