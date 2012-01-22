package com.prime.showcase.integration.picklist;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class PickListPojoIntegrationTest extends AbstractIntegrationTest {

	private List<WebElement> sourcePlayers;

	private WebElement addButton;

	private WebElement removeButton;

	private WebElement moveUpButton;

	private WebElement moveTopButton;

	private WebElement targetColumn;

	@Before
	public void before() {
		driver.get(toShowcaseUrl("pickList.jsf"));
		List<WebElement> pickListColumns = findElementById("form:pojoPickList").findElements(By.tagName("td"));

		sourcePlayers = pickListColumns.get(1).findElements(By.tagName("li"));
		addButton = pickListColumns.get(14).findElements(By.tagName("button")).get(0);

		removeButton = pickListColumns.get(14).findElements(By.tagName("button")).get(2);
		targetColumn = pickListColumns.get(15);

		List<WebElement> targetButtons = pickListColumns.get(16).findElements(By.tagName("button"));
		moveUpButton = targetButtons.get(0);
		moveTopButton = targetButtons.get(1);
	}

	@Test
	public void shouldPickPlayers() {

		WebElement player1 = sourcePlayers.get(0);

		WebElement player2 = sourcePlayers.get(1);
		String player2Name = getPlayerName(player2);

		WebElement player3 = sourcePlayers.get(2);
		String player3Name = getPlayerName(player3);

		selectPlayer(player1);
		selectPlayer(player2);
		selectPlayer(player3);

		operateOnLastTargetPlayer(moveTopButton);
		operateOnLastTargetPlayer(moveUpButton);
		operateOnLastTargetPlayer(removeButton);

		List<WebElement> targetPlayers = getTargetPlayers();

		assertThat(getPlayerName(targetPlayers.get(0)), equalTo(player3Name));
		assertThat(getPlayerName(targetPlayers.get(1)), equalTo(player2Name));

		submitForm();

		String selectedPlayers = getSelectedPlayers();
		assertThat(selectedPlayers.indexOf(player3Name), lessThan(selectedPlayers.indexOf(player2Name)));
	}

	private String getSelectedPlayers() {
		return findElementById("form:displayPlayers").findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1).getText();
	}

	private void operateOnLastTargetPlayer(WebElement button) {
		List<WebElement> targetPlayers = getTargetPlayers();
		clickAndWait(targetPlayers.get(2));
		clickAndWait(button);
	}

	private List<WebElement> getTargetPlayers() {
		return targetColumn.findElements(By.tagName("li"));
	}

	private String getPlayerName(WebElement player) {
		return player.findElements(By.tagName("td")).get(1).getText();
	}

	private void clickAndWait(WebElement element) {
		element.click();
		waitForSeconds(3);
	}

	public void selectPlayer(WebElement player) {
		clickAndWait(player);
		clickAndWait(addButton);
	}

	private void submitForm() {
		clickToElementById("form:pojoSubmit");
		waitUntilAjaxRequestCompletes();
	}

}
