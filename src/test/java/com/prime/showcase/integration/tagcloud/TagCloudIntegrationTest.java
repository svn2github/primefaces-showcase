package com.prime.showcase.integration.tagcloud;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class TagCloudIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("tagCloud.jsf"));
	}
	
	@Test
	public void shouldRenderTags() {
		
		WebElement tagCloud = driver.findElement(By.className("ui-tagcloud"));

		List<WebElement> itemsLevel1 = tagCloud.findElements(By.className("ui-tagcloud-strength-1"));
		
		assertThat(itemsLevel1.get(0).getText(), equalTo("Transformers"));
		assertThat(itemsLevel1.get(1).getText(), equalTo("Rocks"));
		
		List<WebElement> itemsLevel2 = tagCloud.findElements(By.className("ui-tagcloud-strength-2"));
		
		assertThat(itemsLevel2.get(0).getText(), equalTo("AJAX"));
		assertThat(itemsLevel2.get(1).getText(), equalTo("JSF 2.0"));
		
		List<WebElement> itemsLevel3 = tagCloud.findElements(By.className("ui-tagcloud-strength-3"));
		
		assertThat(itemsLevel3.get(0).getText(), equalTo("RIA"));
		assertThat(itemsLevel3.get(1).getText(), equalTo("Mobile"));
		
		List<WebElement> itemsLevel4 = tagCloud.findElements(By.className("ui-tagcloud-strength-4"));
		
		assertThat(itemsLevel4.get(0).getText(), equalTo("NextGen"));
		assertThat(itemsLevel4.get(1).getText(), equalTo("Themes"));
		
		List<WebElement> itemsLevel5 = tagCloud.findElements(By.className("ui-tagcloud-strength-5"));
		
		assertThat(itemsLevel5.get(0).getText(), equalTo("jQuery"));
		assertThat(itemsLevel5.get(1).getText(), equalTo("FCB"));
		
	}

}
