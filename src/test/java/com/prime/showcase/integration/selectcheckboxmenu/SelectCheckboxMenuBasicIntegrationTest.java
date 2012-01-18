package com.prime.showcase.integration.selectcheckboxmenu;

import com.prime.showcase.integration.AbstractIntegrationTest;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SelectCheckboxMenuBasicIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "selectCheckboxMenu.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            WebElement menu = findElementById("form:menu");
            
            List<WebElement> items = findElementsBySelector(menu, "input[name='form:menu'][type='checkbox']");
            
            assertTrue("Should render hidden checkbox inputs.", items.size() == 4);
            for (WebElement e : items) {
                assertTrue("Checkbox input elements should be hidden.", !e.isDisplayed());
            }
            
            WebElement menupanel = findElementById("form:menu_panel");
            
            assertTrue("Menu panel should be hidden on startup.", !menupanel.isDisplayed());
            
            assertTrue("Should render correct size of checkbox boxes.", findElementsBySelector(menupanel, ".ui-chkbox-box").size() == items.size());
            
            findElementById("form:submit");
            
            findElementById("form:dialog");
            
        }
        catch(NoSuchElementException e){
            assertTrue("SelectCheckboxMenu showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldManageMenuPanel() {
        
        WebElement menu = findElementById("form:menu");
        
        findElementBySelector(menu, ".ui-selectcheckboxmenu-trigger").click();
        
        waitUntilAnimationCompletes(escapeJSId("form:menu_panel"));
        
        WebElement panel = findElementById("form:menu_panel");
        
        assertTrue(panel.isDisplayed());
        
        List<WebElement> items = findElementsBySelector(panel, ".ui-selectcheckboxmenu-item");
        List<WebElement> hiddens = findElementsBySelector(menu, "input[type='checkbox']");
        
        for (int i = 0; i < items.size(); i++) {
            
            WebElement e = items.get(i);
            
            WebElement h = hiddens.get(i);
            
            WebElement box = findElementBySelector(e, ".ui-chkbox-box");
            
            WebElement icon = findElementBySelector(box, ".ui-chkbox-icon");
            
            assertTrue("Should not be actived on false.", !hasClass(box, "ui-state-active"));
            assertTrue("Icon should be hidden on false.", !icon.isDisplayed());
            
            box.click();
            
            assertTrue("Should active checkbox by check.", hasClass(box, "ui-state-active"));
            
            assertTrue("Icon should be displayed on true.", icon.isDisplayed());
            
            WebElement label = findElementBySelector(e, "label");

            assertThat("Labels should match.", label.getText(), equalTo(h.getAttribute("value")));
            
            label.click();
            
            assertTrue("Should remove active class on checkbox.", !hasClass(box, "ui-state-active"));
            assertTrue("Icon should be hidden on false.", !icon.isDisplayed());
            
            box.click();
        }
        
        findElementById("content").click();
        
        waitUntilAnimationCompletes(escapeJSId("form:menu_panel"));
        
        assertTrue("Menu panel should be hidden by clicking outside.", !panel.isDisplayed());
        
        findElementById("form:submit").click();
        
        waitUntilAjaxRequestCompletes();
        
        WebElement dialog = findElementById("form:dialog");
        
        assertTrue("Should display result dialog.", dialog.isDisplayed());
        
        assertTrue("Should display valid results.", findElementsBySelector(dialog, " .ui-datalist-item").size() == 4);
    }
}
