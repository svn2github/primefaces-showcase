package com.prime.showcase.integration.filedownload;

import static junit.framework.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class FileDownloadIntegrationTest extends AbstractIntegrationTest{

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("fileDownload.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldDownlaodFile() throws InterruptedException{
		WebElement downloadLink = findElementById("form:downloadLink");
		downloadLink.click();
		
		File file = new File(System.getProperty("user.home")+File.separator+"downloaded_optimus.jpg");
		assertTrue(file.exists());
		file.delete();
	}
}
