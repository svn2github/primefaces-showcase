package com.prime.showcase.integration.treetable;

import com.prime.showcase.integration.AbstractIntegrationTest;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class TreeTableSelectionIntegrationTest extends AbstractIntegrationTest {
    
    public static final String TEST_URL = PRIME_SHOWCASE_UI + "treeTableSelection.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        try{
            
            findElementById("form");
            findElementById("form:singleSelect:singleBtn");
            findElementById("form:singleSelect");
            findElementById("form:multiSelect");
            
            assertTrue("Should growl hidden.", !findElementById("form:messages").isDisplayed());
            
        }catch(NoSuchElementException e) {
            assertTrue("TreeTable Selection showcase DOM not verified.", false);
        }
    }
    
    
    @Test
    public void shouldManageSingleSelectTreeTable(){
        for (WebElement e : findElementsBySelector(findElementById("form:singleSelect"), "tbody > tr")) {
            shouldManageSingleSelectTree(e);
        }
    }
    
    protected void shouldManageSingleSelectTree(WebElement e){
        
        String nodeName = findElementBySelector(e, "td:first-child").getText();
        
        findElementBySelector(e, "td:first-child").click();
        
        findElementById("form:singleSelect:singleBtn").click();
        
        waitUntilAjaxRequestCompletes();
        
        waitUntilAllAnimationsComplete();
        
        WebElement dialog = findElementById("form:dialog");
        
        assertTrue("Dialog should be displayed.", dialog.isDisplayed());
        
        assertThat("Dialog should be updated.", nodeName, equalTo(findElementBySelector(dialog, "table tr:first-child td:first-child + td").getText()));
        
        findElementBySelector(dialog, "a.ui-dialog-titlebar-close").click();
        
        waitUntilAllAnimationsComplete();
        
        WebElement toggler = findElementBySelector(e, "td:first-child .ui-treetable-toggler");
        
        String expanded = e.getAttribute("aria-expanded");
        
        assertTrue("Should aria labeled.", expanded != null);
        
        String id = e.getAttribute("id");
        
        if(toggler.isDisplayed()) {
            
            List<WebElement> children = findElementsByXpath(findElementById("form:singleSelect"), "//tbody/tr[starts-with(@id, '"+ id +"')]");
            
            if(Boolean.valueOf(expanded)) {
                assertTrue("Should be visible child.", children.size() > 1);
            }
            else {
                assertTrue("Should be visible child.", children.size() == 1);
                toggler.click();
                waitUntilAjaxRequestCompletes();
                e = findElementById(id);
            }
            
            children = findElementsByXpath(findElementById("form:singleSelect"), "//tbody/tr[starts-with(@id, '"+ id +"')]");
            
            for (WebElement c : children) {
                if(!c.getAttribute("id").equals(id)){
                    shouldManageSingleSelectTree(c);
                }
            }
        }
    }
}
