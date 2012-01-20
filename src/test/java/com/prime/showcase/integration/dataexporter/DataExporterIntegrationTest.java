package com.prime.showcase.integration.dataexporter;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static junit.framework.Assert.assertTrue;

public class DataExporterIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("exporter.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldExportExcel() throws InterruptedException {
		WebElement downloadLink = findElementById("form:excel");
		downloadLink.click();
		
		File file = new File(System.getProperty("user.home")+File.separator+"cars.xls");
		assertTrue(file.exists());
		
		file.delete();
	}
	
	@Test
	public void shouldExportPDF() throws InterruptedException {
		WebElement downloadLink = findElementById("form:pdf");
		downloadLink.click();
		
		File file = new File(System.getProperty("user.home")+File.separator+"cars.pdf");
		assertTrue(file.exists());
		
		file.delete();
	}
	
	@Test
	public void shouldExportCSV() throws InterruptedException {
		WebElement downloadLink = findElementById("form:csv");
		downloadLink.click();
		
		File file = new File(System.getProperty("user.home")+File.separator+"cars.csv");
		assertTrue(file.exists());
		
		file.delete();
	}
	
	@Test
	public void shouldExportXML() throws InterruptedException {
		WebElement downloadLink = findElementById("form:xml");
		downloadLink.click();
		
		File file = new File(System.getProperty("user.home")+File.separator+"cars.xml");
		assertTrue(file.exists());
		
		file.delete();
	}
}
