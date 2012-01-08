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

public class TreeContextMenuIntegrationTest extends AbstractIntegrationTest {

	private SeleniumActionHelper action;
	
	@Before
	public void init(){
		String testUrl = toShowcaseUrl("treeContextMenu.jsf");
		driver.get(testUrl);
		action = new SeleniumActionHelper(driver);
		
		WebElement firstRow = findElementByClass("ui-tree-selectable-node");
		action.rightClick(firstRow);
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
		
		assertThat(menus.size(), equalTo(2));
		assertThat(menus.get(0).getText(), equalTo("View"));
		assertThat(menus.get(1).getText(), equalTo("Delete"));
	}
}
