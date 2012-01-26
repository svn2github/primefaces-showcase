package com.prime.showcase.integration.inputtext;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class InputTextIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		driver.get(toShowcaseUrl("inputText.jsf"));
	}

	@Test
	public void shouldFocusInputText() {
		WebElement inputText = findElementById("input");
		inputText.sendKeys("Primefaces");

		assertTrue(inputText.getAttribute("class").contains("ui-state-focus"));
	}

}
