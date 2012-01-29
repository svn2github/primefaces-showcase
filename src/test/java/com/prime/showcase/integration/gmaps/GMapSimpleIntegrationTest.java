package com.prime.showcase.integration.gmaps;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.NoSuchElementException;

public class GMapSimpleIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "gmapBasic.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
           assertTrue("Should map displayed.", findElementBySelector("div#gmap").isDisplayed());
           
        }
        catch(NoSuchElementException e){
            assertTrue("GMap Simple showcase DOM not verified.", false);
        }
    }
}
