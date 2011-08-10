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

        OhlcChartSeries ohlc1 = new OhlcChartSeries(2007, 143.82, 144.56, 136.04, 136.97);
        OhlcChartSeries ohlc2 = new OhlcChartSeries(2008, 138.7, 139.68, 135.18, 135.4);
        OhlcChartSeries ohlc3 = new OhlcChartSeries(2009, 143.46, 144.66, 139.79, 140.02);
        OhlcChartSeries ohlc4 = new OhlcChartSeries(2010, 140.67, 143.56, 132.88, 142.44);
        OhlcChartSeries ohlc5 = new OhlcChartSeries(2011, 136.01, 139.5, 134.53, 139.48);
        OhlcChartSeries ohlc6 = new OhlcChartSeries(2012, 124.76, 135.9, 124.55, 135.81);
        OhlcChartSeries ohlc7 = new OhlcChartSeries(2012, 123.73, 129.31, 121.57, 122.5);
        
        ohlcModel.addRecord(ohlc1);
        ohlcModel.addRecord(ohlc2);
        ohlcModel.addRecord(ohlc3);
        ohlcModel.addRecord(ohlc4);
        ohlcModel.addRecord(ohlc5);
        ohlcModel.addRecord(ohlc6);
        ohlcModel.addRecord(ohlc7);
    }
    
    private void createOhlcModel2(){
        ohlcModel2 = new OhlcChartModel();
        ohlcModel2.addRecord( new OhlcChartSeries(1, 138.7, 139.68, 135.18, 135.4));
        ohlcModel2.addRecord( new OhlcChartSeries(2, 140.67, 143.56, 132.88, 142.44));
        ohlcModel2.addRecord( new OhlcChartSeries(3, 140.67, 143.56, 132.88, 142.44));
        ohlcModel2.addRecord( new OhlcChartSeries(4, 136.01, 139.5, 134.53, 139.48));
        ohlcModel2.addRecord( new OhlcChartSeries(5, 143.82, 144.56, 136.04, 136.97));
        ohlcModel2.addRecord( new OhlcChartSeries(6, 136.47, 146.4, 136, 144.67));
        ohlcModel2.addRecord( new OhlcChartSeries(7, 124.76, 135.9, 124.55, 135.81));
        ohlcModel2.addRecord( new OhlcChartSeries(8, 123.73, 129.31, 121.57, 122.5));
        ohlcModel2.addRecord( new OhlcChartSeries(9, 127.37, 130.96, 119.38, 122.42));
        ohlcModel2.addRecord( new OhlcChartSeries(10, 128.24, 133.5, 126.26, 129.19));
        ohlcModel2.addRecord( new OhlcChartSeries(11, 122.9, 127.95, 122.66, 127.24));
        ohlcModel2.addRecord( new OhlcChartSeries(12, 121.73, 127.2, 118.6, 123.9));
        ohlcModel2.addRecord( new OhlcChartSeries(13, 120.01, 124.25, 115.76, 123.42));
        ohlcModel2.addRecord( new OhlcChartSeries(14, 114.94, 120, 113.28, 119.57));
        ohlcModel2.addRecord( new OhlcChartSeries(15, 104.51, 116.13, 102.61, 115.99));
        ohlcModel2.addRecord( new OhlcChartSeries(16, 102.71, 109.98, 101.75, 106.85));
        ohlcModel2.addRecord( new OhlcChartSeries(17, 96.53, 103.48, 94.18, 101.59));
        ohlcModel2.addRecord( new OhlcChartSeries(18, 84.18, 97.2, 82.57, 95.93));
        ohlcModel2.addRecord( new OhlcChartSeries(19, 88.12, 92.77, 82.33, 85.3));
        ohlcModel2.addRecord( new OhlcChartSeries(20, 91.65, 92.92, 86.51, 89.31));
        ohlcModel2.addRecord( new OhlcChartSeries(21, 96.87, 97.04, 89, 91.2));
        ohlcModel2.addRecord( new OhlcChartSeries(22, 100, 103, 95.77, 99.16));
        ohlcModel2.addRecord( new OhlcChartSeries(23, 89.1, 100, 88.9, 99.72));
        ohlcModel2.addRecord( new OhlcChartSeries(24, 88.86, 95, 88.3, 90.13));
        ohlcModel2.addRecord( new OhlcChartSeries(25, 81.93, 90, 78.2, 88.36));
        ohlcModel2.addRecord( new OhlcChartSeries(26, 90.46, 90.99, 80.05, 82.33));
        ohlcModel2.addRecord( new OhlcChartSeries(27, 93.17, 97.17, 90.04, 90.58));
        ohlcModel2.addRecord( new OhlcChartSeries(28, 86.52, 91.04, 84.72, 90.75));
        ohlcModel2.addRecord( new OhlcChartSeries(29, 90.02, 90.03, 84.55, 85.81));
        ohlcModel2.addRecord( new OhlcChartSeries(30, 95.99, 96.48, 88.02, 90));
        ohlcModel2.addRecord( new OhlcChartSeries(31, 97.28, 103.6, 92.53, 98.27));
        ohlcModel2.addRecord( new OhlcChartSeries(32, 91.3, 96.23, 86.5, 94));
        ohlcModel2.addRecord( new OhlcChartSeries(33, 85.21, 95.25, 84.84, 92.67));
        ohlcModel2.addRecord( new OhlcChartSeries(34, 88.48, 91.58, 79.14, 82.58));
        ohlcModel2.addRecord( new OhlcChartSeries(35, 100.17, 100.4, 86.02, 90.24));
        ohlcModel2.addRecord( new OhlcChartSeries(36, 105.93, 111.79, 95.72, 98.24));
        ohlcModel2.addRecord( new OhlcChartSeries(37, 95.07, 112.19, 91.86, 107.59));
        ohlcModel2.addRecord( new OhlcChartSeries(38, 99.78, 101.25, 90.11, 96.38));
        ohlcModel2.addRecord( new OhlcChartSeries(39, 104.55, 116.4, 85.89, 97.4));
    }
    
    private void createBubbleModel(){
        bubbleModel = new BubbleChartModel();
        
        BubbleChartSeries bubble1 = new BubbleChartSeries("Acura", 11, 123, 1236);
        BubbleChartSeries bubble2 = new BubbleChartSeries("Alfa Romeo", 45, 92, 1067);
        BubbleChartSeries bubble3 = new BubbleChartSeries("AM General", 24, 104, 1176);
        BubbleChartSeries bubble4 = new BubbleChartSeries("Bugatti", 50, 23, 610);
        BubbleChartSeries bubble5 = new BubbleChartSeries("BMW", 7, 89, 864);
        BubbleChartSeries bubble6 = new BubbleChartSeries("Audi", 18, 17, 539);
        BubbleChartSeries bubble7 = new BubbleChartSeries("Aston Martin", 2, 13, 1026);
        
        bubbleModel.addBubble(bubble1);
        bubbleModel.addBubble(bubble2);
        bubbleModel.addBubble(bubble3);
        bubbleModel.addBubble(bubble4);
        bubbleModel.addBubble(bubble5);
        bubbleModel.addBubble(bubble6);
        bubbleModel.addBubble(bubble7);
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
        
        Map<String, Number> circle = new LinkedHashMap<String, Number>();
        circle.put("Brand 1", 150);
        circle.put("Brand 2", 400);
        circle.put("Brand 3", 200);
        circle.put("Brand 4", 10);
        donutModel.addCircle(circle);
        
        circle = new LinkedHashMap<String, Number>();
        circle.put("Brand 1", 540);
        circle.put("Brand 2", 125);
        circle.put("Brand 3", 702);
        circle.put("Brand 4", 421);
        donutModel.addCircle(circle);
        
        circle = new LinkedHashMap<String, Number>();
        circle.put("Brand 1", 40);
        circle.put("Brand 2", 325);
        circle.put("Brand 3", 402);
        circle.put("Brand 4", 421);
        donutModel.addCircle(circle);
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
        
        meterGaugeModel = new MeterGaugeChartModel("km/h", 140, intervals);
    }
}