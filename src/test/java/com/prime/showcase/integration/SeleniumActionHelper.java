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
}
