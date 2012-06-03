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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.OhlcChartSeries;
import org.primefaces.model.chart.PieChartModel;

public class ChartBean implements Serializable {

    private CartesianChartModel categoryModel;

    private CartesianChartModel linearModel;
    
    private CartesianChartModel fillToZero;

    private PieChartModel pieModel;
    
    private DonutChartModel donutModel;
    
    private MeterGaugeChartModel meterGaugeModel;
    
    private BubbleChartModel bubbleModel;
    
    private OhlcChartModel ohlcModel;
    
    private OhlcChartModel ohlcModel2;

    private PieChartModel livePieModel;

	public ChartBean() {
        createCategoryModel();
        createLinearModel();
        createPieModel();
        createLivePieModel();
        createDonutModel();
        createBubbleModel();
        createOhlcModel();
        createOhlcModel2();
        createFillToZero();
        createMeterGaugeModel();
	}

	public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
        
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }
    
    public MeterGaugeChartModel getMeterGaugeModel() {
        return meterGaugeModel;
    }
    
    public DonutChartModel getDonutModel() {
        return donutModel;
    }
    
    public CartesianChartModel getFillToZero() {
        return fillToZero;
    }
    
    public BubbleChartModel getBubbleModel() {
        return bubbleModel;
    }
    
    public OhlcChartModel getOhlcModel() {
        return ohlcModel;
    }
    
    public OhlcChartModel getOhlcModel2() {
        return ohlcModel2;
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }
    
    public PieChartModel getLivePieModel() {
        int random1 = (int)(Math.random() * 1000);
		int random2 = (int)(Math.random() * 1000);

		livePieModel.getData().put("Candidate 1", random1);
        livePieModel.getData().put("Candidate 2", random2);
        
        return livePieModel;
    }
    
    private void createCategoryModel() {
        categoryModel = new CartesianChartModel();

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

        categoryModel.addSeries(boys);
        categoryModel.addSeries(girls);
    }
    
    private void createOhlcModel(){
        ohlcModel = new OhlcChartModel();

        ohlcModel.add(new OhlcChartSeries(2007, 143.82, 144.56, 136.04, 136.97));
        ohlcModel.add(new OhlcChartSeries(2008, 138.7, 139.68, 135.18, 135.4));
        ohlcModel.add(new OhlcChartSeries(2009, 143.46, 144.66, 139.79, 140.02));
        ohlcModel.add(new OhlcChartSeries(2010, 140.67, 143.56, 132.88, 142.44));
        ohlcModel.add(new OhlcChartSeries(2011, 136.01, 139.5, 134.53, 139.48));
        ohlcModel.add(new OhlcChartSeries(2012, 124.76, 135.9, 124.55, 135.81));
        ohlcModel.add(new OhlcChartSeries(2012, 123.73, 129.31, 121.57, 122.5));
    }
    
    private void createOhlcModel2(){
        ohlcModel2 = new OhlcChartModel();
        for( int i=1 ; i < 41 ; i++)
            ohlcModel2.addRecord(new OhlcChartSeries(i, Math.random() * 80 + 80, Math.random() * 50 + 110, Math.random() * 20 + 80, Math.random() * 80 + 80));
    }
    
    private void createBubbleModel(){
        bubbleModel = new BubbleChartModel();
        
        bubbleModel.add(new BubbleChartSeries("Acura", 70, 183,55));
        bubbleModel.add(new BubbleChartSeries("Alfa Romeo", 45, 92, 36));
        bubbleModel.add(new BubbleChartSeries("AM General", 24, 104, 40));
        bubbleModel.add(new BubbleChartSeries("Bugatti", 50, 123, 60));
        bubbleModel.add(new BubbleChartSeries("BMW", 15, 89, 25));
        bubbleModel.add(new BubbleChartSeries("Audi", 40, 180, 80));
        bubbleModel.add(new BubbleChartSeries("Aston Martin", 70, 70, 48));
    }

    private void createLinearModel() {
        linearModel = new CartesianChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
        series2.setMarkerStyle("diamond");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        linearModel.addSeries(series1);
        linearModel.addSeries(series2);
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        
        pieModel.set("Brand 1", 540);
        pieModel.set("Brand 2", 325);
        pieModel.set("Brand 3", 702);
        pieModel.set("Brand 4", 421);
    }
    
    private void createDonutModel() {
        donutModel = new DonutChartModel();
        
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        circle1.put("Brand 1", 150);
        circle1.put("Brand 2", 400);
        circle1.put("Brand 3", 200);
        circle1.put("Brand 4", 10);
        donutModel.addCircle(circle1);
        
        Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
        circle2.put("Brand 1", 540);
        circle2.put("Brand 2", 125);
        circle2.put("Brand 3", 702);
        circle2.put("Brand 4", 421);
        donutModel.addCircle(circle2);
        
        Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
        circle3.put("Brand 1", 40);
        circle3.put("Brand 2", 325);
        circle3.put("Brand 3", 402);
        circle3.put("Brand 4", 421);
        donutModel.addCircle(circle3);
    }

    private void createLivePieModel() {
        livePieModel = new PieChartModel();

        livePieModel.set("Candidate 1", 540);
        livePieModel.set("Candidate 2", 325);
    }

    private void createFillToZero() {
        fillToZero = new CartesianChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set("4, -3, 3, 6, 2, -2", 0);

        fillToZero.addSeries(series1);
    }

    private void createMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>(){{
            add(20);
            add(50);
            add(120);
            add(220);
        }};
        
        meterGaugeModel = new MeterGaugeChartModel(140, intervals);
    }
}