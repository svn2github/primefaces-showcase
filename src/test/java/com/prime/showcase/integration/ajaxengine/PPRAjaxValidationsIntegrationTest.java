package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class PPRAjaxValidationsIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("pprAjaxValidations.jsf");
	private final String firstName = "Omer";
	private final String surname = "GOK";
	private final String msgFormat = "Welcome %s %s!";
	private final String msgFirstNameRequired = "Firstname: Validation Error: Value is required.";
	private final String msgFirstNameLengthError = "Firstname: Validation Error: Length is less than allowable minimum of '2'";
	private final String msgSurnameRequired = "Surname: Validation Error: Value is required.";
	private String msg;
	private WebElement txtFirstName;
	private WebElement txtSurname;
	private WebElement btnSubmit;
	private WebElement msgFirstName;
	private WebElement msgSurname;

	@Before
	public void init() {
		driver.get(testUrl);
		msg = String.format(msgFormat, firstName, surname);
		txtFirstName = findElementById("form:firstname");
		txtSurname = findElementById("form:surname");
		btnSubmit = findElementById("form:btn");
	}

	@Test
	public void shouldShowErrorMessagesForBlankForm() {
		btnSubmit.click();
		waitUntilAjaxRequestCompletes();

		msgFirstName = findElementById("form:msgFirstname").findElement(
				By.className("ui-message-error-detail"));

		assertTrue(msgFirstNameRequired.equals(msgFirstName.getText()));

		msgSurname = findElementById("form:msgSurname").findElement(
				By.className("ui-message-error-detail"));

		assertTrue(msgSurnameRequired.equals(msgSurname.getText()));

		List<WebElement> msgs = getMessageList();

		assertTrue(msgFirstNameRequired.equals(msgs.get(0).getText()));
		assertTrue(msgSurnameRequired.equals(msgs.get(1).getText()));
	}

	@Test
	public void shouldShowMinimumLengthErrorForFirstname() {
		txtFirstName.sendKeys("a");
		txtSurname.sendKeys(surname);
		btnSubmit.click();
		waitUntilAjaxRequestCompletes();

		msgFirstName = findElementById("form:msgFirstname").findElement(
				By.className("ui-message-error-detail"));

		assertTrue(msgFirstNameLengthError.equals(msgFirstName.getText()));

		List<WebElement> msgs = getMessageList();

		assertTrue(msgFirstNameLengthError.equals(msgs.get(0).getText()));
	}

	@Test
	public void shouldShowInfoMessage() {
		txtFirstName = findElementById("form:firstname");
		txtSurname = findElementById("form:surname");
		txtFirstName.clear();
		txtSurname.clear();

		txtFirstName.sendKeys(firstName);
		txtSurname.sendKeys(surname);
		btnSubmit.click();

		waitUntilAjaxRequestCompletes();

		List<WebElement> msgs = getMessageList();

		assertTrue(msg.equals(msgs.get(0).getText()));
	}

	private List<WebElement> getMessageList() {
		return findElementById("form:msgs").findElement(By.tagName("div"))
				.findElement(By.tagName("ul")).findElements(By.tagName("li"));
	}

}
