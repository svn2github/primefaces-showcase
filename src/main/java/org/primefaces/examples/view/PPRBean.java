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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

public class PPRBean implements Serializable {

	private String firstname;
	
	private String surname;
	
	private String city;

	private String suburb;
	
	private Map<String,String> cities = new HashMap<String, String>();

	private Map<String,Map<String,String>> suburbsData = new HashMap<String, Map<String,String>>();
	
	private Map<String,String> suburbs = new HashMap<String, String>();
	
	private Map<String,String> rooms = new HashMap<String, String>();
	
	private Map<String,Map<String,String>> itemsData = new HashMap<String, Map<String,String>>();
	
	private Map<String,String> items = new HashMap<String, String>();
	
	private String room;

	private String item;

	private String[] selectedCities;

	public PPRBean() {
		cities.put("Istanbul", "Istanbul");
		cities.put("Ankara", "Ankara");
		cities.put("Izmir", "Izmir");
		
		Map<String,String> suburbsIstanbul = new HashMap<String, String>();
		suburbsIstanbul.put("Kadikoy", "Kadikoy");
		suburbsIstanbul.put("Levent", "Levent");
		suburbsIstanbul.put("Cengelkoy", "Cengelkoy");
		
		Map<String,String> suburbsAnkara = new HashMap<String, String>();
		suburbsAnkara.put("Kecioren", "Kecioren");
		suburbsAnkara.put("Cankaya", "Cankaya");
		suburbsAnkara.put("Yenimahalle", "Yenimahalle");
		
		Map<String,String> suburbsIzmir = new HashMap<String, String>();
		suburbsIzmir.put("Cesme", "Cesme");
		suburbsIzmir.put("Gumuldur", "Gumuldur");
		suburbsIzmir.put("Foca", "Foca");
		
		suburbsData.put("Istanbul", suburbsIstanbul);
		suburbsData.put("Ankara", suburbsAnkara);
		suburbsData.put("Izmir", suburbsIzmir);
		
		rooms.put("Living Room", "Living Room");
		rooms.put("Kitchen", "Kitchen");
		rooms.put("Bedroom", "Bedroom");
		
		Map<String,String> livingRoomItems = new HashMap<String, String>();
		livingRoomItems.put("Sofa", "Sofa");
		livingRoomItems.put("Armchair", "Armchair");
		livingRoomItems.put("Coffee Table", "Coffee Table");
		
		Map<String,String> kitchenItems = new HashMap<String, String>();
		kitchenItems.put("Refrigirator", "Refrigirator");
		kitchenItems.put("Dishwasher", "Dishwasher");
		kitchenItems.put("Oven", "Oven");
		
		Map<String,String> bedroomItems = new HashMap<String, String>();
		bedroomItems.put("Bed", "Bed");
		bedroomItems.put("Wardrobe", "Wardrobe");
		bedroomItems.put("Drawer Chest", "Drawer Chest");
		
		itemsData.put("Living Room", livingRoomItems);
		itemsData.put("Kitchen", kitchenItems);
		itemsData.put("Bedroom", bedroomItems);
	}

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
	
	public void savePerson(ActionEvent actionEvent) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You've registered"));
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public Map<String, String> getCities() {
		return cities;
	}

	public void setCities(Map<String, String> cities) {
		this.cities = cities;
	}
	
	public Map<String, Map<String, String>> getSuburbsData() {
		return suburbsData;
	}

	public void setSuburbsData(Map<String, Map<String, String>> suburbsData) {
		this.suburbsData = suburbsData;
	}
	
	public Map<String, String> getSuburbs() {
		return suburbs;
	}

	public void setSuburbs(Map<String, String> suburbs) {
		this.suburbs = suburbs;
	}
	
	public void handleCityChange() {
		if(city !=null && !city.equals(""))
			suburbs = suburbsData.get(city);
		else
			suburbs = new HashMap<String, String>();
	}
	
	public void handleRoomChange(ActionEvent actionEvent) {
		if(room !=null && !room.equals(""))
			items = itemsData.get(room);
		else
			items = new HashMap<String, String>();
	}
	
	private boolean checked;

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public String[] getSelectedCities() {
		return selectedCities;
	}
	public void setSelectedCities(String[] selectedCities) {
		this.selectedCities = selectedCities;
	}
	
	public String getSelectedCitiesAsString() {
		if(selectedCities == null)
			return "";
		
		StringBuffer buffer = new StringBuffer();
		
		for(String city : selectedCities) {
			buffer.append("(");
			buffer.append(city);
			buffer.append(")");
		}
		
		return buffer.toString();
	}
	
	public Map<String, String> getRooms() {
		return rooms;
	}
	public void setRooms(Map<String, String> rooms) {
		this.rooms = rooms;
	}

	public Map<String, Map<String, String>> getItemsData() {
		return itemsData;
	}
	public void setItemsData(Map<String, Map<String, String>> itemsData) {
		this.itemsData = itemsData;
	}

	public Map<String, String> getItems() {
		return items;
	}
	public void setItems(Map<String, String> items) {
		this.items = items;
	}

	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

    public void displayLocation() {
        FacesMessage msg = new FacesMessage("Selected", "City:" + city + ", Suburb: " + suburb);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void reset() {
        RequestContext.getCurrentInstance().reset("form:panel");
    }
    
    public void resetFail() {
        this.firstname = null;
        this.surname = null;
        
        FacesMessage msg = new FacesMessage("Model reset, but it won't work.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
