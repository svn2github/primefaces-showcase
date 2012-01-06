package com.prime.showcase.integration.calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CalendarBasicIntegrationTest extends AbstractIntegrationTest {

	private CalendarTestingHelper calendarTestingHelper = new CalendarTestingHelper();

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("calendarBasic.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldTestInlineCalendar() {
		testCalendarBehavior("form:inlineCal_inline", "form:inlineDate");
	}

	@Test
	public void shouldTestPopupCalendar() {
		WebElement inputText = findElementById("form:popupCal_input");
		inputText.click();
		
		testCalendarBehavior("ui-datepicker-div", "form:popupDate");
	}

	@Test
	public void shouldTestPopUpButtonCalendar() {
		WebElement calendar = findElementById("form:popupButtonCal");
		WebElement popupButton = calendar.findElement(By.tagName("button"));
		popupButton.click();

		testCalendarBehavior("ui-datepicker-div", "form:popupButtonDate");
	}

	private void testCalendarBehavior(String calendarElementId, String elementIdToValidate) {
		WebElement calendar = findElementById(calendarElementId);

		WebElement firtCellOfSecondRow = calendarTestingHelper.getCell(calendar);

		Date expectedDate = calendarTestingHelper.getClickedDate(firtCellOfSecondRow);

		firtCellOfSecondRow.click();

		submitFormAndWaitUntilAjaxRequestCompletes();

		WebElement popupButtonDate = findElementById(elementIdToValidate);

		assertThat(calendarTestingHelper.parseDateString(popupButtonDate.getText()), equalTo(expectedDate));
	}

	private void submitFormAndWaitUntilAjaxRequestCompletes() {
		WebElement submitButton = findElementById("form:submitButton");

		submitButton.click();

		waitUntilAjaxRequestCompletes();
	}

}
