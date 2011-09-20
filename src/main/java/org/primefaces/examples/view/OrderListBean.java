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

import java.util.ArrayList;
import java.util.List;

import org.primefaces.examples.domain.Player;

public class OrderListBean {

	private List<Player> players;
	
	private List<String> cities;

	public OrderListBean() {
        
		//Players
        players = new ArrayList<Player>();
		players.add(new Player("Messi", 10, "messi.jpg"));
		players.add(new Player("Iniesta", 8, "iniesta.jpg"));
		players.add(new Player("Villa", 7, "villa.jpg"));
		players.add(new Player("Xavi", 6, "xavi.jpg"));
        
		//Cities
		cities = new ArrayList<String>();
		
		cities.add("Istanbul");
		cities.add("Ankara");
		cities.add("Izmir");
		cities.add("Antalya");
		cities.add("Bursa");
	}

    public List<String> getCities() {
        return cities;
    }
    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}