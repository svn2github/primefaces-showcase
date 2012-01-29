package com.prime.showcase.integration.resizable;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ResizableAjaxIntegrationTest extends AbstractIntegrationTest {
   
    public static final String TEST_URL = PRIME_SHOWCASE_UI + "resizeAjax.jsf";
    
    protected static SeleniumActionHelper action;
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            WebElement wrapper = findElementBySelector("div.ui-wrapper");
            
            assertTrue("Resizable should be wrapped.", wrapper.isDisplayed());
            
            assertTrue("Resize handles should be placed.", findElementsBySelector(wrapper, ".ui-resizable-handle").size() == 3);
            
            WebElement image = findElementById("campnou");

            assertTrue("Should image displayed.", image.isDisplayed());

            assertTrue("Should image sourced.", !image.getAttribute("src").isEmpty());

            assertTrue("Should render hidden growl.", !findElementById("growl").isDisplayed());
           
        }
        catch(NoSuchElementException e){
            assertTrue("Resize Ajax showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldAjaxResize(){
        
        WebElement e = findElementBySelector(".ui-wrapper");
        
        resize(e, "se", 50, 50);
        
        waitUntilAjaxRequestCompletes();
        
        waitUntilAllAnimationsComplete();
        
        assertTrue("Should ajax values rendered.", findElementsBySelector(".ui-growl-message").size() == 1);
        
        assertTrue("Should ajax values displayed.", findElementBySelector(".ui-growl-message").isDisplayed());
        
        
    }
   
    protected void resize(WebElement e, String dir, int x, int y){
        action.dndByOffset(findElementBySelector(e, ".ui-resizable-handle.ui-resizable-" + dir), x, y);
    }
}