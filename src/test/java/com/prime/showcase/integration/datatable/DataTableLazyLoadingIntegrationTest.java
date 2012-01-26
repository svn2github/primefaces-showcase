package com.prime.showcase.integration.datatable;

import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class DataTableLazyLoadingIntegrationTest extends
		AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("datatableLazy.jsf");
	private SeleniumActionHelper action;

	@Before
	public void init() {
		driver.get(testUrl);
		action = new SeleniumActionHelper(driver);
	}

	@Test
	public void shouldChangeRows() {
		WebElement paginator = findElementById("form:carTable_paginator_top")
				.findElement(By.tagName("select"));
		List<WebElement> dataList;

		scrollToDefaults();
		paginator.click();

		paginator.findElements(By.tagName("option")).get(0).click();

		waitUntilAjaxRequestCompletes();

		dataList = findElementById("form:carTable_data").findElements(
				By.tagName("tr"));

		assertTrue(dataList.size() == 5);

		scrollToDefaults();
		paginator.click();

		paginator.findElements(By.tagName("option")).get(1).click();

		waitUntilAjaxRequestCompletes();

		dataList = findElementById("form:carTable_data").findElements(
				By.tagName("tr"));

		assertTrue(dataList.size() == 10);

		scrollToDefaults();
		paginator.click();

		paginator.findElements(By.tagName("option")).get(2).click();

		waitUntilAjaxRequestCompletes();

		dataList = findElementById("form:carTable_data").findElements(
				By.tagName("tr"));

		assertTrue(dataList.size() == 15);

		WebElement nextButton = findElementById("form:carTable_paginator_top")
				.findElement(By.className("ui-paginator-next"));
		String firstModel = dataList.get(0).findElements(By.tagName("td"))
				.get(0).getText();

		scrollToDefaults();
		nextButton.click();
		waitUntilAjaxRequestCompletes();

		dataList = findElementById("form:carTable_data").findElements(
				By.tagName("tr"));
		String txtModel = dataList.get(0).findElements(By.tagName("td")).get(0)
				.getText();

		assertTrue(!firstModel.equals(txtModel));

	}

}
