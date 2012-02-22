package com.prime.showcase.integration.breadcrumb;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.Ignore;

public class BreadCrumbIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		String testUrl = toShowcaseUrl("breadCrumb.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldRenderAllBreadCrumbItems() {
		
		List<WebElement> breadCrumbs = driver.findElements(By.className("ui-breadcrumb"));
		
		WebElement firstBreadCrumb = breadCrumbs.get(0);
		
		List<WebElement> listItems = firstBreadCrumb.findElements(By.tagName("li"));
		
		assertThat(listItems.size(),equalTo(15));
		
		assertBreadCrumbItem(listItems.get(2),"Sports","#");
		assertBreadCrumbItem(listItems.get(4),"Football","#");
		assertBreadCrumbItem(listItems.get(6),"Countries","#");
		assertBreadCrumbItem(listItems.get(8),"Spain","#");
		assertBreadCrumbItem(listItems.get(10),"F.C. Barcelona","#");
		assertBreadCrumbItem(listItems.get(12),"Squad","#");
		assertBreadCrumbItem(listItems.get(14),"Lionel Messi","#");
	}
	

	private void assertBreadCrumbItem(WebElement breadCrumbItem, String text, String url) {
		assertThat(breadCrumbItem.findElement(By.tagName("a")).getAttribute("href"),endsWith(url));
		assertThat(breadCrumbItem.findElement(By.tagName("span")).getText(),equalTo(text));
	}

}
