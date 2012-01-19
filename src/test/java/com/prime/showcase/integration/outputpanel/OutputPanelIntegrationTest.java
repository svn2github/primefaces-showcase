package com.prime.showcase.integration.outputpanel;

import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class OutputPanelIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("outputPanel.jsf"));
	}

	@Test
	public void shouldUpdateOutputPanel() {
		WebElement panel = getPanel();
		try {
			checkImgExists(panel);
			throw new AssertionFailedError("Image is on the page but it should not be!");
		}catch(NoSuchElementException e) {
			// everything is ok
		}
		
		findElementById("form:showButton").click();
		
		panel = getPanel();
		checkImgExists(panel);
	}

	private void checkImgExists(WebElement panel) {
		panel.findElement(By.tagName("img"));
	}

	private WebElement getPanel() {
		return findElementById("form:panel");
	}
}
