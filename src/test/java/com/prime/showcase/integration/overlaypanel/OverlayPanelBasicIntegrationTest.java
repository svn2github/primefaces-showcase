package com.prime.showcase.integration.overlaypanel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class OverlayPanelBasicIntegrationTest extends AbstractIntegrationTest{
 
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "overlayPanel.jsf";
    protected static SeleniumActionHelper action;
    
    @BeforeClass
    public static void init() {
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    @Test
    public void shouldVerifyDOM() {
        
        try{
            
            findElementById("form:carBtn");
            assertTrue("Car panel should be hidden on startup.", !findElementById("form:carPanel").isDisplayed());
            
            findElementById("form:chartBtn");
            assertTrue("Chart panel should be hidden on startup.", !findElementById("form:chartPanel").isDisplayed());
            
            findElementById("form:img");
            assertTrue("Image panel should be hidden on startup.", !findElementById("form:imgPanel").isDisplayed());
            
        }
        catch(NoSuchElementException e){
            assertTrue("OverlayPanel showcase DOM not verified.", false);
        }
    }
   
    @Test
    public void shouldDisplayPanels() throws InterruptedException {
        
        WebElement car = findElementBySelector(escapeClientId("form:carPanel"));
        WebElement image = findElementBySelector(escapeClientId("form:imgPanel"));
        WebElement chart = findElementBySelector(escapeClientId("form:chartPanel"));
        WebElement title = findElementBySelector(".title");
        
        findElementById("form:chartBtn").click();
        waitUntilAllAnimationsComplete();
        assertTrue("Should only display chart panel.", chart.isDisplayed() && !car.isDisplayed() && !image.isDisplayed());
        
        title.click();
        waitUntilAllAnimationsComplete();
        findElementById("form:carBtn").click();
        waitUntilAjaxRequestCompletes();
        chart = findElementBySelector(escapeClientId("form:chartPanel"));
        waitUntilAllAnimationsComplete();
        assertTrue("Should only display car panel.", car.isDisplayed() && !chart.isDisplayed() && !image.isDisplayed());

        title.click();
        waitUntilAllAnimationsComplete();
        WebElement img = findElementById("form:img");
        action.mouseHover(img);
        waitUntilAllAnimationsComplete();
        assertTrue("Should only display image panel.", image.isDisplayed() && !car.isDisplayed() && !chart.isDisplayed());
        
        title.click();
        waitUntilAllAnimationsComplete();
        assertTrue("Should none display.", !image.isDisplayed() && !car.isDisplayed() && !chart.isDisplayed());
        
    }
}
