package com.prime.showcase.integration.terminal;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.junit.Assert.assertTrue;

public class TerminalIntegrationTest extends AbstractIntegrationTest {

    public static final String TEST_URL = PRIME_SHOWCASE_UI + "terminal.jsf";

    @BeforeClass
    public static void init() {
        driver.get(TEST_URL);
    }

    @Test
    public void shouldVerifyDOM() {

        try {

            findElementBySelector("form#form " + escapeClientId("form:terminal") + " form input[type='text']");

        } catch (NoSuchElementException e) {
            assertTrue("Terminal basic showcase not verified.", false);
        }
    }

    @Test
    public void shouldTerminalGreet() throws InterruptedException {

        WebElement commandLine = findElementBySelector("form#form " + escapeClientId("form:terminal") + " form input[type='text']");
        commandLine.sendKeys("greet Prime");

        commandLine.submit();

        waitUntilAjaxRequestCompletes();


        WebElement response = findElementByXpath("//form[@id='form']//div[@id='form:terminal']/div[contains(@class, 'ui-terminal-content')]//div[contains(text(), 'Hello Prime')]");

        assertTrue("Should response displayed.", response.isDisplayed());

    }
}
