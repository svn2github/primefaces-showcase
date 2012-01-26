package com.prime.showcase.integration.carousel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CarouselTabsIntegrationTest extends AbstractIntegrationTest {

	private static final String TABS_CAROUSEL_ID = "tabsCarousel";

	private CarouselTestingHelper testingHelper = new CarouselTestingHelper();

	private WebElement carousel;

	@Before
	public void before() {
		driver.get(toShowcaseUrl("carouselTabs.jsf"));
		carousel = testingHelper.findCarouselById(driver, TABS_CAROUSEL_ID);
	}

	@Test
	public void shouldSwitchBetweenCarouselTabs() {

		Integer carouselWidth = testingHelper.getWidth(carousel);

		Integer leftValue = getCssLeftValue(carousel);

		clickToElementByClass("ui-carousel-next-button");
		waitForOneSecond();

		assertThat(getCssLeftValue(carousel), equalTo(leftValue + carouselWidth));

	}

	private Integer getCssLeftValue(WebElement carousel) {
		return testingHelper.getCssLeftValue(carousel);
	}

}
