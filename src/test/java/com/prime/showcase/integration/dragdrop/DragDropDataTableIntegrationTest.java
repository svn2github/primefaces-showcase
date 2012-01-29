package com.prime.showcase.integration.dragdrop;

import org.junit.Ignore;
import org.openqa.selenium.Point;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class DragDropDataTableIntegrationTest extends AbstractIntegrationTest {
 
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "dndTable.jsf";
    protected static SeleniumActionHelper action;
    
    protected static WebElement form;
    protected static WebElement dropArea;
    protected static WebElement target;
    protected static WebElement source;
    protected static List<WebElement> items;
    
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            form = findElementBySelector("form#carForm");
            
            source = findElementBySelector(form, escapeClientId("carForm:availableCarsField") + " " + escapeClientId("carForm:availableCars"));

            items = findElementsBySelector(source, ".ui-draggable");

            target = findElementBySelector(form, escapeClientId("carForm:selectedCars"));
            
            dropArea = findElementBySelector(target, escapeClientId("carForm:dropArea"));
        
        }
        catch(NoSuchElementException e){
            assertTrue("DnD Datatable showcase not verified.", false);
        }
    }
    
    @Test
    public void shouldDragItemInvalid() throws InterruptedException{
        for (WebElement e : items) {
            Point ps = e.getLocation();
            
            action.dndByOffset(e, 10, 0);
            
            waitUntilAllAnimationsComplete();
            
            Point pe = e.getLocation();
            
            //should return startup position
            if(ps.x != pe.x || ps.y != pe.y){
                assertTrue(false);
            }
        }
    }
    
    
    @Test
    public void shouldDragItemValid() throws InterruptedException{
        
        scrollByOffset(0, 300);
        
        for (int i = items.size(); i > 0 ; i--) {
            
            boolean shouldNotFound = false;
            WebElement e = findElementBySelector(source, escapeClientId("carForm:availableCars_data") + " > tr:nth-last-child(n)");
            
            WebElement drag = findElementBySelector(e, "td:first-child .ui-draggable");

            String text = findElementBySelector(e, "td:first-child + td").getText();
            
            action.dndToElement(drag, dropArea);
            
            waitUntilAjaxRequestCompletes();
            
            source = findElementBySelector(form, escapeClientId("carForm:availableCarsField") + " " + escapeClientId("carForm:availableCars"));
            dropArea = findElementBySelector(target, escapeClientId("carForm:dropArea"));
            target = findElementBySelector(form, escapeClientId("carForm:selectedCars"));
            
            try{
                findElementByXpath(source, "/table//div[contains(@class, 'ui-dt-c')][contains(.,'" + text + "')]");
            }
            catch(NoSuchElementException ex){
                shouldNotFound = true;
            }
            
            if(!shouldNotFound){
                assertTrue("Should remove from source.", shouldNotFound);
            }
            
            try{
                findElementByXpath(dropArea, "//div[contains(@class, 'ui-dt-c')][contains(.,'" + text + "')]");
            }
            catch(NoSuchElementException ex){
                assertTrue("Should insert element to target.", false);
            }
        }
    }
}
