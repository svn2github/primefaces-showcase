package com.prime.showcase.integration.datatable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Test;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class DataTableInCellEditingIntegrationTest extends AbstractIntegrationTest {

	private static final String MODEL_INPUT = "form:carList:0:modelInput";

	private static final String OK_BUTTON = "ui-icon-check";
	
	private static final String EDIT_BUTTON = "ui-icon-pencil";

	@Before
	public void before() {
		driver.get(toShowcaseUrl("datatableEditing.jsf"));
	}

	@Test
	public void shouldEditRows() {

		String prevModelName = getModelName();

		clickToElementByClass(EDIT_BUTTON);
		findElementById(MODEL_INPUT).sendKeys("PF");
		clickToElementByClass(OK_BUTTON);

        waitUntilAjaxRequestCompletes();
        waitUntilAllAnimationsComplete();
        
		String newModelName = getModelName();

		assertThat(newModelName, not(equalTo(prevModelName)));
	}

	private String getModelName() {
		return findElementByClass("ui-cell-editor-output").getText();
	}

}
