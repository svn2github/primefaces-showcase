package com.prime.showcase.integration.datatable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DataTableSummaryRowIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("datatableSummaryRow.jsf"));
	}

	@Test
	public void shouldSummarizeBasedOnCarModels() {
		groupAndVerifyColumnByHeaderAndColumnNumber("color", 3);
		groupAndVerifyColumnByHeaderAndColumnNumber("year" , 1);
	}
	
	private void groupAndVerifyColumnByHeaderAndColumnNumber(String header, int columnNumber) {
		getHeaderFor(header).click();
		waitForOneSecond();
		verifyGroupedColumnsByColumnId(columnNumber);
	}

	private void verifyGroupedColumnsByColumnId(int i) {
		System.out.println();
		List<WebElement> rows = findElementById("form:carsTable_data").findElements(By.tagName("tr"));
		String currentColor = null;
		boolean isValid = true;
		for (WebElement row : rows) {
			if (isSummaryRow(row)) {
				currentColor = null;
				continue;
			}

			String columnText = getColumnText(row, i);
			if (currentColor == null) {
				currentColor = columnText;
			} else if (!currentColor.equals(columnText)) {
				isValid = false;
				break;
			}

		}

		assertThat(isValid, equalTo(true));
	}

	private boolean isSummaryRow(WebElement row) {
		return row.getAttribute("class").contains("ui-datatable-summaryrow");
	}

	private String getColumnText(WebElement row, int i) {
		return row.findElements(By.tagName("td")).get(i).getText();
	}

	private WebElement getHeaderFor(String id) {
		return findElementById("form:carsTable:" + id + "Header").findElement(By.tagName("span"));

	}

}
