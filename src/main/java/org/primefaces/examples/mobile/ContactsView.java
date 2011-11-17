/*
 * Copyright 2009-2011 Prime Technology.
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
package org.primefaces.examples.mobile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.examples.domain.Contact;

public class ContactsView implements Serializable{
    
    private Contact contact = new Contact();
    
    private List<Contact> contacts;

    public ContactsView() {
        contacts = new ArrayList<Contact>();
    }
    
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    public List<Contact> getContacts() {
        return contacts;
    }
    
    public String saveContact(){
        if(!contacts.contains(contact)) {
            contacts.add(contact);
        }
        
        return "pm:main";
    }
    
    public String prepareNewContact() {
        contact = new Contact();
        
        return "pm:new";
    }

}
