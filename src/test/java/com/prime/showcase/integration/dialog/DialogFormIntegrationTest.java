package com.prime.showcase.integration.dialog;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DialogFormIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("dialogForm.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldSendAjaxForm() {
		String text = "Voila!";
		String expectedText = "Hello " + text;
		WebElement showDialogButton = findElementById("showDialogButton");

		showDialogButton.click();

		WebElement firstNameInput = findElementById("form:firstname");
		
		firstNameInput.sendKeys(text);

		WebElement submitButton = findElementById("form:submitButton");

		submitButton.click();

		waitUntilElementGetsValue("display", expectedText);

	}
}
