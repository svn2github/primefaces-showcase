package com.prime.showcase.integration.focus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class FocusIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("focus.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldFocusFirstInputElement() throws InterruptedException{
		Thread.sleep(1000);
		WebElement activeElement = driver.switchTo().activeElement();
		assertThat(activeElement.getAttribute("id"), equalTo("form:firstname"));
	}
	
	@Test
	public void shouldFocusSecondInputAfterFirstInputValidAfterSubmit() throws InterruptedException{
		WebElement firstName = findElementById("form:firstname");
		WebElement submitButton = findElementById("form:submitButton");
		
		firstName.sendKeys("deneme");
		submitButton.click();
		
		waitUntilAjaxRequestCompletes();
		
		Thread.sleep(1000);
		WebElement activeElement = driver.switchTo().activeElement();
		assertThat(activeElement.getAttribute("id"), equalTo("form:surname"));
	}
}
