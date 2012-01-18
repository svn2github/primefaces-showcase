package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AjaxifyKeyEventsIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("ajaxifyKeyEvents.jsf");
	private WebElement txtFirstName;
	private WebElement txtSurname;
	private WebElement lblFirstName;
	private WebElement lblSurname;
	private final String firstName = "Omer";
	private final String surname = "GOK";

	@Before
	public void init() {
		driver.get(testUrl);
		txtFirstName = findElementById("form:firstname");
		txtSurname = findElementById("form:surname");
	}

	@Test
	public void shouldChangeLabels() {
		txtFirstName.sendKeys(firstName);
		waitUntilAjaxRequestCompletes();
		lblFirstName = findElementById("form:out1");
		assertTrue(lblFirstName.getText().equals(firstName));
		txtSurname.sendKeys(surname);
		txtFirstName.click();
		waitUntilAjaxRequestCompletes();
		lblSurname = findElementById("form:out2");
		assertTrue(lblSurname.getText().equals(surname));
	}

}
