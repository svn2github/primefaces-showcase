package com.prime.showcase.integration.notificationbar;

import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class NotificationBarIntegrationTest extends AbstractIntegrationTest {
 
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "notificationBar.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
           
            findElementById("showbtn");
            findElementById("hidebtn");
            
            if(findElementById("notification").isDisplayed()){
                throw new NoSuchElementException("NotificationBarIntegrationTest : Notification panel should not visible on startup.");
            }
            
        }
        catch(NoSuchElementException e){
            assertTrue(false);
        }
    }
    
    @Test
    public void shouldDisplayBar() throws InterruptedException{
        
        findElementById("showbtn").click();
        Thread.sleep(1000);
        if(!findElementById("notification").isDisplayed()){
            assertTrue(false);
        }
        
        
        findElementById("hidebtn").click();
        Thread.sleep(1000);
        if(findElementById("notification").isDisplayed()){
            assertTrue(false);
        }
    }
}
