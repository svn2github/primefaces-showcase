/*
 * Copyright 2010 Prime Technology.
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
import java.util.List;
import org.primefaces.event.DragDropEvent;
import org.primefaces.examples.domain.Player;

public class Barcelona implements Serializable {

    private List<Player> players;

    private List<Player> selectedPlayers;

    public Barcelona() {
        players = new ArrayList<Player>();
        selectedPlayers = new ArrayList<Player>();
        
        players.add(new Player("Messi", 10, "messi_thumb.jpg", "forward"));
        players.add(new Player("Ibrahimovic", 9, "ibra_thumb.jpg", "forward"));
        players.add(new Player("Henry", 14, "henry_thumb.jpg", "forward"));
        players.add(new Player("Bojan", 22, "bojan_thumb.jpg", "forward"));
        players.add(new Player("Xavi", 6, "xavi_thumb.jpg", "midfield"));
        players.add(new Player("Iniesta", 8, "iniesta_thumb.jpg", "midfield"));
        players.add(new Player("Marquez", 4, "marquez_thumb.jpg", "defence"));
        players.add(new Player("Puyol", 2, "puyol_thumb.jpg", "defence"));
        players.add(new Player("Alves", 3, "alves_thumb.jpg", "defence"));
        players.add(new Player("Valdes", 1, "valdes_thumb.jpg", "goalkeeper"));
        players.add(new Player("Abidal", 15, "abidal_thumb.jpg", "defence"));
        players.add(new Player("Toure", 16, "toure_thumb.jpg", "midfield"));
        players.add(new Player("Pinto", 25, "pinto_thumb.jpg", "goalkeeper"));
        players.add(new Player("Pique", 25, "pique_thumb.jpg", "defence"));
        players.add(new Player("Keita", 7, "keita_thumb.jpg", "midfield"));
        players.add(new Player("Maxwell", 5, "maxwell_thumb.jpg", "defence"));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getSelectedPlayers() {
        return selectedPlayers;
    }
    
    public void onDrop(DragDropEvent event) {
        Player player = (Player) event.getData();
        
        selectedPlayers.add(player);
    }
}
