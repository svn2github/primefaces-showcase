package com.prime.showcase.integration.datatable;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DataTablePaginationIntegrationTest extends AbstractIntegrationTest {
	private final String[] cssClasses = { "ui-paginator-current", "ui-paginator-first", "ui-paginator-prev",
			"ui-paginator-pages", "ui-paginator-next", "ui-paginator-last" };

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("datatablePagination.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldDisplayPaginatorTop() {
		WebElement element = findElementById("dataTable_paginator_top");
		assertThat(element.getTagName(), equalTo("th"));
		for (String eachClass : cssClasses) {
			element = findElementBySelector("#dataTable_paginator_top span." + eachClass);
		}
	}

	@Test
	public void shouldDisplayPaginatorBottom() {
		WebElement element = findElementById("dataTable_paginator_bottom");
		assertThat(element.getTagName(), equalTo("td"));
		for (String eachClass : cssClasses) {
			element = findElementBySelector("#dataTable_paginator_bottom span." + eachClass);
		}
	}

	@Test
	public void shouldDisplayPaginatorOptions() {
		WebElement elementBottom = findElementBySelector("#dataTable_paginator_bottom select.ui-paginator-rpp-options");
		WebElement elementTop = findElementBySelector("#dataTable_paginator_top select.ui-paginator-rpp-options");
		assertThat(elementBottom.getText(), equalTo(elementTop.getText()));
		assertThat(elementBottom.getAttribute("value"), equalTo(elementTop.getAttribute("value")));
	}
	
	@Test
	public void shouldBeEqualPaginators() {
		WebElement elementBottom = findElementBySelector("#dataTable_paginator_bottom select.ui-paginator-rpp-options");
		selectElementByValue(elementBottom,"5");
		WebElement elementTop = findElementBySelector("#dataTable_paginator_top select.ui-paginator-rpp-options");
		assertThat(elementTop.getAttribute("value"), equalTo("5"));
		
		selectElementByValue(elementTop, "15");
		elementBottom = findElementBySelector("#dataTable_paginator_bottom select.ui-paginator-rpp-options");
		assertThat(elementBottom.getAttribute("value"), equalTo("15"));
	}
	

	@Test
	public void shouldBeEqualPaginatorsAndRows() {
		WebElement pagenatorElement = findElementBySelector("#dataTable_paginator_bottom select.ui-paginator-rpp-options");
		List<WebElement> rows = findElementById("dataTable_data").findElements(By.className("ui-widget-content"));
		assertThat(pagenatorElement.getAttribute("value"), equalTo(String.valueOf(rows.size())));
		
		selectElementByValue(pagenatorElement, "15");
		pagenatorElement = findElementBySelector("#dataTable_paginator_bottom select.ui-paginator-rpp-options");
		rows = findElementById("dataTable_data").findElements(By.className("ui-widget-content"));
		assertThat(pagenatorElement.getAttribute("value"), equalTo(String.valueOf(rows.size())));
	}
	

}
