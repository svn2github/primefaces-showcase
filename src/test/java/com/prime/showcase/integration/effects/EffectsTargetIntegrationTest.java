package com.prime.showcase.integration.effects;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class EffectsTargetIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "effectTarget.jsf";
    
    @BeforeClass
    public static void init() {
        driver.get(TEST_URL);
    }
    
    
    @Test
    public void shouldVerifyDOM() {
        
        try {
            
            findElementById("lnk");
            assertTrue("Image should be hidden on startup.", !findElementById("img").isDisplayed());
            
        } catch (NoSuchElementException e) {
            assertTrue("Effects Targeting showcase DOM not verified.", false);
        }
    }
    
    
    @Test
    public void shouldAnimationWorks() throws InterruptedException {
        
        WebElement image = findElementById("img");
       
        findElementById("lnk").click();
        
        assertTrue("Should start animation", anyAnimationInProgress("#img"));
        shouldElementAnimating(image, "opacity", INCREASING, 500);
        waitUntilAnimationCompletes("#image");
        assertTrue("Should animation completes.", image.isDisplayed());
    }
    
}
