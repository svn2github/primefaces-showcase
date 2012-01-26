package com.prime.showcase.integration.charts;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class BarChartIntegrationTest extends AbstractIntegrationTest{

	@Before
	public void init() {
		driver.get(toShowcaseUrl("barChart.jsf"));
	}
	
	@Test
	public void chartsMustBeExist(){
		findElementById("basic").findElement(By.className("jqplot-barRenderer-highlight-canvas"));
		assertTrue(findElementById("basic").findElements(By.className("jqplot-xaxis-tick")).get(1).getText().equals("2005"));
		assertTrue(findElementById("basic").findElements(By.className("jqplot-yaxis-tick")).get(1).getText().equals("50"));
		
		findElementById("horizontal").findElement(By.className("jqplot-barRenderer-highlight-canvas"));
		assertTrue(findElementById("horizontal").findElements(By.className("jqplot-xaxis-tick")).get(1).getText().equals("22.222"));
		assertTrue(findElementById("horizontal").findElements(By.className("jqplot-yaxis-tick")).get(1).getText().equals("2005"));
		
		findElementById("stacked").findElement(By.className("jqplot-barRenderer-highlight-canvas"));
		assertTrue(findElementById("stacked").findElements(By.className("jqplot-xaxis-tick")).get(1).getText().equals("2005"));
		assertTrue(findElementById("stacked").findElements(By.className("jqplot-yaxis-tick")).get(1).getText().equals("75"));
	}
}
