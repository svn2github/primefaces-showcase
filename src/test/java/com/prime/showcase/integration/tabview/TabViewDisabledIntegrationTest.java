package com.prime.showcase.integration.tabview;import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class TabViewDisabledIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("tabviewDisabled.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldDisableTab(){
		WebElement tabView = findElementById("tabView");
		List<WebElement> tabs = tabView.findElements(By.tagName("li"));
		
		assertFalse(tabs.get(0).getAttribute("class").contains("ui-state-disabled"));
		assertTrue(tabs.get(1).getAttribute("class").contains("ui-state-disabled"));
		assertFalse(tabs.get(2).getAttribute("class").contains("ui-state-disabled"));
	}
}
