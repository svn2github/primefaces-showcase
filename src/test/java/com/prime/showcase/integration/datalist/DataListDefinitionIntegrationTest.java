package com.prime.showcase.integration.datalist;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;


public class DataListDefinitionIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("dataListDefinition.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldDisplayAsDefinition() {
	
		WebElement element = findElementById("definition_list");
		assertThat(element.getTagName(), equalTo("dl"));
		List<WebElement> dts = findElementsByClass("ui-datalist-item");
		assertTrue(dts.size() > 0);
		
		for(WebElement dt : dts)
			assertThat(dt.getTagName(), equalTo("dt"));
	}
}
