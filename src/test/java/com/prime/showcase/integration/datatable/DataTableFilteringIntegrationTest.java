package com.prime.showcase.integration.datatable;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class DataTableFilteringIntegrationTest extends AbstractDataTableIntegrationTest {
	
	@Before
	public void init() {
		String testUrl = toShowcaseUrl("datatableFiltering.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldFilterContains() {
		WebElement filterElement = findElementById("dataTable:modelColumn_filter");
		WebElement dataTableElement = findElementById("dataTable_data");
		List<WebElement> rows = dataTableElement.findElements(By.cssSelector(".ui-widget-content"));
		assertTrue(rows.size() > 0);
		List<String> models = getTableColumns(rows, 0);
		String key = String.valueOf(models.get(0).charAt(4));
		filterElement.sendKeys(key);
		waitUntilAjaxRequestCompletes();
		
		dataTableElement = findElementById("dataTable_data");
		List<WebElement> rowsAfterFiltering = dataTableElement.findElements(By.cssSelector(".ui-widget-content"));
		List<String> modelsAfterFiltering = getTableColumns(rowsAfterFiltering, 0);
		assertTrue(modelsAfterFiltering.size() > 0);
		for (String eachString : modelsAfterFiltering) {
			assertTrue(eachString.contains(key));
		}
	}
	
	@Test
	public void shouldFilterStarsWith() {
		WebElement filterElement = findElementById("dataTable:yearColumn_filter");
		WebElement dataTableElement = findElementById("dataTable_data");
		List<WebElement> rows = dataTableElement.findElements(By.cssSelector(".ui-widget-content"));
		assertTrue(rows.size() > 0);
		List<String> years = getTableColumns(rows, 1);
		String key = String.valueOf(years.get(0).subSequence(0, 2));
		filterElement.sendKeys(key);
		waitUntilAjaxRequestCompletes();

		dataTableElement = findElementById("dataTable_data");
		List<WebElement> rowsAfterFiltering = dataTableElement.findElements(By.cssSelector(".ui-widget-content"));
		List<String> keysAfterFiltering = getTableColumns(rowsAfterFiltering, 1);
		assertTrue(keysAfterFiltering.size() > 0);
		for (String eachString : keysAfterFiltering) {
			assertTrue(eachString.startsWith(key));
		}
	}
	
	@Test
	public void shouldFilterExactItems() {
		WebElement filterSelectElement = findElementById("dataTable:ManufacturerColumn_filter");
		WebElement dataTableElement = findElementById("dataTable_data");
		List<WebElement> rows = dataTableElement.findElements(By.cssSelector(".ui-widget-content"));
		assertTrue(rows.size() > 0);
		List<String> cars = getTableColumns(rows, 2);
		
		String selectItemValue = filterSelectElement.getText().split("\n")[3];
		selectElementByValue(filterSelectElement,selectItemValue);
		waitUntilAjaxRequestCompletes();
		
		dataTableElement = findElementById("dataTable_data");
		List<WebElement> rowsAfterFiltering = dataTableElement.findElements(By.cssSelector(".ui-widget-content"));
		int rowSize = rowsAfterFiltering.size();
		if(rowSize == 1) {
			if(rowsAfterFiltering.get(0).getText().contains("with given criteria")) {
				rowSize = 0;
			}
		}
		int i=0;
		for (String eachCar : cars) {
			if(eachCar.equalsIgnoreCase(selectItemValue)) {
				i++;
			}
		}
		assertTrue(i == rowSize);
	}
	
}
