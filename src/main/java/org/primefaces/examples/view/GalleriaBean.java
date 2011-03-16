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
import javax.annotation.PostConstruct;
import org.primefaces.examples.domain.Player;

public class GalleriaBean {

    private List<String> images;

    private List<Player> players;

    private Player selectedPlayer;

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();

        for(int i=1;i<=12;i++) {
            images.add("galleria" + i + ".jpg");
        }

        players = new ArrayList<Player>();

        players.add(new Player("Messi", 10, "messi.jpg", "CF"));
        players.add(new Player("Iniesta", 8, "iniesta.jpg", "CM"));
        players.add(new Player("Villa", 7, "villa.jpg", "CF"));
        players.add(new Player("Xavi", 6, "xavi.jpg", "CM"));
        players.add(new Player("Puyol", 5, "puyol.jpg", "CB"));
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

   
    public List<String> getImages() {
        return images;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
