package com.prime.showcase.integration.datatable;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class DataTableFilteringIntegrationTest extends AbstractDataTableIntegrationTest {
	private static final String DATA_TABLE_DATA = "dataTable_data";
	@Before
	public void init() {
		String testUrl = toShowcaseUrl("datatableFiltering.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldFilterContains() {
		WebElement filterElement = findElementById("dataTable:modelColumn_filter");
		WebElement dataTableElement = findElementById(DATA_TABLE_DATA);
		List<WebElement> rows = dataTableElement.findElements(By.cssSelector(ROW_CLASS));
		assertTrue(rows.size() > 0);
		List<String> models = getModels(rows);
		String key = String.valueOf(models.get(0).charAt(4));
		filterElement.sendKeys(key);
		waitUntilAjaxRequestCompletes();
		
		dataTableElement = findElementById(DATA_TABLE_DATA);
		List<WebElement> rowsAfterFiltering = dataTableElement.findElements(By.cssSelector(ROW_CLASS));
		List<String> modelsAfterFiltering = getModels(rowsAfterFiltering);
		assertTrue(modelsAfterFiltering.size() > 0);
		for (String eachString : modelsAfterFiltering) {
			assertTrue(eachString.contains(key));
		}
	}
	
	@Test
	public void shouldFilterStarsWith() {
		WebElement filterElement = findElementById("dataTable:yearColumn_filter");
		WebElement dataTableElement = findElementById(DATA_TABLE_DATA);
		List<WebElement> rows = dataTableElement.findElements(By.cssSelector(ROW_CLASS));
		assertTrue(rows.size() > 0);
		List<String> years = getYears(rows);
		String key = String.valueOf(years.get(0).subSequence(0, 2));
		filterElement.sendKeys(key);
		waitUntilAjaxRequestCompletes();

		dataTableElement = findElementById(DATA_TABLE_DATA);
		List<WebElement> rowsAfterFiltering = dataTableElement.findElements(By.cssSelector(ROW_CLASS));
		List<String> yearsAfterFiltering = getYears(rowsAfterFiltering);
		assertTrue(yearsAfterFiltering.size() > 0);
		for (String eachString : yearsAfterFiltering) {
			assertTrue(eachString.startsWith(key));
		}
	}
	
	@Test
	public void shouldFilterExactItems() {
		WebElement filterSelectElement = findElementById("dataTable:manufacturerColumn_filter");
		WebElement dataTableElement = findElementById(DATA_TABLE_DATA);
		List<WebElement> rows = dataTableElement.findElements(By.cssSelector(ROW_CLASS));
		assertTrue(rows.size() > 0);
		List<String> cars = getManufacturers(rows);
		
		String selectItemValue = filterSelectElement.getText().split("\n")[3];
		selectElementByValue(filterSelectElement,selectItemValue);
		waitUntilAjaxRequestCompletes();
		
		dataTableElement = findElementById(DATA_TABLE_DATA);
		List<WebElement> rowsAfterFiltering = dataTableElement.findElements(By.cssSelector(ROW_CLASS));
		int rowSize = rowsAfterFiltering.size();
		rowSize = setRowSizeToZeroIfTableEmpty(rowsAfterFiltering, rowSize);
		int exactCarCount = exactCarCount(cars, selectItemValue);
		assertTrue(exactCarCount == rowSize);
	}

	private int exactCarCount(List<String> cars, String selectItemValue) {
		int count = 0;
		for (String eachCar : cars) {
			if(eachCar.equalsIgnoreCase(selectItemValue)) {
				count++;
			}
		}
		return count;
	}

	private int setRowSizeToZeroIfTableEmpty(List<WebElement> rowsAfterFiltering, int rowSize) {
		if(rowSize == 1) {
			if(rowsAfterFiltering.get(0).getText().contains("with given criteria")) {
				rowSize = 0;
			}
		}
		return rowSize;
	}
	
	@Test
	public void shouldFilterEndsWiths() {
		WebElement dataTableElement = findElementById(DATA_TABLE_DATA);
		List<WebElement> rows = dataTableElement.findElements(By.cssSelector(ROW_CLASS));
		assertTrue(rows.size() > 0);
		List<String> colors = getColors(rows);
		String searchCriteria = String.valueOf(colors.get(0).charAt(colors.get(0).length()-1));
		findElementById("dataTable:colorColumn_filter").sendKeys(searchCriteria);
		waitUntilAjaxRequestCompletes();
		
		int colorCount = getCarCount(colors, searchCriteria);
		
		dataTableElement = findElementById(DATA_TABLE_DATA);
		List<WebElement> rowsAfterFiltering = dataTableElement.findElements(By.cssSelector(ROW_CLASS));
		int rowSize = rowsAfterFiltering.size();
		assertTrue(rowSize == colorCount);
		List<String> colorsAfterFiltering = getColors(rowsAfterFiltering);
		for (String car : colorsAfterFiltering) {
			assertTrue(car.endsWith(searchCriteria));
		}
	}

	private int getCarCount(List<String> cars, String searchCriteria) {
		int count = 0;
		for (String car : cars) {
			if(car.endsWith(searchCriteria)) {
				count++;
			}
		}
		return count;
	}
	
}
