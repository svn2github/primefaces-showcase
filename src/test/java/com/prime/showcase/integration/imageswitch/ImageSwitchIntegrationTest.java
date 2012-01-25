package com.prime.showcase.integration.imageswitch;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

import static org.junit.Assert.assertTrue;

public class ImageSwitchIntegrationTest extends AbstractIntegrationTest {

	protected static final String TEST_URL = PRIME_SHOWCASE_UI + "imageSwitch.jsf";

	protected static SeleniumActionHelper action;

	@BeforeClass
	public static void init() {
		driver.get(TEST_URL);
		action = new SeleniumActionHelper(driver);
	}

	@Test
	public void shouldSwitchImagesForFeedEffect() throws InterruptedException {
		assertTrue(findElementById("form:fadeEffectImages:0:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:1:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:2:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:3:image").isDisplayed());

		Thread.sleep(4000l);

		assertTrue(!findElementById("form:fadeEffectImages:0:image").isDisplayed());
		assertTrue(findElementById("form:fadeEffectImages:1:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:2:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:3:image").isDisplayed());

		Thread.sleep(3000l);

		assertTrue(!findElementById("form:fadeEffectImages:0:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:1:image").isDisplayed());
		assertTrue(findElementById("form:fadeEffectImages:2:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:3:image").isDisplayed());

		Thread.sleep(3000l);

		assertTrue(!findElementById("form:fadeEffectImages:0:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:1:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:2:image").isDisplayed());
		assertTrue(findElementById("form:fadeEffectImages:3:image").isDisplayed());

		Thread.sleep(3000l);

		assertTrue(findElementById("form:fadeEffectImages:0:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:1:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:2:image").isDisplayed());
		assertTrue(!findElementById("form:fadeEffectImages:3:image").isDisplayed());
	}
	
	@Test
	public void shouldSwitchImagesWithManuelControl() throws InterruptedException {
		
		WebElement nextButton = findElementById("form:next");
		WebElement prevButton = findElementById("form:prev");
		
		
		assertTrue(findElementById("form:manuelSwitcherImages:0:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:1:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:2:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:3:image").isDisplayed());
		
		nextButton.click();
		
		Thread.sleep(2000l);
		
		assertTrue(! findElementById("form:manuelSwitcherImages:0:image").isDisplayed());
		assertTrue( findElementById("form:manuelSwitcherImages:1:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:2:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:3:image").isDisplayed());
		
		nextButton.click();
		Thread.sleep(2000l);
		
		assertTrue(! findElementById("form:manuelSwitcherImages:0:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:1:image").isDisplayed());
		assertTrue(findElementById("form:manuelSwitcherImages:2:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:3:image").isDisplayed());
		
		prevButton.click();
		Thread.sleep(2000l);
		
		assertTrue(! findElementById("form:manuelSwitcherImages:0:image").isDisplayed());
		assertTrue( findElementById("form:manuelSwitcherImages:1:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:2:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:3:image").isDisplayed());
		
		nextButton.click();
		Thread.sleep(2000l);
		
		assertTrue(! findElementById("form:manuelSwitcherImages:0:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:1:image").isDisplayed());
		assertTrue(findElementById("form:manuelSwitcherImages:2:image").isDisplayed());
		assertTrue(! findElementById("form:manuelSwitcherImages:3:image").isDisplayed());
	}
}
