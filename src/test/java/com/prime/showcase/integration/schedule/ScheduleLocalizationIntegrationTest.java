package com.prime.showcase.integration.schedule;

import org.junit.Before;
import org.junit.Test;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.junit.Assert.assertEquals;

public class ScheduleLocalizationIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("scheduleLocale.jsf"));
	}

	@Test
	public void shouldRenderInTurkish() {

		assertEquals("bugŸn", findElementByXpath("//div[@id='form:schedule_container']/table/tbody/tr/td/span[4]/span/span").getText());
		assertEquals("Ay", findElementByXpath("//div[@id='form:schedule_container']/table/tbody/tr/td[3]/span/span/span").getText());
		assertEquals("GŸn", findElementByXpath("//div[@id='form:schedule_container']/table/tbody/tr/td[3]/span[3]/span/span").getText());
		assertEquals("Hafta", findElementByXpath("//div[@id='form:schedule_container']/table/tbody/tr/td[3]/span[2]/span/span").getText());
		
		findElementByXpath("//div[@id='form:schedule_container']/table/tbody/tr/td[3]/span[2]/span/span").click();
		assertEquals("TŸm GŸn", findElementBySelector("table.fc-agenda-allday > tbody > tr > th.ui-widget-header.fc-agenda-axis").getText());
		
		
	}
}
