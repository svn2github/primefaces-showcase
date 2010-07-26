package org.primefaces.examples.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.comet.CometContext;

public class ChatController implements Serializable {

	private String message;
	
	private String username;
	
	private boolean loggedIn;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void send(ActionEvent event) {
		CometContext.publish("chat", username + ": " + message);
		
		message = null;
	}
	
	public void login(ActionEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You're logged in!"));
		loggedIn = true;
		
		CometContext.publish("chat", username + " has logged in.");
	}
}
