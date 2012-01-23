package com.prime.showcase.integration.ajaxpoll;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AjaxPollStartStopIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("pollStartStop.jsf");
	private WebElement btnStart;
	private WebElement btnStop;
	private WebElement txtCount;

	@Before
	public void init() {
		driver.get(testUrl);
		btnStart = findElementById("form:btnStart");
		btnStop = findElementById("form:btnStop");
	}

	@Test
	public void shouldStartCounting() throws InterruptedException {
		btnStart.click();
		Thread.sleep(4001L);
		waitUntilAjaxRequestCompletes();
		txtCount = findElementById("form:txt_count");
		assertTrue(txtCount.getText().equals("1"));
	}

	@Test
	public void shouldStopCounting() throws InterruptedException {
		btnStart.click();
		Thread.sleep(4001L);
		waitUntilAjaxRequestCompletes();
		txtCount = findElementById("form:txt_count");
		String currentCount = txtCount.getText();
		btnStop.click();
		Thread.sleep(4001L);
		waitUntilAjaxRequestCompletes();
		txtCount = findElementById("form:txt_count");
		assertTrue(txtCount.getText().equals(currentCount));
	}
}
