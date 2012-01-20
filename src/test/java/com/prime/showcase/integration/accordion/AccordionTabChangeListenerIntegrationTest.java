package com.prime.showcase.integration.accordion;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import org.openqa.selenium.WebElement;

public class AccordionTabChangeListenerIntegrationTest extends AbstractAccordionIntegrationTest {
 
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "accordionPanelChangeListener.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    @Override
    public void shouldVerifyDOM(){
        super.shouldVerifyDOM();
    }
    
    @Test
    public void shouldManageAccordion(){
        
        WebElement accordion = findElementById("accordion");
            
        List<WebElement> headers = findElementsBySelector(accordion, "h3.ui-accordion-header[role='tab']");

        List<WebElement> contents = findElementsBySelector(accordion, "h3.ui-accordion-header[role='tab'] + div.ui-accordion-content[role='tabpanel']");
        
        for ( int i = 1; i < headers.size(); i++) {
            
            WebElement link = findElementBySelector(headers.get(i), "a");
            
            link.click();
            
            waitUntilAjaxRequestCompletes();
            
            waitUntilAllAnimationsComplete();
            
            accordionItemShouldBe(headers.get(i), contents.get(i), EXPANDED);

            List<WebElement> growl = findElementsBySelector(".ui-growl .ui-growl-item-container .ui-growl-item .ui-growl-message");

            assertTrue("Should catch growl.", growl.size() == 1);
            
            assertTrue("Should display growl.", growl.get(0).isDisplayed());
            
            String growlTitle = findElementBySelector(growl.get(0), "span.ui-growl-title").getText();
            
            assertThat("Should execute listener correct with title.", growlTitle, equalTo("Tab Changed"));
            
            String growlMessage = findElementBySelector(growl.get(0), "p").getText();
            
            assertThat("Should execute correct item.", growlMessage, containsString(link.getText()));
            
            //dont miss zero
            if(i == headers.size() - 1){
                i = -1;
                continue;
            }
            else if( i == 0 ){ //end
                break;
            }
        }
    }
   
}
