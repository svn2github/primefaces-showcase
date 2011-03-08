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
package org.primefaces.examples.touch;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import javax.faces.event.ActionEvent;

import org.primefaces.examples.service.WeatherService;
import org.primefaces.examples.service.YAHOOWeatherService;

public class WeatherController implements Serializable {

	private String conditions;
	private String city;
	private String unit = "c";		//default
    private Map<String,String> cities;

	private WeatherService weatherService = new YAHOOWeatherService();

    @PostConstruct
    public void init() {
        cities = new LinkedHashMap<String, String>();
        cities.put("Istanbul", "TUXX0014");
        cities.put("Barcelona", "SPXX0015");
        cities.put("London", "UKXX0085");
        cities.put("New York", "USNY0996");
        cities.put("Paris", "FRXX2071");
        cities.put("Rome", "ITXX0067");
    }

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
		
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

    public Map<String, String> getCities() {
        return cities;
    }

    public void retrieveConditions() {
		conditions = weatherService.getConditions(city, unit);
	}

    public void saveSettings() {
        conditions = null;
    }
}
