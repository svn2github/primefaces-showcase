package com.prime.showcase.integration.dock;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class DockIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "dock.jsf";

    protected static SeleniumActionHelper action;
    
    protected static List<WebElement> items;
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
        items = new ArrayList<WebElement>();
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            //look top dock
            items.add(findElementBySelector("div#dockTop a#top0"));
            items.add(findElementBySelector("div#dockTop a#top1"));
            items.add(findElementBySelector("div#dockTop a#top2"));
            items.add(findElementBySelector("div#dockTop a#top3"));
            items.add(findElementBySelector("div#dockTop a#top4"));
            items.add(findElementBySelector("div#dockTop a#top5"));
            items.add(findElementBySelector("div#dockTop a#top6"));
            items.add(findElementBySelector("div#dockTop a#top7"));

            //look bottom dock
            items.add(findElementBySelector("div#dockBottom a#bottom0"));
            items.add(findElementBySelector("div#dockBottom a#bottom1"));
            items.add(findElementBySelector("div#dockBottom a#bottom2"));
            items.add(findElementBySelector("div#dockBottom a#bottom3"));
            items.add(findElementBySelector("div#dockBottom a#bottom4"));
            items.add(findElementBySelector("div#dockBottom a#bottom5"));
            items.add(findElementBySelector("div#dockBottom a#bottom6"));
            items.add(findElementBySelector("div#dockBottom a#bottom7"));
           
        }
        catch(NoSuchElementException e){
            assertTrue("Should render dock items.", false);
        }
    }
    
    @Test
    public void shouldVisitItems() throws InterruptedException {
        
        for (WebElement e : items) {
            
            Dimension ds = e.getSize();
            Point ps = e.getLocation();
            
            action.mouseHover(e);
            
            waitUntilAllAnimationsComplete();
            
            Dimension de = e.getSize();
            Point pe = e.getLocation();
            
            assertTrue("Should hover dock items.", de.height > ds.height && pe.x <= ps.x);
        }
    }
    
}
