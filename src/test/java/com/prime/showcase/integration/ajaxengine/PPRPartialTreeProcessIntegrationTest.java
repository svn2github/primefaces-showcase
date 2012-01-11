package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class PPRPartialTreeProcessIntegrationTest extends
		AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("pprPartialTree.jsf");
	private final String errMsg = "Surname is required.";
	private String emptyMsg;
	private final String msgFormat = "Welcome %s %s!";
	private WebElement txtFirstName;
	private WebElement txtSurname;
	private WebElement btnAll;
	private WebElement btnForm;
	private WebElement btnThis;
	private WebElement btnNone;
	private WebElement btnParent;
	private WebElement btnSurname;

	@Before
	public void init() {
		driver.get(testUrl);
		txtFirstName = findElementById("form:firstname");
		txtSurname = findElementById("form:surname");
		btnAll = findElementById("form:btnAll");
		btnForm = findElementById("form:btnForm");
		btnThis = findElementById("form:btnThis");
		btnNone = findElementById("form:btnNone");
		btnParent = findElementById("form:btnParent");
		btnSurname = findElementById("form:btnSurname");
		emptyMsg = String.format(msgFormat, null, null);
	}

	@Test
	public void shouldShowSurnameRequiredErrorMessage() {
		btnAll.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(errMsg.equals(getMessage()));

		btnForm.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(errMsg.equals(getMessage()));

		btnThis.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(emptyMsg.equals(getMessage()));

		btnNone.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementById("form:msgs").getText().equals(""));

		btnParent.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(emptyMsg.equals(getMessage()));

		btnSurname.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(errMsg.equals(getMessage()));
	}

	@Test
	public void shouldShowWelcomeMessage() {
		final String firstname = "Omer";
		final String surname = "GOK";

		String msg = String.format(msgFormat, firstname, surname);

		fillInputs(firstname, surname);
		btnAll.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(msg.equals(getMessage()));

		fillInputs(firstname, surname);
		btnForm.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(msg.equals(getMessage()));

		fillInputs(firstname, surname);
		btnThis.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(emptyMsg.equals(getMessage()));

		fillInputs(firstname, surname);
		btnNone.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(findElementById("form:msgs").getText().equals(""));

		fillInputs(firstname, surname);
		btnParent.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(emptyMsg.equals(getMessage()));

		fillInputs(firstname, surname);
		btnSurname.click();
		waitUntilAjaxRequestCompletes();
		assertTrue(String.format(msgFormat, null, surname).equals(getMessage()));
	}

	private void fillInputs(String firstname, String surname) {
		txtFirstName = findElementById("form:firstname");
		txtSurname = findElementById("form:surname");
		txtFirstName.clear();
		txtSurname.clear();
		txtFirstName.sendKeys(firstname);
		txtSurname.sendKeys(surname);
	}

	private String getMessage() {
		return findElementById("form:msgs").findElement(By.tagName("div"))
				.findElement(By.tagName("ul")).findElements(By.tagName("li"))
				.get(0).getText();
	}

}
