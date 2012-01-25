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
		shouldRender("form:fadeEffectImages:", 0);

		Thread.sleep(4000l);

		shouldRender("form:fadeEffectImages:", 1);

		Thread.sleep(3000l);
		shouldRender("form:fadeEffectImages:", 2);

		Thread.sleep(3000l);
		shouldRender("form:fadeEffectImages:", 3);

		Thread.sleep(3000l);
		shouldRender("form:fadeEffectImages:", 0);
	}

	private void shouldRender(String component, int render) {
		for (int index = 0; index < 4; index++) {
			if (index == render)
				assertTrue(findElementById(component + index + ":image").isDisplayed());
			else
				assertTrue(!findElementById(component + index + ":image").isDisplayed());
		}

	}

	@Test
	public void shouldSwitchImagesWithManuelControl() throws InterruptedException {

		WebElement nextButton = findElementById("form:next");
		WebElement prevButton = findElementById("form:prev");
		
		shouldRender("form:manuelSwitcherImages:", 0);

		nextButton.click();
		Thread.sleep(2000l);
		
		shouldRender("form:manuelSwitcherImages:", 1);

		nextButton.click();
		Thread.sleep(2000l);
		shouldRender("form:manuelSwitcherImages:", 2);

		prevButton.click();
		Thread.sleep(2000l);
		shouldRender("form:manuelSwitcherImages:", 1);
	

		nextButton.click();
		Thread.sleep(2000l);
		shouldRender("form:manuelSwitcherImages:", 2);
	}
}
