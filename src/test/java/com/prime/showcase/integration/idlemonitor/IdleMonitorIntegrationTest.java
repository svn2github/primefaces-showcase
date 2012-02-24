package com.prime.showcase.integration.idlemonitor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class IdleMonitorIntegrationTest extends AbstractIntegrationTest {
	
	
	@Test
	public void shouldShowIdleMonitorClientSide() throws InterruptedException {
		String testUrl = toShowcaseUrl("idleMonitor.jsf");
		driver.get(testUrl);
		
		Thread.sleep(13000);
		
		WebElement dialogElement = driver.findElement(By.className("ui-dialog"));
		
		assertTrue("Dialog should be displayed.", dialogElement.isDisplayed());
	}
	
	@Test
	public void shouldShowIdleMonitorWithAjaxListeners() throws InterruptedException {
		String testUrl = toShowcaseUrl("idleMonitorEvents.jsf");
		driver.get(testUrl);

		Thread.sleep(7000);
        
        waitUntilAllAnimationsComplete();
		
		WebElement growlElement = driver.findElement(By.className("ui-growl-message"));
		
		assertThat(growlElement.getText(),containsString("You have been idle for at least 5 seconds"));

        growlElement.click();
        
        waitUntilAjaxRequestCompletes();
        waitUntilAllAnimationsComplete();
        
		growlElement = driver.findElement(By.className("ui-growl-message"));
		
		
		assertThat(growlElement.getText(),containsString("That's a long coffee break!"));
		
	}


}
