package com.prime.showcase.integration.log;

import org.junit.Before;
import org.junit.Test;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.junit.Assert.assertTrue;

public class LogIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("log.jsf");
		driver.get(testUrl);
        scrollByOffset(0, 310);
	}

	@Test
	public void shouldDisplayInfoMessage(){
		findElementById("info").click();
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li").getText().contains("This is an info message."));
	}
	@Test
	public void shouldDisplayWarnMessage(){
		findElementById("warn").click();
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li").getText().contains("This is a warn message."));
	}
	@Test
	public void shouldDisplayDebugMessage(){
		findElementById("debug").click();
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li").getText().contains("This is a debug message."));
	}
	@Test
	public void shouldDisplayErrorMessage(){
		findElementById("error").click();
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li").getText().contains("This is an error message."));
	}
	
	@Test
	public void shouldDisplayAjaxRequest(){
		findElementById("frm:name").clear();
		findElementById("frm:name").sendKeys("PrimeFaces");
		findElementById("frm:submit").click();
		
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li").getText().contains("Initiating ajax request."));
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li[2]").getText().contains("Form to post frm."));
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li[3]").getText().contains("URL to post /prime-showcase/ui/log.jsf"));
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li[5]").getText().contains("Response received succesfully."));
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li[6]").getText().contains("DOM is updated."));
		assertTrue(findElementByXpath("//div[@id='log']/div[2]/ul/li[7]").getText().contains("Response completed."));
		

	}
}
