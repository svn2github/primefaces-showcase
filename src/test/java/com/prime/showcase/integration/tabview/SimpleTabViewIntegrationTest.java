package com.prime.showcase.integration.tabview;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class SimpleTabViewIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("tabview.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldRenderBasicTabViewContent(){

		List<WebElement> tabs = findElementById("tabView").findElements(By.tagName("li"));
		assertTrue(tabs.get(0).getAttribute("class").contains("ui-state-active"));
		assertFalse(tabs.get(1).getAttribute("class").contains("ui-state-active"));
		assertFalse(tabs.get(2).getAttribute("class").contains("ui-state-active"));
		
		assertTrue(findElementById("tabView:tab1Img").getAttribute("src").contains("/prime-showcase/images/godfather/godfather1.jpg"));
		assertTrue(findElementById("tabView:tab2Img").getAttribute("src").contains("/prime-showcase/images/godfather/godfather2.jpg"));
		assertTrue(findElementById("tabView:tab3Img").getAttribute("src").contains("/prime-showcase/images/godfather/godfather3.jpg"));
		
		assertTrue(findElementById("tabView:tab1Text").getText().startsWith("The story begins as Don Vito Corleone"));
	}
	
	@Test
	public void shouldChangeTab(){
		List<WebElement> tabs = findElementById("tabView").findElements(By.tagName("li"));
		tabs.get(1).click();
		
		assertFalse(tabs.get(0).getAttribute("class").contains("ui-state-active"));
		assertTrue(tabs.get(1).getAttribute("class").contains("ui-state-active"));
		assertFalse(tabs.get(2).getAttribute("class").contains("ui-state-active"));
		
		assertTrue(findElementById("tabView:tab2Text").getText().startsWith("Francis Ford Coppola's legendary continuation"));
	}
}
