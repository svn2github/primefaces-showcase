package com.prime.showcase.integration.tree;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class TreeListenersIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("treeEvents.jsf");
	private SeleniumActionHelper action;

	@Before
	public void init() {
		driver.get(testUrl);
		action = new SeleniumActionHelper(driver);
	}

	@Test
	public void shouldShowGrowlMessageForEvents() {
		WebElement node0 = findElementById("form:tree_node_0");
		node0.findElement(By.tagName("div")).findElement(By.tagName("span"))
				.click();
		waitUntilAjaxRequestCompletes();
		WebElement growlTitle = findElementByClass("ui-growl-title");
		WebElement growlText = findElementByClass("ui-growl-message")
				.findElement(By.tagName("p"));

		assertTrue(growlTitle.getText().equals("Selected"));
		assertTrue(growlText.getText().equals("Node 0"));

		waitForSeconds(8);

		// TODO : CONTROL tusu calismadigi icin unselect testi yapilamadi.
		// action.keyDown(Keys.CONTROL);
		// node0.findElement(By.tagName("div")).findElement(By.tagName("span"))
		// .click();
		// action.keyUp(Keys.CONTROL);
		// waitUntilAjaxRequestCompletes();
		//
		// growlTitle = findElementByClass("ui-growl-title");
		// growlText = findElementByClass("ui-growl-message").findElement(
		// By.tagName("p"));
		//
		// assertTrue(growlTitle.getText().equals("Unselected"));
		// assertTrue(growlText.getText().equals("Node 0"));
		//
		// waitForSeconds(8);

		node0.findElement(By.tagName("div")).findElement(By.tagName("span"))
				.findElements(By.tagName("span")).get(0).click();
		waitUntilAjaxRequestCompletes();

		growlTitle = findElementByClass("ui-growl-title");
		growlText = findElementByClass("ui-growl-message").findElement(
				By.tagName("p"));

		assertTrue(growlTitle.getText().equals("Expanded"));
		assertTrue(growlText.getText().equals("Node 0"));

		waitForSeconds(8);

		node0.findElement(By.tagName("div")).findElement(By.tagName("span"))
				.findElements(By.tagName("span")).get(0).click();
		waitUntilAjaxRequestCompletes();

		growlTitle = findElementByClass("ui-growl-title");
		growlText = findElementByClass("ui-growl-message").findElement(
				By.tagName("p"));

		assertTrue(growlTitle.getText().equals("Collapsed"));
		assertTrue(growlText.getText().equals("Node 0"));

	}

}
