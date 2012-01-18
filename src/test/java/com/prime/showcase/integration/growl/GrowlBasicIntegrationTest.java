package com.prime.showcase.integration.growl;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.openqa.selenium.NoSuchElementException;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;

public class GrowlBasicIntegrationTest extends AbstractIntegrationTest {
 
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "growl.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM() {
        
        try{
            //fill form
            WebElement input = findElementById("form:name");
            WebElement button = findElementById("form:btn");
            WebElement growl = findElementById("form:growl");
            
            input.sendKeys("Prime");
            button.click();
            
            waitUntilAjaxRequestCompletes();
            
            assertTrue("Should display growls.", findElementsBySelector(".ui-growl .ui-growl-item").size() == 2);
        }
        catch(NoSuchElementException e){
            assertTrue("Growl showcase DOM not verified.", false);
        }
    }
}
