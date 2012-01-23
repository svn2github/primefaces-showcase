package com.prime.showcase.integration.menubutton;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class MenuButtonIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("menubutton.jsf"));
	}
	
	@Test
	public void shouldRenderMenu() {
		WebElement menuButton = driver.findElement(By.className("ui-button"));
		
		menuButton.click();
		
		assertThat(menuButton.getText(),equalTo("Options"));
		
		List<WebElement> menuItems = driver.findElements(By.className("ui-menuitem-text"));
		
		assertThat(menuItems.get(0).getText(),equalTo("Save"));
		assertThat(menuItems.get(1).getText(),equalTo("Update"));
		assertThat(menuItems.get(2).getText(),equalTo("Delete"));
		assertThat(menuItems.get(3).getText(),equalTo("Homepage"));
	}
	
	@Test
	public void shouldClickMenuItem() throws InterruptedException {
		WebElement menuButton = driver.findElement(By.className("ui-button"));
		
		menuButton.click();
		
		assertThat(menuButton.getText(),equalTo("Options"));
		
		List<WebElement> menuItems = driver.findElements(By.className("ui-menuitem-text"));
		
		menuItems.get(0).click();
		
		waitUntilElementExists(By.className("ui-growl-message"));
		
		assertThat(driver.findElement(By.className("ui-growl-message")).getText(),equalTo("Data saved"));
	}
	
	
	@Test
	public void shouldClickAndGoToPage() {
		WebElement menuButton = driver.findElement(By.className("ui-button"));
		
		menuButton.click();
		
		assertThat(menuButton.getText(),equalTo("Options"));
		
		List<WebElement> menuItems = driver.findElements(By.className("ui-menuitem-text"));
		
		menuItems.get(3).click();
		
		assertThat(driver.getCurrentUrl(),equalTo("http://www.primefaces.org/"));
		
	}
	
	

}
