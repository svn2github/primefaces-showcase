package com.prime.showcase.integration.ring;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;


public class RingIntegrationTest extends AbstractIntegrationTest{
	
	@Before
	public void init() {
		driver.get(toShowcaseUrl("ring.jsf"));
	}
	
	@Test
	public void shouldChangeFrontItemOfBasicRingAndShowInformations(){
		WebElement ring = findElementById("form:basic");
		List<WebElement> ringItems = ring.findElements(By.className("ui-ring-item"));
		
		WebElement frontItem = ringItems.get(0);
		WebElement choosenItem = ringItems.get(1);
		
		choosenItem.findElement(By.tagName("button")).click();

		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.id("form:dialog")).getAttribute("style").contains("display: block;");
			}
		});	
		waitUntilAllAnimationsComplete();
		
		assertTrue(frontItem.getAttribute("style").contains("z-index: 296;"));
		assertTrue(choosenItem.getAttribute("style").contains("z-index: 400;"));
		
		assertTrue(choosenItem.getText().contains(findElementById("form:modelNo").getText()));
	}
	
	@Test
	public void shouldChangeFrontItemOfCustomRing(){
		WebElement ring = findElementById("form:custom");
		List<WebElement> ringItems = ring.findElements(By.className("ui-ring-item"));
		
		WebElement frontItem = ringItems.get(0);
		WebElement choosenItem = ringItems.get(1);
		
		choosenItem.click();
		waitForOneSecond();
		assertTrue(frontItem.getAttribute("style").contains("z-index: 296;"));
		assertTrue(choosenItem.getAttribute("style").contains("z-index: 400;"));
		
	}
}
