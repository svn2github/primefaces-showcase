package com.prime.showcase.integration.requestcontext;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.prime.showcase.integration.AbstractIntegrationTest;
import junit.framework.Assert;

public class RequestContextIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		String testUrl = toShowcaseUrl("requestContext.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldExecuteJsFromRequestContextAndUpdate() throws InterruptedException {
		driver.findElement(By.id("form:firstname")).sendKeys("Cagatay");
		driver.findElement(By.id("form:surname")).sendKeys("Civici");
		
		driver.findElement(By.className("ui-button")).click();
        
        waitUntilAjaxRequestCompletes();
        waitUntilAllAnimationsComplete();
        
        Long top = (Long) executeJS("return $(window).scrollTop();");
        
        Assert.assertTrue("Should request context scroll.", top > 0);

    }

}
