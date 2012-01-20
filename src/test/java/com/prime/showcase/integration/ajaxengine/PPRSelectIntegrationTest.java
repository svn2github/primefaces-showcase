package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class PPRSelectIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("pprSelect.jsf");
	private List<WebElement> cityList;
	private List<WebElement> suburbList;

	@Before
	public void init() {
		driver.get(testUrl);
		cityList = findElementById("form:city_panel").findElement(
				By.tagName("ul")).findElements(By.tagName("li"));
	}

	@Test
	public void shouldChangeSuburbListAfterCitySelected()
			throws InterruptedException {
		// Istanbul will be selected from the city list
		findElementById("form:city").findElement(
				By.className("ui-icon-triangle-1-s")).click();
		WebElement city = cityList.get(2);
		city.click();
		waitUntilAjaxRequestCompletes();
		suburbList = findElementById("form:suburbs_panel").findElement(
				By.tagName("ul")).findElements(By.tagName("li"));
		findElementById("form:suburbs").findElement(
				By.className("ui-icon-triangle-1-s")).click();
		assertTrue(suburbList.size() == 4);
		assertTrue(suburbList.get(1).getText().equals("Levent"));
	}

	@Test
	public void shouldShowGrowlMessageOnSubmit() {
		cityList = findElementById("form:city_panel").findElement(
				By.tagName("ul")).findElements(By.tagName("li"));
		findElementById("form:city").findElement(
				By.className("ui-icon-triangle-1-s")).click();
		WebElement city = cityList.get(2);
		city.click();
		waitUntilAjaxRequestCompletes();
		suburbList = findElementById("form:suburbs_panel").findElement(
				By.tagName("ul")).findElements(By.tagName("li"));
		findElementById("form:suburbs").findElement(
				By.className("ui-icon-triangle-1-s")).click();
		WebElement suburb = suburbList.get(1);
		suburb.click();
		WebElement btnSubmit = findElementById("form:btnSubmit");
		btnSubmit.click();
		waitUntilAjaxRequestCompletes();
		WebElement growlMessage = findElementByClass("ui-growl-message")
				.findElement(By.tagName("p"));
		assertTrue(growlMessage.getText().equals(
				"City:Istanbul, Suburb: Levent"));
	}

}
