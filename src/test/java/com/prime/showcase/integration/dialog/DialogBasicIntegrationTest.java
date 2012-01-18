package com.prime.showcase.integration.dialog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DialogBasicIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("dialog.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldOpenBasicDialog() {
		WebElement basicDialog = findElementById("basicDialog");

		assertThat(basicDialog.getAttribute("style"), not(containsString("z-index")));

		WebElement basicDialogButton = findElementById("basic");
		basicDialogButton.click();

		assertThat(basicDialog.getAttribute("style"), containsString("z-index"));
	}

	@Test
	public void shouldOpenModalDialog() {

		WebElement modalDialogButton = findElementById("modalDialogButton");
		modalDialogButton.click();

		waitUntilElementExists(By.id("modalDialog_modal"));
	}

	@Test
	public void shouldOpenDialogWithEffects() {

		WebElement effectsDialogButton = findElementById("effectsDialogButton");
		effectsDialogButton.click();

		findElementByClass("ui-effects-explode");
	}
}
