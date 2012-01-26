package com.prime.showcase.integration.charts;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AreaChartIntegrationTest extends AbstractIntegrationTest{

	@Before
	public void init() {
		driver.get(toShowcaseUrl("areaChart.jsf"));
	}
	
	@Test
	public void chartsMustBeExist(){
		findElementById("filled").findElement(By.className("jqplot-lineRenderer-highlight-canvas"));
		assertTrue(findElementById("filled").findElements(By.className("jqplot-xaxis-tick")).get(1).getText().equals("2005"));
		assertTrue(findElementById("filled").findElements(By.className("jqplot-yaxis-tick")).get(3).getText().equals("150"));
		
		findElementById("stacked").findElement(By.className("jqplot-lineRenderer-highlight-canvas"));
		assertTrue(findElementById("stacked").findElements(By.className("jqplot-xaxis-tick")).get(2).getText().equals("2006"));
		assertTrue(findElementById("stacked").findElements(By.className("jqplot-yaxis-tick")).get(1).getText().equals("0"));
	}
}
