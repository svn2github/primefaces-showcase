package com.prime.showcase.integration.tree;

import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class TreeCheckBoxSelectionIntegrationTest extends
		AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("treeSelectionCheckbox.jsf");
	private WebElement btnDisplay;

	@Before
	public void init() {
		driver.get(testUrl);
		btnDisplay = findElementById("form:btnDisplay");
	}

	@Test
	public void shouldShowNodes() {
		List<WebElement> parentNodes = findElementsBySelector(escapeClientId("form:checkboxTree")
				+ "> ul > li");

		checkNodesAreVisibleAndSelectable(getSubParentNodes(parentNodes));
		checkGrowlMessage();

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
		node.click();
	}

	private void checkGrowlMessage() {
		btnDisplay.click();
		waitUntilAjaxRequestCompletes();
		List<WebElement> msgs = findElementsByClass("ui-growl-message");
		WebElement growlMessage = msgs.get(msgs.size() - 1).findElement(
				By.tagName("p"));

		String selectedNodeValues = getSelectedNodeValues();
		assertTrue(growlMessage.getText().equals(selectedNodeValues));
	}

	private String getSelectedNodeValues() {
		WebElement hiddenInput = findElementById("form:checkboxTree_selection");
		String[] arrVal = hiddenInput.getAttribute("value").split(",");

		if (arrVal == null || arrVal.length == 0)
			return "";

		StringBuffer selectedValues = new StringBuffer();
		for (int i = 0; i < arrVal.length; i++) {
			String val = arrVal[i];
			selectedValues.append(getNodeText(val));
			if (i != arrVal.length - 1)
				selectedValues.append("\n");
		}

		return selectedValues.toString();

	}

	private String getNodeText(String nodeId) {
		WebElement node = findElementById("form:checkboxTree_node_" + nodeId);
		return node.findElement(By.tagName("div"))
				.findElement(By.tagName("span")).getText();
	}
}
