package com.prime.showcase.integration.schedule;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.junit.Assert.assertEquals;

public class ScheduleLocalizationIntegrationTest extends AbstractIntegrationTest {

	@Before
	public void before() {
		driver.get(toShowcaseUrl("scheduleLocale.jsf"));
	}

	@Test
	public void shouldRenderInTurkish() {

		assertEquals("Bugün", driver.findElement(By.xpath("//div[@id='form:schedule_container']/table/tbody/tr/td/span[4]/span/span")).getText());
		assertEquals("Ay", driver.findElement(By.xpath("//div[@id='form:schedule_container']/table/tbody/tr/td[3]/span/span/span")).getText());
		assertEquals("Gün", driver.findElement(By.xpath("//div[@id='form:schedule_container']/table/tbody/tr/td[3]/span[3]/span/span")).getText());
		assertEquals("Hafta", driver.findElement(By.xpath("//div[@id='form:schedule_container']/table/tbody/tr/td[3]/span[2]/span/span")).getText());
	}
}
