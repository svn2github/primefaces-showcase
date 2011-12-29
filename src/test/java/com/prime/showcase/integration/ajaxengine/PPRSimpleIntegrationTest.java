package com.prime.showcase.integration.ajaxengine;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class PPRSimpleIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("pprUpdate.jsf");

	@Test
	public void shouldUpdateNameWithPPR() {

		final String nameToTest = "Basri";

		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.

		driver.get(testUrl);
		// Alternatively the same thing can be done like this
		// driver.navigate().to(""); 

		// Find the text input element by its name
		WebElement inputField = findElementById("form:name");

		// Enter something to search for
		inputField.sendKeys(nameToTest);
		// Now submit the form. WebDriver will find the form for us from the
		// element
		WebElement button = findElementById("form:btn");
		button.submit();

		boolean nameTextUpdated = false;
		
		waitUntilElementGetsValue("form:display", nameToTest);
		
		WebElement nameField = findElementById("form:display");
		nameTextUpdated = nameToTest.equals(nameField.getText());
		assertTrue(nameTextUpdated);
	}

}
