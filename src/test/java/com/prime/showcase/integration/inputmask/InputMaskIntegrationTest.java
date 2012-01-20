package com.prime.showcase.integration.inputmask;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Keyboard;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class InputMaskIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void init() {
		String testUrl = toShowcaseUrl("inputMask.jsf");
		driver.get(testUrl);
	}

	@Test
	public void shouldAcceptCorrectDate() {
		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
		WebElement element = findElementById("form:date");
		element.click();
		element.clear();
		keyboard.sendKeys("26122008");
		findElementById("form:masks_header").click(); // change focus
		assertThat(element.getAttribute("value"), equalTo("26/12/2008"));
	}

	@Test
	public void shouldAcceptCorrectPhone() {
		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
		WebElement element = findElementById("form:phone");
		element.click();
		element.clear();
		keyboard.sendKeys("1234567890");
		findElementById("form:masks_header").click(); // change focus
		assertThat(element.getAttribute("value"), equalTo("(123) 456-7890"));
	}

	@Test
	public void shouldAcceptCorrectPhoneWithExtension() {
		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
		WebElement element = findElementById("form:phoneWithExt");
		element.click();
		element.clear();
		keyboard.sendKeys("123456789012345");
		findElementById("form:masks_header").click(); // change focus
		assertThat(element.getAttribute("value"), equalTo("(123) 456-7890 x12345"));
	}

	@Test
	public void shouldAcceptCorrectTax() {
		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
		WebElement element = findElementById("form:taxId");
		element.click();
		element.clear();
		keyboard.sendKeys("123456789");
		findElementById("form:masks_header").click(); // change focus
		assertThat(element.getAttribute("value"), equalTo("12-3456789"));
	}

	@Test
	public void shouldAcceptCorrectSSN() {
		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
		WebElement element = findElementById("form:ssn");
		element.click();
		element.clear();
		keyboard.sendKeys("123456789");
		findElementById("form:masks_header").click(); // change focus
		assertThat(element.getAttribute("value"), equalTo("123-45-6789"));
	}

	@Test
	public void shouldAcceptCorrectProductKey() {
		Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
		WebElement element = findElementById("form:key");
		element.click();
		element.clear();
		keyboard.sendKeys("ab123c456");
		findElementById("form:masks_header").click(); // change focus
		assertThat(element.getAttribute("value"), equalTo("ab-123-c456"));
	}

	@Test
	public void shouldSubmitValues() throws InterruptedException {
		shouldAcceptCorrectDate();
		shouldAcceptCorrectPhone();
		shouldAcceptCorrectPhoneWithExtension();
		shouldAcceptCorrectTax();
		shouldAcceptCorrectSSN();
		shouldAcceptCorrectProductKey();

		findElementById("form:submit").click();
		waitUntilAjaxRequestCompletes();

		assertTrue(findElementById("form:modalDialog").isDisplayed());

		assertThat(findElementById("form:dateValue").getText(), equalTo("26/12/2008"));
		assertThat(findElementById("form:phoneValue").getText(), equalTo("(123) 456-7890"));
		assertThat(findElementById("form:phoneWithExtValue").getText(), equalTo("(123) 456-7890 x12345"));
		assertThat(findElementById("form:taxValue").getText(), equalTo("12-3456789"));
		assertThat(findElementById("form:ssnValue").getText(), equalTo("123-45-6789"));
		assertThat(findElementById("form:keyValue").getText(), equalTo("ab-123-c456"));
	}
}
