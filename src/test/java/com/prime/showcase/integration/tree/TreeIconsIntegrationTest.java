package com.prime.showcase.integration.tree;

import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class TreeIconsIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("treeIcons.jsf");

	@Before
	public void init() {
		driver.get(testUrl);
	}

	@Test
	public void shouldShowNodes() {
		List<WebElement> parentNodes = findElementsBySelector(escapeClientId("form:docTree")
				+ "> ul > li");

		checkNodesAndIconsAreVisible(getSubParentNodes(parentNodes));

	}

	private void checkNodesAndIconsAreVisible(List<WebElement> parentNodes) {
		for (WebElement parentNode : parentNodes) {
			WebElement iconElement = findElementsBySelector(
					escapeClientId(parentNode.getAttribute("id"))
							+ "> div > span > span").get(1);

			assertTrue(iconElement.getAttribute("class").contains(
					"ui-icon-folder-collapsed"));

			parentNode.findElement(By.tagName("div"))
					.findElement(By.tagName("span"))
					.findElements(By.tagName("span")).get(0).click();

			assertTrue(iconElement.getAttribute("class").contains(
					"ui-icon-folder-open"));

			WebElement list = parentNode.findElement(By.tagName("ul"));

			assertTrue(list.getCssValue("display").equals("block"));

			List<WebElement> subParentNodes = findElementsBySelector(escapeClientId(parentNode
					.getAttribute("id")) + "> ul > li");

			List<WebElement> tmpSubParentNodes = getSubParentNodes(subParentNodes);
			List<WebElement> childNodes = getChildNodes(subParentNodes);
			checkChildNodeIconsAreVisible(childNodes);

			if (tmpSubParentNodes != null && !tmpSubParentNodes.isEmpty())
				checkNodesAndIconsAreVisible(tmpSubParentNodes);
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

	private void checkChildNodeIconsAreVisible(List<WebElement> clidNodes) {
		for (WebElement child : clidNodes) {
			String styleClass = child.getAttribute("class");

			WebElement iconElement = findElementsBySelector(
					escapeClientId(child.getAttribute("id"))
							+ "> div > span > span").get(0);

			String iconClass = iconElement.getAttribute("class");

			if (styleClass.contains("document")) {
				assertTrue(iconClass.contains("ui-icon-document"));
			} else if (styleClass.contains("picture")) {
				assertTrue(iconClass.contains("ui-icon-image"));
			} else if (styleClass.contains("mp3")) {
				assertTrue(iconClass.contains("ui-icon-video"));
			}

		}
	}

}
