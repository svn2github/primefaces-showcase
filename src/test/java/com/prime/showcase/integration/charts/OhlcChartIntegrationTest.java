package com.prime.showcase.integration.charts;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class OhlcChartIntegrationTest extends AbstractIntegrationTest{

	@Before
	public void init() {
		driver.get(toShowcaseUrl("ohlcChart.jsf"));
	}
	
	@Test
	public void chartsMustBeExist(){
		assertTrue(findElementById("sample").findElements(By.className("jqplot-xaxis-tick")).get(3).getText().equals("2009"));
		assertTrue(findElementById("sample").findElements(By.className("jqplot-yaxis-tick")).get(5).getText().equals("140"));
		
		assertTrue(findElementById("candleStick").findElements(By.className("jqplot-xaxis-tick")).get(2).getText().equals("5"));
		assertTrue(findElementById("candleStick").findElements(By.className("jqplot-yaxis-tick")).get(2).getText().equals("100"));
	}
}
