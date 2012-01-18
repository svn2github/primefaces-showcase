package com.prime.showcase.integration.selectmanycheckbox;

import com.prime.showcase.integration.AbstractIntegrationTest;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SelectManyCheckboxBasicIntegrationTest extends AbstractIntegrationTest {

    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "selectManyCheckbox.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            WebElement horizontal = findElementById("form:horizontal");
            
            List<WebElement> boxes = findElementsBySelector(horizontal, ".ui-chkbox-box");
            List<WebElement> hiddens = findElementsBySelector(horizontal, "input[type='checkbox']:not([checked])");
            List<WebElement> labels = findElementsBySelector(horizontal, "label");
            
            assertTrue("Should render valid number of boxes.", boxes.size() == 3);
            assertTrue("Should render valid number of hiddens.", hiddens.size() == 3);
            assertTrue("Should render valid number of labels.", labels.size() == 3);
            
            for(int i = 0; i < boxes.size(); i++){
                
                WebElement b = boxes.get(i);
                WebElement h = hiddens.get(i);
                WebElement l = labels.get(i);
                WebElement icon = findElementBySelector(b, ".ui-chkbox-icon");
                
                assertTrue("Icon should be hidden with false.", !icon.isDisplayed());
                
                assertTrue("Should not active stated.", !hasClass(b, "ui-state-active"));
                
                assertTrue("Input element should be hidden.", !h.isDisplayed());
                
                assertThat("Should labels match.", l.getText(), equalTo(h.getAttribute("value")));
            }
            
            
            WebElement vertical = findElementById("form:vertical");
            
            boxes = findElementsBySelector(vertical, ".ui-chkbox-box");
            hiddens = findElementsBySelector(vertical, "input[type='checkbox']:not([checked])");
            labels = findElementsBySelector(vertical, "label");
            
            assertTrue("Should render valid number of boxes.", boxes.size() == 4);
            assertTrue("Should render valid number of hiddens.", hiddens.size() == 4);
            assertTrue("Should render valid number of labels.", labels.size() == 4);
            
            for(int i = 0; i < boxes.size(); i++){
                
                WebElement b = boxes.get(i);
                WebElement h = hiddens.get(i);
                WebElement l = labels.get(i);
                WebElement icon = findElementBySelector(b, ".ui-chkbox-icon");
                
                assertTrue("Icon should be hidden with false.", !icon.isDisplayed());
                
                assertTrue("Should not active stated.", !hasClass(b, "ui-state-active"));
                
                assertTrue("Input element should be hidden.", !h.isDisplayed());
                
                assertThat("Should labels match.", l.getText(), equalTo(h.getAttribute("value")));
            }
            
            findElementById("form:submit");
            
            assertTrue("Results dialog should be hidden.", !findElementById("form:dialog").isDisplayed());
            
        }
        catch(NoSuchElementException e){
            assertTrue(false);
        }
    }
    
    @Test
    public void shouldManageMenu(){
        
        WebElement horizontal = findElementById("form:horizontal");
            
        List<WebElement> boxes = findElementsBySelector(horizontal, ".ui-chkbox-box");
        List<WebElement> hiddens = findElementsBySelector(horizontal, "input[type='checkbox']:not([checked])");
        List<WebElement> labels = findElementsBySelector(horizontal, "label");
        
        WebElement vertical = findElementById("form:vertical");
        
        boxes.addAll(findElementsBySelector(vertical, ".ui-chkbox-box"));
        hiddens.addAll(findElementsBySelector(vertical, "input[type='checkbox']:not([checked])"));
        labels.addAll(findElementsBySelector(vertical, "label"));
        
        for(int i = 0; i < boxes.size(); i++){
                
            WebElement b = boxes.get(i);
            WebElement icon = findElementBySelector(b, ".ui-chkbox-icon");
            WebElement h = hiddens.get(i);
            WebElement l = labels.get(i);
            
            b.click();
            
            assertTrue("Should be active stated.", hasClass(b, "ui-state-active"));
            
            assertTrue("Should change hidden input.", h.isSelected());
            
            assertTrue("Icon should have displayed.", icon.isDisplayed());
            
            assertTrue("Icon should have checked.", hasClass(icon, "ui-icon-check"));
            
            l.click();
            
            assertTrue("Should be deactived.", !hasClass(b, "ui-state-active"));
            
            assertTrue("Should change hidden input.", !h.isSelected());
            
            assertTrue("Icon should diappeared.", !icon.isDisplayed());
            
            assertTrue("Icon should have unchecked.", !hasClass(icon, "ui-icon-check"));
            
            b.click();
        }
        
        
        findElementById("form:submit").click();
        
        waitUntilAjaxRequestCompletes();
        
        WebElement dialog = findElementById("form:dialog");
        
        assertTrue("Should display result dialog.", dialog.isDisplayed());
        
        assertTrue("Should display valid results.", findElementsBySelector(dialog, escapeClientId("form:selectedOptions") + " .ui-datalist-item").size() == 3);
        
        assertTrue("Should display valid results.", findElementsBySelector(dialog, escapeClientId("form:selectedMovies") + " .ui-datalist-item").size() == 4);

    }
}
