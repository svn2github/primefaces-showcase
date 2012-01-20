package com.prime.showcase.integration.datalist;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class DataListOrderedIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("dataListOrdered.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldDisplayAsOrdered() {
		WebElement element = findElementById("ordered_list");
		assertThat(element.getTagName(), equalTo("ol"));
	}
	
	@Test
	public void shouldDisplayAsOrdered_A() {
		WebElement element = findElementById("itemType_A_list");
		assertThat(element.getTagName(), equalTo("ol"));
		assertThat(element.getAttribute("type"), equalTo("A"));
	}
	
	@Test
	public void shouldDisplayAsOrdered_a() {
		WebElement element = findElementById("itemType_a_list");
		assertThat(element.getTagName(), equalTo("ol"));
		assertThat(element.getAttribute("type"), equalTo("a"));
	}
	
	@Test
	public void shouldDisplayAsOrdered_i() {
		WebElement element = findElementById("itemTypeI_list");
		assertThat(element.getTagName(), equalTo("ol"));
		assertThat(element.getAttribute("type"), equalTo("i"));
	}
}
