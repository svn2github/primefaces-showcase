package com.prime.showcase.integration.tabview;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class TabViewModelIntegratinoTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("tabviewModel.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldRenderPlayerModel(){
		WebElement tabView = findElementById("tabView");
		List<WebElement> tabs = tabView.findElements(By.tagName("li"));
		
		assertThat(tabs.size(), equalTo(5));
		assertTrue(findElementById("tabView:0:playerImg").getAttribute("src").contains("/prime-showcase/images/barca/messi.jpg"));
		assertThat(findElementById("tabView:0:playerName").getText(), equalTo("Messi"));
		assertThat(findElementById("tabView:0:playerNumber").getText(), equalTo("10"));
		assertThat(findElementById("tabView:0:playerPosition").getText(), equalTo("CF"));
	}
}
