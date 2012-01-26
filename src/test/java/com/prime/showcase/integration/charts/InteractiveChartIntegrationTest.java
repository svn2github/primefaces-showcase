package com.prime.showcase.integration.charts;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
public class InteractiveChartIntegrationTest extends AbstractIntegrationTest{
	private SeleniumActionHelper actionHelper;

	@Before
	public void init() {
		driver.get(toShowcaseUrl("interactiveCharts.jsf"));
		actionHelper = new SeleniumActionHelper(driver);
	}
	
	@Test
	public void shoudShowChartValues(){
		findElementById("pieChart").findElement(By.className("jqplot-pieRenderer-highlight-canvas"));
		
		WebElement chart = findElementById("pieChart").findElement(By.className("jqplot-event-canvas"));
		actionHelper.mouseHover(chart);
		actionHelper.clickOnCurrentPosition();
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.className("ui-chart-tooltip")).getAttribute("style").contains("display: block;");
			}
		});
		waitUntilElementExists(By.id("growl"));
		WebElement clickedPlace = findElementById("pieChart").findElement(By.className("ui-chart-tooltip"));
		assertTrue(clickedPlace.getText().equals("Brand 3 : 702"));
	}
}
