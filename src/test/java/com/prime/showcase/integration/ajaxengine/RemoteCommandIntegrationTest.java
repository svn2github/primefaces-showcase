package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class RemoteCommandIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("remoteCommand.jsf");
	private WebElement lazyPanel;
	private WebElement btnLoad;
	private final String strLazyPanelText = "This part of page is lazily loaded on demand using a RemoteCommand";

	@Before
	public void init() {
		driver.get(testUrl);
		btnLoad = findElementById("form:btnLoad");
	}

	@Test
	public void shouldChangeLazyPanelText() {
		btnLoad.click();
		waitUntilAjaxRequestCompletes();
		lazyPanel = findElementById("form:lazypanel");
		assertTrue(lazyPanel.getText().equals(strLazyPanelText));
	}
}
