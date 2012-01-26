package com.prime.showcase.integration.carousel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CarouselIntegrationTest extends AbstractIntegrationTest {

	private static final String SMALL_CAROUSEL_ID = "smallCarousel";

	private static final String BASIC_CAROUSEL_ID = "basicCarousel";

	private CarouselTestingHelper testingHelper = new CarouselTestingHelper();

	@Before
	public void before() {
		driver.get(toShowcaseUrl("carousel.jsf"));
	}

	@Test
	public void shouldViewBasicCarousel() {

		WebElement carousel = findCarouselById(BASIC_CAROUSEL_ID);

		Integer leftValue = getCssLeftValue(carousel);

		clickToElementByClass("ui-carousel-next-button");
		waitForOneSecond();

		assertThat(getCssLeftValue(carousel), not(equalTo(leftValue)));

		clickToElementByClass("ui-carousel-prev-button");
		waitForOneSecond();

		assertThat(getCssLeftValue(carousel), equalTo(leftValue));

	}

	private Integer getCssLeftValue(WebElement carousel) {
		return testingHelper.getCssLeftValue(carousel);
	}

	private WebElement findCarouselById(String carouselId) {
		return testingHelper.findCarouselById(driver, carouselId);
	}

	@Test
	public void shouldViewItemsOneByOne() {
		WebElement carousel = findCarouselById(SMALL_CAROUSEL_ID);

		selectElementAndVerify(carousel, "2");
		selectElementAndVerify(carousel, "4");
	}

	public void selectElementAndVerify(WebElement carousel, String value) {
		Integer leftValue = getCssLeftValue(carousel);
		selectElement(value);
		assertThat(getCssLeftValue(carousel), greaterThan(leftValue));
	}

	public void selectElement(String value) {
		WebElement selectElement = findElementByClass("ui-carousel-dropdown");
		selectElementByValue(selectElement, value);
		waitForOneSecond();
	}

}
