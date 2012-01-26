package com.prime.showcase.integration.orderlist;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.prime.showcase.integration.AbstractIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrderListIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("orderList.jsf"));
	}
	
	@Test
	public void shouldBeAbleToMoveItems() throws InterruptedException {
		
		assertThat("Istanbul",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[1]").getText()));
		assertThat("Ankara",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[2]").getText()));
		assertThat("Izmir",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[3]").getText()));
		assertThat("Antalya",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[4]").getText()));
		assertThat("Bursa",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[5]").getText()));
		
		WebElement bursa = findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[5]");
		
		Actions action = new Actions(driver);
		action.clickAndHold(bursa);
		action.moveByOffset(0,-70);
		action.click();
		action.build().perform();
		
		Thread.sleep(2000L);
		
		assertThat("Istanbul",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[1]").getText()));
		assertThat("Bursa",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[2]").getText()));
		assertThat("Ankara",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[3]").getText()));
		assertThat("Izmir",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[4]").getText()));
		assertThat("Antalya",equalTo(findElementByXpath("//table[@id='form:basicList']/tbody/tr/td/ul/li[5]").getText()));

	}
	
	@Test
	public void shouldBeAbleToMoveItemsToTop() throws InterruptedException {
		WebElement Xavi = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[4]/table/tbody/tr/td[2]");
		assertThat("Xavi - 6",equalTo(Xavi.getText()));
		
		clickWithScroll(Xavi);
		findElementByXpath("//table[@id='form:customList']/tbody/tr/td/button[2]").click();;
		
		Thread.sleep(1000L);
		
		Xavi = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[1]/table/tbody/tr/td[2]");
		assertThat("Xavi - 6",equalTo(Xavi.getText()));
		WebElement Villa = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[4]/table/tbody/tr/td[2]");
		assertThat("Villa - 7",equalTo(Villa.getText()));
	}
	
	@Test
	public void shouldBeAbleToMoveItemsToBottom() throws InterruptedException {
		WebElement Messi = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[1]/table/tbody/tr/td[2]");
		assertThat("Messi - 10",equalTo(Messi.getText()));
		
		clickWithScroll(Messi);
		findElementByXpath("//table[@id='form:customList']/tbody/tr/td/button[4]").click();;
		
		Thread.sleep(1000L);
		
		Messi = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[4]/table/tbody/tr/td[2]");
		assertThat("Messi - 10",equalTo(Messi.getText()));
		WebElement Xavi = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[3]/table/tbody/tr/td[2]");
		assertThat("Xavi - 6",equalTo(Xavi.getText()));
	}
	
	@Test
	public void shouldBeAbleToMoveItemsToUp() throws InterruptedException {
		WebElement Villa = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[3]/table/tbody/tr/td[2]");
		assertThat("Villa - 7",equalTo(Villa.getText()));
		
		clickWithScroll(Villa);
		findElementByXpath("//table[@id='form:customList']/tbody/tr/td/button").click();;
		
		Thread.sleep(1000L);
		
		Villa = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[2]/table/tbody/tr/td[2]");
		assertThat("Villa - 7",equalTo(Villa.getText()));
		WebElement Iniesta = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[3]/table/tbody/tr/td[2]");
		assertThat("Iniesta - 8",equalTo(Iniesta.getText()));
	}
	
	@Test
	public void shouldBeAbleToMoveItemsToDown() throws InterruptedException {
		WebElement Iniesta = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[2]/table/tbody/tr/td[2]");
		assertThat("Iniesta - 8",equalTo(Iniesta.getText()));
		
		clickWithScroll(Iniesta);
		findElementByXpath("//table[@id='form:customList']/tbody/tr/td/button[3]").click();;
		
		Thread.sleep(1000L);
		
		Iniesta = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[3]/table/tbody/tr/td[2]");
		assertThat("Iniesta - 8",equalTo(Iniesta.getText()));
		WebElement Villa = findElementByXpath("//table[@id='form:customList']/tbody/tr/td[2]/ul/li[2]/table/tbody/tr/td[2]");
		assertThat("Villa - 7",equalTo(Villa.getText()));
	}
	
	

}
