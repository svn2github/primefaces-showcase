package com.prime.showcase.integration.starrating;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class StarRatingIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("rating.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldSubmitStarRating() throws InterruptedException {
		
		findElementByLinkText("3").click();
		findElementByLinkText("7").click();
		findElementById("form:submit").click();
		waitUntilAjaxRequestCompletes();
		assertThat("Rating 1: 3.0",equalTo(findElementById("form:rating1value").getText()));
		assertThat("Rating 2: 7.0",equalTo(findElementById("form:rating2value").getText()));
	}
	
	@Test
	public void shouldCancelStarRating() throws InterruptedException {
		
		findElementByLinkText("3").click();
		findElementById("form:submit").click();
		waitUntilAjaxRequestCompletes();
		assertThat("Rating 1: 3.0",equalTo(findElementById("form:rating1value").getText()));
		
		findElementBySelector("a[title=\"Cancel Rating\"]").click();
		findElementById("form:submit").click();
		waitUntilAjaxRequestCompletes();
		assertThat("Rating 1: 0.0",equalTo(findElementById("form:rating1value").getText()));
	}
	
	@Test
	public void shouldSubmitWithAjax() throws InterruptedException {
		findElementByXpath("//span[@id='form:ajaxRating']/span/div[6]/a").click();
		waitUntilAjaxRequestCompletes();
		assertThat("You rated:5", equalTo(findElementBySelector("div.ui-growl-message > p").getText()));
	}
	
	@Test
	public void shouldDisabledRatingDisplay() throws InterruptedException {
		WebElement rating = findElementByXpath("//span[@id='form:disabledRating']/span/div[2]");
		assertTrue( rating.getAttribute("class").contains("star-rating-on"));
		rating = findElementByXpath("//span[@id='form:disabledRating']/span/div[3]");
		assertTrue( rating.getAttribute("class").contains("star-rating-on"));
		rating = findElementByXpath("//span[@id='form:disabledRating']/span/div[4]");
		assertTrue( rating.getAttribute("class").contains("star-rating-on"));
		rating = findElementByXpath("//span[@id='form:disabledRating']/span/div[5]");
		assertTrue( ! rating.getAttribute("class").contains("star-rating-on"));
		rating = findElementByXpath("//span[@id='form:disabledRating']/span/div[6]");
		assertTrue( ! rating.getAttribute("class").contains("star-rating-on"));
	}
}
