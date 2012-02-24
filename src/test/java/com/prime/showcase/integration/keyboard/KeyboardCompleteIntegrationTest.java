package com.prime.showcase.integration.keyboard;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;

public class KeyboardCompleteIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "keyboard.jsf";
    
    protected static SeleniumActionHelper action;
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementById("form:default");
            findElementById("form:qwerty");
            findElementById("form:alphabetic");
            findElementById("form:custom1");
            findElementById("form:custom2");
            findElementById("form:keypad");
            findElementById("form:buttonmode");
            findElementById("form:image");
            findElementById("form:password");
            
            assertTrue("Keypad should be hidden on startup.", !findElementById("keypad-div").isDisplayed());
            
        }
        catch(NoSuchElementException e){
            assertTrue("Keyboard showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldUseDefaultKeyboard() throws InterruptedException{
        
        WebElement panel = findElementById("keypad-div");
        WebElement title = findElementByClass("title");

        findElementBySelector(escapeClientId("form:default") + "[type='text']").click();
        Thread.sleep(1000);
        assertTrue("Should display default keyboard panel.", panel.isDisplayed());
        assertTrue("Should render valid default keyboard keys.", findElementsBySelector(panel, ".keypad-row .keypad-key").size() == 74);

        //hide previous keyboard
        title.click();
        waitUntilAllAnimationsComplete();
        
        //test new one
        findElementBySelector(escapeClientId("form:qwerty") + "[type='text']").click();
        Thread.sleep(1000);
        assertTrue("Should display qwerty keyboard panel.", panel.isDisplayed());
        assertTrue("Should render valid qwerty keyboard keys.", findElementsBySelector(panel, ".keypad-row .keypad-key").size() == 30);

        title.click();
        waitUntilAllAnimationsComplete();
        findElementBySelector(escapeClientId("form:alphabetic") + "[type='text']").click();
        Thread.sleep(1000);
        assertTrue("Should display alphabetic keyboard panel.", panel.isDisplayed());
        assertTrue("Should render valid alphabetic keyboard keys.", findElementsBySelector(panel, ".keypad-row .keypad-key").size() == 30);

        title.click();
        waitUntilAllAnimationsComplete();
        findElementBySelector(escapeClientId("form:custom1") + "[type='text']").click();
        Thread.sleep(1000);
        assertTrue("Should display custom1 keyboard panel.", panel.isDisplayed());
        assertTrue("Should render valid custom1 keyboard keys.", findElementsBySelector(panel, ".keypad-row .keypad-key").size() == 18);

        title.click();
        waitUntilAllAnimationsComplete();
        findElementBySelector(escapeClientId("form:custom2") + "[type='text']").click();
        Thread.sleep(1000);
        assertTrue("Should display custom2 keyboard panel.", panel.isDisplayed());
        assertTrue("Should render valid custom2 keyboard keys.", findElementsBySelector(panel, ".keypad-row .keypad-key").size() == 30);

        title.click();
        waitUntilAllAnimationsComplete();
        findElementBySelector(escapeClientId("form:keypad") + "[type='text'][readonly='readonly']").click();
        Thread.sleep(1000);
        assertTrue("Should display keypad keyboard panel.", panel.isDisplayed());
        assertTrue("Should render valid keypad keyboard keys.", findElementsBySelector(panel, ".keypad-row .keypad-key").size() == 13);

        title.click();
        waitUntilAllAnimationsComplete();
        findElementBySelector(escapeClientId("form:buttonmode") + "[type='text'][readonly='readonly'] + button").click();
        Thread.sleep(1000);
        assertTrue("Should display buttonmode keyboard panel.", panel.isDisplayed());
        assertTrue("Should render valid buttonmode keyboard keys.", findElementsBySelector(panel, ".keypad-row .keypad-key").size() == 13);

        title.click();
        waitUntilAllAnimationsComplete();
        findElementBySelector(escapeClientId("form:image") + "[type='text'][readonly='readonly'] + img").click();
        Thread.sleep(1000);
        assertTrue("Should display image keyboard panel.", panel.isDisplayed());
        assertTrue("Should render valid image keyboard keys.", findElementsBySelector(panel, ".keypad-row .keypad-key").size() == 13);

        title.click();
        waitUntilAllAnimationsComplete();
        findElementBySelector(escapeClientId("form:password") + "[type='password'][readonly='readonly']").click();
        Thread.sleep(1000);
        assertTrue("Should display password keyboard panel.", panel.isDisplayed());
        assertTrue("Should render valid password keyboard keys.", findElementsBySelector(panel, ".keypad-row .keypad-key").size() == 13);

    }
}
