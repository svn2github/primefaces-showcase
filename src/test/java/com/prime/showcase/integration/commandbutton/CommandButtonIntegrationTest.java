package com.prime.showcase.integration.commandbutton;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommandButtonIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("commandButton.jsf");
		driver.get(testUrl);
	}

	
	protected void submitNotValidFormForButtom(String buttomId) {
		findElementById("form:firstname").sendKeys("P");
		findElementById("form:surname").sendKeys("Faces");
		
		WebElement button = findElementById(buttomId);
		button.click();
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
	public void shouldAjaxButtonCreateValidationError() throws InterruptedException {
		submitNotValidFormForButtom("form:ajax");
	}
	
	@Test
	public void shouldAjaxButtonSubmit() throws InterruptedException {
		submitValidFormFor("form:ajax");
	}

	
	@Test
	public void shouldNonAjaxButtonCreateValidationError() throws InterruptedException {
		submitNotValidFormForButtom("form:nonAjax");
	}
	
	@Test
	public void shouldNonAjaxButtonSubmit() throws InterruptedException {
		submitValidFormFor("form:nonAjax");
	}
	
	@Test
	public void shouldWithIconButtonCreateValidationError() throws InterruptedException {
		submitNotValidFormForButtom("form:withIcon");
	}
	
	@Test
	public void shouldWithIconButtonSubmit() throws InterruptedException {
		submitValidFormFor("form:withIcon");
	}
	
	
	@Test
	public void shouldIconOnlyButtonCreateValidationError() throws InterruptedException {
		submitNotValidFormForButtom("form:iconOnly");
	}
	
	@Test
	public void shouldIconOnlyButtonSubmit() throws InterruptedException {
		submitValidFormFor("form:iconOnly");
	}
	
	@Test
	public void shouldDisabledButtonShouldBeDisabled() throws InterruptedException {
		WebElement element = findElementById("form:disabled");
		assertThat(element.getAttribute("disabled"),equalTo("true"));
	}
	
	
}
