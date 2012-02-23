package com.prime.showcase.integration.dialog;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DialogLoginIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("dialogLogin.jsf"));
	}

	@Test
	public void shouldLoginAndHideDialog() {
		String username = "admin", password = "admin";

		findElementById("loginLink").click();

		findElementById("form:username").sendKeys(username);
		findElementById("form:password").sendKeys(password);

		findElementById("form:loginButton").click();

		waitForCondition(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return !driver.findElement(By.id("dialog")).isDisplayed()
						&& !driver.findElement(By.id("loginLink")).isDisplayed();
			}
		});

	}

	@Test
	public void shouldNotHideDialogWhenLoginFails() {
		findElementById("loginLink").click();

		findElementById("form:loginButton").click();

		waitForCondition(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return !driver.findElement(By.id("loginLink")).getAttribute("style").contains("display: none;");
			}
		});
	}

}
