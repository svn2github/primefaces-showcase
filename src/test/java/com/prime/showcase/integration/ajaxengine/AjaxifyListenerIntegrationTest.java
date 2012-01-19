package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AjaxifyListenerIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("ajaxifyAction.jsf");
	private WebElement txtCount;
	private WebElement lblOut;

	@Before
	public void init() {
		driver.get(testUrl);
		txtCount = findElementById("form:counter");
	}

	@Test
	public void shouldIncreaseKeyCount() {
		txtCount.sendKeys("a");
		waitUntilAjaxRequestCompletes();
		lblOut = findElementById("form:out");
		assertTrue(lblOut.getText().equals("1"));
	}

}
