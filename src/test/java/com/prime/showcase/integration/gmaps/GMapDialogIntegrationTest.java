package com.prime.showcase.integration.gmaps;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.openqa.selenium.NoSuchElementException;

public class GMapDialogIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "gmapDialog.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementById("btn");
            
            assertTrue("Gmap dialog should not displayed on startup.", !findElementBySelector("div#gmapDialog").isDisplayed());
           
            assertTrue("Gmap should not displayed on startup.", !findElementBySelector("div#gmap").isDisplayed());
           
        }
        catch(NoSuchElementException e){
            assertTrue("GMap Dialog showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldManageMap(){
        
        findElementById("btn").click();
        
        waitUntilAllAnimationsComplete();
        
        assertTrue("Should display dialog.", findElementBySelector("div#gmapDialog").isDisplayed());
        
        assertTrue("Should display map.", findElementBySelector("div#gmap").isDisplayed());
    }
}
