package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class AjaxDataTableIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("pprDataTable.jsf");
	private final String title = "title";
	private final String author = "author";
	private final String errMsg = "This book has already been added";

	@Before
	public void init() {
		driver.get(testUrl);
	}

	@Test
	public void shouldUpdateDataTable() {

		WebElement txtTitle = findElementById("form:txt_title");
		WebElement txtAuthor = findElementById("form:txt_author");
		WebElement btnAdd = findElementById("form:btnAdd");

		txtTitle.sendKeys(title);
		txtAuthor.sendKeys(author);

		btnAdd.click();

        waitUntilAjaxRequestCompletes();
        
		WebElement lblTitle = findElementById("form:bookTable:0:lblTitle");
		WebElement lblAuthor = findElementById("form:bookTable:0:lblAuthor");

		assertTrue(title.equals(lblTitle.getText()));
		assertTrue(author.equals(lblAuthor.getText()));

	}

	@Test
	public void shouldShowErrorMessageForDuplicateRecord() {
		WebElement txtTitle = findElementById("form:txt_title");
		WebElement txtAuthor = findElementById("form:txt_author");
		WebElement btnAdd = findElementById("form:btnAdd");

		txtTitle.sendKeys(title);
		txtAuthor.sendKeys(author);

		btnAdd.click();

		waitUntilAjaxRequestCompletes();

		txtTitle = findElementById("form:txt_title");
		txtAuthor = findElementById("form:txt_author");
		btnAdd = findElementById("form:btnAdd");

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
