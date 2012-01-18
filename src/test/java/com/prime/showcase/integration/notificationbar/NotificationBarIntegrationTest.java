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
            
            assertTrue("Notification bar should be hidden on startup.", !findElementById("notification").isDisplayed());
            
        }
        catch(NoSuchElementException e){
            assertTrue("NotificationBar showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldDisplayBar() throws InterruptedException{
        
        findElementById("showbtn").click();
        Thread.sleep(1000);
        assertTrue("Should show notification bar on command.", findElementById("notification").isDisplayed());
        
        
        findElementById("hidebtn").click();
        Thread.sleep(1000);
        assertTrue("Should hide notification bar on command.", !findElementById("notification").isDisplayed());
    }
}
