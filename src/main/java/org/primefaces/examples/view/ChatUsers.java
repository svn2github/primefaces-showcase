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

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

public class ChatUsers {
    
    private List<String> users;
    
    @PostConstruct
    public void init() {
        this.users = new ArrayList<String>();
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
    
    public void addUser(String user) {
        this.users.add(user);
    }
    
    public void removeUser(String user) {
        this.users.remove(user);
    }
    
    public boolean contains(String user) {
        return this.users.contains(user);
    }
}
