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

import java.util.ArrayList;
import java.util.List;

import org.primefaces.examples.domain.Player;

public class CarouselBean {

	private List<Player> players;
	
	public CarouselBean() {
		players = new ArrayList<Player>();
		players.add(new Player("Lionel Messi", "barca/messi.jpg"));
		players.add(new Player("Samuel Eto'o", "barca/etoo.jpg"));
		players.add(new Player("Thierry Henry", "barca/henry.jpg"));
		players.add(new Player("Xavi Hernandez", "barca/xavi.jpg"));
		players.add(new Player("Andres Iniesta", "barca/iniesta.jpg"));
		players.add(new Player("Carles Puyol", "barca/puyol.jpg"));
		players.add(new Player("Rafael Marquez", "barca/marquez.jpg"));
		players.add(new Player("Dani Alves", "barca/alves.jpg"));
		players.add(new Player("Victor Valdes", "barca/valdes.jpg"));
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	} 
}
