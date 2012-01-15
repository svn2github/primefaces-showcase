package com.prime.showcase.integration.editor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class EditorIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("editor.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldSendBoldString() throws InterruptedException {
		driver.switchTo().frame(findElementByTag("iframe"));
		driver.findElement(By.xpath("html/body")).sendKeys("This is a editor content");
		
		driver.switchTo().defaultContent();
		findElementById("form:submitButton").click();
		
		WebElement display = findElementById("form:display");
		assertThat(display.getText(), equalTo("This is a editor content"));
	}
	
	@Test
	public void shouldClearEditorContent() throws InterruptedException{
		driver.switchTo().frame(findElementByTag("iframe"));
		driver.findElement(By.xpath("html/body")).sendKeys("This is a editor content");
		
		driver.switchTo().defaultContent();
		findElementById("form:clearButton").click();
		
		driver.switchTo().frame(findElementByTag("iframe"));
		assertThat(driver.findElement(By.xpath("html/body")).getText(), equalTo(""));
	}
}
