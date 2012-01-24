package com.prime.showcase.integration.datatable;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public abstract class AbstractDataTableIntegrationTest extends AbstractIntegrationTest{
	
	protected enum Columns {
	    MODEL, YEAR, MANUFACTURER, COLOR,
	}
	
	protected List<String> getTableColumns(List<WebElement> rows, int column) {
		List<String> columns = new ArrayList<String>();
		String eachModel;
		WebElement eachElement;
		for (WebElement eachRow : rows) {
			eachElement = eachRow.findElements(By.cssSelector(".ui-dt-c")).get(column);
			eachModel = eachElement.getText();
			columns.add(eachModel);
		}
		return columns;
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
	
}
