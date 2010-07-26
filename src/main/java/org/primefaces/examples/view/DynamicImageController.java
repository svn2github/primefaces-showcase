package org.primefaces.examples.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class DynamicImageController {

	private StreamedContent image;
	
	private StreamedContent barcode;
	
	private StreamedContent chart;

	private static final Logger logger = Logger.getLogger(DynamicImageController.class.getName());

	public DynamicImageController() {
		//Database image simulation
		InputStream stream = this.getClass().getResourceAsStream("optimusprime.jpg");
		image = new DefaultStreamedContent(stream, "image/jpeg");
		
		//Chart
		try {
			JFreeChart jfreechart = ChartFactory.createPieChart("Turkish Cities", createDataset(), true, true, false);
			File chartFile = new File("dynamichart");
			ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
			chart = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	
		//Barcode
		try {
			File barcodeFile = new File("dynamicbarcode");
			BarcodeImageHandler.saveJPEG(BarcodeFactory.createCode128("PRIMEFACES"), barcodeFile);
			barcode = new DefaultStreamedContent(new FileInputStream(barcodeFile), "image/jpeg");
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
	}
	
	public StreamedContent getBarcode() {
		return barcode;
	}
	public void setBarcode(StreamedContent barcode) {
		this.barcode = barcode;
	}
	
	public StreamedContent getImage() {
		return image;
	}
	public void setImage(StreamedContent image) {
		this.image = image;
	}
	
	public StreamedContent getChart() {
		return chart;
	}
	public void setChart(StreamedContent chart) {
		this.chart = chart;
	}
	
	private PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Istanbul", new Double(45.0));
		dataset.setValue("Ankara", new Double(15.0));
		dataset.setValue("Izmir", new Double(25.2));
		dataset.setValue("Antalya", new Double(14.8));

		return dataset;
	}
}
