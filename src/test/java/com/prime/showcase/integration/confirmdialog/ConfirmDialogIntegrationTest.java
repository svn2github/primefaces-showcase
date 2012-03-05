package com.prime.showcase.integration.confirmdialog;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class ConfirmDialogIntegrationTest extends AbstractIntegrationTest{

	@Before
	public void init(){
		driver.get(toShowcaseUrl("confirmDialog.jsf"));
	}
	
	@Test
	public void shouldHideDialogWhenNotConfirmed(){
		WebElement showDialogButton = findElementById("showDialogButton");
		showDialogButton.click();
		
		WebElement declineButton = findElementById("decline");
		declineButton.click();
		
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.id("confirmDialog")).isDisplayed();
			}
		});	
	}
	
	@Test
	public void shouldInvokeConfirmationActionListenerWhenConfirmed(){
		WebElement showDialogButton = findElementById("showDialogButton");
		showDialogButton.click();
		
		WebElement confirmButton = findElementById("confirm");
		confirmButton.click();
		
		waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.id("confirmDialog")).isDisplayed();
			}
		});	
		
		waitUntilElementExists(By.className("ui-growl"));
		
	}
	
	
}
