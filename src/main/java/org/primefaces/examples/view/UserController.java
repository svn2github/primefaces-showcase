package org.primefaces.examples.view;

import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.examples.domain.User;

public class UserController {

	private User user = new User();

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void saveUser(ActionEvent actionEvent) {
		//save user
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("saved", true);
		context.addCallbackParam("user", user);
	}
}