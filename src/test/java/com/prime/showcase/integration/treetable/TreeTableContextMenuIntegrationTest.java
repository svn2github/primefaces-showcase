package com.prime.showcase.integration.treetable;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class TreeTableContextMenuIntegrationTest extends AbstractIntegrationTest {
    
    public static final String TEST_URL = PRIME_SHOWCASE_UI + "treeTableContextMenu.jsf";
    
    protected static SeleniumActionHelper action;
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementById("form");
            
            findElementById("form:docs");
           
            assertTrue("Should context menu not displayed on startup.", !findElementById("form:contextMenuId").isDisplayed());
            
        }
        catch(NoSuchElementException e){
            assertTrue("TreeTable ContextMenu showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldManageTreeTable() throws InterruptedException{
        List<WebElement> root = findElementsBySelector(findElementById("form:docs"), "tbody > tr");
        for (int i = root.size(); i > 0; i--) {
            shouldManageTree(findElementsBySelector(findElementById("form:docs"), "tbody > tr").get(i-1));
        }
    }
    
    
    protected void shouldManageTree(WebElement e) throws InterruptedException{
        WebElement menu = findElementById("form:contextMenuId");
        
        String nodeName = findElementBySelector(e, "td:first-child").getText();
        
        action.rightClick(findElementBySelector(e, "td:first-child"));
        
        waitUntilAllAnimationsComplete();
        
        assertTrue("Should menu displayed.", menu.isDisplayed());
        
        findElementBySelector(menu, "a:first-child").click();
        
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
            
            List<WebElement> children = findElementsByXpath(findElementById("form:docs"), "//tbody/tr[starts-with(@id, '"+ id +"')]");
            
            if(Boolean.valueOf(expanded)) {
                assertTrue("Should be visible child.", children.size() > 1);
            }
            else {
                assertTrue("Should be visible child.", children.size() == 1);
                toggler.click();
                waitUntilAjaxRequestCompletes();
                e = findElementById(id);
            }
            
            children = findElementsByXpath(findElementById("form:docs"), "//tbody/tr[starts-with(@id, '"+ id +"')]");
            
            for (int i = children.size(); i > 0; i--) {
                WebElement c = children.get(i-1);
                if(!c.getAttribute("id").equals(id)){
                    shouldManageTree(c);
                    children = findElementsByXpath(findElementById("form:docs"), "//tbody/tr[starts-with(@id, '"+ id +"')]");
                }
            }
            
            e = findElementById(id);
        }
        
        action.rightClick(findElementBySelector(e, "td:first-child"));
        
        waitUntilAllAnimationsComplete();
        
        assertTrue("Should menu displayed.", menu.isDisplayed());
        
        findElementBySelector(menu, "ul li:last-child a").click();
        
        waitUntilAjaxRequestCompletes();
        
        boolean shouldNotFound = false;
        
        try {
            findElementById(id);
        } catch (NoSuchElementException ex) {
            shouldNotFound = true;
        }
        
        if(!shouldNotFound){
            assertTrue("Should remove node.", false);
        }
    }
}
