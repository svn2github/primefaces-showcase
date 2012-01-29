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

public class TreeTableBasicIntegrationTest extends AbstractIntegrationTest {
    
    public static final String TEST_URL = PRIME_SHOWCASE_UI + "treeTable.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementById("form");
            
            WebElement treetable = findElementById("form:treetable");
            
            assertTrue("TreeTable should be displayed on startup.", treetable.isDisplayed());
            
            //table should be labeled.
            assertTrue("TreeTable should be all labeled/roled.", findElementsBySelector(treetable, "table[role='treegrid'] tbody tr[role='row'][aria-expanded='false'] td[role='gridcell']").size() == 12);
            
            assertTrue("Dialog should be hidden on startup.", !findElementById("form:dialog").isDisplayed());
            
        }
        catch(NoSuchElementException e){
            assertTrue("TreeTable Basic showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldManageTreeTable(){
        for (WebElement e : findElementsBySelector(findElementById("form:treetable"), "tbody > tr")) {
            shouldManageTree(e);
        }
    }
    
    protected void shouldManageTree(WebElement e){
        
        String nodeName = findElementBySelector(e, "td:first-child").getText();
        
        findElementBySelector(e, "td:last-child a").click();
        
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
            
            List<WebElement> children = findElementsByXpath(findElementById("form:treetable"), "//tbody/tr[starts-with(@id, '"+ id +"')]");
            
            if(Boolean.valueOf(expanded)) {
                assertTrue("Should be visible child.", children.size() > 1);
            }
            else {
                assertTrue("Should be visible child.", children.size() == 1);
                toggler.click();
                waitUntilAjaxRequestCompletes();
                e = findElementById(id);
            }
            
            children = findElementsByXpath(findElementById("form:treetable"), "//tbody/tr[starts-with(@id, '"+ id +"')]");
            
            for (WebElement c : children) {
                if(!c.getAttribute("id").equals(id)){
                    shouldManageTree(c);
                }
            }
        }
    }
}
