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
import org.primefaces.component.chart.series.ChartSeries;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.examples.domain.Birth;
import org.primefaces.examples.domain.ServerLoad;
import org.primefaces.examples.domain.Vote;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.PieChartModel;

public class ChartBean implements Serializable {

	private List<Sale> sales;
	
	private List<Birth> births;
	
	private List<Vote> votes;

    private List<ServerLoad> servers;

    private CartesianChartModel cartesianModel;

    private PieChartModel pieModel;

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

        createCartesianModel();
        createPieModel();
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

    public CartesianChartModel getCartesianModel() {
        return cartesianModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }
    
    private void createCartesianModel() {
        cartesianModel = new CartesianChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");

        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");

        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        cartesianModel.addSeries(boys);
        cartesianModel.addSeries(girls);
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        
        pieModel.set("Brand 1", 540);
        pieModel.set("Brand 2", 325);
        pieModel.set("Brand 3", 702);
        pieModel.set("Brand 4", 421);
    }
}