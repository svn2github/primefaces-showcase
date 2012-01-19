package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AjaxifyPartialSubmitIntegrationTest extends
		AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("ajaxifyPartialSubmit.jsf");
	private WebElement txtFirstName;
	private WebElement txtSurname;
	private WebElement lblOut2;
	private final String firstName = "Omer";
	private final String surname = "GOK";

	@Before
	public void init() {
		driver.get(testUrl);
		txtFirstName = findElementById("form:firstname");
		txtSurname = findElementById("form:surname");
	}

	@Test
	public void shouldChangeSurnameOutput() {
		txtFirstName.sendKeys(firstName);
		txtSurname.sendKeys(surname);
		txtFirstName.click();
		waitUntilAjaxRequestCompletes();
		lblOut2 = findElementById("form:out2");
		assertTrue(lblOut2.getText().equals(surname));
	}

}
