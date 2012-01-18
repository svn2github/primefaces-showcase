package org.primefaces.examples.test.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class PersonBean {

	private String firstname;

	private String surname;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void savePerson(ActionEvent actionEvent) throws Exception {
		Thread.sleep(2000L);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Welcome " + firstname + " " + surname + "!"));
	}
}
