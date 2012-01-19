package com.prime.showcase.integration.messages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class MessagesIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("messages.jsf"));
	}

	@Test
	public void shouldShowMessages() {
		testMessagesForType("info", "PrimeFaces rocks!");
		testMessagesForType("warn", "Watch out for PrimeFaces!");
		testMessagesForType("error", "PrimeFaces makes no mistakes");
		testMessagesForType("fatal", "Fatal Error in System");
	}

	@Test
	public void shouldShowMessagesForInputs() {
		findElementById("form2:submitButton").click();
		waitUntilAjaxRequestCompletes();

		WebElement messages = findElementById("form1:messages");
		List<WebElement> errors = messages.findElements(By.tagName("li"));
		assertThat(errors.size(), equalTo(3));

		testMessageExists("form2:defaultMessage", true, true);
		testMessageExists("form2:textOnlyMessage", false, true);
		testMessageExists("form2:iconOnlyMessage", false, false);
	}

	private void testMessagesForType(String type, String expectedMessageDetail) {
		String buttonId = "form1:" + type + "Button";
		String iconClass = "ui-messages-" + type + "-icon";
		String summaryClass = "ui-messages-" + type + "-summary";
		String detailClass = "ui-messages-" + type + "-detail";

		findElementById(buttonId).click();
		waitUntilAjaxRequestCompletes();

		WebElement messages = findElementById("form1:messages");

		messages.findElement(By.className(iconClass));

		String summary = messages.findElement(By.className(summaryClass)).getText();
		assertThat(summary, equalTo("Sample " + type + " message"));

		String detail = messages.findElement(By.className(detailClass)).getText();
		assertThat(detail, equalTo(expectedMessageDetail));
	}

	private void testMessageExists(String messageId, boolean iconExpected, boolean detailExpected) {
		WebElement message = findElementById(messageId);

		if (iconExpected) {
			message.findElement(By.className("ui-message-error-icon"));
		}

		if (detailExpected) {
			message.findElement(By.className("ui-message-error-detail"));
		}
	}

}
