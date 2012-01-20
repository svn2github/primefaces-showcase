package com.prime.showcase.integration.datalist;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.prime.showcase.integration.AbstractIntegrationTest;


public class DataListPaginationIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("dataListAjax.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldPaginate() {
	
		driver.getPageSource().contains("(1 of 10)");
		
		new Select(driver.findElement(By.cssSelector("select.ui-paginator-rpp-options"))).selectByVisibleText("15");
		findElementBySelector("option[value=\"15\"]").click();
		waitUntilAjaxRequestCompletes();
		driver.getPageSource().contains("(1 of 4)");
		
		new Select(driver.findElement(By.cssSelector("select.ui-paginator-rpp-options"))).selectByVisibleText("10");
		findElementBySelector("option[value=\"10\"]").click();
		waitUntilAjaxRequestCompletes();
		driver.getPageSource().contains("(1 of 5)");
		

	}
}
