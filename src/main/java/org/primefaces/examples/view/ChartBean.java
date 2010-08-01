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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.examples.domain.Birth;
import org.primefaces.examples.domain.ServerLoad;
import org.primefaces.examples.domain.Vote;

public class ChartBean implements Serializable {

	private List<Sale> sales;
	
	private List<Birth> births;
	
	private List<Vote> votes;

    private List<ServerLoad> servers;

	public ChartBean() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Brand 1", 540));
		sales.add(new Sale("Brand 2", 325));
		sales.add(new Sale("Brand 3", 702));
		sales.add(new Sale("Brand 4", 421));
		
		births = new ArrayList<Birth>();
		births.add(new Birth(2004, 120, 52));
		births.add(new Birth(2005, 100, 60));
		births.add(new Birth(2006, 44, 110));
		births.add(new Birth(2007, 150, 135));
		births.add(new Birth(2008, 125, 120));
		
		votes = new ArrayList<Vote>();
		votes.add(new Vote("Candidate 1", 100));
		votes.add(new Vote("Candidate 2", 100));

        servers = new ArrayList<ServerLoad>(4);
        for (int i = 0; i < 4; i++) {
            servers.add(i, new ServerLoad("Server " + i, (int)(Math.random() * 100), (int)(Math.random() * 100)));
        }

	}

	public List<Sale> getSales() {
		return sales;
	}
	
	public List<Birth> getBirths() {
		return births;
	}
	
	public List<Vote> getVotes() {
		int random1 = (int)(Math.random() * 1000);
		int random2 = (int)(Math.random() * 1000);
	
		votes.get(0).add(random1);
		votes.get(1).add(random2);
		
		return votes;
	}

    public List<ServerLoad> getServers() {
        for (int i = 0; i < 4; i++) {
            servers.get(i).update( (int)(Math.random() * 100), (int)(Math.random() * 100));
        }

        return servers;
    }

	public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
        
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}