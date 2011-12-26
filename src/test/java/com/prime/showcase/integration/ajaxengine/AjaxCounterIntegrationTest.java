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

		WebElement button = findElementById("j_idt14:j_idt17");

		button.click();

		waitUntilAjaxRequestCompletes();
		
		WebElement numberText = findElementById("j_idt14:txt_count");

		assertThat(numberText.getText(), equalTo("1"));
		
		button.click();
		
		waitUntilElementExistsAndGetsValue("j_idt14:txt_count", "2");
		
		numberText = findElementById("j_idt14:txt_count");

		assertThat(numberText.getText(), equalTo("2"));

	}

}
