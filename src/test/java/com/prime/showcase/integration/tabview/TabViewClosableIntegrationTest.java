package com.prime.showcase.integration.tabview;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class TabViewClosableIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("tabviewClosable.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldCloseTab() throws InterruptedException{
		WebElement tabView = findElementById("form:tabView");
		List<WebElement> tabs = tabView.findElements(By.tagName("li"));
		tabs.get(1).findElement(By.className("ui-icon-close")).click();
		
		waitUntilAjaxRequestCompletes();
		List<WebElement> tabContents = findElementByClass("ui-tabs-panels").findElements(By.tagName("div"));
		tabs = tabView.findElements(By.tagName("li"));
		assertThat(tabs.size(), equalTo(2));
		assertThat(tabContents.size(), equalTo(2));
		
		assertThat(tabs.get(0).findElement(By.tagName("a")).getText(), equalTo("Godfather Part I"));
		assertThat(tabs.get(1).findElement(By.tagName("a")).getText(), equalTo("Godfather Part III"));
	}
}
