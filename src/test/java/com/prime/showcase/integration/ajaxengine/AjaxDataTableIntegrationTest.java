package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AjaxDataTableIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("pprDataTable.jsf");
	private final String title = "title";
	private final String author = "author";
	private final String errMsg = "This book has already been added";

	@Test
	public void shouldUpdateDataTable() {
		driver.get(testUrl);

		WebElement txtTitle = findElementById("txt_title");
		WebElement txtAuthor = findElementById("txt_author");
		WebElement btnAdd = findElementById("btnAdd");

		txtTitle.sendKeys(title);
		txtAuthor.sendKeys(author);

		btnAdd.click();

		waitUntilElementExistsAndGetsValue("bookTable:0:lblTitle", title);
		waitUntilElementExistsAndGetsValue("bookTable:0:lblAuthor", author);

		WebElement lblTitle = findElementById("bookTable:0:lblTitle");
		WebElement lblAuthor = findElementById("bookTable:0:lblAuthor");

		assertTrue(title.equals(lblTitle.getText()));
		assertTrue(author.equals(lblAuthor.getText()));

	}

	@Test
	public void shouldShowErrorMessageForDuplicateRecord() {
		WebElement txtTitle = findElementById("txt_title");
		WebElement txtAuthor = findElementById("txt_author");
		WebElement btnAdd = findElementById("btnAdd");

		txtTitle.sendKeys(title);
		txtAuthor.sendKeys(author);

		btnAdd.click();

		waitUntilAjaxRequestCompletes();

		WebElement growlMessage = driver.findElement(By
				.className("ui-growl-message"));

		String msgString = growlMessage.findElement(By.tagName("p")).getText();

		assertTrue(errMsg.equals(msgString));
	}
}
