package com.prime.showcase.integration.effects;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class EffectsOnLoadIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "effectMessages.jsf";
    
    @BeforeClass
    public static void init() {
        driver.get(TEST_URL);
    }
    
    
    @Test
    public void shouldVerifyDOM() {
        
        try {
            
            findElementById("messages");
            
            findElementById("text");
            
            findElementById("submit");
            
        } catch (NoSuchElementException e) {
            assertTrue("Effects On Load showcase DOM not verified.", false);
        }
    }
    
    
    @Test
    public void shouldAnimationWorksOnLoad() throws InterruptedException {
        
        findElementById("text").sendKeys("something");
        
        findElementById("submit").click();
        
        waitUntilAjaxRequestCompletes();
        
        WebElement m = findElementById("messages");
        
        assertTrue("Animation should start.", shouldElementAnimating(m, "opacity", DECREASING, 1000));
        
        assertTrue("Animation should start.", anyAnimationInProgress("#messages"));
        
        waitUntilAnimationCompletes("#messages");
        
        assertTrue("Animation should end correct.", m.isDisplayed());
        
    }
    
    
}
