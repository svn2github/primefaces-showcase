package com.prime.showcase.integration.menubar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;
import com.prime.showcase.integration.SeleniumActionHelper;

public class MenubarIntegrationTest extends AbstractIntegrationTest  {
	
    protected static SeleniumActionHelper action;
    
	@Before
	public void before() {
		driver.get(toShowcaseUrl("menubar.jsf"));
        action = new SeleniumActionHelper(driver);
	}
	
	@Test
	public void shouldRenderMenuBar() throws InterruptedException {
		WebElement menuBar = driver.findElement(By.className("ui-menu"));

		List<WebElement> menuItems = menuBar.findElements(By.className("ui-menuitem"));

		assertThat(menuItems.get(0).getText(),equalTo("File"));
		assertThat(menuItems.get(6).getText(),equalTo("Edit"));
		assertThat(menuItems.get(9).getText(),equalTo("Help"));
		assertThat(menuItems.get(15).getText(),equalTo("Actions"));
		assertThat(menuItems.get(21).getText(),equalTo("Quit"));
	}
	
	@Test
	public void shouldRenderSubMenu() {
		WebElement menuBar = driver.findElement(By.className("ui-menu"));

		List<WebElement> menuItems = menuBar.findElements(By.className("ui-menuitem"));
		
		WebElement editSubMenu = menuItems.get(6);
		
        action.mouseHover(editSubMenu);
		
		WebElement ulOfEditSubMenu = editSubMenu.findElement(By.tagName("ul"));
		
		assertThat(ulOfEditSubMenu.getText(),equalTo("Undo\nRedo"));
	}
	
	@Test
	public void shouldRenderSecondLevelMenu() {
		WebElement menuBar = driver.findElement(By.className("ui-menu"));

		List<WebElement> menuItems = menuBar.findElements(By.className("ui-menuitem"));
		WebElement fileMenu = menuItems.get(0);
		
        action.mouseHover(fileMenu);
		
		WebElement newMenu = fileMenu.findElement(By.tagName("ul")).findElement(By.className("ui-menuitem"));

        action.mouseHover(newMenu);
        
		assertThat(newMenu.findElement(By.tagName("ul")).getText(), equalTo("Project\nOther"));
	}

}
