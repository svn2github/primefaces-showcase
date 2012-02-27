package com.prime.showcase.integration.datatable;

import java.util.NoSuchElementException;
import org.junit.BeforeClass;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class DataTableFilteringIntegrationTest extends AbstractDataTableIntegrationTest {

    public static final String TEST_URL = PRIME_SHOWCASE_UI + "datatableFiltering.jsf";
    
    @Before
    public void init(){
        driver.get(TEST_URL);
    }
	
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            findElementById("form:dataTable");
            findElementById("form:dataTable:globalFilter");
            findElementById("form:dataTable:modelColumn_filter");
            findElementById("form:dataTable:yearColumn_filter");
            findElementById("form:dataTable:manufacturerColumn_filter");
            findElementById("form:dataTable:colorColumn_filter");
            
        }
        catch(NoSuchElementException e){
            assertTrue("DataTableFiltering showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldContains() {
        
        WebElement table = findElementById("form:dataTable");
        
        int validCount = findElementsByXpath(table, "table/tbody//tr/td[1][contains(., 'a')]").size();
        
        findElementById("form:dataTable:modelColumn_filter").sendKeys("a");
        
        waitUntilAjaxRequestCompletes();
        
        List<WebElement> cols = findElementsByXpath(table, "table/tbody//tr/td[1][contains(., 'a')]");
        
        assertTrue("Should find valid amount.", cols.size() == validCount);
        
        for (WebElement e : cols) {
            assertTrue("Should find valid (contains) items.", e.getText().contains("a"));
        }
    }
    
    @Test
    public void shouldStartsWith() {
        
        WebElement table = findElementById("form:dataTable");
        
        int validCount = findElementsByXpath(table, "table/tbody//tr/td[2][starts-with(., '199')]").size();
        
        findElementById("form:dataTable:yearColumn_filter").sendKeys("199");
        
        waitUntilAjaxRequestCompletes();
        
        List<WebElement> cols = findElementsByXpath(table, "table/tbody//tr/td[2][starts-with(., '199')]");
        
        assertTrue("Should find valid amount.", cols.size() == validCount);
        
        for (WebElement e : cols) {
            assertTrue("Should find valid (starts with) items.", e.getText().startsWith("199"));
        }
    }
    
    @Test
    public void shouldExact() {
        
        WebElement table = findElementById("form:dataTable");
        
        int validCount = findElementsByXpath(table, "table/tbody//tr/td[3][. = 'Ford']").size();
        
        selectElementByValue(findElementById("form:dataTable:manufacturerColumn_filter"), "Ford");
        
        waitUntilAjaxRequestCompletes();
        
        List<WebElement> cols = findElementsByXpath(table, "table/tbody//tr/td[3][. = 'Ford']");
        
        assertTrue("Should find valid amount.", cols.size() == validCount);
        
        for (WebElement e : cols) {
            assertTrue("Should find valid (exact) items.", e.getText().equals("Ford"));
        }
    }
    
    @Test
    public void shouldEndsWith() {
        
        WebElement table = findElementById("form:dataTable");
        
        int validCount = findElementsByXpath(table, "table/tbody//tr/td[4][. = 'Yellow']").size();
        
        findElementById("form:dataTable:colorColumn_filter").sendKeys("low");
        
        waitUntilAjaxRequestCompletes();
        
        List<WebElement> cols = findElementsByXpath(table, "table/tbody//tr/td[4][. = 'Yellow']");
        
        assertTrue("Should find valid amount.", cols.size() == validCount);
        
        for (WebElement e : cols) {
            assertTrue("Should find valid (ends with) items.", e.getText().endsWith("low"));
        }
    }
}
