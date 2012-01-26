package com.prime.showcase.integration.charts;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.prime.showcase.integration.AbstractIntegrationTest;
public class MeterGaugeChartIntegrationTest extends AbstractIntegrationTest{

	@Before
	public void init() {
		driver.get(toShowcaseUrl("meterGaugeChart.jsf"));
	}
	
	@Test
	public void chartsMustBeExist(){
		assertTrue(findElementById("sample").findElements(By.className("jqplot-meterGauge-tick")).size() == 5);
		assertTrue(findElementById("sample").findElement(By.className("jqplot-meterGauge-label")).getText().equals("km/h"));
		assertTrue(findElementById("custom").findElement(By.className("jqplot-meterGauge-label")).getText().equals("km/h"));
		
	}
}
