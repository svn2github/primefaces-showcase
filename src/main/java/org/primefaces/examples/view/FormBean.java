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
package org.primefaces.examples.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

public class FormBean implements Serializable {

	private boolean value1;

	private boolean value2;

	private List<String> selectedMovies;

	private List<String> selectedOptions;
    
    private String car;
    
    private List<SelectItem> cars;

	private Map<String, String> movies;

	private Integer option;

	private Integer number;

	public FormBean() {
		movies = new HashMap<String, String>();
		movies.put("Scarface", "Scarface");
		movies.put("Goodfellas", "Goodfellas");
		movies.put("Godfather", "Godfather");
		movies.put("Carlito's Way", "Carlito's Way");
        
        SelectItemGroup g1 = new SelectItemGroup("German Cars");
        g1.setSelectItems(new SelectItem[] {new SelectItem("BMW", "BMW"), new SelectItem("Mercedes", "Mercedes"), new SelectItem("Volkswagen", "Volkswagen")});
        
        SelectItemGroup g2 = new SelectItemGroup("American Cars");
        g2.setSelectItems(new SelectItem[] {new SelectItem("Chrysler", "Chrysler"), new SelectItem("GM", "GM"), new SelectItem("Ford", "Ford")});
        
        cars = new ArrayList<SelectItem>();
        cars.add(g1);
        cars.add(g2);
	}

	public boolean isValue1() {
		return value1;
	}

	public void setValue1(boolean value1) {
		this.value1 = value1;
	}

	public boolean isValue2() {
		return value2;
	}

	public void setValue2(boolean value2) {
		this.value2 = value2;
	}

	public List<String> getSelectedMovies() {
		return selectedMovies;
	}

	public void setSelectedMovies(List<String> selectedMovies) {
		this.selectedMovies = selectedMovies;
	}

	public List<String> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(List<String> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	public Map<String, String> getMovies() {
		return movies;
	}

	public Integer getOption() {
		return option;
	}

	public void setOption(Integer option) {
		this.option = option;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public List<SelectItem> getCars() {
        return cars;
    }

	public void addMessage() {
		String summary = value2 ? "Checked" : "Unchecked";

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	}
}
