package com.prime.showcase.integration.selectmanybutton;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SelectManyButtonBasicIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "selectManyButton.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            WebElement menu = findElementById("form:menu");
            
            assertTrue("Should render valid number of buttons.", findElementsBySelector(menu, "div.ui-button input[type='checkbox']:not([checked])").size() == 3);
            
            findElementById("form:submit");
            
            assertTrue("Result dialog should be hidden on startup.", !findElementById("form:dialog").isDisplayed());
           
        }
        catch(NoSuchElementException e){
            assertTrue("SelectManyButton showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldManageMenu(){
        
        for (WebElement e : findElementsBySelector(escapeClientId("form:menu") + " .ui-button")) {
            
            WebElement hidden = findElementBySelector(e, "input[type='checkbox']");
            
            assertTrue("Should not be actived with unchecked", !hasClass(e, "ui-state-active"));
            assertTrue("Hidden value should be valid.", !hidden.isSelected());
            
            e.click();
            
            assertTrue("Should be actived by selection.", hasClass(e, "ui-state-active"));
            assertTrue("Hidden value should change.", hidden.isSelected());
            
            e.click();
            
            assertTrue("Should remove active state.", !hasClass(e, "ui-state-active"));
            assertTrue("Hidden value should change.", !hidden.isSelected());
            
            e.click();
        }
        
        findElementById("form:submit").click();
        
        waitUntilAjaxRequestCompletes();
        
        WebElement dialog = findElementById("form:dialog");
        
        assertTrue("Should display result dialog.", dialog.isDisplayed());
        
        assertTrue("Should display valid results.", findElementsBySelector(dialog, ".ui-datalist-item").size() == 3);
    }
}
