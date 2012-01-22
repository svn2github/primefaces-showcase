package com.prime.showcase.integration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumActionHelper {

	private static Actions builder;

	public SeleniumActionHelper(WebDriver driver) {
		builder = new Actions(driver);
	}

	public void rightClick(WebElement body) {
		builder.contextClick(body).build().perform();
	}

	public void mouseHover(WebElement element) {
		builder.moveToElement(element).build().perform();
	}

	public void dndByOffset(WebElement element, int x, int y) {
		builder.dragAndDropBy(element, x, y).build().perform();
	}

	public void dndToElement(WebElement source, WebElement target) {
		builder.dragAndDrop(source, target).build().perform();
	}
    
    public void keyDown(WebElement e, Keys keys) {
		builder.keyDown(e, keys).build().perform();
	}
    
    public void keyDown(Keys keys) {
		builder.keyDown(keys).build().perform();
	}
    
    public void keyUp(WebElement e, Keys keys) {
		builder.keyUp(e, keys).build().perform();
	}
    
    public void keyUp(Keys keys) {
		builder.keyUp(keys).build().perform();
	}

	public void clickOnCurrentPosition() {
		builder.click().perform();
	}
	
	public void clickAndHoldOnElement(WebElement element) {
		builder.clickAndHold(element).perform();
	}
	
    public void dblClick(WebElement element) {
		builder.doubleClick(element).perform();
	}
	
	public void moveByOffSet(int x, int y) {
		builder.moveByOffset(x, y).perform();
	}
	
	public void releaseMouse() {
		builder.release().perform();
	}
	
}
