package com.prime.showcase.integration.charts;

import org.junit.Before;
import org.junit.Test;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class JFreeChartIntegrationTest extends AbstractIntegrationTest{

	@Before
	public void init() {
		driver.get(toShowcaseUrl("dynamicImage.jsf"));
	}
	
	@Test
	public void imagesMustBeExist(){
		findElementById("chart");
		findElementById("barcode");
		findElementById("text");
	}
}
