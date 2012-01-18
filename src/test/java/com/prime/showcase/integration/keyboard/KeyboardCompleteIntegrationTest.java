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
            if(findElementById("keypad-div").isDisplayed()){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Keypad division must be hidden on startup.");
            }
            
        }
        catch(NoSuchElementException e){
            assertTrue(false);
        }
    }
    
    @Test
    public void shouldUseDefaultKeyboard() throws InterruptedException{
        
        try{
            WebElement panel = findElementById("keypad-div");
            
            findElementBySelector(escapeClientId("form:default") + "[type='text']").click();
            Thread.sleep(1000);
            if(!panel.isDisplayed() && findElementsBySelector(panel, ".keypad-row .keypad-key").size() != 74){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Invalid default keyboard keys.");
            }
            
            findElementBySelector(escapeClientId("form:qwerty") + "[type='text']").click();
            Thread.sleep(1000);
            if(!panel.isDisplayed() && findElementsBySelector("#keypad-div .keypad-row .keypad-key").size() != 30){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Invalid qwerty keyboard keys.");
            }
            
            findElementBySelector(escapeClientId("form:alphabetic") + "[type='text']").click();
            Thread.sleep(1000);
            if(!panel.isDisplayed() && findElementsBySelector("#keypad-div .keypad-row .keypad-key").size() != 30){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Invalid alphabetic keyboard keys.");
            }
            
            findElementBySelector(escapeClientId("form:custom1") + "[type='text']").click();
            Thread.sleep(1000);
            if(!panel.isDisplayed() && findElementsBySelector("#keypad-div .keypad-row .keypad-key").size() != 18){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Invalid custom1 keyboard keys.");
            }
            
            findElementBySelector(escapeClientId("form:custom2") + "[type='text']").click();
            Thread.sleep(1000);
            if(!panel.isDisplayed() && findElementsBySelector("#keypad-div .keypad-row .keypad-key").size() != 30){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Invalid custom2 keyboard keys.");
            }
            
            findElementBySelector(escapeClientId("form:keypad") + "[type='text'][readonly='readonly']").click();
            Thread.sleep(1000);
            if(!panel.isDisplayed() && findElementsBySelector("#keypad-div .keypad-row .keypad-key").size() != 13){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Invalid keypad keyboard keys.");
            }
            
            findElementBySelector(escapeClientId("form:buttonmode") + "[type='text'][readonly='readonly'] + button").click();
            Thread.sleep(1000);
            if(!panel.isDisplayed() && findElementsBySelector("#keypad-div .keypad-row .keypad-key").size() != 13){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Invalid button keyboard keys.");
            }
            
            findElementBySelector(escapeClientId("form:image") + "[type='text'][readonly='readonly'] + img").click();
            Thread.sleep(1000);
            if(!panel.isDisplayed() && findElementsBySelector("#keypad-div .keypad-row .keypad-key").size() != 13){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Invalid image keyboard keys.");
            }
            
            findElementBySelector(escapeClientId("form:password") + "[type='password'][readonly='readonly']").click();
            Thread.sleep(1000);
            if(!panel.isDisplayed() && findElementsBySelector("#keypad-div .keypad-row .keypad-key").size() != 13){
                throw new NoSuchElementException("KeyboardCompleteIntegrationTest : Invalid password keyboard keys.");
            }
        }
        catch(NoSuchElementException e){
            assertTrue(false);
        }
    }
}
