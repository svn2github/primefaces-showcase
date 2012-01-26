package com.prime.showcase.integration.carousel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.TestingUtils;

public class CarouselTestingHelper {
	
	public static final String CAROUSEL_CLASS = "ui-carousel-viewport";
	
	public WebElement findCarouselById(WebDriver driver, String id) {
		return driver.findElement(By.id(id)).findElement(By.className(CAROUSEL_CLASS));
	}
	
	public Integer getCssLeftValue(WebElement carousel) {
		WebElement ulElement = carousel.findElement(By.tagName("ul"));
		return TestingUtils.getInteger(ulElement.getCssValue("left"));
	}
	
	public Integer getWidth(WebElement carousel) {
		String carouselWidthCss = carousel.getCssValue("width");
		return TestingUtils.getInteger(carouselWidthCss);
	}


}
