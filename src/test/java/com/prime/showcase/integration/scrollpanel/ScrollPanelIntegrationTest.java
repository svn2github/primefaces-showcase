package com.prime.showcase.integration.scrollpanel;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class ScrollPanelIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("scrollPanel.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldShowBasicScrollPanel(){
		WebElement basicScroll = findElementById("form:basicScroll");
        assertTrue(basicScroll.getSize().width == 252 && basicScroll.getSize().height == 202);
        
		WebElement container = findElementByClass("ui-scrollpanel-container");
        assertTrue(container.getSize().width == 250 && container.getSize().height == 200);
		
	}
	
	@Test
	public void shouldShowNativeScrollPanel(){
		WebElement nativeScroll = findElementById("form:nativeScroll");
        assertTrue(nativeScroll.getSize().width == 252 && nativeScroll.getSize().height == 202);

	}
}
