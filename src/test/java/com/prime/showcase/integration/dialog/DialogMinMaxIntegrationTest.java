package com.prime.showcase.integration.dialog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DialogMinMaxIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("dialogMinMax.jsf"));
	}

	@Test
	public void shouldMaximizeDialog() {
		testDialogMinMaxFunctionality("ui-icon-extlink", "left: 0px; top: 0px;");
	}

	@Test
	public void shouldMinimizeDialog() {
		testDialogMinMaxFunctionality("ui-icon-minus", "float: left;");
	}

	private void testDialogMinMaxFunctionality(final String clickedButtonId, final String expectedStyle) {
		WebElement openDialogButton = findElementById("basic");
		openDialogButton.click();

		WebElement dialog = findElementById("minMaxDialog");
		String dialogStyle = dialog.getAttribute("style");

		verifyDialogNotMinimizedAndMaximized(dialogStyle);

		findElementByClass(clickedButtonId).click();

		waitForCondition(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				String dialogStyle = driver.findElement(By.id("minMaxDialog")).getAttribute("style");
				return dialogStyle.contains(expectedStyle);
			}
		});

	}

	private void verifyDialogNotMinimizedAndMaximized(String dialogStyle) {
		assertThat(dialogStyle, not(containsString("left: 0px; top: 0px;")));
		assertThat(dialogStyle, not(containsString("float: left;")));
	}
}
