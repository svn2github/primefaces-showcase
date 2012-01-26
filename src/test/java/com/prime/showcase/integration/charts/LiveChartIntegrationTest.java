package com.prime.showcase.integration.charts;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
public class LiveChartIntegrationTest extends AbstractIntegrationTest{
	private SeleniumActionHelper actionHelper;

	@Before
	public void init() {
		driver.get(toShowcaseUrl("liveChart.jsf"));
		actionHelper = new SeleniumActionHelper(driver);
	}
	
	@Test
	public void shoudChangeChartValues(){
		findElementById("form:votes").findElement(By.className("jqplot-pieRenderer-highlight-canvas"));
		
		WebElement chart = findElementById("form:votes").findElement(By.className("jqplot-event-canvas"));
		actionHelper.mouseHover(chart);
		actionHelper.clickOnCurrentPosition();
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.className("ui-chart-tooltip")).getAttribute("style").contains("display: block;");
			}
		});
		WebElement clickedPlace = findElementById("form:votes").findElement(By.className("ui-chart-tooltip"));
		String firstValue = clickedPlace.getText();
		
		waitForSeconds(3);
		
		chart = findElementById("form:votes").findElement(By.className("jqplot-event-canvas"));
		actionHelper.mouseHover(chart);
		actionHelper.clickOnCurrentPosition();
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.className("ui-chart-tooltip")).getAttribute("style").contains("display: block;");
			}
		});
		WebElement secondClickedPlace = findElementById("form:votes").findElement(By.className("ui-chart-tooltip"));
		String secondValue = secondClickedPlace.getText();
		
		assertFalse(secondValue.equalsIgnoreCase(firstValue));
	}
}
