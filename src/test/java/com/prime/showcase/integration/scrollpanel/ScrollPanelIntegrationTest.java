package com.prime.showcase.integration.scrollpanel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
		assertThat(basicScroll.getAttribute("style"), equalTo("width: 250px; height: 200px;"));

		WebElement container = findElementByClass("ui-scrollpanel-container");
		assertThat(container.getAttribute("style"), equalTo("width: 250px; height: 200px;"));
		
	}
	
	@Test
	public void shouldShowNativeScrollPanel(){
		WebElement nativeScroll = findElementById("form:nativeScroll");
		assertThat(nativeScroll.getAttribute("style"), equalTo("width: 250px; height: 200px;"));

	}
}
