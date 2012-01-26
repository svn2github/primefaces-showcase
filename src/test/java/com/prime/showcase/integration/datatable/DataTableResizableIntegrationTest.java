package com.prime.showcase.integration.datatable;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class DataTableResizableIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("datatableResizable.jsf");
	private SeleniumActionHelper action;

	@Before
	public void init() {
		driver.get(testUrl);
		action = new SeleniumActionHelper(driver);
	}

	@Test
	public void shouldResizeColumns() {
		WebElement modelColumn = findElementById("form:basicTable:model");
		WebElement modelColumnResizer = modelColumn.findElement(By
				.tagName("span"));
		int startWidth = Integer.parseInt(modelColumn
				.findElement(By.tagName("div")).getCssValue("width")
				.replace("px", ""));

		action.clickAndHoldOnElement(modelColumnResizer);
		Point point = modelColumnResizer.getLocation();
		action.moveByOffSet(point.x + 1, point.y);
		action.releaseMouse();

		int endWidth = Integer.parseInt(modelColumn
				.findElement(By.tagName("div")).getCssValue("width")
				.replace("px", ""));

		assertTrue(endWidth > startWidth);

		WebElement yearColumn = findElementById("form:ajaxTable:year");
		WebElement yearColumnResizer = yearColumn.findElement(By
				.tagName("span"));

		action.clickAndHoldOnElement(yearColumnResizer);
		Point location = yearColumnResizer.getLocation();
		action.moveByOffSet(point.x + 1, point.y);
		action.releaseMouse();
		waitUntilAjaxRequestCompletes();

		WebElement growlMessage = findElementByClass("ui-growl-message")
				.findElement(By.tagName("span"));
		assertTrue(growlMessage.getText().equals(
				"Column form:ajaxTable:year resized"));

	}

}
