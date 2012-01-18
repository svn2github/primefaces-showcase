package com.prime.showcase.integration.colorpicker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class ColorPickerIntegrationTest extends AbstractIntegrationTest {

	private static final String EXPECTED_COLOR = "804141";

	private SeleniumActionHelper actionHelper = new SeleniumActionHelper(driver);

	@Before
	public void before() {
		String testUrl = toShowcaseUrl("colorPicker.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldPickColorWithInlineColorPicker() {
		WebElement colorPicker = findElementById("form:inlineCP");
		pickColor(colorPicker);

		WebElement clickedColor = colorPicker.findElement(By.tagName("input"));
		assertThat(clickedColor.getAttribute("value"), equalTo(EXPECTED_COLOR));

		WebElement button = findElementById("form:btn");

		button.click();

		waitForCondition(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.id("form:inlineCPColor")).getText().equals(EXPECTED_COLOR);
			}
		});

	}

	private void pickColor(WebElement colorPicker) {
		WebElement colorPalette = colorPicker.findElement(By.className("ui-colorpicker_color"));

		actionHelper.mouseHover(colorPalette);
		actionHelper.clickOnCurrentPosition();
	}

	// @Test
	public void shouldPickColorWithPopupColorPicker() {
		// WebElement popupButton = findElementById("form:popupCP_button");
		// popupButton.click();

		List<WebElement> colorPickers = findElementsByClass("ui-colorpicker-container");
		pickColor(colorPickers.get(colorPickers.size() - 1));

		WebElement button = findElementById("form:btn");

		button.click();

		waitUntilAjaxRequestCompletes();

		waitForCondition(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.id("form:popupCPColor")).getText().equals(EXPECTED_COLOR);
			}
		});
	}
}
