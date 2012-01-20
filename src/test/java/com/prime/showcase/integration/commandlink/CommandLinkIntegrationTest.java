package com.prime.showcase.integration.commandlink;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommandLinkIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("commandLink.jsf");
		driver.get(testUrl);
	}

	
	protected void submitNotValidFormForButtom(String linkId) {
		findElementById("form:firstname").sendKeys("P");
		findElementById("form:surname").sendKeys("Faces");
		
		WebElement link = findElementById(linkId);
		link.click();
		waitUntilAjaxRequestCompletes();
		
		WebElement message = findElementById("form:messages");
		assertTrue(message.isDisplayed());
	}
	
	protected void submitValidFormFor(String buttomId) {
		findElementById("form:firstname").sendKeys("Prime");
		findElementById("form:surname").sendKeys("Faces");
		
		WebElement button = findElementById(buttomId);
		button.click();
		waitUntilAjaxRequestCompletes();
		
		assertThat(findElementById("form:txt1").getText(),equalTo("Prime"));
		assertThat(findElementById("form:txt2").getText(),equalTo("Faces"));
	}
	
	@Test
	public void shouldAjaxLinkCreateValidationError() throws InterruptedException {
		submitNotValidFormForButtom("form:ajax");
	}
	
	@Test
	public void shouldAjaxLinkSubmit() throws InterruptedException {
		submitValidFormFor("form:ajax");
	}

	
	@Test
	public void shouldNonAjaxLinkCreateValidationError() throws InterruptedException {
		submitNotValidFormForButtom("form:nonAjax");
	}
	
	@Test
	public void shouldNonAjaxLinkSubmit() throws InterruptedException {
		submitValidFormFor("form:nonAjax");
	}
	
}
