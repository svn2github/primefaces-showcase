package com.prime.showcase.integration.schedule;

import org.junit.Before;
import org.junit.Test;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScheduleLocalizationIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("scheduleLocale.jsf"));
	}

	@Test
	public void shouldRenderInTurkish() {

		assertTrue(findElementById("form:schedule").isDisplayed());
		assertEquals("Ay", findElementByXpath("//div[@id='form:schedule_container']/table/tbody/tr/td[3]/span/span/span").getText());
		assertEquals("Hafta", findElementByXpath("//div[@id='form:schedule_container']/table/tbody/tr/td[3]/span[2]/span/span").getText());
		assertEquals("Pt", findElementByXpath("//div[@id='form:schedule_container']/div/div/table/thead/tr/th[1]").getText());
		assertEquals("Sa", findElementByXpath("//div[@id='form:schedule_container']/div/div/table/thead/tr/th[2]").getText());
		assertEquals("Pz", findElementByXpath("//div[@id='form:schedule_container']/div/div/table/thead/tr/th[7]").getText());
		
		
		
	}
}
