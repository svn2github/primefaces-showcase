package com.prime.showcase.integration.accordion;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class AccordionDataModelIntegrationTest extends AbstractAccordionIntegrationTest{
    
    public static final String TEST_URL = PRIME_SHOWCASE_UI + "accordionPanelModel.jsf";
    
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
            
            assertTrue("Should render valid count of accordion items.", headers.size() == 5 && contents.size() == 5);
            
            for ( int i = 0; i < headers.size(); i++) {
                WebElement h = headers.get(i);
                WebElement c = contents.get(i);
                
                assertTrue("Accordion header should be displayed.", h.isDisplayed());
                
                accordionItemShouldBe(h, c, i == 0 ? EXPANDED : NOT_EXPANDED);
            }
            
        }
        catch(NoSuchElementException e){
            assertTrue("Accordion Data Model showcase DOM not verified.", false);
        }
    }
    
    
    @Test
    public void shouldRenderModelData(){
        
        try{
            
            WebElement accordion = findElementById("accordion");
            
            //looks only any sourced images to detect model rendered
            findElementsBySelector(accordion, "h3.ui-accordion-header + div.ui-accordion-content table img[src]");
            
        }
        catch(NoSuchElementException e){
            assertTrue("Accordion modeled data not verified.", false);
        }
        
    }
    
    @Test
    public void shouldManageAccordion(){
        
        WebElement accordion = findElementById("accordion");
            
        List<WebElement> headers = findElementsBySelector(accordion, "h3.ui-accordion-header");

        List<WebElement> contents = findElementsBySelector(accordion, "h3.ui-accordion-header + div.ui-accordion-content");
        
        
        for (int i = 0; i < headers.size(); i++) {
            
            findElementBySelector(headers.get(i), "a").click();
            
            waitUntilAllAnimationsComplete();
            
            accordionItemShouldBe(headers.get(i), contents.get(i), i == 0 ? NOT_EXPANDED : EXPANDED);
            
            //should be only one expanded
            for (int j = 0; j < headers.size(); j++) {
                if( i == j) {
                    continue;
                }
                
                accordionItemShouldBe(headers.get(j), contents.get(j), NOT_EXPANDED);
                
            }
        }
    }
}
