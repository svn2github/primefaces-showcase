package org.primefaces.examples.view;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

public class MenuBean {

	private MenuModel model;

	public MenuBean() {
		model = new DefaultMenuModel();
		
		//First submenu
		Submenu submenu = new Submenu();
		submenu.setLabel("Dynamic Submenu 1");
		
		MenuItem item = new MenuItem();
		item.setValue("Dynamic Menuitem 1.1");
		item.setUrl("#");
		submenu.getChildren().add(item);
		
		model.addSubmenu(submenu);
		
		//Second submenu
		submenu = new Submenu();
		submenu.setLabel("Dynamic Submenu 2");
		
		item = new MenuItem();
		item.setValue("Dynamic Menuitem 2.1");
		item.setUrl("#");
		submenu.getChildren().add(item);
		
		item = new MenuItem();
		item.setValue("Dynamic Menuitem 2.2");
		item.setUrl("#");
		submenu.getChildren().add(item);
		
		model.addSubmenu(submenu);
	}

	public MenuModel getModel() {
		return model;
	}	
}