package com.prime.showcase.integration.selectboolbutton;

import com.prime.showcase.integration.AbstractIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SelectBoolButtonBasicIntegrationTest extends AbstractIntegrationTest {

    protected static final String TEST_URL = PRIME_SHOWCASE_UI + "selectBooleanButton.jsf";

    @BeforeClass
    public static void init() {
        driver.get(TEST_URL);
    }

    @Test
    public void shouldVerifyDOM() {

        try {

            assertTrue("Value1 checkbox input element should be hidden.", !findElementBySelector(escapeClientId("form:value1") + " input[type='checkbox']").isDisplayed());

            assertTrue("Value2 checkbox input element should be hidden.", !findElementBySelector(escapeClientId("form:value2") + " input[type='checkbox']").isDisplayed());

            findElementById("form:submit");

            assertTrue("Values dialog should be hidden on startup.", !findElementById("form:dialog").isDisplayed());

        } catch (NoSuchElementException e) {
            assertTrue("SelectBoolButton showcase DOM not verified.", false);
        }
    }

    @Test
    public void shouldUseButtons() {

        WebElement v1 = findElementById("form:value1");
        WebElement v1_input = findElementBySelector(v1, "input");
        
        WebElement v2 = findElementById("form:value2");
        WebElement v2_input = findElementBySelector(v2, "input");

        assertThat("Value1 should be no.", v1.getText(), equalTo("No"));
        assertTrue("Value1 should have valid hidden input element.", !v1_input.isSelected());
        
        v1.click();

        assertThat("Value1 should be yes.", v1.getText(), equalTo("Yes"));
        
        assertTrue("Value1 should toggle hidden input on change.", v1_input.isSelected());

        assertThat("Value2 should be no.", v2.getText(), equalTo("No"));
        assertTrue("Value2 should have valid hidden input element.", !v2_input.isSelected());

        try {
            assertTrue("Value2 should display icon.", findElementBySelector(v2, ".ui-icon.ui-icon-close").isDisplayed());
        } catch (NoSuchElementException e) {
            assertTrue("Value2 should render icon.", false);
        }
        
        v2.click();
        
        waitUntilAjaxRequestCompletes();
        
        assertTrue("Value2 should toggle hidden input on change.", v2_input.isSelected());
        
        assertThat("Value2 should be yes.", v2.getText(), equalTo("Yes"));
        
        try {
            assertTrue("Value2 should display icon.", findElementBySelector(v2, ".ui-icon.ui-icon-check").isDisplayed());
        } catch (NoSuchElementException e) {
            assertTrue("Value2 should render icon.", false);
        }
    }
}
