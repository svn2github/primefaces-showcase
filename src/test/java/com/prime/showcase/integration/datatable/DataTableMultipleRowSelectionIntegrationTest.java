package com.prime.showcase.integration.datatable;

import org.junit.Before;

import com.prime.showcase.integration.SeleniumActionHelper;

public class DataTableMultipleRowSelectionIntegrationTest extends AbstractDataTableIntegrationTest {
	private static SeleniumActionHelper action;
	

	@Before
	public void before() {
		driver.get(toShowcaseUrl("datatableRowSelectionMultiple.jsf"));
		action = new SeleniumActionHelper(driver);
	}
	/*
	 * Shift ve Control tuslari calismadigi icin test edilemiyor.
	 * Onlar duzelince tekrar elden gecirilecek
	 * 
	 * ********************   Deniz Silahcilar ********
	 * 
	@Test
	public void shouldDisplaySelectedRows() {
		List<WebElement> rows = findElementById("form:cars_data").findElements(By.cssSelector(ROW_CLASS));
		Integer rowIndex = new Random().nextInt(rows.size());
		List<String> aRow = getRowByRowIndex(rows, rowIndex, END_COLUMN);
		rows.get(rowIndex).findElement(By.xpath("td/div")).click();
		
		Integer rowIndex2 = getAnotherRandomNumber(rowIndex, rows.size());
		List<String> aRow2 = getRowByRowIndex(rows, rowIndex2,END_COLUMN);
		action.keyDown(Keys.SHIFT);
		rows.get(rowIndex2).findElement(By.xpath("td/div")).click();
		action.keyUp(Keys.SHIFT);
		
		
	}*/
}
