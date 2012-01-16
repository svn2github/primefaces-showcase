package com.prime.showcase.integration.tabview;

import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class TabViewChangeListenerIntegrationTest extends
		AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("tabviewChangeListener.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldGrowlShownByTabChangeListener() throws InterruptedException {
		WebElement tabView = findElementById("form:tabView");
		List<WebElement> tabs = tabView.findElements(By.tagName("li"));
		tabs.get(1).click();

		waitUntilAjaxRequestCompletes();
		assertTrue(findElementByClass("ui-growl-message").isDisplayed());
	}
}
