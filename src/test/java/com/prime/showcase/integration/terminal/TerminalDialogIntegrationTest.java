package com.prime.showcase.integration.terminal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TerminalDialogIntegrationTest extends AbstractIntegrationTest {

    public static final String TEST_URL = PRIME_SHOWCASE_UI + "terminalDialog.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementBySelector("form#form " + escapeClientId("form:terminalImage"));
            findElementBySelector("form#form " + escapeClientId("form:terminalDialog") + " " + escapeClientId("form:terminal") + " form input[type='text']");
            
        }
        catch(NoSuchElementException e){
            assertTrue("Terminal Dialog showcase not verified.", false);
        }
    }
    
    @Test
    public void shouldManageDialogTerminal(){
        WebElement dialog = findElementBySelector("form#form " + escapeClientId("form:terminalDialog"));
        
        assertTrue("Dialog should not displayed on startup.", !dialog.isDisplayed());
        
        findElementBySelector("form#form " + escapeClientId("form:terminalImage")).click();
        
        waitUntilAllAnimationsComplete();
        
        assertTrue("Dialog should displayed on show.", dialog.isDisplayed());
        
        WebElement terminal = findElementBySelector("form#form " + escapeClientId("form:terminalDialog") + " " + escapeClientId("form:terminal"));
        
        assertTrue("Terminal should be displayed in dialog.", terminal.isDisplayed());
        
        WebElement commandLine = findElementBySelector(terminal, "form input[type='text']");
        
        commandLine.sendKeys("greet Prime");
        
        commandLine.submit();

        waitUntilAjaxRequestCompletes();

        WebElement response = findElementByXpath(terminal, "//div[contains(@class, 'ui-terminal-content')]//div[contains(text(), 'Hello Prime')]");

        assertTrue("Should response displayed.", response.isDisplayed());
        
    }
}
