/*
 * Copyright 2009-2012 Prime Teknoloji.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.examples.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

public class MenuBean {

	private MenuModel model;

	public MenuBean() {
		model = new DefaultMenuModel();
		
		//First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");
        
        DefaultMenuItem item = new DefaultMenuItem("External");
		item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
		firstSubmenu.addElement(item);
        
        model.addElement(firstSubmenu);
		
		//Second submenu
		DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");

		item = new DefaultMenuItem("Save");
		item.setIcon("ui-icon-disk");
        item.setCommand("#{menuBean.save}");
        item.setUpdate("messages");
		secondSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Delete");
        item.setIcon("ui-icon-close");
        item.setCommand("#{menuBean.delete}");
        item.setAjax(false);
        secondSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Redirect");
        item.setIcon("ui-icon-search");
        item.setCommand("#{menuBean.redirect}");
		secondSubmenu.addElement(item);

        model.addElement(secondSubmenu);
	}

	public MenuModel getModel() {
		return model;
	}	
    
    public void save() {
		addMessage("Data saved");
	}
	
	public void update() {
		addMessage("Data updated");
	}
	
	public void delete() {
		addMessage("Data deleted");
	}
    
    public String redirect() {
		return "home?faces-redirect=true";
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}