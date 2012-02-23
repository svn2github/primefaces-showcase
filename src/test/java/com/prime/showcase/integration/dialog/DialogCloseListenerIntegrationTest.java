package com.prime.showcase.integration.dialog;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DialogCloseListenerIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("dialogClose.jsf"));
	}

	@Test
	public void shouldInvokeDialogCloseListener() {
		WebElement openDialogLink = findElementById("form:openDialogLink");
		openDialogLink.click();
		
        waitUntilAllAnimationsComplete();
        
		findElementBySelector(".ui-dialog-titlebar-close").click();
		
		waitUntilElementExists(By.className("ui-growl"));
	}

}
