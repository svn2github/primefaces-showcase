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

public class DonutChartIntegrationTest extends AbstractIntegrationTest{
	private SeleniumActionHelper actionHelper;
	
	@Before
	public void init() {
		driver.get(toShowcaseUrl("donutChart.jsf"));
		actionHelper = new SeleniumActionHelper(driver);
	}

	@Test
	public void chartsMustBeExist(){
		findElementById("sample").findElement(By.className("jqplot-donutRenderer-highlight-canvas"));
		findElementById("custom").findElement(By.className("jqplot-donutRenderer-highlight-canvas"));
		
		WebElement chart = findElementById("sample").findElement(By.className("jqplot-event-canvas"));
		actionHelper.mouseHover(chart);
		actionHelper.clickOnCurrentPosition();
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.className("ui-chart-tooltip")).getAttribute("style").contains("display: block;");
			}
		});
		WebElement clickedPlace = findElementById("sample").findElement(By.className("ui-chart-tooltip"));
		assertTrue(clickedPlace.getText().equals("Brand 3 : 402"));
	}
}
