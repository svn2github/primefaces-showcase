package com.prime.showcase.integration.datatable;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class DataTableContextMenuIntegrationTest extends
		AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("datatableContextMenu.jsf");
	private SeleniumActionHelper action;

	@Before
	public void init() {
		driver.get(testUrl);
		action = new SeleniumActionHelper(driver);
	}

	@Test
	public void shouldShowContextMenuAndViewAndDeleteRow() {
		List<WebElement> allRows = findElementById("form:cars_data")
				.findElements(By.tagName("tr"));

		int firstSize = allRows.size();

		WebElement firstRow = allRows.get(0);
		String model = firstRow.findElements(By.tagName("td")).get(0)
				.findElement(By.tagName("div")).getText();
		String year = firstRow.findElements(By.tagName("td")).get(1)
				.findElement(By.tagName("div")).getText();
		String manufacturer = firstRow.findElements(By.tagName("td")).get(2)
				.findElement(By.tagName("div")).getText();
		String color = firstRow.findElements(By.tagName("td")).get(3)
				.findElement(By.tagName("div")).getText();
		firstRow.findElement(By.tagName("td")).findElement(By.tagName("div"))
				.click();
		action.rightClick(firstRow);

		WebElement contextMenu = findElementById("form:contextMenuId");

		assertTrue(contextMenu.isDisplayed());

		List<WebElement> cmds = contextMenu.findElement(By.tagName("ul"))
				.findElements(By.tagName("li"));

		cmds.get(0).findElement(By.tagName("a")).click();

		WebElement dialog = findElementById("form:dialog");

		assertTrue(dialog.isDisplayed());

		List<WebElement> result = findElementById("form:display").findElement(
				By.tagName("tbody")).findElements(By.tagName("tr"));

		assertTrue(model.equals(result.get(0).findElements(By.tagName("td"))
				.get(1).findElement(By.tagName("span")).getText()));

		assertTrue(year.equals(result.get(1).findElements(By.tagName("td"))
				.get(1).findElement(By.tagName("span")).getText()));

		assertTrue(manufacturer.equals(result.get(2)
				.findElements(By.tagName("td")).get(1)
				.findElement(By.tagName("span")).getText()));

		assertTrue(color.equals(result.get(3).findElements(By.tagName("td"))
				.get(1).findElement(By.tagName("span")).getText()));

		dialog.findElement(By.className("ui-dialog-titlebar-close")).click();

		firstRow.findElement(By.tagName("td")).findElement(By.tagName("div"))
				.click();
		action.rightClick(firstRow);

		contextMenu = findElementById("form:contextMenuId");

		cmds = contextMenu.findElement(By.tagName("ul")).findElements(
				By.tagName("li"));

		cmds.get(1).findElement(By.tagName("a")).click();
		waitUntilAjaxRequestCompletes();

		allRows = findElementById("form:cars_data").findElements(
				By.tagName("tr"));

		int finalSize = allRows.size();

		assertTrue(firstSize == finalSize + 1);
	}
}
