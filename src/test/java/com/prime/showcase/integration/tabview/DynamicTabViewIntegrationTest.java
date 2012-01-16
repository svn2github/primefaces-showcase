package com.prime.showcase.integration.tabview;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DynamicTabViewIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("tabviewDynamic.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldRenderDynamicContent(){
		WebElement tabView = findElementById("form:tabView");
		List<WebElement> tabs = tabView.findElements(By.tagName("li"));
		assertTrue(tabs.get(0).getAttribute("class").contains("ui-state-active"));
		assertFalse(tabs.get(1).getAttribute("class").contains("ui-state-active"));
		assertFalse(tabs.get(2).getAttribute("class").contains("ui-state-active"));
		
		assertThat(tabView.findElements(By.tagName("img")).size(), equalTo(1));
	}
	
	@Test
	public void shouldLoadContentLazy(){
		WebElement tabView = findElementById("form:tabView");
		List<WebElement> tabs = tabView.findElements(By.tagName("li"));
		tabs.get(2).click();
		
		assertFalse(tabs.get(0).getAttribute("class").contains("ui-state-active"));
		assertFalse(tabs.get(1).getAttribute("class").contains("ui-state-active"));
		assertTrue(tabs.get(2).getAttribute("class").contains("ui-state-active"));
		
		assertThat(tabView.findElements(By.tagName("img")).size(), equalTo(2));
	}
}
