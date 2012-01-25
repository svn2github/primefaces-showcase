package com.prime.showcase.integration.datatable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public abstract class AbstractDataTableIntegrationTest extends AbstractIntegrationTest{
	protected static final String ROW_CLASS = ".ui-widget-content";
	protected static final String COLUMN_CLASS = ".ui-dt-c";
	protected static final int END_COLUMN = 4;
	protected static final int FIRST_COLUMN = 0;

	
	protected enum Columns {
	    MODEL, YEAR, MANUFACTURER, COLOR,
	}
	
	protected List<String> getTableColumns(List<WebElement> rows, int column) {
		List<String> columns = new ArrayList<String>();
		String eachItem;
		WebElement eachElement;
		for (WebElement eachRow : rows) {
			eachElement = eachRow.findElements(By.cssSelector(COLUMN_CLASS)).get(column);
			eachItem = eachElement.getText();
			columns.add(eachItem);
		}
		return columns;
	}
	
	protected String getItemByColumnAndRow(List<WebElement> rows,int rowIndex, int columnIndex) {
		return rows.get(rowIndex).findElements(By.cssSelector(COLUMN_CLASS)).get(columnIndex).getText();
	}
	
	protected List<String> getRowByRowIndex(List<WebElement> rows, int rowIndex,int firstColumn, int endColumn) {
		List<String> items = new ArrayList<String>();
		String eachItem;
		for (int columnIndex = firstColumn; columnIndex < endColumn; columnIndex++) {
			eachItem = getItemByColumnAndRow(rows, rowIndex, columnIndex);
			items.add(eachItem);
		}
		return items;
	}
	
	protected List<String> getModels(List<WebElement> rows) {
		return getTableColumns(rows, Columns.MODEL.ordinal());
	}
	
	protected List<String> getYears(List<WebElement> rows) {
		return getTableColumns(rows, Columns.YEAR.ordinal());
	}

	protected List<String> getManufacturers(List<WebElement> rows) {
		return getTableColumns(rows, Columns.MANUFACTURER.ordinal());
	}
	
	protected List<String> getColors(List<WebElement> rows) {
		return getTableColumns(rows, Columns.COLOR.ordinal());
	}
	
	protected int getAnotherRandomNumber(int currentNumber, int max) {
		int randomNumber = new Random().nextInt(max);
		if(randomNumber == currentNumber) {
			getAnotherRandomNumber(currentNumber, max);
		}
		return randomNumber;
	}
	
}
