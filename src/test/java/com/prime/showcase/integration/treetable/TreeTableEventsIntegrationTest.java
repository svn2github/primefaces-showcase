package com.prime.showcase.integration.treetable;

import com.prime.showcase.integration.AbstractIntegrationTest;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class TreeTableEventsIntegrationTest extends AbstractIntegrationTest {
 
    public static final String TEST_URL = PRIME_SHOWCASE_UI + "treeTableEvents.jsf";

    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementById("form");
            
            findElementById("form:treetable");

            assertTrue("Growl should be hidden on startup.", !findElementById("form:messages").isDisplayed());
        
        }
        catch(NoSuchElementException e){
            assertTrue(false);
        }
    }
    
    @Test
    public void shouldManageTreeTable(){
        for (WebElement e : findElementsBySelector(findElementById("form:treetable"), "tbody > tr")) {
            shouldManageTree(e);
        }
    }
    
    protected void shouldManageTree(WebElement e){
        
        String nodeName = findElementBySelector(e, "td:first-child").getText();
        
        findElementBySelector(e, "td:last-child").click();
        
        waitUntilAjaxRequestCompletes();
        
        waitUntilAllAnimationsComplete();
        
        try {
            WebElement growl = findElementBySelector(".ui-growl-message");
            
            assertTrue("Should growl displayed." ,growl.isDisplayed());
            
            assertTrue("Should growl contains right results." ,growl.getText().equals("Selected\n" + nodeName));
            
        } catch (NoSuchElementException ex) {
            assertTrue("Growl should be rendered with node select.", false);
        }
        
        WebElement toggler = findElementBySelector(e, "td:first-child .ui-treetable-toggler");
        
        String expanded = e.getAttribute("aria-expanded");
        
        assertTrue("Should aria labeled.", expanded != null);
        
        String id = e.getAttribute("id");
        
        if(toggler.isDisplayed()) {
            
            List<WebElement> children = findElementsByXpath(findElementById("form:treetable"), "//tbody/tr[starts-with(@id, '"+ id +"')]");
            
            if(Boolean.valueOf(expanded)) {
                assertTrue("Should be visible child.", children.size() > 1);
            }
            else {
                assertTrue("Should be visible child.", children.size() == 1);
                toggler.click();
                waitUntilAjaxRequestCompletes();
                waitUntilAllAnimationsComplete();
                
                try {
                    WebElement growl = findElementBySelector(".ui-growl-message");

                    assertTrue("Should growl displayed." ,growl.isDisplayed());

                    assertTrue("Should growl contains right results." ,growl.getText().equals("Expanded\n" + nodeName));

                } catch (NoSuchElementException ex) {
                    assertTrue("Growl should be rendered with expand.", false);
                }
                
                e = findElementById(id);
            }
            
            children = findElementsByXpath(findElementById("form:treetable"), "//tbody/tr[starts-with(@id, '"+ id +"')]");
            
            for (WebElement c : children) {
                if(!c.getAttribute("id").equals(id)){
                    shouldManageTree(c);
                }
            }
            
            findElementBySelector(e, "td:first-child .ui-treetable-toggler").click();
            waitUntilAjaxRequestCompletes();
            waitUntilAllAnimationsComplete();

            try {
                WebElement growl = findElementBySelector(".ui-growl-message");

                assertTrue("Should growl displayed." ,growl.isDisplayed());

                assertTrue("Should growl contains right results." ,growl.getText().equals("Collapsed\n" + nodeName));

            } catch (NoSuchElementException ex) {
                assertTrue("Growl should be rendered with collapse.", false);
            }
        }
    }
}
