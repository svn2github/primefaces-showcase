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
import com.lowagie.text.PageSize;
import java.util.Map;
import javax.faces.model.SelectItem;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
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

    private List<Car> carsLarge;
	
	private Date date = new Date();
	
	private Car selectedCar;

	private Car[] selectedCars;
	
	private Cell selectedCell;
	
	private Cell[] selectedCells;

	private LazyDataModel<Car> lazyModel;

    private List<ManufacturerSale> sales;

    private List<String> columns;

    private List<Car[]> dynamicCars;

    private String columnName;

    private SelectItem[] manufacturerOptions;

    private List<Car> droppedCars;

    private List<ColumnModel> simpleColumns;

    private boolean editMode;

	public TableBean() {
		cars = new ArrayList<Car>();
		carsSmall = new ArrayList<Car>();
        carsLarge = new ArrayList<Car>();
        droppedCars = new ArrayList<Car>();
		
		populateRandomCars(cars, 50);
		populateRandomCars(carsSmall, 9);
        populateRandomCars(carsLarge, 200);
        populateRandomSales();

        createDynamicColumns();
        populateDynamicCars();
        manufacturerOptions = createFilterOptions(manufacturers);
        	
		lazyModel = new LazyDataModel<Car>() {

			/**
			 * Dummy implementation of loading a certain segment of data.
			 * In a real application, this method should load data from a datasource
			 */
			@Override
			public List<Car> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String,String> filters) {
				logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});

                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned
				
				List<Car> lazyCars = new ArrayList<Car>();
				populateLazyRandomCars(lazyCars, pageSize);
				
				return lazyCars;
			}
		};

        /**
         * In a real application, this number should be resolved by a projection query
         */
        lazyModel.setRowCount(100000000);
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
	
	private void populateLazyRandomCars(List<Car> list, int size) {
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

    public List<Car> getCarsLarge() {
		return carsLarge;
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
        pdf.open();
        pdf.setPageSize(PageSize.A4);

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
        FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getModel());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getModel());

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

    public int getLastYearTotal() {
        int total = 0;

        for(ManufacturerSale sale : getSales()) {
            total += sale.getLastYearSale();
        }

        return total;
    }

    public int getThisYearTotal() {
        int total = 0;

        for(ManufacturerSale sale : getSales()) {
            total += sale.getThisYearSale();
        }

        return total;
    }

    private void populateDynamicCars() {
        dynamicCars = new ArrayList<Car[]>();

        for(int i=0; i < 9; i++) {
            Car[] cars = new Car[columns.size()];
            
            for(int j=0; j < columns.size(); j++) {
                cars[j] = new Car(getRandomModel(), getRandomYear(), columns.get(j), getRandomColor());
            }

            dynamicCars.add(cars);
        }
    }

    private void createDynamicColumns() {
        columns = new ArrayList<String>();
        for(int i=0; i < 3; i++) {
            columns.add(manufacturers[i]);
        }

        simpleColumns = new ArrayList<ColumnModel>();
        simpleColumns.add(new ColumnModel("Model", "model"));
        simpleColumns.add(new ColumnModel("Manufacturer", "manufacturer"));
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<ColumnModel> getSimpleColumns() {
        return simpleColumns;
    }

    public List<Car[]> getDynamicCars() {
        return dynamicCars;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void addColumn() {
        columns.add(columnName);
        columnName = null;

        populateDynamicCars();
    }

    public void removeColumn() {
        String columnToRemove = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("columnToRemove");

        columns.remove(columnToRemove);

        populateDynamicCars();
    }

    public String[] getManufacturers() {
        return manufacturers;
    }

    public String[] getColors() {
        return colors;
    }

    public List<String> getAvailableManufacturers() {
        List<String> availableManufacturers = new ArrayList<String>();

        for(String manufacturer : manufacturers) {
            if(!columns.contains(manufacturer))
                availableManufacturers.add(manufacturer);
        }

        return availableManufacturers;
    }

    private SelectItem[] createFilterOptions(String[] data)  {
        SelectItem[] options = new SelectItem[data.length + 1];

        options[0] = new SelectItem("", "Select");
        for(int i = 0; i < data.length; i++) {
            options[i + 1] = new SelectItem(data[i], data[i]);
        }

        return options;
    }

    public SelectItem[] getManufacturerOptions() {
        return manufacturerOptions;
    }

    public void onCarDrop(DragDropEvent ddEvent) {
        Car car = ((Car) ddEvent.getData());

        droppedCars.add(car);
        carsSmall.remove(car);
    }

    public List<Car> getDroppedCars() {
        return droppedCars;
    }
    
    static public class ColumnModel implements Serializable {

        private String header;
        private String property;

        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }
    }

    public void delete() {
        carsSmall.remove(selectedCar);
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public String navigate() {
        return "home";
    }
}