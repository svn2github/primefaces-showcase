package com.prime.showcase.integration.hotkey;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Keyboard;
import org.openqa.selenium.Keys;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.junit.Assert.assertTrue;

public class HotkeyIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("hotkey.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldDisplayNextImage() {

		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();

		assertTrue(findElementById("img1") != null);
		keyboard.sendKeys(Keys.ARROW_RIGHT);
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementById("img2") != null);
		keyboard.sendKeys(Keys.ARROW_RIGHT);
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementById("img3") != null);
		keyboard.sendKeys(Keys.ARROW_LEFT);
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementById("img4") != null);
	}
}
