package com.prime.showcase.integration.tree;

import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class TreeSingleSelectionIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("treeSelectionSingle.jsf");
	private WebElement btnDisplay;

	@Before
	public void init() {
		driver.get(testUrl);
		btnDisplay = findElementById("form:btnDisplay");
	}

	@Test
	public void shouldShowNodes() {
		List<WebElement> parentNodes = findElementsBySelector(escapeClientId("form:treeSingle")
				+ "> ul > li");

		checkNodesAreVisibleAndSelectable(getSubParentNodes(parentNodes));

	}

	private void checkNodesAreVisibleAndSelectable(List<WebElement> parentNodes) {
		for (WebElement parentNode : parentNodes) {
			selectAndCheckTheNode(parentNode);

			parentNode.findElement(By.tagName("div"))
					.findElement(By.tagName("span"))
					.findElements(By.tagName("span")).get(0).click();

			WebElement list = parentNode.findElement(By.tagName("ul"));

			assertTrue(list.getCssValue("display").equals("block"));

			List<WebElement> subParentNodes = findElementsBySelector(escapeClientId(parentNode
					.getAttribute("id")) + "> ul > li");

			List<WebElement> tmpSubParentNodes = getSubParentNodes(subParentNodes);
			List<WebElement> childNodes = getChildNodes(subParentNodes);
			for (WebElement childNode : childNodes) {
				selectAndCheckTheNode(childNode);
			}

			if (tmpSubParentNodes != null && !tmpSubParentNodes.isEmpty())
				checkNodesAreVisibleAndSelectable(tmpSubParentNodes);
		}
	}

	private List<WebElement> getSubParentNodes(List<WebElement> subParentNodes) {
		List<WebElement> tmpSubParentNodes = new ArrayList<WebElement>();
		for (WebElement tmpNode : subParentNodes) {
			if (tmpNode.getAttribute("class").equals("ui-tree-parent default"))
				tmpSubParentNodes.add(tmpNode);
		}
		return tmpSubParentNodes;
	}

	private List<WebElement> getChildNodes(List<WebElement> subParentNodes) {
		List<WebElement> tmpSubParentNodes = new ArrayList<WebElement>();
		for (WebElement tmpNode : subParentNodes) {
			if (!tmpNode.getAttribute("class").equals("ui-tree-parent default"))
				tmpSubParentNodes.add(tmpNode);
		}
		return tmpSubParentNodes;
	}

	private void selectAndCheckTheNode(WebElement item) {
		WebElement node = item.findElement(By.tagName("div")).findElement(
				By.tagName("span"));
		String strNode = node.getText();
		node.click();
		btnDisplay.click();
		waitUntilAjaxRequestCompletes();
		List<WebElement> msgs = findElementsByClass("ui-growl-message");
		WebElement growlMessage = msgs.get(msgs.size() - 1).findElement(
				By.tagName("p"));

		assertTrue(growlMessage.getText().equals(strNode));

		waitForSeconds(8);
	}

}
