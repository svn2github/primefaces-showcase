package com.prime.showcase.integration.accordion;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;

public abstract class AbstractAccordionIntegrationTest extends AbstractIntegrationTest{
    
    public static final Boolean DISABLED = true;
    public static final Boolean ENABLED = false;
    public static final Boolean EXPANDED = true;
    public static final Boolean NOT_EXPANDED = false;
    public static final Boolean LOADED = true;
    public static final Boolean NOT_LOADED = false;
    
    protected void accordionItemShouldBe( WebElement h, WebElement c, boolean expanded) {
        accordionItemShouldBe(h, c, expanded, ENABLED, LOADED);
    }
    
    protected void accordionItemShouldBe( WebElement h, WebElement c, boolean expanded, boolean disabled) {
        accordionItemShouldBe(h, c, expanded, disabled, LOADED);
    }
    
    protected void accordionItemShouldBe( WebElement h, WebElement c, boolean expanded, boolean disabled, boolean loaded ) {
        
        assertTrue("Should be valid header style.", hasClass(h, "ui-state-active") ^ !expanded);
        
        assertTrue("Should be valid aria expanded value.", h.getAttribute("aria-expanded") != null && h.getAttribute("aria-expanded").equals(String.valueOf(expanded)));
        
        assertTrue("Should be valid content status.", c.isDisplayed() ^ !expanded);
        
        assertTrue("Should be valid aria content hidden value.", c.getAttribute("aria-hidden") != null && c.getAttribute("aria-hidden").equals(String.valueOf(!expanded)));
            
        assertTrue("Should be valid lazy status.", findElementsBySelector(c, ":first-child").isEmpty() ^ loaded);
        
        assertTrue("Should be valid icon status.", hasClass(findElementBySelector(h, ".ui-icon"), "ui-icon-triangle-1-s") ^ !expanded);
        
        assertTrue("Should be valid disabled status.", hasClass(h, "ui-state-disabled") ^ !disabled);
    }
    
    public void shouldVerifyDOM(){
        
        try{
            
            WebElement accordion = findElementById("accordion");
            
            List<WebElement> headers = findElementsBySelector(accordion, "h3.ui-accordion-header[role='tab']");
            
            List<WebElement> contents = findElementsBySelector(accordion, "h3.ui-accordion-header[role='tab'] + div.ui-accordion-content[role='tabpanel']");
            
            assertTrue("Should render valid count of accordion items.", headers.size() == 3 && contents.size() == 3);
            
            for ( int i = 0; i < headers.size(); i++) {
                WebElement h = headers.get(i);
                WebElement c = contents.get(i);
                
                assertTrue("Accordion header should be displayed.", h.isDisplayed());
                
                accordionItemShouldBe(h, c, i == 0 ? EXPANDED : NOT_EXPANDED);
            }
            
        }
        catch(NoSuchElementException e){
            assertTrue("Accordion showcase DOM not verified.", false);
        }
    }
}
