package com.prime.showcase.integration.notificationbar;

import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class NotificationBarIntegrationTest extends AbstractIntegrationTest {
 
    
    @Test
    public void shouldInit(){
        driver.get(toShowcaseUrl("notificationBar.jsf"));
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
            throw new NoSuchElementException("NotificationBarIntegrationTest : Notification panel should display on show click.");
        }
        
        
        findElementById("hidebtn").click();
        Thread.sleep(1000);
        if(findElementById("notification").isDisplayed()){
            throw new NoSuchElementException("NotificationBarIntegrationTest : Notification panel should hide on hide click.");
        }
    }
}
