package com.prime.showcase.integration.datatable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DataTableGroupingIntegrationTest extends AbstractIntegrationTest {

	List<WebElement> headerRows;
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("datatableGrouping.jsf"));
		headerRows = findElementById("form:salesTable").findElement(By.tagName("thead")).findElements(By.tagName("tr"));
	}

	@Test
	public void shouldGroupColumns() {

		WebElement titleRow = headerRows.get(0);
		verifyColumnNumber(titleRow, 1);
		verifyColumnText(titleRow, 0, "Sales/Profits of Manufacturers");
		
		WebElement manufacturerRow = headerRows.get(1);
		verifyColumnNumber(manufacturerRow, 2);
		verifyColumnText(manufacturerRow, 0, "Manufacturer");
		verifyColumnText(manufacturerRow, 1, "Sales");
		
		WebElement salesRow = headerRows.get(2);
		verifyColumnNumber(salesRow, 2);
		verifyColumnText(salesRow, 0, "Sales Count");
		verifyColumnText(salesRow, 1, "Profit");
		
		WebElement yearsRow = headerRows.get(3);
		verifyColumnNumber(yearsRow, 4);
		verifyColumnText(yearsRow, 0, "Last Year");
		verifyColumnText(yearsRow, 1, "This Year");
		verifyColumnText(yearsRow, 2, "Last Year");
		verifyColumnText(yearsRow, 3, "This Year");
	}

	private void verifyColumnText(WebElement row, int columnNumber, String expectedText) {
		assertThat(getColumnText(row, columnNumber), equalTo(expectedText));
		
	}

	private String getColumnText(WebElement row, int columnNumber) {
		return getColumns(row).get(columnNumber).getText();
	}

	private void verifyColumnNumber(WebElement row, int expectedColumnNumber) {
		assertThat(getColumns(row).size(), equalTo(expectedColumnNumber));
		
	}

	private List<WebElement> getColumns(WebElement row) {
		return row.findElements(By.tagName("th"));
	}

}
