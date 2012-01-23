/*
 * Copyright 2009 Prime Technology.
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
import javax.faces.event.ActionEvent;

public class ButtonBean {

	private String text;

    private boolean toggled;
    
    private Integer number;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
	public String submitButtonAction(){
		text = "Command clicked";

		return null;
	}
	
	public void destroyWorld(ActionEvent actionEvent){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "System Error",  "Please try again later.");
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void displayMessage(ActionEvent actionEvent) {
		addMessage("You said:'" + text + "'");
	}
	
	public void save(ActionEvent actionEvent) {
		addMessage("Data saved");
	}
	
	public void update(ActionEvent actionEvent) {
		addMessage("Data updated");
	}
	
	public void delete(ActionEvent actionEvent) {
		addMessage("Data deleted");
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }
}
