package com.prime.showcase.integration.selectmanymenu;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SelectManyMenuIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "selectManyMenu.jsf";
    
    protected static SeleniumActionHelper action;
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
           
            WebElement basic =  findElementById("form:basic");
            
            List<WebElement> items = findElementsBySelector(basic, "li");
            WebElement hidden_input = findElementBySelector(basic, "select[multiple]");
            List<WebElement> hidden_items = findElementsBySelector(hidden_input, "option:not([selected])");
            
            assertTrue("Should render valid number of items.", items.size() == 3);
            assertTrue("Should render valid number of hiddens.", hidden_items.size() == 3);
            
            for (int i = 0; i < items.size(); i++) {
                
                WebElement e = items.get(i);
                WebElement h = hidden_items.get(i);
                
                assertThat("Should labels match.", e.getText(), equalTo(h.getAttribute("value")));
            }
            
            WebElement scroll =  findElementById("form:scroll");
            
            items = findElementsBySelector(scroll, "li");
            hidden_input = findElementBySelector(scroll, "select[multiple]");
            hidden_items = findElementsBySelector(scroll, "option:not([selected])");
            
            assertTrue("Should render valid number of items.", items.size() == 13);
            
            assertTrue("Should render valid number of hiddens.", hidden_items.size() == 13);
            
            findElementById("form:scroll");
            
            findElementById("form:btn");
            
            findElementById("form:dialog");
            
        }
        catch(NoSuchElementException e){
            assertTrue("SelectManyMenu showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldSelectMany(){
        
        WebElement submit = findElementById("form:btn");
            
        WebElement dialog = findElementById("form:dialog");
        
        assertTrue("Results dialog be hidden on startup.", !dialog.isDisplayed());
        
        int basicResult = testGivenMenu(findElementById("form:basic"));
        
        int scrollResult = testGivenMenu(findElementById("form:scroll"));
        
        submit.click();
        
        waitUntilAjaxRequestCompletes();
        
        assertTrue("Should display result dialog.", dialog.isDisplayed());
        
        assertTrue("Should display valid results.", findElementsBySelector(dialog, escapeClientId("form:selectedOptions") + " li").size() == 1);
        
        assertTrue("Should display valid results.", findElementsBySelector(dialog, escapeClientId("form:selectedPlayers") + " li").size() == 1);
    }
    
    protected int testGivenMenu( WebElement menu ){
        
        List<WebElement> items = findElementsBySelector(menu, "li");
        WebElement hidden_input = findElementBySelector(menu, "select[multiple]");
        List<WebElement> hidden_items = findElementsBySelector(hidden_input, "option");
        
        action.keyDown(Keys.CONTROL);
        action.keyDown(Keys.ALT);
        
        for (int i = 0; i < items.size(); i++) {
            
            WebElement e = items.get(i);
            WebElement h = hidden_items.get(i);
            
            assertTrue("Should not be active stated without selection.", !hasClass(e, "ui-state-active"));
            
            e.click();
            
            assertTrue("Should be selected.", h.isSelected());
            
            assertTrue("Should be active stated.", hasClass(e, "ui-state-active"));
            
            assertTrue("Should be only one selected.", findElementsBySelector(menu, "li.ui-state-active").size() == 1);
        }
        
        action.keyUp(Keys.ALT);
        action.keyUp(Keys.CONTROL);
        
   
        /* META KEY NOT WORKING ON OSX FirefoxDriver.
         
        action.keyDown(Keys.META);
        
        for (int i = 0; i < items.size(); i++) {
            
            WebElement e = items.get(i);
            WebElement h = hidden_items.get(i);
            
            if(i != items.size() - 1){
                assertTrue("Should not be active stated without selection.", !hasClass(e, "ui-state-active"));

                e.click();

                assertTrue("Should be selected.", h.isSelected());

                assertTrue("Should be active stated.", hasClass(e, "ui-state-active"));

                assertTrue("Should be cumulative selected.", findElementsBySelector(menu, "li.ui-state-active").size() == i + 2);
            }
            else {
                assertTrue("Should be active stated at first.", hasClass(e, "ui-state-active"));

                e.click();

                assertTrue("Should be deselected.", !h.isSelected());

                assertTrue("Should be deactivated.", !hasClass(e, "ui-state-active"));

                assertTrue("Should be cumulative selected.", findElementsBySelector(menu, "li.ui-state-active").size() == items.size() - 1);
            }
        }
        
        action.keyUp(Keys.META);
        */
        
        return items.size() - 1;
    }
    
}
