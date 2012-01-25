package com.prime.showcase.integration.datatable;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DataTableDynamicColumnsIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("datatableDynamicColumns.jsf"));
	}

	@Test
	public void shouldRemoveAndAddColumns() {
		scrollByOffset(0, 300);
		removeAllColumns();
		verifyNumberOfColumns(0);
		
		addColumnAndVerify("Audi", 1);
		scrollByOffset(0, -400);
		addColumnAndVerify("Ferrari", 2);
	}
	
	private void addColumnAndVerify(String car, int expectedColumnNumber) {
		addColumn(car);
		verifyNumberOfColumns(expectedColumnNumber);
	}
	
	private void addColumn(String car) {
		clickToElementById("form:carsTable:addButton");
		waitForOneSecond();
		selectElementByValue(findElementById("form:selectCar"), car);
		clickToElementById("form:saveButton");
		waitUntilAjaxRequestCompletes();
	}

	private void verifyNumberOfColumns(int expected) {
		assertThat(getNumberOfColumns(), equalTo(expected));
	}

	private void removeAllColumns() {
		int numberOfColumns = getNumberOfColumns();
		while(numberOfColumns-- > 0) {
			getRemoveColumnButton().click();
			waitUntilAjaxRequestCompletes();
		}
	}
	
	private int getNumberOfColumns() {
		return getButtonsInDataTable().size() - 1;
	}

	private List<WebElement> getButtonsInDataTable() {
		return findElementById("form:carsTable").findElements(By.tagName("button"));
	}

	private WebElement getRemoveColumnButton() {
		return getButtonsInDataTable().get(1);
	}
}
