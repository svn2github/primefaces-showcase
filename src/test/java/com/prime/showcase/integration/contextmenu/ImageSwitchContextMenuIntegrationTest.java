package com.prime.showcase.integration.contextmenu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class ImageSwitchContextMenuIntegrationTest extends AbstractIntegrationTest{

	private SeleniumActionHelper action;
	
	@Before
	public void init(){
		String testUrl = toShowcaseUrl("contextMenuAttach.jsf");
		driver.get(testUrl);
		action = new SeleniumActionHelper(driver);
		
		WebElement images = findElementById("images");
		action.rightClick(images);
	}
	
	@Test
	public void shouldShowContextMenuWhenImageRightClicked(){
		WebElement contextMenu = findElementById("contextMenuId");
		assertThat(contextMenu.isDisplayed(), equalTo(true));
	}
	
	@Test
	public void shouldContextFourMenuItem(){
		WebElement contextMenu = findElementById("contextMenuId");
		List<WebElement> menus = contextMenu.findElements(By.className("ui-menuitem-text"));
		
		assertThat(menus.size(), equalTo(3));
		assertThat(menus.get(0).getText(), equalTo("Previous"));
		assertThat(menus.get(1).getText(), equalTo("Next"));
		assertThat(menus.get(2).getText(), equalTo("Remove All"));
	}
}
