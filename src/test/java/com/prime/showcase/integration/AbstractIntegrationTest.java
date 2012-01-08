package com.prime.showcase.integration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

public abstract class AbstractIntegrationTest {

	private static final String PRIME_SHOWCASE_UI = "http://localhost:8080/prime-showcase/ui/";

	private static final String JQUERY_ACTIVE_CONNECTIONS_QUERY = "return $.active == 0;";

	private static final int DEFAULT_SLEEP_TIME_IN_SECONDS = 2;

	private static final int DEFAULT_TIMEOUT_IN_SECONDS = 10;

	protected static WebDriver driver;

	@BeforeClass
	public static void beforeClass() {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void afterClass() {
		driver.quit();
	}

	/**
	 * Use when element is on the page or will be on the page. Can be used element is not on the page before the ajax call and will be on the page after the ajax call
	 * @param elementId
	 * @param value
	 */
	protected void waitUntilElementGetsValue(final String elementId, final String value) {
		new FluentWait<WebDriver>(driver).withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver wd) {
						WebElement element = wd.findElement(By.id(elementId));
						return element.getText().equals(value);
					}
				});
	}

	
	/**
	 * Use when element is already precisely on the page. Throws NoSuchElementException when element is not found
	 * @param elementId
	 * @param value
	 */
	protected void waitUntilElementExistsAndGetsValue(final String elementId, final String value) {
		new FluentWait<WebDriver>(driver).withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver wd) {
						WebElement element = wd.findElement(By.id(elementId));
						return element.getText().equals(value);
					}
				});
	}

	protected void waitUntilAjaxRequestCompletes() {
		new FluentWait<WebDriver>(driver).withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS).pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						JavascriptExecutor jsExec = (JavascriptExecutor) d;
						return (Boolean) jsExec.executeScript(JQUERY_ACTIVE_CONNECTIONS_QUERY);
					}
				});
	}

	protected WebElement findElementById(String elementId) {
		return driver.findElement(By.id(elementId));
	}

	protected List<WebElement> findElementsById(String elementId) {
		return driver.findElements(By.id(elementId));
	}
	
	protected WebElement findElementByTag(String tagName) {
		return driver.findElement(By.tagName(tagName));
	}
	
	protected List<WebElement> findElementsByTag(String tagName) {
		return driver.findElements(By.tagName(tagName));
	}

	protected WebElement findElementByName(String elementName) {
		return driver.findElement(By.name(elementName));
	}

	protected List<WebElement> findElementsByName(String elementName) {
		return driver.findElements(By.name(elementName));
	}
	
	protected WebElement findElementByClass(String className) {
		return driver.findElement(By.className(className));
	}

	protected List<WebElement> findElementsByClass(String className) {
		return driver.findElements(By.className(className));
	}
	
	protected String toShowcaseUrl(String relativeUrl) {
		return PRIME_SHOWCASE_UI + relativeUrl;
	}
	
}
