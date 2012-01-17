package com.prime.showcase.integration.tabview;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class TabViewOrientationIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("tabviewOrientation.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldRenderOrientedTabs(){
		assertTrue(findElementById("tabViewTop").getAttribute("class").contains("ui-tabs-top"));
		assertTrue(findElementById("tabViewBottom").getAttribute("class").contains("ui-tabs-bottom"));
		assertTrue(findElementById("tabViewLeft").getAttribute("class").contains("ui-tabs-left"));
		assertTrue(findElementById("tabViewRight").getAttribute("class").contains("ui-tabs-right"));
	}
}
