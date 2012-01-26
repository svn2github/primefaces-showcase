package com.prime.showcase.integration.datatable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.TestingUtils;

public class DataTableContextIntegrationTest extends AbstractIntegrationTest {

	private static final String CAR_MODEL_TO_FILTER = "Audi";

	@Before
	public void before() {
		driver.get(toShowcaseUrl("datatableComplex.jsf"));
	}

	@Test
	public void shouldFilterAndSortRows() {
		filterRowsByModelAndVerify();

		sortRowsByYearAndVerify();
		
		clickToFirstRowAndVerify();
	}

	private void filterRowsByModelAndVerify() {
		WebElement manufacturerFilter = findElementById("form:carsTable:manufacturer_filter");
		String carModel = CAR_MODEL_TO_FILTER;

		manufacturerFilter.sendKeys(carModel);
		waitForSeconds(2);
		verifyAllCarsAre(carModel);
	}

	private void clickToFirstRowAndVerify() {
		WebElement firstRow = getTableRows().get(0);
		clickToFirstRow(firstRow);
		
		assertThat(findElementById("form:model").getText(), equalTo(getColumnData(firstRow, 0)));
		assertThat(findElementById("form:year").getText(), equalTo(getColumnData(firstRow, 1)));
		assertThat(findElementById("form:manufacturer").getText(), equalTo(getColumnData(firstRow, 2)));
		assertThat(findElementById("form:color").getText(), equalTo(getColumnData(firstRow, 3)));
	}

	private void sortRowsByYearAndVerify() {
		WebElement yearSorter = getYearSorter();
		yearSorter.click();

		waitForSeconds(2);
		verifyAllCarsAreSortedByYear();
	}

	private void clickToFirstRow(WebElement firstRow) {
		firstRow.findElement(By.tagName("td")).click();
		waitForSeconds(2);
	}

	private void verifyAllCarsAreSortedByYear() {
		Integer year = null;
		for(WebElement row : getTableRows()) {
			Integer currentYear = TestingUtils.getInteger(getColumnData(row, 1));
			if(year == null) {
				year = currentYear;
			} else if(currentYear.compareTo(year) < 0) {
				throw new AssertionFailedError("DataTableComplex sorting failed");
			}
		}
	}

	private void verifyAllCarsAre(String carModel) {
		List<WebElement> rows = getTableRows();

		for (WebElement row : rows) {
			assertThat(getColumnData(row, 2), equalTo(carModel));
		}
	}

	private WebElement getYearSorter() {
		return findElementById("form:carsTable:year").findElement(By.className("ui-sortable-column-icon"));
	}

	private String getColumnData(WebElement row, int columnNumber) {
		return row.findElements(By.className("ui-dt-c")).get(columnNumber).getText();
	}

	private List<WebElement> getTableRows() {
		return findElementById("form:carsTable_data").findElements(By.tagName("tr"));
	}

}
