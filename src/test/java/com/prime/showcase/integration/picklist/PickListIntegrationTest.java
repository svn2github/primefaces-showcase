package com.prime.showcase.integration.picklist;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class PickListIntegrationTest extends AbstractIntegrationTest {

	private List<WebElement> cities;

	private WebElement addButton;

	private WebElement addAllButton;

	private WebElement removeButton;

	private WebElement removeAllButton;

	@Before
	public void before() {
		driver.get(toShowcaseUrl("picklist.jsf"));

		List<WebElement> pickListColumns = findElementById("form:pickList").findElements(By.tagName("td"));
		WebElement sourceCitiesColumn = pickListColumns.get(0);
		WebElement buttonsColumn = pickListColumns.get(1);
		cities = sourceCitiesColumn.findElements(By.tagName("li"));
		List<WebElement> buttons = buttonsColumn.findElements(By.tagName("button"));

		addButton = buttons.get(0);
		addAllButton = buttons.get(1);
		removeButton = buttons.get(2);
		removeAllButton = buttons.get(3);
	}

	@Test
	public void shouldAddTwoItems() {

		List<String> selectedCities = new ArrayList<String>();

		select(selectedCities, cities.get(0));
		select(selectedCities, cities.get(1));

		submitForm();

		verifyRemovedFromSourceCities(selectedCities);
		verifyAddedToTargetCities(selectedCities);

	}

	@Test
	public void shouldAddAllItems() {

		List<String> selectedCities = new ArrayList<String>();

		clickAndWait(addAllButton);

		submitForm();

		WebElement sourceCities = getCities(true);
		assertThat(sourceCities.getText(), equalTo(""));
		verifyAddedToTargetCities(selectedCities);

	}

	@Test
	public void shouldAddAndRemoveItems() {

		List<String> selectedCities = new ArrayList<String>();

		select(selectedCities, cities.get(0));
		select(selectedCities, cities.get(1));
		removeFirstClickedItem(selectedCities);

		submitForm();

		verifyRemovedFromSourceCities(selectedCities);
		verifyAddedToTargetCities(selectedCities);
	}

	@Test
	public void shouldAddAndRemoveAllItems() {
		List<String> selectedCities = new ArrayList<String>();
		for (WebElement city : cities) {
			select(selectedCities, city);
		}

		clickAndWait(removeAllButton);

		submitForm();

		WebElement sourceCities = getCities(true);
		for (String selectedCity : selectedCities) {
			assertThat(sourceCities.getText(), containsString(selectedCity));
		}

		WebElement targetCities = getCities(false);
		assertThat(targetCities.getText(), equalTo(""));
	}

	private void clickAndWait(WebElement element) {
		element.click();
		waitForOneSecond();
	}

	private void submitForm() {
		clickToElementById("form:citySubmit");
		waitUntilAjaxRequestCompletes();
	}

	private void verifyRemovedFromSourceCities(List<String> cities) {
		WebElement sourceCities = getCities(true);

		for (String selectedCity : cities) {
			assertThat(sourceCities.getText(), not(containsString(selectedCity)));
		}

	}

	private void verifyAddedToTargetCities(List<String> cities) {
		WebElement targetCities = getCities(false);

		for (String selectedCity : cities) {
			assertThat(targetCities.getText(), containsString(selectedCity));
		}
	}

	private WebElement getCities(boolean isSource) {
		List<WebElement> selectedCityRows = findElementById("form:displayCities").findElements(By.tagName("tr"));
		return selectedCityRows.get(isSource ? 0 : 1).findElements(By.tagName("td")).get(1);
	}

	private void select(List<String> selectedCities, WebElement city) {
		selectedCities.add(city.getText());
		city.click();
		clickAndWait(addButton);
	}

	private void removeFirstClickedItem(List<String> cities) {
		List<WebElement> targetCities = findElementById("form:pickList").findElements(By.tagName("td")).get(2).findElements(By.tagName("li"));
		WebElement city = targetCities.get(0);
		cities.remove(city.getText());
		city.click();
		clickAndWait(removeButton);
	}

}
