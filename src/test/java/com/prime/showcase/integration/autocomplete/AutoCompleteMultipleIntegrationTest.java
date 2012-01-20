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
public class AutoCompleteMultipleIntegrationTest extends AbstractAutoCompleteTest {

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("autoCompleteMultiple.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldSelectElementFromListWithBasic() {
		WebElement autocomplete = findElementById("form:basic_input");
		autocomplete.sendKeys("p");
		waitUntilAjaxRequestCompletes();
		selectAutoCompleteListElement("form:basic", 0, 1);
		autocomplete.sendKeys("r");
		waitUntilAjaxRequestCompletes();
		selectAutoCompleteListElement("form:basic", 0, 1);
		autocomplete.sendKeys("i");
		waitUntilAjaxRequestCompletes();
		selectAutoCompleteListElement("form:basic", 0, 1);
		autocomplete.sendKeys("m");
		waitUntilAjaxRequestCompletes();
		selectAutoCompleteListElement("form:basic", 0, 1);
		autocomplete.sendKeys("e");
		waitUntilAjaxRequestCompletes();
		selectAutoCompleteListElement("form:basic", 0, 1);
		
		WebElement submit = findElementById("form:submit");
		submit.click();
		waitUntilAjaxRequestCompletes();
		
		assertThat(findElementByXpath("//ul[@id='form:texts_list']/li[1]/span").getText(), equalTo("p0"));
		assertThat(findElementByXpath("//ul[@id='form:texts_list']/li[2]/span").getText(), equalTo("r0"));
		assertThat(findElementByXpath("//ul[@id='form:texts_list']/li[3]/span").getText(), equalTo("i0"));
		assertThat(findElementByXpath("//ul[@id='form:texts_list']/li[4]/span").getText(), equalTo("m0"));
		assertThat(findElementByXpath("//ul[@id='form:texts_list']/li[5]/span").getText(), equalTo("e0"));
	}
	
	@Test
	public void shouldSelectElementFromListWithComplex() {
		WebElement autocomplete = findElementById("form:advanced_input");
		WebElement panel = findElementById("form:advanced_input");
		
		selectPlayer(autocomplete, panel,"A");
		selectPlayer(autocomplete, panel,"M");
		selectPlayer(autocomplete, panel,"X");
		selectPlayer(autocomplete, panel,"V");
		selectPlayer(autocomplete, panel,"I");
		
		WebElement submit = findElementById("form:submit");
		submit.click();
		waitUntilAjaxRequestCompletes();
		
		assertThat(findElementByXpath("//ul[@id='form:players_list']/li[1]/span").getText(), equalTo("Afellay"));
		assertThat(findElementByXpath("//ul[@id='form:players_list']/li[2]/span").getText(), equalTo("Messi"));
		assertThat(findElementByXpath("//ul[@id='form:players_list']/li[3]/span").getText(), equalTo("Xavi"));
		assertThat(findElementByXpath("//ul[@id='form:players_list']/li[4]/span").getText(), equalTo("Villa"));
		assertThat(findElementByXpath("//ul[@id='form:players_list']/li[5]/span").getText(), equalTo("Iniesta"));
	}

	protected void selectPlayer(WebElement autocomplete, WebElement panel,String firstChar) {
		autocomplete.sendKeys(firstChar);
		waitUntilAjaxRequestCompletes();
		waitUntilElementExistsAndGet(panel, By.tagName("table"), 1);
		findElementByXpath("//div[@id='form:advanced_panel']/table/tbody/tr/td[2]").click();
	}

	
}
