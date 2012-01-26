package com.prime.showcase.integration.inplace;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

import static org.junit.Assert.assertTrue;

public class InplaceIntegrationTest extends AbstractIntegrationTest{
	private SeleniumActionHelper action;
	
	@Before
	public void init(){
		driver.get(toShowcaseUrl("inplace.jsf"));
		action = new SeleniumActionHelper(driver);
	}
	
	@Test
	public void shouldDisplayBasicInplaceContent(){
		WebElement display = findElementById("basic_display");
		WebElement content = findElementById("basic_content");
		assertTrue(display.getAttribute("style").contains("display: inline;"));
		assertTrue(content.getAttribute("style").contains("display: none;"));
		
		display.click();
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !driver.findElement(By.id("basic_content")).getAttribute("style").contains("display: none;");
			}
		});
		content.findElement(By.xpath("input")).sendKeys("Primefaces");
		assertTrue(display.getAttribute("style").contains("display: none;"));
	}
	
	@Test
	public void shouldNotChangeAjaxInplaceWhenCanceled(){
		WebElement display = findElementById("ajaxInplace_display");
		assertTrue(display.getText().equals("PrimeFaces"));
		assertTrue(display.getAttribute("style").contains("display: inline;"));
		assertTrue(findElementById("ajaxInplace_content").getAttribute("style").contains("display: none;"));
		
		display.click();
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !driver.findElement(By.id("ajaxInplace_content")).getAttribute("style").contains("display: none;");
			}
		});
		findElementById("ajaxInplace_content").findElement(By.xpath("input")).sendKeys("Ajax inplace changed!");
		findElementBySelector(".ui-inplace-cancel").click();
		
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementById("ajaxInplace_display").getAttribute("style").contains("display: inline;"));
		assertTrue(findElementById("ajaxInplace_display").getText().equals("PrimeFaces"));
		
	}
	
	@Test
	public void shouldChangeAjaxInplaceWhenSaved(){
		WebElement display = findElementById("ajaxInplace_display");
		assertTrue(display.getText().equals("PrimeFaces"));
		assertTrue(findElementById("ajaxInplace_content").getAttribute("style").contains("display: none;"));
		
		display.click();
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !driver.findElement(By.id("ajaxInplace_content")).getAttribute("style").contains("display: none;");
			}
		});
		findElementById("ajaxInplace_content").findElement(By.xpath("input")).sendKeys("Ajax inplace changed!");
		findElementBySelector(".ui-inplace-save").click();
		
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementById("ajaxInplace_display").getAttribute("style").contains("display: inline;"));
		assertTrue(findElementById("ajaxInplace_display").getText().contains("Ajax inplace changed!"));
		
	}
	
	@Test
	public void shouldDisplaySelectOneMenu(){
		WebElement display = findElementById("selectableInplace_display");
		WebElement content = findElementById("selectableInplace_content");
		assertTrue(display.getAttribute("style").contains("display: inline;"));
		assertTrue(content.getAttribute("style").contains("display: none;"));
		
		action.dblClick(display);
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !driver.findElement(By.id("selectableInplace_content")).getAttribute("style").contains("display: none;");
			}
		});
		assertTrue(display.getAttribute("style").contains("display: none;"));
	}
	
	@Test
	public void shouldDisplayCheckbox(){
		WebElement display = findElementById("checkboxInplace_display");
		WebElement content = findElementById("checkboxInplace_content");
		assertTrue(display.getAttribute("style").contains("display: inline;"));
		assertTrue(content.getAttribute("style").contains("display: none;"));
		
		display.click();
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !driver.findElement(By.id("checkboxInplace_content")).getAttribute("style").contains("display: none;");
			}
		});
		assertTrue(display.getAttribute("style").contains("display: none;"));
	}
	
	@Test
	public void shouldDisplayImage(){
		WebElement display = findElementById("slidingInplace_display");
		WebElement content = findElementById("slidingInplace_content");
		assertTrue(display.getAttribute("style").contains("display: inline;"));
		assertTrue(content.getAttribute("style").contains("display: none;"));
		
		display.click();
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !driver.findElement(By.id("slidingInplace_content")).getAttribute("style").contains("display: none;");
			}
		});
		assertTrue(display.getAttribute("style").contains("display: none;"));
	}

}
