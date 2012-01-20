/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prime.showcase.integration.autocomplete;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * 
 * @author yigitdarcin
 */
public class AutoCompleteListenerIntegrationTest extends AbstractAutoCompleteTest {

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("autoCompleteSelect.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldInvokeListenerWithSimple() {
		doTestOnAutoComplete("form:acSimple", "Prime");
		assertTrue(findElementByClass("ui-growl-message").isDisplayed());
	}

	@Test
	public void shouldInvokeListenerWithPojo() {
		WebElement autocomplete = findElementById("form:acPojo_input");
		autocomplete.clear();
		autocomplete.sendKeys("M");
		waitUntilAjaxRequestCompletes();
		selectAutoCompleteListElement("form:acPojo", 0, 1);
		assertThat(autocomplete.getAttribute("value"), equalTo("Messi"));
		assertTrue(findElementByClass("ui-growl-message").isDisplayed());
	}

	@Test
	public void shoulSubmitValues() {
		shouldInvokeListenerWithSimple();
		shouldInvokeListenerWithPojo();

		WebElement submit = findElementById("form:submit");
		submit.click();
		waitUntilAjaxRequestCompletes();

		assertThat(findElementById("form:txt1").getText(), equalTo("Prime2"));
		assertThat(findElementById("form:txt2").getText(), equalTo("Messi"));

	}
}
