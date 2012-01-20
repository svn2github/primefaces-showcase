/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prime.showcase.integration.autocomplete;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * 
 * @author yigitdarcin
 */
public class AutoCompletePojoIntegrationTest extends AbstractAutoCompleteTest {

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("autoCompletePojo.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldSelectFromBasicPojo() {
		WebElement autocomplete = findElementById("form:basicPojo_input");
		autocomplete.clear();
		autocomplete.sendKeys("M");
		waitUntilAjaxRequestCompletes();
		selectAutoCompleteListElement("form:basicPojo", 0, 1);
		assertThat(autocomplete.getAttribute("value"), equalTo("Messi"));
	}

	@Test
	public void shouldSelectFromCustomPojo() {
		WebElement autocomplete = findElementById("form:customPojo_input");
		autocomplete.clear();
		autocomplete.sendKeys("V");
		waitUntilAjaxRequestCompletes();
		WebElement panel = findElementById("form:customPojo_panel");
		waitUntilElementExistsAndGet(panel, By.tagName("table"), 1);
		findElementByXpath("//div[@id='form:customPojo_panel']/table/tbody/tr/td[2]").click();
		assertThat(autocomplete.getAttribute("value"), equalTo("Villa"));
	}
	
	@Test
	public void shoulSubmitValues(){
		shouldSelectFromBasicPojo();
		shouldSelectFromCustomPojo();

		WebElement submit = findElementById("form:submit");
		submit.click();
		waitUntilAjaxRequestCompletes();
		
		assertThat(findElementById("form:txt1").getText(), equalTo("Player 1: Messi"));
		assertThat(findElementById("form:txt2").getText(), equalTo("Player 2: Villa"));
		
	}
}
