package com.prime.showcase.integration.galleria;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CustomGalleriaIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("galleriaCustom.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldRenderAccordingToWidthHeight(){
		assertThat(findElementByClass("ui-galleria-panel_wrap").getAttribute("style").contains("width: 500px; height: 250px;"), equalTo(true));
		assertThat(findElementsByClass("ui-galleria-img-wrap").get(0).getAttribute("style").contains("height: 70px; width: 160px;"), equalTo(true));
		assertThat(findElementByClass("gv-strip_wrapper").getAttribute("style").contains("top: 0px;"), equalTo(true));
	}
}
