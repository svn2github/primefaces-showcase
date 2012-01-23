package com.prime.showcase.integration.requestcontext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class RequestContextIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		String testUrl = toShowcaseUrl("requestContext.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldExecuteJsFromRequestContextAndUpdate() {
		driver.findElement(By.id("form:firstname")).sendKeys("Cagatay");
		driver.findElement(By.id("form:surname")).sendKeys("Civici");
		
		driver.findElement(By.className("ui-button")).click();

		Alert alert = driver.switchTo().alert();
		
		assertThat(alert.getText(),equalTo("Hello from the Backing Bean"));
		alert.accept();
		
		alert = driver.switchTo().alert();
		
		assertThat(alert.getText(),equalTo("Save:true"));
		alert.accept();

		alert = driver.switchTo().alert();
		assertThat(alert.getText(),equalTo("FirstName: Cagatay, Lastname: Civici"));
	}

}
