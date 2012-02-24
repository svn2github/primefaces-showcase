package com.prime.showcase.integration.datatable;

import org.junit.BeforeClass;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import com.prime.showcase.integration.AbstractIntegrationTest;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class DataTableDynamicColumnsIntegrationTest extends AbstractIntegrationTest {
	
    
    public static final String TEST_URL = PRIME_SHOWCASE_UI + "datatableDynamicColumns.jsf";
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
    }
	
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementById("form:template");
            findElementById("form:btn");
            
            List<WebElement> cols = findElementsBySelector(findElementById("form:cars"), "thead th");
            
            assertTrue("Should render valid number of columns", cols.size() == 3);
            assertTrue("Should render valid columns.", cols.get(0).getText().equalsIgnoreCase("model"));
            assertTrue("Should render valid columns.", cols.get(1).getText().equalsIgnoreCase("manufacturer"));
            assertTrue("Should render valid columns.", cols.get(2).getText().equalsIgnoreCase("year"));
            
            
            
        }
        catch(NoSuchElementException e){
            assertTrue("DataTable dynamic columns showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldChangeColumns(){
        
        findElementById("form:template").sendKeys(" model");
        findElementById("form:btn").click();
        
        waitUntilAjaxRequestCompletes();
        
        List<WebElement> cols = findElementsBySelector(findElementById("form:cars"), "thead th");
            
        assertTrue("Should render valid number of columns", cols.size() == 4);
        assertTrue("Should render valid columns.", cols.get(0).getText().equalsIgnoreCase("model"));
        assertTrue("Should render valid columns.", cols.get(1).getText().equalsIgnoreCase("manufacturer"));
        assertTrue("Should render valid columns.", cols.get(2).getText().equalsIgnoreCase("year"));
        assertTrue("Should render valid columns.", cols.get(3).getText().equalsIgnoreCase("model"));
    }
    
}
