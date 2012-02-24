package com.prime.showcase.integration.selectonemenu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class SelectOneMenuIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		String testUrl = toShowcaseUrl("selectOneMenu.jsf");
		driver.get(testUrl);
	}
	
	
	@Test
	public void shouldRenderAndSubmitValuesForSelectOneMenu() throws InterruptedException {
		List<WebElement> selectOneMenus = driver.findElements(By.className("ui-selectonemenu"));
		List<WebElement> selectOneMenuItems = driver.findElements(By.className("ui-selectonemenu-items"));
        WebElement title = findElementBySelector(".title");
		
		selectOneMenus.get(1).findElement(By.className("ui-selectonemenu-trigger")).click();
		selectOneMenuItems.get(1).findElements(By.tagName("li")).get(2).click();

        title.click();
        waitUntilAllAnimationsComplete();
		selectOneMenus.get(2).findElement(By.className("ui-selectonemenu-trigger")).click();
		selectOneMenus.get(2).findElement(By.tagName("input")).sendKeys(" edited");
		
        title.click();
        waitUntilAllAnimationsComplete();
		selectOneMenus.get(3).findElement(By.className("ui-selectonemenu-trigger")).click();
		selectOneMenuItems.get(3).findElements(By.tagName("li")).get(2).click();

        title.click();
        waitUntilAllAnimationsComplete();
		selectOneMenus.get(4).findElement(By.className("ui-selectonemenu-trigger")).click();
		selectOneMenuItems.get(4).findElements(By.className("ui-selectonemenu-item")).get(3).click();
		
		driver.findElement(By.className("ui-button")).click();
		
		String dialogText = driver.findElement(By.className("ui-dialog-content")).getText();
		
		assertThat(dialogText,containsString("Value 1: 2"));
		assertThat(dialogText,containsString("Value 2: Select One edited"));
		assertThat(dialogText,containsString("Value 3: Bojan"));
		assertThat(dialogText,containsString("Value 4: Iniesta"));
		
	}

}
