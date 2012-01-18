package com.prime.showcase.integration.dynaimage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class DynaImageIntegrationTest extends AbstractIntegrationTest {

    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "dynamicImage.jsf";
    
    @BeforeClass
    public static void init() {
        driver.get(TEST_URL);
    }

    @Test
    public void shouldVerifyDOM() {

        try {

            String chart = findElementBySelector("img#chart").getAttribute("src");
            String barcode = findElementBySelector("img#barcode").getAttribute("src");
            String text = findElementBySelector("img#text").getAttribute("src");

            if (chart == null   || 
                barcode == null || 
                text == null    || 
                chart.trim().isEmpty()  || 
                barcode.trim().isEmpty()|| 
                text.trim().isEmpty()) {
                throw new NoSuchElementException("Image Not Sourced.");
            }


        } catch (NoSuchElementException e) {
            assertTrue(false);
        }
    }
}
