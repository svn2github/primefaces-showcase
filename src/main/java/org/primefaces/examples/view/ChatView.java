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
import org.primefaces.context.RequestContext;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

public class ChatView {
    
    private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();
    
    private ChatUsers users;

	private String privateMessage;
    
    private String globalMessage;
	
	private String username;
	
	private boolean loggedIn;
    
    private String privateUser;
    
    private final static String CHANNEL = "/chat/";

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
        pushContext.push(CHANNEL + "*", username + ": " + globalMessage);
		
		globalMessage = null;
	}
    
    public void sendPrivate() {
        pushContext.push(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage);
        
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
            pushContext.push(CHANNEL + "*", username + " joined the channel.");
            requestContext.execute("subscriber.connect('/" + username + "')");
            loggedIn = true;
        }
	}
    
    public void disconnect() {
        //remove user and update ui
        users.removeUser(username);
        RequestContext.getCurrentInstance().update("form:users");
        
        //push leave information
        pushContext.push(CHANNEL + "*", username + " left the channel.");
        
        //reset state
        loggedIn = false;
        username = null;
    }
}
