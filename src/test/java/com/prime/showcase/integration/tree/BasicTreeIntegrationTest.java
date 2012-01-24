package com.prime.showcase.integration.tree;

import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class BasicTreeIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("tree.jsf");

	@Before
	public void init() {
		driver.get(testUrl);
	}

	@Test
	public void shouldShowNodes() {
		List<WebElement> parentNodes = findElementsBySelector(escapeClientId("form:tree")
				+ "> ul > li");

		checkNodesAreVisible(getSubParentNodes(parentNodes));

	}

	private void checkNodesAreVisible(List<WebElement> parentNodes) {
		for (WebElement parentNode : parentNodes) {
			parentNode.findElement(By.tagName("div"))
					.findElement(By.tagName("span"))
					.findElements(By.tagName("span")).get(0).click();

			WebElement list = parentNode.findElement(By.tagName("ul"));

			assertTrue(list.getCssValue("display").equals("block"));

			List<WebElement> subParentNodes = findElementsBySelector(escapeClientId(parentNode
					.getAttribute("id")) + "> ul > li");

			List<WebElement> tmpSubParentNodes = getSubParentNodes(subParentNodes);

			if (tmpSubParentNodes != null && !tmpSubParentNodes.isEmpty())
				checkNodesAreVisible(tmpSubParentNodes);
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
}
