package com.prime.showcase.integration.fieldset;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class FieldSetIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("fieldset.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldShowBasicFieldSet(){
		WebElement simpleFieldSet = findElementById("simpleFieldSet");
		WebElement legend = simpleFieldSet.findElement(By.tagName("legend"));
		WebElement fieldSetContent = simpleFieldSet.findElement(By.className("ui-fieldset-content"));
		
		assertFalse(legend.getAttribute("class").contains("ui-state-active"));
		assertThat(legend.getText(), equalTo("Simple FieldSet"));
		assertTrue(fieldSetContent.isDisplayed());
	}
	
	@Test
	public void shouldToggle() throws InterruptedException{
		WebElement toggleFieldSet = findElementById("form:toggleFieldSet");
		WebElement legend = toggleFieldSet.findElement(By.tagName("legend"));
		WebElement fieldSetContent = toggleFieldSet.findElement(By.className("ui-fieldset-content"));
		
		legend.click();
		
		waitUntilAjaxRequestCompletes();
		Thread.sleep(1000);
		assertFalse(fieldSetContent.isDisplayed());
	}
}
