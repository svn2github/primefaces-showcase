package com.prime.showcase.integration.dragdrop;

import org.openqa.selenium.Point;
import java.util.List;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;

public class DragDropDataGridIntegrationTest extends AbstractIntegrationTest {

    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "dndGrid.jsf";
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

            items = findElementsBySelector(source, ".ui-draggable > .ui-widget-header");
            
            target = findElementBySelector(form,  escapeClientId("carForm:selectedCars"));
            
            dropArea = findElementBySelector(target, escapeClientId("carForm:dropArea"));
        
        }
        catch(NoSuchElementException e){
            assertTrue(false);
        }
    }
    
    @Test
    public void shouldDragItemInvalid() throws InterruptedException{
        
        for (WebElement e : items) {
            
            Point ps = e.getLocation();
            
            action.dndByOffset(e, -50, -50);
            
            Thread.sleep(1000);
            
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
            WebElement e = findElementBySelector(source, ".ui-draggable > .ui-widget-header:nth-last-child(n)");
            String text = e.getText();
            
            action.dndToElement(e, dropArea);
            
            waitUntilAjaxRequestCompletes();
            
            source = findElementBySelector(form, escapeClientId("carForm:availableCarsField") + " " + escapeClientId("carForm:availableCars"));
            target = findElementBySelector(form, escapeClientId("carForm:selectedCars"));
            dropArea = findElementBySelector(target, escapeClientId("carForm:dropArea"));
            
            try{
                findElementByXpath(source, "//div[contains(@class, 'ui-draggable')]//div[contains(@class, 'ui-widget-header')][contains(.,'" + text + "')]");
            }
            catch(NoSuchElementException ex){
                shouldNotFound = true;
            }
            
            if(!shouldNotFound){
                assertTrue(shouldNotFound);
            }
            
            try{
                findElementByXpath(dropArea, "//div[contains(@class, 'ui-dt-c')][contains(.,'" + text + "')]");
            }
            catch(NoSuchElementException ex){
                assertTrue("DnD DataGrid should provide valid interactions.", false);
            }
        }
        
    }
    
}
