package com.prime.showcase.integration.charts;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class LineChartIntegrationTest extends AbstractIntegrationTest{
	
	@Before
	public void init() {
		driver.get(toShowcaseUrl("lineChart.jsf"));
	}
	
	@Test
	public void chartsMustBeExist(){
		findElementById("linear").findElement(By.className("jqplot-lineRenderer-highlight-canvas"));
		assertTrue(findElementById("linear").findElements(By.className("jqplot-xaxis-tick")).get(2).getText().equals("1.5"));
		assertTrue(findElementById("linear").findElements(By.className("jqplot-yaxis-tick")).get(2).getText().equals("5.0"));
		
		findElementById("category").findElement(By.className("jqplot-lineRenderer-highlight-canvas"));
		assertTrue(findElementById("category").findElements(By.className("jqplot-xaxis-tick")).get(2).getText().equals("2006"));
		assertTrue(findElementById("category").findElements(By.className("jqplot-yaxis-tick")).get(2).getText().equals("100"));
	}
}
