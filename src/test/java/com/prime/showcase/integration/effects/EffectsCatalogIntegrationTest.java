package com.prime.showcase.integration.effects;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class EffectsCatalogIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "effects.jsf";
    
    protected static SeleniumActionHelper action;

    @BeforeClass
    public static void init() {
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    
    @Test
    public void shouldVerifyDOM() {
        
        try {
            
            findElementBySelector("#blind   #blind_header:not(:empty)   + #blind_content:not(:empty)");
            findElementBySelector("#clip    #clip_header:not(:empty)    + #clip_content:not(:empty)");
            findElementBySelector("#drop    #drop_header:not(:empty)    + #drop_content:not(:empty)");
            findElementBySelector("#explode #explode_header:not(:empty) + #explode_content:not(:empty)");
            findElementBySelector("#fold    #fold_header:not(:empty)    + #fold_content:not(:empty)");
            findElementBySelector("#puff    #puff_header:not(:empty)    + #puff_content:not(:empty)");
            findElementBySelector("#slide   #slide_header:not(:empty)   + #slide_content:not(:empty)");
            findElementBySelector("#scale   #scale_header:not(:empty)   + #scale_content:not(:empty)");
            findElementBySelector("#bounce  #bounce_header:not(:empty)  + #bounce_content:not(:empty)");
            findElementBySelector("#pulsate #pulsate_header:not(:empty) + #pulsate_content:not(:empty)");
            findElementBySelector("#shake   #shake_header:not(:empty)   + #shake_content:not(:empty)");
            findElementBySelector("#size    #size_header:not(:empty)    + #size_content:not(:empty)");
            
        } catch (NoSuchElementException e) {
            assertTrue("Effects Catalog showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldEffectsWork() throws InterruptedException {
        
        WebElement blind = findElementById("blind");
        blind.click();
        assertTrue("Should start animation", anyAnimationInProgress("#blind"));
        waitUntilAllAnimationsComplete();
        assertTrue("Should animation completes.", !blind.isDisplayed());
        
        
        WebElement clip = findElementById("clip");
        clip.click();
        assertTrue("Should start animation", anyAnimationInProgress("#clip"));
        waitUntilAnimationCompletes("#clip");
        assertTrue("Should animation completes.", !clip.isDisplayed());

        
        WebElement drop = findElementById("drop");
        drop.click();
        assertTrue("Should start animation", anyAnimationInProgress("#drop"));
        assertTrue("Should animate correct.", shouldElementAnimating(drop, "left", DECREASING, 300));
        waitUntilAnimationCompletes("#drop");
        assertTrue("Should animation completes.", !drop.isDisplayed());
        
        
        WebElement explode = findElementById("explode");
        explode.click();
        assertTrue("Should start animation", anyAnimationInProgress("#explode"));
        waitUntilAnimationCompletes("#explode");
        assertTrue("Should animation completes.", !explode.isDisplayed());
        
        
        WebElement fold = findElementById("fold");
        action.dblClick(fold);
        assertTrue("Should start animation", anyAnimationInProgress("#fold"));
        waitUntilAllAnimationsComplete();
        assertTrue("Should animation completes.", !fold.isDisplayed());
        
        
        WebElement puff = findElementById("puff");
        action.dblClick(puff);
        assertTrue("Should start animation", anyAnimationInProgress("#puff"));
        waitUntilAnimationCompletes("#puff");
        assertTrue("Should animation completes.", !puff.isDisplayed());
        
        
        WebElement slide = findElementById("slide");
        String slideLeft = slide.getCssValue("left");
        action.dblClick(slide);
        assertTrue("Should start animation", anyAnimationInProgress("#slide"));
        waitUntilAnimationCompletes("#slide");
        assertTrue("Should backward to initial left value.", slide.getCssValue("left").equalsIgnoreCase(slideLeft));
        assertTrue("Should animation completes.", slide.isDisplayed());
        
        
        WebElement scale = findElementById("scale");
        action.dblClick(scale);
        assertTrue("Should start animation", anyAnimationInProgress("#scale"));
        shouldElementAnimating(scale, "width", DECREASING, 200);
        shouldElementAnimating(scale, "height", DECREASING, 200);
        waitUntilAnimationCompletes("#scale");
        assertTrue("Should animation completes.", scale.isDisplayed());
        
        WebElement bounce = findElementById("bounce");
        String top = bounce.getCssValue("top");
        bounce.click();
        assertTrue("Should start animation", anyAnimationInProgress("#bounce"));
        shouldElementAnimating(bounce, "top", DECREASING, 100);
        waitUntilAnimationCompletes("#bounce");
        assertTrue("Should backward to initial top value.", bounce.getCssValue("top").equalsIgnoreCase(top));
        assertTrue("Should animation completes.", bounce.isDisplayed());
        
        
        WebElement pulsate = findElementById("pulsate");
        pulsate.click();
        assertTrue("Should start animation", anyAnimationInProgress("#pulsate"));
        assertTrue("Should animate correct.", shouldElementAnimating(pulsate, "opacity", DECREASING, 200));
        waitUntilAnimationCompletes("#pulsate");
        assertTrue("Should animation completes.", pulsate.isDisplayed());
        
        
        WebElement shake = findElementById("shake");
        String left = shake.getCssValue("left");
        shake.click();
        assertTrue("Should start animation", anyAnimationInProgress("#shake"));
        assertTrue("Should animate correct.", shouldElementAnimating(shake, "left", DECREASING, 300));
        waitUntilAnimationCompletes("#shake");
        assertTrue("Should backward to initial left value.", shake.getCssValue("left").equalsIgnoreCase(left));
        assertTrue("Should animation completes.", shake.isDisplayed());

        
        WebElement size = findElementById("size");
        size.click();
        assertTrue("Should start animation", anyAnimationInProgress("#size"));
        assertTrue("Should animate correct.", shouldElementAnimating(size, "width", INCREASING, 200));
        assertTrue("Should animate correct.", shouldElementAnimating(size, "height", INCREASING, 200));
        waitUntilAnimationCompletes("#size");
        assertTrue("Should animation completes.", size.isDisplayed());
    }
}
