package com.prime.showcase.integration.ajaxengine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AjaxCounterIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("pprCounter.jsf");
	
	@Test
	public void shouldIncreaseCounter() {

		driver.get(testUrl);

		WebElement button = findElementById("form:btn");

		button.click();

		waitUntilAjaxRequestCompletes();
		
		WebElement numberText = findElementById("form:txt_count");

		assertThat(numberText.getText(), equalTo("1"));
		
		button.click();
		
		waitUntilElementExistsAndGetsValue("form:txt_count", "2");
		
		numberText = findElementById("form:txt_count");

		assertThat(numberText.getText(), equalTo("2"));

	}

}
