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

public class DataTableHeaderFooterIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("datatableHeaderFooter.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldDisplayHeader() {
		WebElement element = findElementById("dataTable").findElement(By.xpath("table/thead/tr/th"));
		assertThat(element.getAttribute("class"), equalTo("ui-datatable-header ui-widget-header"));
	}

	@Test
	public void shouldDisplayColumnHeaders() {
		WebElement element = findElementById("dataTable").findElement(By.xpath("table/thead"));
		List<WebElement> elements = element.findElements(By.className("ui-state-default"));
		assertTrue(elements.size() > 1);
		for (WebElement eachElement : elements) {
			assertThat(eachElement.getTagName(), equalTo("th"));
		}
	}

	@Test
	public void shouldDisplayColumnFooters() {
		WebElement element = findElementById("dataTable").findElement(By.xpath("table/tfoot/tr"));
		List<WebElement> elements = element.findElements(By.className("ui-state-default"));
		assertTrue(elements.size() > 1);
		for (WebElement eachElement : elements) {
			assertThat(eachElement.getTagName(), equalTo("td"));
		}
	}

	@Test 
	public void shouldDisplayFooter() {
		WebElement element = findElementById("dataTable").findElement(By.xpath("table/tfoot"))
				.findElement(By.className("ui-datatable-footer"));
		assertThat(element.getTagName(), equalTo("td"));
	}
}
