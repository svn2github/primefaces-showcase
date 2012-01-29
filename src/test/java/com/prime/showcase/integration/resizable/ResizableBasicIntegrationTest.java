package com.prime.showcase.integration.resizable;

import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.jfree.data.time.MovingAverage;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;

public class ResizableBasicIntegrationTest extends AbstractIntegrationTest {
    
    public static final String TEST_URL = PRIME_SHOWCASE_UI + "resizable.jsf";
    
    protected static SeleniumActionHelper action;
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
           
            findElementBySelector("#basic");
            findElementBySelector("#aspectRatio");
            findElementBySelector("#ghost");
            findElementBySelector("#animation");
            findElementBySelector("#boundaries");
            findElementBySelector("#grid");
            findElementBySelector("#handles");
            findElementBySelector("#containmentPanel #containment");
            
        }
        catch(NoSuchElementException e){
            assertTrue("", false);
        }
    }
    
    @Test
    public void shouldBasicResize(){
        WebElement r = findElementBySelector("#basic");
        
        Dimension d = r.getSize();
        
        resize(r, "e", 10, 10);
        
        Dimension n = r.getSize();
        
        assertTrue("Should resize correct.", n.width == d.width + 5 && n.height == d.height );
        
        d = n;
        
        
        resize(r, "s", 10, 10);

        n = r.getSize();
        
        assertTrue("Should resize correct.", n.width == d.width && n.height == d.height + 10);
        
        d = n;
        
        resize(r, "se", 10, 10);
        
        n = r.getSize();
        
        assertTrue("Should resize correct.", n.width == d.width + 10 && n.height == d.height + 10);
    }
   
    @Test
    public void shouldAspectRatio(){
        WebElement r = findElementBySelector("#aspectRatio");
        
        Dimension d = r.getSize();
        
        resize(r, "e", 20, 20);
        
        Dimension n = r.getSize();
        
        assertTrue("Should preserve ratio.", d.width / d.height == n.width / n.height);
    }
    
    @Test
    public void shouldGhostResize(){
        WebElement r = findElementBySelector("#ghost");
        
        WebElement handle = findElementBySelector(r, ".ui-resizable-handle.ui-resizable-se");
        
        Dimension d = r.getSize();
        
        action.clickAndHoldOnElement(handle);
        
        action.moveByOffSet(20, 20);
        
        assertTrue("Should one resizable helper.", findElementsBySelector(".ui-resizable-helper").size() == 1);
        
        assertTrue("Should one resizable helper displayed.", findElementsBySelector(".ui-resizable-helper").get(0).isDisplayed());
        
        Dimension n = r.getSize();
        
        assertTrue("Should preserve dimensions.", d.width == n.width && d.height == n.height);
       
        action.releaseMouse();
        
        assertTrue("Should no resizable helper.", findElementsBySelector(".ui-resizable-helper").isEmpty());
        
        n = r.getSize();
        
        assertTrue("Should preserve dimensions.", d.width == n.width - 20);
    }
   
    @Test
    public void shouldAnimatedResize(){
        
    }
    
    @Test
    public void shouldBoundryResize(){
        WebElement r = findElementBySelector("#boundaries");
        
        Dimension d = r.getSize();
        
        resize(r, "se", 400, 400);
        
        Dimension n = r.getSize();
        
        assertTrue("Should resize max.", n.height - d.height == 50 && n.width - d.width == 100);
        
        resize(r, "se", -250, -150);
        
        n = r.getSize();

        assertTrue("Should resize min.", d.height - n.height == 50 && d.width - n.width == 100);
    }
  
    @Test
    public void shouldGridResize(){
        WebElement r = findElementBySelector("#grid");
        
        Dimension d = r.getSize();
        
        resize(r, "se", 5, 5);
        
        Dimension n = r.getSize();
        
        assertTrue("Should not resize.", n.height == d.height && n.width == d.width);
        
        resize(r, "se", 10, 10);
        
        n = r.getSize();
        
        assertTrue("Should resize min.", n.height - d.height == 20 && n.width - d.width == 20);
    }
   
    @Test
    public void shouldHandlesResize(){
        
    }
    
    @Test
    public void shouldContainmentResize(){
        WebElement r = findElementBySelector("#containment");
        
        Dimension d = r.getSize();
        
        resize(r, "se", 500, 500);
        
        Dimension n = r.getSize();
        
        assertTrue("Should resize max.", n.height == 200 && n.width == 400);
    }
    
    protected void resize(WebElement e, String dir, int x, int y){
        action.dndByOffset(findElementBySelector(e, ".ui-resizable-handle.ui-resizable-" + dir), x, y);
    }
}
