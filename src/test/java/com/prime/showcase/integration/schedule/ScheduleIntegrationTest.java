package com.prime.showcase.integration.schedule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;
import com.prime.showcase.integration.TestingUtils;

public class ScheduleIntegrationTest extends AbstractIntegrationTest {

	private String SUNDAY_EVENT_TITLE = "Integration testing with Selenium";

	private SeleniumActionHelper actionHelper = new SeleniumActionHelper(driver);

	@Before
	public void before() {
		driver.get(toShowcaseUrl("schedule.jsf"));
	}

	@Test
	public void shouldAddEvent() {

		int initialNumberOfEvents = getNumberOfScheduledEvents();

		addEventToFirstSunday();

		int currentNumberOfEvents = getNumberOfScheduledEvents();

		assertThat(currentNumberOfEvents, equalTo(initialNumberOfEvents + 1));

		boolean eventFound = checkIfEventAdded();

		assertThat(eventFound, equalTo(true));
	}

	private boolean checkIfEventAdded() {
		return getAddedEvent() != null;
	}

	@Test
	public void shouldMoveEvent() {
		addEventToFirstSunday();

		WebElement event = getAddedEvent();

		int initialTopValue = TestingUtils.getInteger(event.getCssValue("top"));

		moveEventToNextSunday();

		event = getAddedEvent();

		assertThat(getDayDelta(), equalTo(7));

		int currentTopValue = TestingUtils.getInteger(event.getCssValue("top"));

		assertThat(currentTopValue, greaterThan(initialTopValue));
	}

	private int getDayDelta() {
		WebElement message = findElementByClass("ui-growl-message");

		return TestingUtils.getInteger(message.findElement(By.tagName("p")).getText());
	}

	@Test
	public void shouldResizeEvent() {
		addEventToFirstSunday();

		WebElement event = getAddedEvent();
		int initialWidth = getCssWidth(event);

		extendEventToMonday();

		assertThat(getDayDelta(), equalTo(2));

		event = getAddedEvent();
		int currentWidth = getCssWidth(event);

		assertThat(currentWidth, greaterThan(initialWidth));
	}

	@Test
	public void shouldEditEvent() {
		String newEventName = "Testing Scheduler";
		addEventToFirstSunday();

		getAddedEvent().click();
		
		setEventNameAndSubmit(newEventName);
		
		assertThat(getEventTitle(getEvent(newEventName)), equalTo(newEventName));
	}

	private void setEventNameAndSubmit(String newEventName) {
		WebElement titleInput = findElementById("form:title"); 
		titleInput.clear();
		titleInput.sendKeys(newEventName);
		clickToElementById("form:addButton");
	}

	private void extendEventToMonday() {
		WebElement event = getAddedEvent();
		WebElement resizer = event.findElement(By.className("ui-resizable-handle"));

		moveByXY(resizer, 110, 0);
	}

	private int getCssWidth(WebElement event) {
		return TestingUtils.getInteger(event.getCssValue("width"));
	}

	private WebElement getAddedEvent() {
		return getEvent(SUNDAY_EVENT_TITLE);
	}
	
	private WebElement getEvent(String eventName) {
		for (WebElement event : getScheduledEvents()) {
			if (getEventTitle(event).equals(eventName)) {
				return event;
			}
		}
		return null;
	}

	private void moveEventToNextSunday() {

		WebElement event = getAddedEvent();

		moveByXY(event, 0, 80);
	}

	private void moveByXY(WebElement element, int x, int y) {
		actionHelper.clickAndHoldOnElement(element);
		waitForOneSecond();
		actionHelper.moveByOffSet(x, y);
		waitForOneSecond();
		actionHelper.releaseMouse();
	}

	private void addEventToFirstSunday() {
		WebElement firstSaturday = getSecondSundayOfTheMonth();
		firstSaturday.click();

		setEventNameAndSubmit(SUNDAY_EVENT_TITLE);

		waitUntilAjaxRequestCompletes();
	}

	private String getEventTitle(WebElement event) {
		return event.findElement(By.className("fc-event-title")).getText();
	}

	private List<WebElement> getScheduledEvents() {
		return findElementsByClass("fc-event");
	}

	private int getNumberOfScheduledEvents() {
		return findElementsByClass("fc-event").size();
	}

	private WebElement getSecondSundayOfTheMonth() {
		return findElementByClass("fc-week1").findElement(By.className("fc-sun"));
	}

}
