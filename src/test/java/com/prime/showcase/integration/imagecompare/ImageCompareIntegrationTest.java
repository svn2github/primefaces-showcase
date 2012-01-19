package com.prime.showcase.integration.imagecompare;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

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
		
		actionHelper.clickAndHolOnElement(dragger);
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
		String style = element.getAttribute("style");
		String widthStr = null;
		
		StringTokenizer tokenizer = new StringTokenizer(style, ";");
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();
			if(token.startsWith("width")) {
				widthStr = token;
				break;
			}
		}
		
		return getInteger(widthStr);
	}
	
	private int getInteger(String str) {
		Pattern intsOnly = Pattern.compile("\\d+");
		Matcher makeMatch = intsOnly.matcher(str);
		makeMatch.find();
		String inputInt = makeMatch.group();
		return Integer.valueOf(inputInt);
	}

}
