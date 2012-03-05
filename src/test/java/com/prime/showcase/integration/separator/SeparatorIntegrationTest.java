package com.prime.showcase.integration.separator;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class SeparatorIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("separator.jsf");

	@Before
	public void init() {
		driver.get(testUrl);
	}

	@Test
	public void shouldShowSeparator() {
		WebElement separator = findElementById("separator");
		assertTrue(separator.isDisplayed());
	}

	@Test
	public void shouldShowCustomSeparator() {
		WebElement separator = findElementById("customSeparator");
		assertTrue(separator.isDisplayed());
		assertTrue(separator.getSize().width >= 500);
		assertTrue(separator.getSize().height >= 20);
	}

}
