package com.prime.showcase.integration.stack;

import com.prime.showcase.integration.AbstractIntegrationTest;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class StackBasicIntegrationTest extends AbstractIntegrationTest {
 
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "stack.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            WebElement stack = findElementById("stack");
            
            WebElement icon = findElementBySelector("#stack > img");
            
            assertTrue("Icon should be displayed.", icon.isDisplayed());
            
            String src = icon.getAttribute("src");
            
            assertTrue("Icon should be sourced.", src != null && !src.trim().isEmpty());
            
            WebElement list = findElementBySelector(stack, "ul");
            
            assertTrue("List should be displayed.", list.isDisplayed());
            
            List<WebElement> items = findElementsBySelector(list, "li");
            
            for (WebElement e : items) {
                
                assertTrue("List item should be displayed.", e.isDisplayed());
                
                WebElement label = findElementBySelector(e, "span");
                
                assertTrue("Label should be hidden.", !label.isDisplayed());
                
                WebElement img = findElementBySelector(e, "img");

                assertTrue("Image should be displayed.", img.isDisplayed());
                
                src = img.getAttribute("src");
                
                assertTrue("Image should be sourced.", src != null && !src.trim().isEmpty());
            }
        }
        catch(NoSuchElementException e){
            assertTrue("Stack showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldManageStack() throws InterruptedException{
        
        WebElement stack = findElementBySelector("#stack > img ");
        
        //open stack
        stack.click();
        
        waitUntilAllAnimationsComplete();
        
        for(WebElement label : findElementsBySelector("#stack ul li span")){
            assertTrue("Label should be displayed.", label.isDisplayed());
        }
        
        assertThat("Should animate correct.", stack.getCssValue("padding-top"), equalTo("0px"));
        
        
        //close stack
        stack.click();
        
        waitUntilAllAnimationsComplete();
        
        for(WebElement label : findElementsBySelector("#stack ul li span")){
            assertTrue("Label should be hidden.", !label.isDisplayed());
        }
        
        assertThat("Should animate correct.", stack.getCssValue("padding-top"), equalTo("35px"));

    }
    
}
