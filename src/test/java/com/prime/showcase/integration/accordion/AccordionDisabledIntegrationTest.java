package com.prime.showcase.integration.accordion;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class AccordionDisabledIntegrationTest  extends AbstractAccordionIntegrationTest{
 
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "accordionPanelDisabled.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    @Override
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
                
                accordionItemShouldBe(h, c, i == 0 ? EXPANDED : NOT_EXPANDED, i == 1 ? DISABLED : ENABLED);
            }
            
        }
        catch(NoSuchElementException e){
            assertTrue("Accordion Disabled showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldManageAccordion(){
        
        WebElement accordion = findElementById("accordion");
            
        List<WebElement> headers = findElementsBySelector(accordion, "h3.ui-accordion-header");

        List<WebElement> contents = findElementsBySelector(accordion, "h3.ui-accordion-header + div.ui-accordion-content");
        
        for ( int i = 0; i < headers.size(); i++) {
            
            findElementBySelector(headers.get(i), "a").click();

            waitUntilAllAnimationsComplete();

            accordionItemShouldBe(headers.get(i), contents.get(i), i == 1 || i == 0 ? NOT_EXPANDED : EXPANDED, i == 1 ? DISABLED : ENABLED);
        }
        
        
    }
    
}
