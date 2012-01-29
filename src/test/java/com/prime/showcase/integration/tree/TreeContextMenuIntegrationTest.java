package com.prime.showcase.integration.tree;

import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class TreeContextMenuIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("treeContextMenu.jsf");
	private SeleniumActionHelper action;

	@Before
	public void init() {
		driver.get(testUrl);
		action = new SeleniumActionHelper(driver);
	}

	@Test
	public void shouldShowContextMenuForRightClickAndViewAndDeleteNode() {
		WebElement node0 = findElementById("form:treeSingle_node_0")
				.findElement(By.tagName("div")).findElement(By.tagName("span"));
		action.rightClick(node0);
		waitUntilAjaxRequestCompletes();
        waitUntilAllAnimationsComplete();
		WebElement contextMenu = findElementById("form:contextMenuId");
		assertTrue(contextMenu.isDisplayed());
		List<WebElement> menuItems = contextMenu.findElement(By.tagName("ul"))
				.findElements(By.tagName("li"));

		menuItems.get(0).findElement(By.tagName("a")).click();
		waitUntilAjaxRequestCompletes();
        waitUntilAllAnimationsComplete();
		assertTrue(findElementByClass("ui-growl-message")
				.findElement(By.tagName("p")).getText().equals("Node 0"));

		action.rightClick(node0);
		contextMenu = findElementById("form:contextMenuId");
		menuItems = contextMenu.findElement(By.tagName("ul")).findElements(
				By.tagName("li"));

		menuItems.get(1).findElement(By.tagName("a")).click();
		waitUntilAjaxRequestCompletes();
        waitUntilAllAnimationsComplete();
		node0 = findElementById("form:treeSingle_node_0").findElement(
				By.tagName("div")).findElement(By.tagName("span"));
		assertTrue(!node0.findElements(By.tagName("span")).get(2).getText()
				.equals("Node 0"));

	}
}
