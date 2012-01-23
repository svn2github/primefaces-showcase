package com.prime.showcase.integration.button;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class ButtonIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		String testUrl = toShowcaseUrl("button.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldGotoProductPageWithValue10Param() {
		List<WebElement> buttonElements = driver.findElements(By.tagName("button"));
		
		WebElement button = buttonElements.get(0);
		
		assertThat(button.getText(),equalTo("Bookmark"));
		
		button.click();
		
		assertThat(driver.getCurrentUrl(),endsWith("productId=10"));
	}
	
	@Test
	public void shouldGotoProductPageWithValue20Param() {
		List<WebElement> buttonElements = driver.findElements(By.tagName("button"));
		
		WebElement button = buttonElements.get(1);
		
		assertThat(button.getText(),equalTo("With Icon"));
		
		button.click();
		
		assertThat(driver.getCurrentUrl(),endsWith("productId=20"));
	}
	
	@Test
	public void shouldGotoProductPageWithValue30Param() {
		List<WebElement> buttonElements = driver.findElements(By.tagName("button"));
		
		WebElement button = buttonElements.get(2);
		
		assertThat(button.getText(),equalTo("ui-button"));
		
		button.click();
		
		assertThat(driver.getCurrentUrl(),endsWith("productId=30"));
	}
	
	
	@Test
	public void shouldNotBeAbleToClickDisabledButton() {
		List<WebElement> buttonElements = driver.findElements(By.tagName("button"));
		
		WebElement button = buttonElements.get(3);
		
		assertThat(button.getText(),equalTo("Bookmark"));
		
		button.click();
		
		assertThat(driver.getCurrentUrl(),endsWith("button.jsf"));
	}
	
	
	
	
	


}
