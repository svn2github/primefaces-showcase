package com.prime.showcase.integration.contextmenu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class BasicContextMenuIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("contextMenu.jsf");
		driver.get(testUrl);
		
		WebElement body = driver.findElement(By.tagName("body"));
		rightClick(body);
	}
	
	@Test
	public void shouldShowContextMenuWhenPageRightClicked() throws InterruptedException{
		
		WebElement contextMenu = findElementById("form:contextMenuId");
		assertThat(contextMenu.isDisplayed(), equalTo(true));
		
	}
	
	@Test
	public void shouldContextFourMenuItem(){
		WebElement contextMenu = findElementById("form:contextMenuId");
		List<WebElement> menus = contextMenu.findElements(By.className("ui-menuitem-text"));
		
		assertThat(menus.size(), equalTo(4));
		assertThat(menus.get(0).getText(), equalTo("Save"));
		assertThat(menus.get(1).getText(), equalTo("Update"));
		assertThat(menus.get(2).getText(), equalTo("Delete"));
		assertThat(menus.get(3).getText(), equalTo("Homepage"));
	}
	
}
