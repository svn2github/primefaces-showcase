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

public class DataTableContextMenuIntegrationTest extends
		AbstractIntegrationTest {

	private SeleniumActionHelper action;
	
	@Before
	public void init(){
		String testUrl = toShowcaseUrl("datatableContextMenu.jsf");
		driver.get(testUrl);
		action = new SeleniumActionHelper(driver);
		
		List<WebElement> oddRows = findElementsByClass("ui-datatable-odd");
		action.rightClick(oddRows.get(0));
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
