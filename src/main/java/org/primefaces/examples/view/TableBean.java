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

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.examples.domain.Car;
import org.primefaces.model.Cell;
import org.primefaces.model.LazyDataModel;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import org.primefaces.event.SelectEvent;
import org.primefaces.examples.domain.ManufacturerSale;

public class TableBean implements Serializable {
	
	private final static Logger logger = Logger.getLogger(TableBean.class.getName());
	
	private final static String[] colors;
	
	private final static String[] manufacturers;
	
	private String theme;

	static {
		colors = new String[10];
		colors[0] = "Black";
		colors[1] = "White";
		colors[2] = "Green";
		colors[3] = "Red";
		colors[4] = "Blue";
		colors[5] = "Orange";
		colors[6] = "Silver";
		colors[7] = "Yellow";
		colors[8] = "Brown";
		colors[9] = "Maroon";
		
		manufacturers = new String[10];
		manufacturers[0] = "Mercedes";
		manufacturers[1] = "BMW";
		manufacturers[2] = "Volvo";
		manufacturers[3] = "Audi";
		manufacturers[4] = "Renault";
		manufacturers[5] = "Opel";
		manufacturers[6] = "Volkswagen";
		manufacturers[7] = "Chrysler";
		manufacturers[8] = "Ferrari";
		manufacturers[9] = "Ford";
	}

	private List<Car> cars;
	
	private List<Car> carsSmall;
	
	private Date date = new Date();
	
	private Car selectedCar;

	private Car[] selectedCars;
	
	private Cell selectedCell;
	
	private Cell[] selectedCells;

	private LazyDataModel<Car> lazyModel;

    private List<ManufacturerSale> sales;

	public TableBean() {
		cars = new ArrayList<Car>();
		carsSmall = new ArrayList<Car>();
		
		populateRandomCars(cars, 50);
		populateRandomCars(carsSmall, 9);
        populateRandomSales();
		
		/**
		* Test with one hundred million records.
		* In a real application use an sql Count query to get the row count.	
		*/	
		lazyModel = new LazyDataModel<Car>(100000000) {

			/**
			 * Dummy implementation of loading a certain segment of data.
			 * In a real applicaiton, this method should access db and do a limit based query
			 */
			@Override
			public List<Car> fetchLazyData(int first, int pageSize) {
				logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});
				
				List<Car> lazyCars = new ArrayList<Car>();
				populateLazyRandomCars(lazyCars, pageSize, first);
				
				return lazyCars;
			}
		};
	}
	
	public LazyDataModel<Car> getLazyModel() {
		return lazyModel;
	}
	
	public Car[] getSelectedCars() {
		return selectedCars;
	}
	public void setSelectedCars(Car[] selectedCars) {
		this.selectedCars = selectedCars;
	}
	
	public Car getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	private void populateRandomCars(List<Car> list, int size) {
		for(int i = 0 ; i < size ; i++)
			list.add(new Car(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));
	}
	
	private void populateLazyRandomCars(List<Car> list, int size, int first) {
		for(int i = 0 ; i < size ; i++) {
			list.add(new Car(getRandomModel(), getRandomYear(), getRandomManufacturer(), getRandomColor()));
		}
	}

	public List<Car> getCars() {
		return cars;
	}
	
	public List<Car> getCarsSmall() {
		return carsSmall;
	}

	private int getRandomYear() {
		return (int) (Math.random() * 50 + 1960);
	}
	
	private String getRandomColor() {
		return colors[(int) (Math.random() * 10)];
	}
	
	private String getRandomManufacturer() {
		return manufacturers[(int) (Math.random() * 10)];
	}

    private int getRandomSale() {
		return (int) (Math.random() * 100000);
	}

    private int getRandomProfit() {
		return (int) (Math.random() * 100);
	}
	
	private String getRandomModel() {
		return UUID.randomUUID().toString().substring(0, 8);
	}
	
	public Cell getSelectedCell() {
		return selectedCell;
	}

	public void setSelectedCell(Cell selectedCell) {
		this.selectedCell = selectedCell;
	}

	public Cell[] getSelectedCells() {
		return selectedCells;
	}

	public void setSelectedCells(Cell[] selectedCells) {
		this.selectedCells = selectedCells;
	}
	
	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);
		
		HSSFCellStyle cellStyle = wb.createCellStyle();  
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
			HSSFCell cell = header.getCell(i);
			
			cell.setCellStyle(cellStyle);
		}
	}
	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String logo = servletContext.getRealPath("") + File.separator + "images" + File.separator + "prime_logo.png";
		
		pdf.add(Image.getInstance(logo));
	}
	
	public void displaySelectedCells(ActionEvent actionEvent) {
		System.out.println(selectedCell.getColumnId());
		Car car = (Car) selectedCell.getRowData();
		System.out.println(car.getModel());
	}
	
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public void save() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Changes Saved"));
	}

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", selectedCar.getModel());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String onRowSelectNavigate(SelectEvent event) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedCar", event.getObject());

        return "carDetail?faces-redirect=true";
    }

    public List<ManufacturerSale> getSales() {
        return sales;
    }

    private void populateRandomSales() {
        sales = new ArrayList<ManufacturerSale>();

        for(int i = 0; i < 10; i++) {
            sales.add(new ManufacturerSale(manufacturers[i], getRandomSale(), getRandomSale(), getRandomProfit(), getRandomProfit()));
        }
    }
}