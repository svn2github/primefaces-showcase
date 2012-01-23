package com.prime.showcase.integration.datatable;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DataTableRowExpansionIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("datatableExpandableRows.jsf"));
	}

	@Test
	public void shouldExpandRows() {
		expandAndVerifyByRowNumber(0);
		expandAndVerifyByRowNumber(1);
	}
	
	private void expandAndVerifyByRowNumber(int rowNumber) {
		List<WebElement> trElements = getDataTableRows();
		WebElement row = trElements.get(rowNumber);
		expand(row);
		verifyExpandedRow(getModel(row), getYear(row), rowNumber);
	}

	private String getYear(WebElement row) {
		return getColumnData(row, 2);
	}

	private String getModel(WebElement row) {
		return getColumnData(row, 1);
	}

	private void verifyExpandedRow(final String expectedModel, final String expectedYear, final int expandedRowNumber) {
		waitForCondition(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.id(toModelElementId(expandedRowNumber))).getText().equals(expectedModel)
						&& driver.findElement(By.id(toYearElementId(expandedRowNumber))).getText().equals(expectedYear);
			}

			private String toYearElementId(final int expandedRowNumber) {
				return "form:carsTable:" + expandedRowNumber + ":year";
			}

			private String toModelElementId(final int expandedRowNumber) {
				return "form:carsTable:" + expandedRowNumber + ":model";
			}
		});
	}

	private String getColumnData(WebElement row, int columnNumber) {
		return row.findElements(By.className("ui-dt-c")).get(columnNumber).getText();
	}

	private void expand(WebElement row) {
		row.findElement(By.className("ui-row-toggler")).click();
	}

	private List<WebElement> getDataTableRows() {
		List<WebElement> trElements =  findElementById("form:carsTable_data").findElements(By.tagName("tr"));
		List<WebElement> rows = new ArrayList<WebElement>();
		for(WebElement tr : trElements) {
			if(tr.getAttribute("class").contains("ui-datatable-even") || tr.getAttribute("class").contains("ui-datatable-odd")) {
				rows.add(tr);
			}
		}
		return rows;
	}

}
