package com.prime.showcase.integration.ajaxengine;

import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class PPRAjaxStatusIntegrationTest extends AbstractIntegrationTest {

	private String testUrl = toShowcaseUrl("ajaxStatus.jsf").replace("ui",
			"test");
	private WebElement imgLoading;
	private WebElement txtFirstName;
	private WebElement txtSurname;
	private WebElement btnGlobal;
	private WebElement btnNonGlobal;
	private WebElement lblErrFirstName;
	private WebElement lblErrSurname;
	private String errMessageFormat = "%s: Validation Error: Value is required.";
	private String errFirstNameRequired;
	private String errSurnameRequired;
	private String welcomeMsgFormat = "Welcome %s %s!";
	private String firstName = "Omer";
	private String surname = "GOK";
	private String msgWelcome;

	@Before
	public void init() {
		driver.get(testUrl);
		txtFirstName = findElementById("form:firstname");
		txtSurname = findElementById("form:surname");
		btnGlobal = findElementById("form:btnGlobal");
		btnNonGlobal = findElementById("form:btnNonGlobal");
		errFirstNameRequired = String.format(errMessageFormat, "Firstname");
		errSurnameRequired = String.format(errMessageFormat, "Surname");
		msgWelcome = String.format(welcomeMsgFormat, firstName, surname);
	}

	@Test
	public void shouldShowErrorMessagesForEmptyForm() {
		btnGlobal.click();
		waitUntilAjaxRequestCompletes();
		lblErrFirstName = findElementById("form:lblErrFirstName").findElement(
				By.className("ui-message-error-detail"));
		lblErrSurname = findElementById("form:lblErrSurname").findElement(
				By.className("ui-message-error-detail"));
		List<String> msgList = getMessageList();
		assertTrue(msgList.contains(errFirstNameRequired));
		assertTrue(msgList.contains(errSurnameRequired));
		assertTrue(lblErrFirstName.getText().equals(errFirstNameRequired));
		assertTrue(lblErrSurname.getText().equals(errSurnameRequired));

		btnNonGlobal = findElementById("form:btnNonGlobal");
		btnNonGlobal.click();
		waitUntilAjaxRequestCompletes();
		lblErrFirstName = findElementById("form:lblErrFirstName").findElement(
				By.className("ui-message-error-detail"));
		lblErrSurname = findElementById("form:lblErrSurname").findElement(
				By.className("ui-message-error-detail"));
		msgList = getMessageList();
		assertTrue(msgList.contains(errFirstNameRequired));
		assertTrue(msgList.contains(errSurnameRequired));
		assertTrue(lblErrFirstName.getText().equals(errFirstNameRequired));
		assertTrue(lblErrSurname.getText().equals(errSurnameRequired));
	}

	@Test
	public void shouldShowLoadingImage() {
		txtFirstName.sendKeys(firstName);
		txtSurname.sendKeys(surname);
		btnGlobal = findElementById("form:btnGlobal");
		btnGlobal.click();
		imgLoading = findElementById("ajaxStatusPanel_start");
		String strCssValue = imgLoading.getCssValue("display");
		assertTrue(strCssValue.equals("block"));
		waitUntilAjaxRequestCompletes();
		assertTrue(getMessageList().contains(msgWelcome));

		btnNonGlobal = findElementById("form:btnNonGlobal");
		btnNonGlobal.click();
		imgLoading = findElementById("ajaxStatusPanel_start");
		strCssValue = imgLoading.getCssValue("display");
		assertTrue(strCssValue.equals("none"));
		waitUntilAjaxRequestCompletes();
		assertTrue(getMessageList().contains(msgWelcome));

	}

	private List<String> getMessageList() {
		List<String> msgList = new ArrayList<String>();
		List<WebElement> itemList = findElementById("form:msgs")
				.findElement(By.tagName("div")).findElement(By.tagName("ul"))
				.findElements(By.tagName("li"));

		for (WebElement item : itemList) {
			msgList.add(item.findElement(By.tagName("span")).getText());
		}

		return msgList;
	}
}
