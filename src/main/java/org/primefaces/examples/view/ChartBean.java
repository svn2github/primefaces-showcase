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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

public class ChartBean implements Serializable {

    private CartesianChartModel cartesianModel;

    private PieChartModel pieModel;

    private PieChartModel livePieModel;

	public ChartBean() {
        createCartesianModel();
        createPieModel();
        createLivePieModel();
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

    public PieChartModel getLivePieModel() {
        int random1 = (int)(Math.random() * 1000);
		int random2 = (int)(Math.random() * 1000);

		livePieModel.getData().put("Candidate 1", random1);
        livePieModel.getData().put("Candidate 2", random2);
        
        return livePieModel;
    }
    
    private void createCartesianModel() {
        cartesianModel = new CartesianChartModel();

        LineChartSeries boys = new LineChartSeries();
        boys.setTitle("Boys");

        boys.set(2004, 120);
        boys.set(2005, 100);
        boys.set(2006, 44);
        boys.set(2007, 150);
        boys.set(2008, 25);

        LineChartSeries girls = new LineChartSeries();
        girls.setTitle("Girls");
        girls.setMarkerStyle("diamond");

        girls.set(2004, 52);
        girls.set(2005, 60);
        girls.set(2006, 110);
        girls.set(2007, 135);
        girls.set(2008, 120);

        cartesianModel.addSeries(boys);
        cartesianModel.addSeries(girls);

        cartesianModel.getCategories().add("2004");
        cartesianModel.getCategories().add("2005");
        cartesianModel.getCategories().add("2006");
        cartesianModel.getCategories().add("2007");
        cartesianModel.getCategories().add("2008");
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        
        pieModel.set("Brand 1", 540);
        pieModel.set("Brand 2", 325);
        pieModel.set("Brand 3", 702);
        pieModel.set("Brand 4", 421);
    }

    private void createLivePieModel() {
        livePieModel = new PieChartModel();

        livePieModel.set("Candidate 1", 540);
        livePieModel.set("Candidate 2", 325);
    }
}