package com.prime.showcase.integration.datatable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DataTableSubTableIntegrationTest extends AbstractIntegrationTest {

	private List<WebElement> tableRows;
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("datatableSubTable.jsf"));
		tableRows = findElementById("form:playersTable_data").findElements(By.tagName("tr"));
	}

	@Test
	public void shouldGroupByPlayers() {
		
		verifyPlayer(0 , "Messi"  , 168, 86);
		verifyPlayer(8 , "Xavi"   , 55 , 127);
		verifyPlayer(16, "Iniesta", 67 , 90);
	}

	private void verifyPlayer(int playerRowNumber, String expectedPlayerName, int expectedTotalGoals, int expectedTotalAssists) {

		WebElement playerRow = tableRows.get(playerRowNumber);
		verifyRowIsHeader(playerRow);
		
		String playerName = playerRow.findElement(By.tagName("div")).getText();
		assertThat(playerName, equalTo(expectedPlayerName));
		
		WebElement statsRow = tableRows.get(playerRowNumber + 7);
		verifyRowIsHeader(playerRow);
		
		int totalGoals = getTotalGoals(statsRow);
		assertThat(totalGoals, equalTo(expectedTotalGoals));
		
		int totalAssists = getTotalAssists(statsRow);
		assertThat(totalAssists, equalTo(expectedTotalAssists));
	}

	private void verifyRowIsHeader(WebElement row) {
		assertThat(row.getAttribute("class"), equalTo("ui-widget-header"));
	}

	private Integer getTotalAssists(WebElement statsRow) {
		return Integer.valueOf(getColumns(statsRow).get(2).getText());
	}

	private Integer getTotalGoals(WebElement statsRow) {
		return Integer.valueOf(getColumns(statsRow).get(1).getText());
	}

	private List<WebElement> getColumns(WebElement statsRow) {
		return statsRow.findElements(By.tagName("div"));
	}

}
