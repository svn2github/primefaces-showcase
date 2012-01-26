package com.prime.showcase.integration.datatable;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DataTableScrollingIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("datatableScrolling.jsf");

	@Before
	public void init() {
		driver.get(testUrl);
	}

	@Test
	public void shouldSetWidthAndHeightValuesForDataTables() {
		WebElement dataTable1 = findElementById("dataTable1");
		WebElement dataTable2 = findElementById("dataTable2");
		WebElement dataTable3 = findElementById("dataTable3");
		WebElement dataTable4 = findElementById("dataTable4");

		assertTrue(dataTable1
				.findElement(By.className("ui-datatable-scrollable-body"))
				.getCssValue("height").equals("150px"));

		assertTrue(dataTable2
				.findElement(By.className("ui-datatable-scrollable-body"))
				.getCssValue("width").equals("400px"));

		assertTrue(dataTable3
				.findElement(By.className("ui-datatable-scrollable-body"))
				.getCssValue("width").equals("400px")
				&& dataTable3
						.findElement(
								By.className("ui-datatable-scrollable-body"))
						.getCssValue("height").equals("150px"));

		assertTrue(dataTable4
				.findElement(By.className("ui-datatable-scrollable-body"))
				.getCssValue("height").equals("150px"));

		int size = findElementById("dataTable4_data").findElements(
				By.tagName("tr")).size();

		executeJS("$('#dataTable4 .ui-datatable-scrollable-body').scrollTop(333);");

		waitUntilAjaxRequestCompletes();

		int sizeAfterRequest = findElementById("dataTable4_data").findElements(
				By.tagName("tr")).size();

		assertTrue(sizeAfterRequest > size);

	}
}
