package com.prime.showcase.integration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class SeleniumActionHelper {

	private static Actions builder;

	public SeleniumActionHelper(WebDriver driver) {
		builder = new Actions(driver);
	}

	public void rightClick(WebElement body) {
		Action rClick = builder.contextClick(body).build();
		rClick.perform();
	}

	public void mouseHover(WebElement element) {
		Action action = builder.moveToElement(element).build();
		action.perform();
	}

	public void dndByOffset(WebElement element, int x, int y) {
		Action action = builder.dragAndDropBy(element, x, y).build();
		action.perform();
	}

	public void dndToElement(WebElement source, WebElement target) {
		Action action = builder.dragAndDrop(source, target).build();
		action.perform();
	}

	public void clickOnCurrentPosition() {
		builder.click().perform();
	}
	
}
