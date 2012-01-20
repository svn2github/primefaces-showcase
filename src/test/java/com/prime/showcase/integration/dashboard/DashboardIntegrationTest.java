package com.prime.showcase.integration.dashboard;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static junit.framework.Assert.assertTrue;

public class DashboardIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("dashboard.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldDragAndDrop() throws InterruptedException {
		WebElement sports = findElementById("form:sports_header");
		WebElement lifestyle = findElementById("form:lifestyle");
		Actions action = new Actions(driver);
		action.dragAndDrop(sports, lifestyle);
		action.build().perform();

		Thread.sleep(2000L);

		assertTrue(findElementByClass("ui-growl-message").isDisplayed());

	}

}
