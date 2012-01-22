package com.prime.showcase.integration.datatable;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;


public class DataTableSimpleIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void init() {
		String testUrl = toShowcaseUrl("datatableBasic.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldDisplayHeaders() {
		WebElement element = findElementById("basic:modelHeader");
		assertThat(element.getAttribute("class"), equalTo("ui-state-default"));
		
		element = findElementById("basic");
		List<WebElement> elements = element.findElements(By.className("ui-state-default"));
		assertTrue(elements.size() > 0);
		for (WebElement eachElem : elements) {
			assertThat(eachElem.getTagName(), equalTo("th"));
		}
	}
	
	@Test
	public void shouldDisplayTableData() {
		WebElement element = findElementById("basic_data");
		assertThat(element.getAttribute("class"), equalTo("ui-datatable-data ui-widget-content"));
		assertThat(element.getTagName(), equalTo("tbody"));
		
		List<WebElement> elements = element.findElements(By.xpath("tr/td/div"));
		assertTrue(elements.size() > 0);
		for (WebElement eachElem : elements) {
			assertThat(eachElem.getAttribute("class"), equalTo("ui-dt-c"));
		}
		
	}
}
