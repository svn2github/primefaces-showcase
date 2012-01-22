package com.prime.showcase.integration.imagecompare;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import com.prime.showcase.integration.TestingUtils;

public class ImageCompareIntegrationTest extends AbstractIntegrationTest {

	private static final int Y_OFFSET_TO_MOVE = 0;
	private static final int X_OFFSET_TO_MOVE = 50;
	private SeleniumActionHelper actionHelper = new SeleniumActionHelper(driver);
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("imageCompare.jsf"));
	}

	@Test
	public void shouldCompareConsoleImages() {

		WebElement consoleCompare = findElementById("consoleCompare");
		WebElement dragger = getDragger(consoleCompare);
		WebElement leftImageDiv = getLeftImageDiv(consoleCompare);
		int leftImageWidth = getWidth(leftImageDiv);
		
		actionHelper.clickAndHoldOnElement(dragger);
		actionHelper.moveByOffSet(X_OFFSET_TO_MOVE, Y_OFFSET_TO_MOVE);
		actionHelper.releaseMouse();
		
		int draggedLeftWidth = getWidth(leftImageDiv);

		assertThat(leftImageWidth, lessThan(draggedLeftWidth));
	}

	private WebElement getLeftImageDiv(WebElement consoleCompare) {
		List<WebElement> divs = consoleCompare.findElements(By.tagName("div"));
		WebElement leftImageDiv = divs.get(divs.size() - 2);
		return leftImageDiv;
	}

	private WebElement getDragger(WebElement consoleCompare) {
		WebElement dragger = consoleCompare.findElement(By.tagName("img"));
		return dragger;
	}

	private int getWidth(WebElement element) {
		return TestingUtils.getInteger(element.getCssValue("width"));
	}
	
}
