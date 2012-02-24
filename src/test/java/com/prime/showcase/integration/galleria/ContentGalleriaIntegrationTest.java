package com.prime.showcase.integration.galleria;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ContentGalleriaIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init(){
		String testUrl = toShowcaseUrl("galleriaContent.jsf");
		driver.get(testUrl);
	}
	
	@Test
	public void shouldRenderContentOfGaleria(){
		List<WebElement> contents = findElementByClass("ui-galleria-panel_wrap").findElements(By.tagName("div"));
		
		assertThat(contents.size(), equalTo(5));
		assertThat(contents.get(0).getAttribute("class").contains("current"), equalTo(true));
		
		assertThat(findElementById("contentGalleria:0:playerImage").getAttribute("src").contains("/prime-showcase/images/barca/messi.jpg"), equalTo(true));
		assertThat(findElementById("contentGalleria:0:name").getText(), equalTo("Messi"));
		assertThat(findElementById("contentGalleria:0:number").getText(), equalTo("10"));
		assertThat(findElementById("contentGalleria:0:position").getText(), equalTo("CF"));
	}
	
	@Test
	public void shoulChangeContentWhenClickArrow() throws InterruptedException{
		
		List<WebElement> contents = findElementByClass("ui-galleria-panel_wrap").findElements(By.tagName("div"));

        waitUntilElementExists(By.cssSelector(".ui-galleria-nav-next.ui-icon"));
        
        waitForCondition(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return findElementBySelector(".ui-galleria-nav-next.ui-icon").isDisplayed();
			}
		});
        
		findElementBySelector(".ui-galleria-nav-next.ui-icon").click();
		Thread.sleep(1000);
		
		assertThat(contents.get(0).getAttribute("class").contains("current"), equalTo(false));
		assertThat(contents.get(1).getAttribute("class").contains("current"), equalTo(true));
	}
}
