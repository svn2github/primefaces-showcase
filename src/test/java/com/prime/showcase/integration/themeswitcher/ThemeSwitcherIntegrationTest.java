package com.prime.showcase.integration.themeswitcher;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static junit.framework.Assert.assertTrue;

public class ThemeSwitcherIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("themeswitcher.jsf");
	private WebElement defaultSwitcher;
	private WebElement statefulSwitcher;
	private WebElement themePreview;

	@Before
	public void init() {
		driver.get(testUrl);
		defaultSwitcher = findElementById("form:defaultSwitcher");
		statefulSwitcher = findElementById("form:statefulSwitcher");
		themePreview = findElementById("form:themePreview");
	}

	@Test
	public void shouldChangeThemeForDefaultSwitcher() {
		waitUntilAjaxRequestCompletes();
		List<WebElement> themeList = findElementById(
				"form:defaultSwitcher_panel").findElement(By.tagName("ul"))
				.findElements(By.tagName("li"));
		List<WebElement> hiddenValues = findElementById(
				"form:defaultSwitcher_input")
				.findElements(By.tagName("option"));

		defaultSwitcher.findElement(By.className("ui-icon-triangle-1-s"))
				.click();
		themeList.get(2).click();
		waitUntilAjaxRequestCompletes();

		List<WebElement> styles = findElementsByTag("link");
		WebElement themeStyle = null;

		for (WebElement style : styles) {
			if (style.getAttribute("href").contains("theme.css.jsf")) {
				themeStyle = style;
				break;
			}
		}

		if (themeStyle != null) {
			assertTrue(themeStyle.getAttribute("href").endsWith(
					hiddenValues.get(2).getAttribute("value")));
		} else {
			assertTrue("link tag for theme style couldn't be found.", false);
		}

	}

	@Test
	public void shouldChangeThemeForStatefulSwitcher() {
		waitUntilAjaxRequestCompletes();
		List<WebElement> themeList = findElementById(
				"form:statefulSwitcher_panel").findElement(By.tagName("ul"))
				.findElements(By.tagName("li"));
		List<WebElement> hiddenValues = findElementById(
				"form:statefulSwitcher_input").findElements(
				By.tagName("option"));

		statefulSwitcher.findElement(By.className("ui-icon-triangle-1-s"))
				.click();
		themeList.get(2).click();
		waitUntilAjaxRequestCompletes();

		List<WebElement> styles = findElementsByTag("link");
		WebElement themeStyle = null;

		for (WebElement style : styles) {
			if (style.getAttribute("href").contains("theme.css.jsf")) {
				themeStyle = style;
				break;
			}
		}

		if (themeStyle != null) {
			assertTrue(themeStyle.getAttribute("href").endsWith(
					hiddenValues.get(2).getAttribute("value")));
		} else {
			assertTrue("link tag for theme style couldn't be found.", false);
		}

	}

	@Test
	public void shouldShowPreviewImageAndChangeThemeForThemePreview() {
		waitUntilAjaxRequestCompletes();
		List<WebElement> themeList = findElementById("form:themePreview_panel")
				.findElement(By.tagName("table"))
				.findElement(By.tagName("tbody"))
				.findElements(By.tagName("tr"));
		List<WebElement> hiddenValues = findElementById(
				"form:themePreview_input").findElements(By.tagName("option"));

		themePreview.findElement(By.className("ui-icon-triangle-1-s")).click();
		assertTrue(themeList.get(2).findElements(By.tagName("td")).get(0)
				.findElement(By.tagName("img")).getAttribute("src")
				.contains(hiddenValues.get(2).getAttribute("value")));
		themeList.get(2).click();
		waitUntilAjaxRequestCompletes();

		List<WebElement> styles = findElementsByTag("link");
		WebElement themeStyle = null;

		for (WebElement style : styles) {
			if (style.getAttribute("href").contains("theme.css.jsf")) {
				themeStyle = style;
				break;
			}
		}

		if (themeStyle != null) {
			assertTrue(themeStyle.getAttribute("href").endsWith(
					hiddenValues.get(2).getAttribute("value")));
		} else {
			assertTrue("link tag for theme style couldn't be found.", false);
		}

	}

}
