package com.prime.showcase.integration.datatable;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class DataTableRadioCheckBoxSelectionIntegrationTest extends AbstractDataTableIntegrationTest {
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("datatableRowSelectionRadioCheckbox.jsf"));
	}
	
	@Test
	public void shouldDisplayRadioButtonSelectedRows() {
		scrollByOffset(0, 300);
		List<WebElement> rows = findElementById("form:cars_data").findElements(By.cssSelector(ROW_CLASS));
		Integer rowIndex = new Random().nextInt(rows.size());
		List<String> aRow = getRowByRowIndex(rows, rowIndex, FIRST_COLUMN + 1, END_COLUMN + 1);
		rows.get(rowIndex).findElement(By.xpath("td/div/div/div")).click();
		clickToElementById("form:cars:viewButton");
		assertTrue(isTextsPresent(findElementById("form:dialog"), aRow));
	}
	
	@Test
	public void shouldDisplayCheckBoxSelectedRows() {
		scrollByOffset(0, 700);
		List<WebElement> rows = findElementById("form:multiCars_data").findElements(By.cssSelector(ROW_CLASS));
		Integer rowIndex = new Random().nextInt(rows.size());
		String model1 = getItemByColumnAndRow(rows, rowIndex, Columns.MODEL.ordinal() + 1);
		rows.get(rowIndex).findElement(By.xpath("td/div/div/div")).click();
		scrollByOffset(0, -300);
		
		rowIndex = getAnotherRandomNumber(rowIndex, rows.size());
		String model2 = getItemByColumnAndRow(rows, rowIndex, Columns.MODEL.ordinal() + 1);
		rows.get(rowIndex).findElement(By.xpath("td/div/div/div")).click();
		clickToElementById("form:multiCars:multiViewButton");
		scrollByOffset(0, -300);
		
		assertTrue(isTextPresent(findElementById("form:multiDialog"), model1));
		assertTrue(isTextPresent(findElementById("form:multiDialog"), model2));
	}

}
