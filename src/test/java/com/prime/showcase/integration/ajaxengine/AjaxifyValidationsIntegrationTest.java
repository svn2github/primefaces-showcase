package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AjaxifyValidationsIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("ajaxifyValidations.jsf");
	private WebElement lblFirstName;
	private WebElement txtFirstName;
	private WebElement lblErrMessage;

	@Before
	public void init() {
		driver.get(testUrl);
		lblFirstName = findElementById("form:lblFirstName");
		txtFirstName = findElementById("form:firstname");
	}

	@Test
	public void shouldShowErrorIconForMinimumLengthOnBlur() {
		txtFirstName.sendKeys("Omer");
		lblFirstName.click();
		waitUntilAjaxRequestCompletes();
		lblErrMessage = findElementById("form:msg").findElement(
				By.tagName("span"));
		assertTrue(lblErrMessage.getAttribute("class").equals(
				"ui-message-error-icon"));
	}
}
