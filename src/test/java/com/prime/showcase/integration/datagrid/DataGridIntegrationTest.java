package com.prime.showcase.integration.datagrid;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DataGridIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("datagrid.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldPaginate() throws InterruptedException {
		driver.getPageSource().contains("(1 of 5)");
		findElementByXpath("//div[@id='form:cars_paginator_top']/span[4]/span[2]").click();
		new Select(driver.findElement(By.cssSelector("select.ui-paginator-rpp-options"))).selectByVisibleText("15");
		findElementBySelector("option[value=\"15\"]").click();
		driver.getPageSource().contains("(1 of 4)");
	}
}
