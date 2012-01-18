package com.prime.showcase.integration.overlaypanel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class OverlayPanelBasicIntegrationTest extends AbstractIntegrationTest{
 
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "overlayPanel.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementById("form:carBtn");
            assertTrue("Car panel should be hidden on startup.", !findElementById("form:carPanel").isDisplayed());
            
            findElementById("form:chartBtn");
            assertTrue("Chart panel should be hidden on startup.", !findElementById("form:chartPanel").isDisplayed());
            
            findElementById("form:imgBtn");
            assertTrue("Image panel should be hidden on startup.", !findElementById("form:imgPanel").isDisplayed());
            
        }
        catch(NoSuchElementException e){
            assertTrue("OverlayPanel showcase DOM not verified.", false);
        }
    }
   
    @Test
    public void shouldDisplayPanels() throws InterruptedException{
        
        WebElement car = findElementBySelector(escapeClientId("form:carPanel") + " " + escapeClientId("form:table"));
        WebElement chart = findElementBySelector(escapeClientId("form:chartPanel") + " " + escapeClientId("form:chart"));
        WebElement image = findElementBySelector(escapeClientId("form:imgPanel") + " " + escapeClientId("form:img"));
        
        findElementById("form:carBtn").click();
        Thread.sleep(500);
        assertTrue("Should only display car panel.", car.isDisplayed() && !chart.isDisplayed() && !image.isDisplayed());

        findElementById("form:chartBtn").click();
        Thread.sleep(500);
        assertTrue("Should only display chart panel.", chart.isDisplayed() && !car.isDisplayed() && !image.isDisplayed());

        findElementById("form:imgBtn").click();
        Thread.sleep(500);
        assertTrue("Should only display image panel.", image.isDisplayed() && !car.isDisplayed() && !chart.isDisplayed());
    }
}
