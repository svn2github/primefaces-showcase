package com.prime.showcase.integration.galleria;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class SimpleGalleriaIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("galleria.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldRenderSimplestGalleria(){
		List<WebElement> bigImageDivs = findElementByClass("ui-galleria-panel_wrap").findElements(By.tagName("div"));
		assertThat(bigImageDivs.size(), equalTo(12));
	}
}
