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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.examples.domain.Player;

public class TabBean {
    
    private List<Player> players;
    
    private String effect = "fade";

    public TabBean() {
        players = new ArrayList<Player>();
        
        players.add(new Player("Messi", 10, "messi.jpg", "CF"));
        players.add(new Player("Iniesta", 8, "iniesta.jpg", "CM"));
        players.add(new Player("Villa", 7, "villa.jpg", "CF"));
        players.add(new Player("Xavi", 6, "xavi.jpg", "CM"));
        players.add(new Player("Puyol", 5, "puyol.jpg", "CB"));
    }

    public List<Player> getPlayers() {
        return players;
    }
    
    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onPlayerChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Player: " + ((Player) event.getData()).getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onTabClose(TabCloseEvent event) {
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}