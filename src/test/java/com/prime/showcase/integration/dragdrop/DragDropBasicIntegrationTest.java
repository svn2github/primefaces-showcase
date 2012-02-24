package com.prime.showcase.integration.dragdrop;

import java.util.List;
import org.openqa.selenium.Point;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;

public class DragDropBasicIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "draggableBasic.jsf";
    protected static SeleniumActionHelper action;
    
    protected static WebElement basic;
    protected static WebElement handle;
    protected static WebElement horizontal;
    protected static WebElement vertical;
    protected static WebElement clone;
    protected static WebElement revert;
    protected static WebElement opacity;
    protected static WebElement grid;
    protected static WebElement restricted;
    protected static WebElement image;
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            basic = findElementBySelector("div.ui-draggable#pnl");
            
            handle = findElementBySelector("div.ui-draggable#handlepnl");
           
            horizontal = findElementBySelector("div.ui-draggable#hpnl");
            
            vertical = findElementBySelector("div.ui-draggable#vpnl");
            
            clone = findElementBySelector("div.ui-draggable#cpnl");
            
            revert = findElementBySelector("div.ui-draggable#rpnl");
            
            opacity = findElementBySelector("div.ui-draggable#opnl");
            
            grid = findElementBySelector("div.ui-draggable#gpnl");
            
            restricted = findElementBySelector("div#restrictPanel div.ui-draggable#conpnl");
            
            image = findElementBySelector("img.ui-draggable#pic");
        }
        catch(NoSuchElementException e){
            assertTrue(false);
        }
    }
    
    @Test
    public void shouldRegularDraggable(){
        //start position
        Point ps = basic.getLocation();
        
        //try to drag container
        action.dndByOffset(basic, 50, 50);
        
        //end position
        Point pe = basic.getLocation();
        
        assertTrue("Regular draggable should move correct.", pe.x - ps.x == 50 && pe.y - ps.y == 50);
    }
    
    @Test
    public void shouldHeaderDraggable(){
        //start position
        Point ps = handle.getLocation();
        
        //try to drag container
        action.dndByOffset(handle, 50, 50);
        
        //end position
        Point pe = handle.getLocation();
        
        assertTrue("Should not move without dragging header.", pe.x == ps.x && pe.y == ps.y);
        
        //try to drag with header
        WebElement header = findElementBySelector("div.ui-draggable#handlepnl #handlepnl_header");
        
        action.dndByOffset(header, 50, 50);
        
        pe = handle.getLocation();
        
        assertTrue("Should move with dragging header.", pe.x - ps.x == 50 && pe.y - ps.y == 50);
    }
    
    @Test
    public void shouldHorizontalDraggable(){
        //start position
        Point ps = horizontal.getLocation();
        
        //try to drag container
        action.dndByOffset(horizontal, 50, 50);
        
        //end position
        Point pe = horizontal.getLocation();
        
        assertTrue("Should move only horizontal", pe.x - ps.x == 50 && pe.y == ps.y);
    }
    
    @Test
    public void shouldVerticalDraggable(){
        //start position
        Point ps = vertical.getLocation();
        
        //try to drag container
        action.dndByOffset(vertical, 50, 50);
        
        //end position
        Point pe = vertical.getLocation();
        
        assertTrue("Should move only vertical.", pe.x == ps.x && pe.y - ps.y == 50);
    }
    
    @Test
    public void shouldCloneDraggable() throws InterruptedException{
        
        Point ps = clone.getLocation();
        
        action.clickAndHoldOnElement(clone);
        
        action.moveByOffSet(10, 10);
        
        Point pe = clone.getLocation();
        
        assertTrue("Real panel should stay.", ps.x == pe.x && ps.y == pe.y);
        
        List<WebElement> copies = findElementsBySelector(".ui-draggable.ui-draggable-dragging");
        assertTrue("Should be only one dragging.", copies.size() == 1);
        
        Dimension cd = copies.get(0).getSize();
        Dimension od = clone.getSize();
        
        assertTrue("Should clone right.", cd.height == od.height && cd.width == od.width);
        
        action.releaseMouse();
        
        assertTrue("Should delete clone.", findElementsBySelector(".ui-draggable.ui-draggable-dragging").isEmpty());
    
        pe = clone.getLocation();
        
        assertTrue("Real panel should stay.", ps.x == pe.x && ps.y == pe.y);
    }
    
    @Test
    public void shouldRevertDraggable() throws InterruptedException{
        //start position
        Point ps = revert.getLocation();
        
//        scrollByOffset(0, 200);
        
        action.clickAndHoldOnElement(revert);
        
        action.moveByOffSet(10, 10);
        
        assertTrue("Should labeled as dragging.", hasClass(revert, "ui-draggable-dragging"));
        
        Point pe = revert.getLocation();
        
        assertTrue("Should move right.", ps.x == pe.x - 10 && ps.y == pe.y - 10);
        
        action.releaseMouse();
        
        waitUntilAllAnimationsComplete();
        
        pe = revert.getLocation();
        
        assertTrue("Should come back.", ps.x == pe.x && ps.y == pe.y);
    }
    
    @Ignore("Not ready")
    @Test
    public void shouldOpacityDraggable(){
        //start position
        Point ps = opacity.getLocation();
        
        //try to drag container
        scrollByOffset(0, 100);
        
        action.clickAndHoldOnElement(opacity);
        
        action.moveByOffSet(10, 10);
        
        assertTrue("Should opacity lower.", Double.valueOf(opacity.getCssValue("opacity")) < 1);
        
        action.releaseMouse();
        
        //end position
        Point pe = opacity.getLocation();
        
        assertTrue("Should move right.", (ps.x == pe.x - 10) && (ps.y == pe.y - 10));
    }
    
    @Test
    public void shouldGridDraggable() throws InterruptedException{
        //start position
        Point ps = grid.getLocation();
        
        //try to drag container
        action.clickAndHoldOnElement(grid);
        
        action.moveByOffSet(5, 5);
        
        //end position
        
        Point pe = grid.getLocation();

        assertTrue("Should not move if lower than grid.", pe.x == ps.x && pe.y == ps.y);
        
        action.moveByOffSet(10, 10);
        
        pe = grid.getLocation();
        
        
        assertTrue("Should move if higher than grid.", pe.x == ps.x + 20);
        
        action.releaseMouse();
        
        pe = grid.getLocation();
        
        assertTrue("Should placed right.", pe.x == ps.x + 20);
    }
   
    @Test
    public void shouldRestrictDraggable() throws InterruptedException{
        //start position
        Point ps = restricted.getLocation();
        
        //try to drag container
        action.dndByOffset(restricted, 50, 50);
        
        //end position
        Point pe = restricted.getLocation();
        
        assertTrue("Restrict draggable should move correct.", pe.x - ps.x == 50 && pe.y - ps.y == 50);
        
        //try to drag container
        action.dndByOffset(restricted, 100, 100);
        
        //end position
        pe = restricted.getLocation();
        
        assertTrue("Restrict draggable should not move.", pe.x - ps.x < 150 && pe.y - ps.y < 150);
        
    }
    
    @Test
    public void shouldImageDraggable(){
        
        scrollByOffset(0, 200);
        
        //start position
        Point ps = image.getLocation();
        
        //try to drag container
        action.dndByOffset(image, 50, 50);
        
        //end position
        Point pe = image.getLocation();
        
        assertTrue("Image draggable should move correct.", pe.x - ps.x == 50 && pe.y - ps.y == 50);
        
    }
    
    
}
