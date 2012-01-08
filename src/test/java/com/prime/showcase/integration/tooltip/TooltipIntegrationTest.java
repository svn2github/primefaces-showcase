package com.prime.showcase.integration.tooltip;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class TooltipIntegrationTest extends AbstractIntegrationTest {

	private SeleniumActionHelper action;
	
	@Before
	public void init(){
		String testUrl = toShowcaseUrl("tooltip.jsf");
		driver.get(testUrl);
		action = new SeleniumActionHelper(driver);
	}
	
	
	@Test
	public void shouldShowOnFocus() throws InterruptedException{
		WebElement toolTipFocus = findElementById("toolTipFocus");
		WebElement focus = findElementById("focus");
		focus.click();
		Thread.sleep(1000);
		
		assertThat(toolTipFocus.isDisplayed(), equalTo(true));
		assertThat(toolTipFocus.getText(), equalTo("This tooltip is displayed when input gets the focus"));
	}
	
	@Test
	public void shouldShowFade() throws InterruptedException{
		WebElement toolTipFocus = findElementById("toolTipFade");
		WebElement fade = findElementById("fade");
		
		action.mouseHover(fade);
		Thread.sleep(1000);
		
		assertThat(toolTipFocus.isDisplayed(), equalTo(true));
		assertThat(toolTipFocus.getText(), equalTo("Fade effect is used by default"));
	}
	
	@Test
	public void shouldShowSlide() throws InterruptedException{
		WebElement toolTipFocus = findElementById("toolTipSlide");
		WebElement slide = findElementById("slide");
		
		action.mouseHover(slide);
		Thread.sleep(1000);
		
		assertThat(toolTipFocus.isDisplayed(), equalTo(true));
		assertThat(toolTipFocus.getText(), equalTo("This tooltip uses slide effect for the animation"));
	}
	
	@Test
	public void shouldShowGrow() throws InterruptedException{
		WebElement toolTipFocus = findElementById("toolTipGrow");
		WebElement grow = findElementById("grow");
		
		action.mouseHover(grow);
		Thread.sleep(1000);
		
		assertThat(toolTipFocus.isDisplayed(), equalTo(true));
		assertThat(toolTipFocus.getText(), equalTo("This tooltip uses clip/explode effects for the animation"));
	}
	
	@Test
	public void shouldShowContent() throws InterruptedException{
		WebElement toolTipFocus = findElementById("primeLogo");
		WebElement lnk = findElementById("lnk");

		action.mouseHover(lnk);
		Thread.sleep(1000);
		
		assertThat(toolTipFocus.isDisplayed(), equalTo(true));
	}
	
}
