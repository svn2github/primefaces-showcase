package com.prime.showcase.integration.datatable;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public abstract class AbstractDataTableIntegrationTest extends AbstractIntegrationTest{
	
	protected List<String> getTableColumns(List<WebElement> rows, Integer column) {
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
}
