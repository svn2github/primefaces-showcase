package com.prime.showcase.integration.password;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class PasswordIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("password.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldRenderInputAsPassword(){
		WebElement nonFeedback = findElementById("form:nonFeedback_input");
		assertThat(nonFeedback.getAttribute("type"), equalTo("password"));
	}
	
	@Test
	public void shouldShowFeedback() throws InterruptedException{
		
		WebElement feedback = findElementById("form:feedback_input");
		feedback.click();

		WebElement feedbackMessage = findElementsByClass("jpassword-righttop").get(0).findElement(By.className("jpassword-info"));
		assertThat(feedbackMessage.getText(), equalTo("Please enter a password"));

		feedback.sendKeys("aaa");
		assertThat(feedbackMessage.getText(), equalTo("Weak"));
		feedback.sendKeys("33");
		assertThat(feedbackMessage.getText(), equalTo("Good"));
		feedback.sendKeys("Z!X'");
		assertThat(feedbackMessage.getText(), equalTo("Strong"));
	}
	
	@Test
	public void shouldShowTurkishFeedback() throws InterruptedException{
		
		WebElement feedback = findElementById("form:turkishFeedback_input");
		feedback.click();

		WebElement feedbackMessage = findElementsByClass("jpassword-righttop").get(1).findElement(By.className("jpassword-info"));
		assertThat(feedbackMessage.getText(), equalTo("Lütfen şifre giriniz"));

		feedback.sendKeys("aaa");
		assertThat(feedbackMessage.getText(), equalTo("Zayıf"));
		feedback.sendKeys("33");
		assertThat(feedbackMessage.getText(), equalTo("Orta seviye"));
		feedback.sendKeys("Z!X'");
		assertThat(feedbackMessage.getText(), equalTo("Güçlü"));
	}
	
	@Test
	public void shouldShowInlineFeedback() throws InterruptedException{
		
		WebElement feedback = findElementById("form:inlineFeedback_input");
		feedback.click();

		WebElement feedbackDiv = findElementsByClass("jpassword-flat").get(0);
		assertTrue(feedbackDiv.isDisplayed());
	}
	
	@Test
	public void shouldShowCustomFeedback() throws InterruptedException{
		
		WebElement feedback = findElementById("form:customFeedback_input");
		feedback.click();

		WebElement feedbackDiv = findElementsByClass("jpassword-flat").get(1);
		assertTrue(feedbackDiv.isDisplayed());
	}
	
	@Test
	public void shouldShowErrorMessageWhenDifferentPassEntered(){
		WebElement pwd1 = findElementById("form:pwd1_input");
		WebElement pwd2 = findElementById("form:pwd2_input");
		WebElement save = findElementById("form:saveButton");
		
		pwd1.sendKeys("123");
		pwd2.sendKeys("321");
		
		save.click();
		waitUntilAjaxRequestCompletes();
		
		WebElement message = findElementById("form:messages");
		assertTrue(message.isDisplayed());
		assertTrue(message.findElement(By.tagName("div")).getAttribute("class").contains("ui-messages-error"));
	}
	
	@Test
	public void shouldNotShowErrorSamePassEntered(){
		WebElement pwd1 = findElementById("form:pwd1_input");
		WebElement pwd2 = findElementById("form:pwd2_input");
		WebElement save = findElementById("form:saveButton");
		
		pwd1.sendKeys("123");
		pwd2.sendKeys("123");
		
		save.click();
		waitUntilAjaxRequestCompletes();
		
		WebElement message = findElementById("form:messages");
		assertFalse(message.isDisplayed());
	}
}
