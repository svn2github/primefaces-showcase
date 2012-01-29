package com.prime.showcase.integration.treetable;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.NoSuchElementException;

public class TreeTableScrollableIntegrationTest extends AbstractIntegrationTest {
    
    public static final String TEST_URL = PRIME_SHOWCASE_UI + "treeTableScrolling.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementById("form");
            
            findElementById("form:yscroll");
            
            findElementById("form:xscroll");
            
            findElementById("form:xyscroll");
            
        }
        catch(NoSuchElementException e){
            assertTrue("TreeTable Scrollable showcase DOM not verified.", false);
        }
    }
}
