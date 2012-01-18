package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AjaxPollIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("poll.jsf");
	private WebElement txtCount;

	@Before
	public void init() {
		driver.get(testUrl);
		txtCount = findElementById("form:txt_count");
	}

	@Test
	public void shouldChangeCount() throws InterruptedException {
		assertTrue(txtCount.getText().equals("0"));
		Thread.sleep(3000);
		waitUntilAjaxRequestCompletes();
		txtCount = findElementById("form:txt_count");
		assertTrue(txtCount.getText().equals("1"));
	}

}
