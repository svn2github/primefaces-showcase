package com.prime.showcase.integration.accordion;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class AccordionDynamicIntegrationTest  extends AbstractAccordionIntegrationTest{
 
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "accordionPanelDynamic.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    @Override
    public void shouldVerifyDOM(){
        
        try{
            
            WebElement accordion = findElementById("form:accordion");
            
            List<WebElement> headers = findElementsBySelector(accordion, "h3.ui-accordion-header[role='tab']");
            
            List<WebElement> contents = findElementsBySelector(accordion, "h3.ui-accordion-header[role='tab'] + div.ui-accordion-content[role='tabpanel']");
            
            assertTrue("Should render valid count of accordion items.", headers.size() == 3 && contents.size() == 3);
            
            for ( int i = 0; i < headers.size(); i++) {
                WebElement h = headers.get(i);
                WebElement c = contents.get(i);
                
                assertTrue("Accordion header should be displayed.", h.isDisplayed());
                
                accordionItemShouldBe(h, c, i == 0 ? EXPANDED : NOT_EXPANDED, ENABLED, i != 0 ? NOT_LOADED : LOADED);
            }
            
        }
        catch(NoSuchElementException e){
            assertTrue("Accordion Dynamic showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldManageAccordion(){
        
        WebElement accordion = findElementById("form:accordion");
            
        List<WebElement> headers = findElementsBySelector(accordion, "h3.ui-accordion-header[role='tab']");

        List<WebElement> contents = findElementsBySelector(accordion, "h3.ui-accordion-header[role='tab'] + div.ui-accordion-content[role='tabpanel']");
        
        for ( int i = 0; i < headers.size(); i++) {
            
            findElementBySelector(headers.get(i), "a").click();
            
            waitUntilAjaxRequestCompletes();
            
            waitUntilAllAnimationsComplete();
            
            accordionItemShouldBe(headers.get(i), contents.get(i), i == 0 ? NOT_EXPANDED : EXPANDED, ENABLED, LOADED);
            
            if(i != 0){
                findElementBySelector(headers.get(i), "a").click();

                waitUntilAllAnimationsComplete();
        
            }
        }
    }
    
}
