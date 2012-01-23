package com.prime.showcase.integration.wizard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class WizardIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("wizard.jsf"));
	}
	
	@Test
	public void shouldGenerateConfirmationFromAllValidFieldsEnteredInWizard() throws InterruptedException {
		
		List<WebElement> inputElements = driver.findElements(By.className("ui-inputfield"));
		
		inputElements.get(1).sendKeys("Cenk");
		inputElements.get(2).sendKeys("Civici");
		inputElements.get(3).sendKeys("90");
		
		driver.findElements(By.className("ui-button")).get(1).click();
		
		inputElements = driver.findElements(By.className("ui-inputfield"));
		inputElements.get(1).sendKeys("Street");
		inputElements.get(2).sendKeys("Postal");
		inputElements.get(3).sendKeys("Istanbul");
		
		driver.findElements(By.className("ui-button")).get(1).click();
		
		inputElements = driver.findElements(By.className("ui-inputfield"));
		inputElements.get(1).sendKeys("cc@cc.com");
		inputElements.get(2).sendKeys("4314324343");
		inputElements.get(3).sendKeys("Additional Info");
		
		driver.findElements(By.className("ui-button")).get(1).click();
		
		String text = driver.findElement(By.className("ui-wizard")).getText();
		assertThat(text, containsString("Firstname: Cenk"));
		assertThat(text, containsString("Lastname: Civici"));
		assertThat(text, containsString("Age: 90"));

		assertThat(text, containsString("Street: Street"));
		assertThat(text, containsString("Postal: Postal"));
		assertThat(text, containsString("City: Istanbul"));

		assertThat(text, containsString("Email: cc@cc.com"));
		assertThat(text, containsString("Phone 4314324343"));
		assertThat(text, containsString("Info: Additional Info"));
		
	}
	
	@Test
	public void shouldSkipToLastStepIfValid() {
		List<WebElement> inputElements = driver.findElements(By.className("ui-inputfield"));
		
		inputElements.get(1).sendKeys("Cenk");
		inputElements.get(2).sendKeys("Civici");
		inputElements.get(3).sendKeys("90");
		
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		
		driver.findElements(By.className("ui-button")).get(1).click();
		
		String text = driver.findElement(By.className("ui-wizard")).getText();
		assertThat(text, containsString("Firstname: Cenk"));
		assertThat(text, containsString("Lastname: Civici"));
		assertThat(text, containsString("Age: 90"));
	}
	
	@Test
	public void shouldNotGoToNextPageIfNotValid() {
		driver.findElements(By.className("ui-button")).get(1).click();
		
		String text = driver.findElement(By.className("ui-wizard")).getText();
		
		assertThat(text, containsString("Firstname: Validation Error: Value is required."));
		
		
	}
	
	
}
