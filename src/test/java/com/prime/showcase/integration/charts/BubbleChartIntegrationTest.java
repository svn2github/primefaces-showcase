package com.prime.showcase.integration.charts;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class BubbleChartIntegrationTest extends AbstractIntegrationTest{

	@Before
	public void init() {
		driver.get(toShowcaseUrl("bubbleChart.jsf"));
	}
	
	@Test
	public void chartsMustBeExist(){
		findElementById("sample").findElement(By.className("jqplot-bubbleRenderer-highlight-canvas"));
		assertTrue(findElementById("sample").findElements(By.className("jqplot-xaxis-tick")).get(3).getText().equals("30"));
		assertTrue(findElementById("sample").findElements(By.className("jqplot-yaxis-tick")).get(3).getText().equals("150"));
		
		findElementById("custom").findElement(By.className("jqplot-bubbleRenderer-highlight-canvas"));
	}
}
