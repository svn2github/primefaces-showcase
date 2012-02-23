package com.prime.showcase.integration.carousel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class CarouselSelectionIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("carouselSelect.jsf"));
	}

	@Test
	public void shouldSelectCarouselItem() {
		selectElementAndVerify(1);
		selectElementAndVerify(2);
	}

	private void selectElementAndVerify(int itemNumber) {

		String imageURL = getImageUrlByElementId("form:carousel:" + itemNumber + ":image");
		String model = findElementById("form:carousel:" + itemNumber + ":model").getText();

        waitUntilAllAnimationsComplete();
		clickToElementById("form:carousel:" + itemNumber + ":view");
		waitUntilAjaxRequestCompletes();
        
		assertThat(imageURL, containsString(getImageUrlByElementId("form:carImage")));
		assertThat(findElementById("form:modelNo").getText(), equalTo(model));
        
        findElementBySelector(findElementById("form:dialog"), ".ui-dialog-titlebar-close").click();
	}

	public String getImageUrlByElementId(String elementId) {
		return findElementById(elementId).getAttribute("src");
	}

}
