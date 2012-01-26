package com.prime.showcase.integration.imagecropper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import static org.junit.Assert.assertTrue;

public class ImageCropperIntegrationTest extends AbstractIntegrationTest {
    
    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "imageCropper.jsf";
    
    protected static SeleniumActionHelper action;
    
    @BeforeClass
    public static void init(){
        driver.get(TEST_URL);
        action = new SeleniumActionHelper(driver);
    }
    
    @Test
    public void shouldVerifyDOM(){
        
        try{
            
            //cropper and hidden value holder
            findElementBySelector("form#form " + escapeClientId("form:imageCropper") + " " + escapeClientId("form:imageCropper_coords"));
            
            //handles
            assertTrue("Should find cropper handles.", findElementsBySelector("form#form " + escapeClientId("form:imageCropper") + " .jcrop-holder .jcrop-handle").size() == 8);

            //crop button
            findElementBySelector("form#form " + escapeClientId("form:btn"));
            
        }
        catch(NoSuchElementException e){
            assertTrue("ImageCropper showcase DOM not verified.", false);
        }
    }
    
    @Test
    public void shouldCropImage() {
        
        
        String[] coords = findElementBySelector("form#form " + escapeClientId("form:imageCropper") + " " + escapeClientId("form:imageCropper_coords")).getAttribute("value").split("_");
        
        int x1 = Integer.valueOf(coords[0]);
        int y1 = Integer.valueOf(coords[1]);
        
        //play with crop dimensions
        for (WebElement e : findElementsBySelector("form#form " + escapeClientId("form:imageCropper") + " .jcrop-holder .jcrop-handle")) {
            action.dndByOffset(e, 10, 10);
        }
        
        
        String[] coords2 = findElementBySelector("form#form " + escapeClientId("form:imageCropper") + " " + escapeClientId("form:imageCropper_coords")).getAttribute("value").split("_");
        
        int x2 = Integer.valueOf(coords2[0]);
        int y2 = Integer.valueOf(coords2[1]);
        int w2 = Integer.valueOf(coords2[2]);
        int h2 = Integer.valueOf(coords2[3]);
        
        //crop
        findElementBySelector("form#form " + escapeClientId("form:btn")).click();

        waitUntilAjaxRequestCompletes();
        
        try {
            
            //check value handler
            assertTrue("Cropper value handler should changed.", x1 != x2 && y1 != y2);
            
            Dimension croppedSize = findElementById("form:localCroppedImage").getSize();
            
            assertTrue("Should crop valid dimensions.", h2 == croppedSize.height && w2 == croppedSize.width);
            
        } catch (NoSuchElementException e) {
            assertTrue("Should render cropped image.", false);
        }
    }
}
