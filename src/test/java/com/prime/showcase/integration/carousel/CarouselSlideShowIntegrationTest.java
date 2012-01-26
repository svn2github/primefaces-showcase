package com.prime.showcase.integration.carousel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CarouselSlideShowIntegrationTest extends AbstractIntegrationTest {

	private static final String SLIDE_SHOW_CAROUSEL_ID = "slideShow";
	
	private CarouselTestingHelper testingHelper = new CarouselTestingHelper();
	
	private WebElement carousel;
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("carouselSlideshow.jsf"));
		carousel = testingHelper.findCarouselById(driver, SLIDE_SHOW_CAROUSEL_ID);
	}

	@Test
	public void shouldSlideBetweenImages() {
		waitAndCheckCarousel();
		waitAndCheckCarousel();
	}

	private void waitAndCheckCarousel() {
		int leftValue = testingHelper.getCssLeftValue(carousel);
		waitForSeconds(5);
		int newLeftValue = testingHelper.getCssLeftValue(carousel);
		assertThat(newLeftValue, not(equalTo(leftValue)));
	}

}
