package com.prime.showcase.integration.calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CalendarButtonPanelIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("calendarButtonPanel.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldReturnToCurrentMonthWhenTodayButtonClicked() {
		
		WebElement input = findElementById("cal_input");
		
		input.click();

		WebElement calendar = findElementById("ui-datepicker-div");

		String initialCurrentMonth = calendar.findElement(By.className("ui-datepicker-month")).getText();

		WebElement nextMonthLink = calendar.findElement(By.linkText("Next"));
		nextMonthLink.click();

		String currentMonthAfterNextButtonClicked = calendar.findElement(By.className("ui-datepicker-month")).getText();

		assertThat(currentMonthAfterNextButtonClicked, not(equalTo(initialCurrentMonth)));
		
		WebElement todayButton = calendar.findElement(By.className("ui-datepicker-buttonpane")).findElements(By.tagName("button")).get(0);
		todayButton.click();
		
		String currentMonthAfterTodayButtonClicked = calendar.findElement(By.className("ui-datepicker-month")).getText();
		
		assertThat(currentMonthAfterTodayButtonClicked, not(equalTo(currentMonthAfterNextButtonClicked)));
		
		assertThat(currentMonthAfterTodayButtonClicked, equalTo(initialCurrentMonth));

	}

}
