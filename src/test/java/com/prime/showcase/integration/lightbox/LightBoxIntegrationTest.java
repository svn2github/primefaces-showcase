package com.prime.showcase.integration.lightbox;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LightBoxIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("lightBox.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldOpenAndNavigateLightbox() throws InterruptedException {

		assertTrue(findElementById("sopranos1_small") != null);
		assertTrue(findElementById("sopranos2_small") != null);
		assertTrue(findElementById("sopranos3_small") != null);
		assertTrue(findElementById("sopranos4_small") != null);

		findElementById("sopranos1_small").click();
		Thread.sleep(3000l);
		WebElement element = findElementBySelector("img.ui-helper-hidden");
		assertTrue(element.isDisplayed());
		WebElement footer = findElementByXpath("//div[@id='lighbox1_panel']/div[2]");
		assertThat(footer.getText(),equalTo("Sopranos 1"));
		
		WebElement next = findElementBySelector("span.ui-icon.ui-icon-carat-1-e");
		next.click();
		Thread.sleep(3000l);
		assertThat(footer.getText(),equalTo("Sopranos 2"));
		
		next.click();
		Thread.sleep(3000l);
		assertThat(footer.getText(),equalTo("Sopranos 3"));
		
		next.click();
		Thread.sleep(3000l);
		assertThat(footer.getText(),equalTo("Sopranos 4"));
		
		WebElement prev = findElementBySelector("span.ui-icon.ui-icon-carat-1-w");
		prev.click();

		Thread.sleep(3000l);
		assertThat(footer.getText(),equalTo("Sopranos 3"));
	}
	
	@Test
	public void shouldDisplayInline() throws InterruptedException{
		
		assertTrue(! findElementById("lighbox2_panel").isDisplayed());
		
		findElementById("lighbox2_link").click();
		Thread.sleep(3000l);
		assertTrue(findElementById("lighbox2_panel").isDisplayed());
	}
	
	@Test
	public void shouldDisplayAsIframe() throws InterruptedException{

		findElementById("lightboxIframe_link").click();
		Thread.sleep(2000L);
		WebElement frame = findElementByTag("iframe");
		assertTrue(frame.isDisplayed());
	}
}
