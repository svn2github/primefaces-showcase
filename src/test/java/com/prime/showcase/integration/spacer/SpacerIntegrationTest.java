package com.prime.showcase.integration.spacer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class SpacerIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("spacer.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldSpace(){
		WebElement firstSpacer = findElementById("firstSpacer");
		WebElement secondSpacer = findElementById("secondSpacer");
		
		assertThat(firstSpacer.getAttribute("width"), equalTo("100"));
		assertThat(firstSpacer.getAttribute("height"), equalTo("10"));

		assertThat(secondSpacer.getAttribute("width"), equalTo("20"));
		assertThat(secondSpacer.getAttribute("height"), equalTo("20"));
	}
}
