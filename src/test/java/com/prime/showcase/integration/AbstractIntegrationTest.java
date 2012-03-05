package com.prime.showcase.integration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public abstract class AbstractIntegrationTest {

	protected static final String PRIME_SHOWCASE_UI = "http://localhost:8080/prime-showcase/ui/";

	private static final String JQUERY_ACTIVE_CONNECTIONS_QUERY = "return $.active == 0;";

	private static final int DEFAULT_SLEEP_TIME_IN_SECONDS = 2;

	private static final int DEFAULT_ANIMATED_INTERVAL_IN_SECONDS = 400;

	private static final int DEFAULT_TIMEOUT_IN_SECONDS = 10;

	private static final int WIDTH = 1280;

	private static final int HEIGHT = 800;

	protected static final boolean INCREASING = true;

	protected static final boolean DECREASING = false;

	protected static WebDriver driver;
    
	@BeforeClass
	public static void beforeClass() {
		
        driver = getDriver();
        
	}
    
    protected static WebDriver getDriver() {
            
        String type = System.getProperty("integrationTestsDriverType");
        String path = System.getProperty("integrationTestsDriverPath");

        if(type != null) {
            if(type.equalsIgnoreCase("chrome")) {

                System.setProperty("webdriver.chrome.driver", path );

                return new ChromeDriver();
            }
            else if(type.equalsIgnoreCase("win32ie")) {
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();  
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                return new InternetExplorerDriver(ieCapabilities);
            }
        }
        
        FirefoxDriver ff = new FirefoxDriver(prepareFirefoxProfileForFileDownload());
        ff.manage().window().setPosition(new Point(0, 0));
		ff.manage().window().setSize(new Dimension(WIDTH, HEIGHT));
        return ff;
    }

	private static FirefoxProfile prepareFirefoxProfileForFileDownload() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel,application/x-excel,application/x-msexcel,application/excel,application/pdf");
		profile.setPreference("browser.download.dir", System.getProperty("user.home"));
		return profile;
	}

	@AfterClass
	public static void afterClass() {
                driver.quit();
	}

	/**
	 * Use when element is on the page or will be on the page. Can be used
	 * element is not on the page before the ajax call and will be on the page
	 * after the ajax call
	 * 
	 * @param elementId
	 * @param value
	 */
	protected void waitUntilElementGetsValue(final String elementId, final String value) {
		new FluentWait<WebDriver>(driver).withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver wd) {
						WebElement element = wd.findElement(By.id(elementId));
						return element.getText().equals(value);
					}
				});
	}

	/**
	 * Use when element is already precisely on the page. Throws
	 * NoSuchElementException when element is not found
	 * 
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
		new FluentWait<WebDriver>(driver).withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						JavascriptExecutor jsExec = (JavascriptExecutor) d;
						return (Boolean) jsExec.executeScript(JQUERY_ACTIVE_CONNECTIONS_QUERY);
					}
				});
	}

	/**
	 * Waits until body elements animated with JS.
	 */
	protected void waitUntilAllAnimationsComplete() {
		waitUntilAnimationCompletes("body *");
	}

	/**
	 * Waits until given selector elements animated with JS.
	 * 
	 * @param selector
	 *            : jQuery element selector
	 */
	protected void waitUntilAnimationCompletes(final String selector) {
		new FluentWait<WebDriver>(driver).withTimeout(DEFAULT_TIMEOUT_IN_SECONDS * 2, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_ANIMATED_INTERVAL_IN_SECONDS, TimeUnit.MILLISECONDS)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						return (Boolean) ((JavascriptExecutor) d).executeScript("return ! $('" + selector
								+ "').is(':animated');");
					}
				});
	}

	protected WebElement findElementById(String elementId) {
		return driver.findElement(By.id(elementId));
	}

	protected WebElement findElementByLinkText(String linkText) {
		return driver.findElement(By.linkText(linkText));
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

	protected WebElement findElementByXpath(String path) {
		return driver.findElement(By.xpath(path));
	}

	protected List<WebElement> findElementsByXpath(String path) {
		return driver.findElements(By.xpath(path));
	}

	protected WebElement findElementByXpath(WebElement parent, String path) {
		return parent.findElement(By.xpath(path));
	}

	protected List<WebElement> findElementsByXpath(WebElement parent, String path) {
		return parent.findElements(By.xpath(path));
	}

	protected WebElement findElementBySelector(String selector) {
		return driver.findElement(By.cssSelector(selector));
	}

	protected List<WebElement> findElementsBySelector(String selector) {
		return driver.findElements(By.cssSelector(selector));
	}

	protected WebElement findElementBySelector(WebElement parent, String selector) {
		return parent.findElement(By.cssSelector(selector));
	}

	protected List<WebElement> findElementsBySelector(WebElement parent, String selector) {
		return parent.findElements(By.cssSelector(selector));
	}

	protected String toShowcaseUrl(String relativeUrl) {
		return PRIME_SHOWCASE_UI + relativeUrl;
	}

	protected String escapeClientId(String id) {
		return "#" + id.replaceAll(":", "\\\\:");
	}

	protected String escapeJSId(String id) {
		return "#" + id.replaceAll(":", "\\\\\\\\:");
	}

	protected boolean hasClass(WebElement e, String c) {
		return e.getAttribute("class") != null && e.getAttribute("class").contains(c);
	}

	protected Object executeJS(String js, Object... os) {
		return ((JavascriptExecutor) driver).executeScript(js, os);
	}

	protected void waitForCondition(ExpectedCondition<Boolean> condition) {
		new FluentWait<WebDriver>(driver).withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS).until(condition);
	}

	protected void waitUntilElementExists(final By by) {
		new FluentWait<WebDriver>(driver).withTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_SLEEP_TIME_IN_SECONDS, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver wd) {
						wd.findElement(by);
						return true;
					}
				});
	}

	protected Integer getAnimationQueueSizeBySelector(String selector, String queue) {
		return ((Long) executeJS("return $('" + selector + "').queue('" + queue + "').length;")).intValue();
	}

	protected Boolean anyAnimationInProgress(String selector, String queue) {
		return (Boolean) executeJS(" var q = $('" + selector + "').queue('" + queue
				+ "'); return q.length && q[0] == 'inprogress';");
	}

	protected Boolean anyAnimationInProgress(String selector) {
		return (Boolean) executeJS(" var q = $('" + selector
				+ "').queue(); return q.length != 0 && q[0] == 'inprogress';");
	}

	/**
	 * Compares given css value before and after a delay time
	 * 
	 * @param WebElement
	 *            e : UI element to look for
	 * @param String
	 *            cssValue : Style property to compare
	 * @param boolean increasing : Should increase or decrease
	 * @param long interval : Time in milliseconds to look before and after
	 * 
	 */
	protected Boolean shouldElementAnimating(WebElement e, String cssValue, boolean increasing, long interval)
			throws InterruptedException {

		String initial = e.getCssValue(cssValue);

		Thread.sleep(interval);

		String after = e.getCssValue(cssValue);

		try {

			double init = Double.parseDouble(initial.replaceAll("px", "")), last = Double.parseDouble(after.replaceAll(
					"px", "")), dif = last - init;

			return dif != 0 && (dif < 0) ^ increasing;

		} catch (NumberFormatException ex) {
			// No action. Try with string compare.
		}

		int diff = initial.compareToIgnoreCase(after);

		return diff != 0 && ((diff > 0) ^ increasing);
	}

	protected WebElement waitUntilElementExistsAndGet(WebElement element, By by) {
		return waitUntilElementExistsAndGet(element, by, 0);
	}

	protected WebElement waitUntilElementExistsAndGet(WebElement element, By by, int waitSecond) {
		WebElement item = null;
		if (element != null && by != null) {
			try {
				Thread.sleep(waitSecond * 1000L);
				item = element.findElement(by);
			} catch (Exception e) {
				return null;
			}
		}

		return item;
	}

	protected void selectElementByValue(WebElement element, String value) {
		new Select(element).selectByValue(value);
	}

	protected void clickToElementById(String elementId) {
		clickWithScroll(findElementById(elementId));
	}

	protected void clickToElementByClass(String elementClass) {
		clickWithScroll(findElementByClass(elementClass));
	}

	protected void waitForOneSecond() {
		waitForSeconds(1);
	}

	protected void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
		}
	}

	protected void scrollByOffset(int x, int y) {
		executeJS("window.scrollBy(" + x + "," + y + ")");
	}
    
	protected Boolean isTextPresent(WebElement element, String text) {
		return element.getText().contains(text);
	}

	protected Boolean isTextsPresent(WebElement element, List<String> strings) {
		String elementStr = element.getText();
		for (String aStr : strings) {
			if (!elementStr.contains(aStr)) {
				return false;
			}
		}
		return true;
	}

	protected void scrollToDefaults() {
		int width = WIDTH * -1;
		int height = HEIGHT * -1;
		executeJS("window.scrollBy(" + width + "," + height + ")");
	}

	protected void clickWithScroll(final WebElement element) {
		scrollToDefaults();
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();
		scrollByOffset(x / 2, y / 2);
		element.click();
	}
}
