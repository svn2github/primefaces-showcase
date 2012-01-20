package com.prime.showcase.integration.datalist;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DataListUnorderedIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("dataListUnordered.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldDisplayAsDisc() {
		WebElement element = findElementById("disc_list");
		assertThat(element.getAttribute("type"), equalTo("disc"));
	}

	@Test
	public void shouldDisplayAsCircle() {
		WebElement element = findElementById("circle_list");
		assertThat(element.getAttribute("type"), equalTo("circle"));

	}

	@Test
	public void shouldDisplayAsSquare() {
		WebElement element = findElementById("square_list");
		assertThat(element.getAttribute("type"), equalTo("square"));

	}
}
