package com.prime.showcase.integration.dialog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DialogDynamicContentIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("dialogDynamic.jsf"));
	}

	@Test
	public void shouldLoadDialogContentDynamically() {
		assertThat(findElementByClass("ui-dialog-content").getText(), equalTo(""));

		findElementById("basic").click();

		waitForCondition(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.className("ui-dialog-content")).getText().equals("This content is loaded lazy.");
			}
		});

	}
}
