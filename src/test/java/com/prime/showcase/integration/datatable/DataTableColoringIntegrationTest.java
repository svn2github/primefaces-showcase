package com.prime.showcase.integration.datatable;

import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DataTableColoringIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("datatableColoring.jsf");

	@Before
	public void init() {
		driver.get(testUrl);
	}

	@Test
	public void shouldSetStyleForOldCars() {

		List<WebElement> allRows = getAllRows();

		for (WebElement row : allRows) {
			int year = Integer.parseInt(row.findElements(By.tagName("td"))
					.get(1).findElement(By.tagName("div")).getText());

			if (year <= 1980) {
				assertTrue(row.getAttribute("class").contains("old"));
			}
		}

	}

	private List<WebElement> getAllRows() {
		List<WebElement> allRows = new ArrayList<WebElement>();

		allRows = findElementById("form:table_data").findElements(
				By.tagName("tr"));

		return allRows;
	}

}
