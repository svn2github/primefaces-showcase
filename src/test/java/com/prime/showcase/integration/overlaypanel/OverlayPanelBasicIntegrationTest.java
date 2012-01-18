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
            if(findElementById("form:carPanel").isDisplayed()){
                throw new NoSuchElementException("OverlayPanelBasicIntegrationTest : Car panel should not display on startup.");
            }
            
            findElementById("form:chartBtn");
            if(findElementById("form:chartPanel").isDisplayed()){
                throw new NoSuchElementException("OverlayPanelBasicIntegrationTest : Chart panel should not display on startup.");
            }
            
            findElementById("form:imgBtn");
            if(findElementById("form:imgPanel").isDisplayed()){
                throw new NoSuchElementException("OverlayPanelBasicIntegrationTest : Image panel should not display on startup.");
            }
            
        }
        catch(NoSuchElementException e){
            assertTrue(false);
        }
    }
   
    @Test
    public void shouldDisplayPanels() throws InterruptedException{
        
        WebElement car = findElementBySelector(escapeClientId("form:carPanel") + " " + escapeClientId("form:table"));
        WebElement chart = findElementBySelector(escapeClientId("form:chartPanel") + " " + escapeClientId("form:chart"));
        WebElement image = findElementBySelector(escapeClientId("form:imgPanel") + " " + escapeClientId("form:img"));
        
        findElementById("form:carBtn").click();
        Thread.sleep(500);
        if(!car.isDisplayed() && (chart.isDisplayed() || image.isDisplayed())){
            assertTrue(false);
        }

        findElementById("form:chartBtn").click();
        Thread.sleep(500);
        if(!chart.isDisplayed() && (car.isDisplayed() || image.isDisplayed())){
            assertTrue(false);
        }

        findElementById("form:imgBtn").click();
        Thread.sleep(500);
        if(!image.isDisplayed() && (car.isDisplayed() || chart.isDisplayed())){
            assertTrue(false);
        }
    }
}
