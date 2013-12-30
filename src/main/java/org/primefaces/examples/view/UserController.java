package org.primefaces.examples.view;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.examples.domain.User;

public class UserController {

	private User user = new User();
    
    @PostConstruct
    public void init() {
        if(!FacesContext.getCurrentInstance().isPostback()) {
            RequestContext.getCurrentInstance().execute("alert('This onload script is added from backing bean.')");
        }
    }

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void saveUser(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("saved", true);    //basic parameter
		context.addCallbackParam("user", user);     //pojo as json
        
        //execute javascript oncomplete
        context.execute("PrimeFaces.info('Hello from the Backing Bean');");
        
        //update panel
        context.update("form:panel");
        
        //scroll to panel
        context.scrollTo("form:panel");
        
        //add facesmessage
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Success"));
	}
}