package com.prime.showcase.integration.menu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class MenuIntegrationTest extends AbstractIntegrationTest {
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("menu.jsf"));
	}
	
	@Test
	public void shouldRenderTieredMenu() {
		List<WebElement> menus = driver.findElements(By.className("ui-menu"));

		WebElement tieredMenu = menus.get(0);
		
		assertThat(tieredMenu.getText(),equalTo("Ajax Menuitems\nNon-Ajax Menuitem\nNavigations"));
	
		WebElement firstMenu = tieredMenu.findElement(By.tagName("li"));
		
		firstMenu.click();
		
		WebElement firstMenuSubItem = firstMenu.findElement(By.tagName("ul"));
		
		assertThat(firstMenuSubItem.getText(),equalTo("Save\nUpdate"));
	}
	
	@Test
	public void shouldRenderRegularMenu() {
		List<WebElement> menus = driver.findElements(By.className("ui-menu"));

		WebElement regularMenu = menus.get(2);
		
		assertThat(regularMenu.getText(),equalTo("Ajax Menuitems\nSave\nUpdate\nNon-Ajax Menuitem\nDelete\nNavigations\nHome\nTouchFaces"));
		
	}
	
	@Test
	public void shouldRenderDynamicMenu() {
		List<WebElement> menus = driver.findElements(By.className("ui-menu"));

		WebElement dynamicMenu = menus.get(3);
		
		assertThat(dynamicMenu.getText(),equalTo("Dynamic Submenu 1\nDynamic Menuitem 1.1\nDynamic Submenu 2\nDynamic Menuitem 2.1\nDynamic Menuitem 2.2"));
	}
	
	

}
