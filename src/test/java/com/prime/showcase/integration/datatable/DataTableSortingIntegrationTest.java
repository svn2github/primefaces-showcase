package com.prime.showcase.integration.datatable;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;


public class DataTableSortingIntegrationTest extends AbstractDataTableIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("datatableSorting.jsf");
		driver.get(testUrl);
	}
	

	@Test
	public void shouldSortModels() {
		WebElement dataTableElement = findElementById("dataTable_data");
		List<WebElement> rows = dataTableElement.findElements(By.cssSelector(".ui-widget-content"));
		assertTrue(rows.size() > 0);
		List<String> models = getTableColumns(rows, 0);
		findElementById("dataTable:modelHeader").click();
		waitUntilAjaxRequestCompletes();

		dataTableElement = findElementById("dataTable_data");
		List<WebElement> rowsAfterSorting = dataTableElement.findElements(By.cssSelector(".ui-widget-content"));
		assertTrue(rowsAfterSorting.size() > 0);
		List<String> modelsAfterSorting = getTableColumns(rowsAfterSorting, 0);
		Collections.sort(models);
		int i = 0;
		for (String str : modelsAfterSorting) {
			assertThat(str, equalTo(models.get(i)));
			i++;
		}
		
	}
}
