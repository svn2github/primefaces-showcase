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

import org.primefaces.context.RequestContext;
import org.primefaces.examples.push.chat.ChatUsers;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ChatView {
    
    //private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();

    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

    private ChatUsers users;

	private String privateMessage;
    
    private String globalMessage;
	
	private String username;
	
	private boolean loggedIn;
    
    private String privateUser;
    
    private final static String CHANNEL = "/{room}/";

    public void setUsers(ChatUsers users) {
        this.users = users;
    }

    public String getPrivateUser() {
        return privateUser;
    }

    public void setPrivateUser(String privateUser) {
        this.privateUser = privateUser;
    }

    public String getGlobalMessage() {
        return globalMessage;
    }

    public void setGlobalMessage(String globalMessage) {
        this.globalMessage = globalMessage;
    }

    public String getPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(String privateMessage) {
        this.privateMessage = privateMessage;
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

	public void sendGlobal() {
        eventBus.publish(CHANNEL + "*", username + ": " + globalMessage);
		
		globalMessage = null;
	}
    
    public void sendPrivate() {
        eventBus.publish(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage);
        
        privateMessage = null;
    }
	
	public void login() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        
		if(users.contains(username)) {
            loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username taken", "Try with another username."));
            
            requestContext.update("growl");
        }
        else {
            users.addUser(username);
            requestContext.execute("PF('subscriber').connect('/" + username + "')");
            eventBus.publish(CHANNEL + "*", username + ": joined the channel.");
            loggedIn = true;
        }
	}
    
    public void disconnect() {
        //remove user and update ui
        users.removeUser(username);
        RequestContext.getCurrentInstance().update("form:users");
        
        //push leave information
        eventBus.publish(CHANNEL + "*", username + " left the channel.");
        
        //reset state
        loggedIn = false;
        username = null;
    }
}
