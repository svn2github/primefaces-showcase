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

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.examples.domain.Car;
import org.primefaces.model.LazyDataModel;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.util.*;
import javax.faces.component.UIComponent;
import javax.faces.model.SelectItem;
import org.primefaces.event.ColumnResizeEvent;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.examples.domain.ManufacturerSale;
import org.primefaces.examples.domain.Player;
import org.primefaces.examples.domain.Stats;

public class TableBean implements Serializable {
    
    private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("model", "manufacturer", "year", "color");
	
	private final static Logger logger = Logger.getLogger(TableBean.class.getName());
	
	private final static String[] colors;
	
	private final static String[] manufacturers;
	
	private String theme;
    
    private String columnTemplate = "model manufacturer year";

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

    private List<Car> filteredCars;
    
	private List<Car> cars;
	
	private List<Car> carsSmall;

    private List<Car> carsLarge;
	
	private Date date = new Date();
	
	private Car selectedCar;

	private Car[] selectedCars;

	private LazyDataModel<Car> lazyModel;

    private List<ManufacturerSale> sales;

    private String columnName;

    private SelectItem[] manufacturerOptions;

    private List<Car> droppedCars;

    private List<ColumnModel> columns = new ArrayList<ColumnModel>();;

    private boolean editMode;
    
    private List<Player> players;
    
    private CarDataModel smallCarsModel;
    
    private CarDataModel mediumCarsModel;

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
        
        manufacturerOptions = createFilterOptions(manufacturers);
        
        populatePlayers();
        
        smallCarsModel = new CarDataModel(carsSmall);
        mediumCarsModel = new CarDataModel(cars);
        	
		lazyModel = new LazyCarDataModel(cars);
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

    public List<Car> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(List<Car> filteredCars) {
        this.filteredCars = filteredCars;
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
    
    public int getRandomPrice() {
		return (int) (Math.random() * 100000);
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

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String[] getManufacturers() {
        return manufacturers;
    }

    public String[] getColors() {
        return colors;
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
    
    public void onEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getModel());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Cancelled", ((Car) event.getObject()).getModel());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onResize(ColumnResizeEvent event) {
        FacesMessage msg = new FacesMessage("Column " + event.getColumn().getClientId() + " resized", "W:" + event.getWidth() + ", H:" + event.getHeight());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void populatePlayers() {
        players = new ArrayList<Player>();
        
        Player messi = new Player("Messi", 10);
        messi.getStats().add(new Stats("2005-2006", 4, 2));
        messi.getStats().add(new Stats("2006-2007", 10, 7));
        messi.getStats().add(new Stats("2007-2008", 16, 10));
        messi.getStats().add(new Stats("2008-2009", 32, 15));
        messi.getStats().add(new Stats("2009-2010", 51, 22));
        messi.getStats().add(new Stats("2010-2011", 55, 30));
        players.add(messi);
        
        Player xavi = new Player("Xavi", 6);
        xavi.getStats().add(new Stats("2005-2006", 6, 15));
        xavi.getStats().add(new Stats("2006-2007", 10, 20));
        xavi.getStats().add(new Stats("2007-2008", 12, 22));
        xavi.getStats().add(new Stats("2008-2009", 9, 24));
        xavi.getStats().add(new Stats("2009-2010", 8, 21));
        xavi.getStats().add(new Stats("2010-2011", 10, 25));
        players.add(xavi);
        
        Player iniesta = new Player("Iniesta", 10);
        iniesta.getStats().add(new Stats("2005-2006", 4, 12));
        iniesta.getStats().add(new Stats("2006-2007", 7, 9));
        iniesta.getStats().add(new Stats("2007-2008", 10, 14));
        iniesta.getStats().add(new Stats("2008-2009", 15, 17));
        iniesta.getStats().add(new Stats("2009-2010", 14, 16));
        iniesta.getStats().add(new Stats("2010-2011", 17, 22));
        players.add(iniesta);
    }

    public List<Player> getPlayers() {
        return players;
    }
    
    public CarDataModel getMediumCarsModel() {
        return mediumCarsModel;
    }

    public CarDataModel getSmallCarsModel() {
        return smallCarsModel;
    }
    
    public void deleteCar() {
        carsSmall.remove(selectedCar);
    }

    public String getColumnTemplate() {
        return columnTemplate;
    }
    public void setColumnTemplate(String columnTemplate) {
        this.columnTemplate = columnTemplate;
    }
    
    public void updateColumns() {
        //reset table state
        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:cars");
        table.setValueExpression("sortBy", null);
        
        //update columns
        createDynamicColumns();
    }
    
    public void createDynamicColumns() {
        String[] columnKeys = columnTemplate.split(" ");
        columns.clear();      
        
        for(String columnKey : columnKeys) {
            String key = columnKey.trim();
            
            if(VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
            }
        }
    }
    
    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                                            "Row State " + event.getVisibility(),
                                            "Model:" + ((Car) event.getData()).getModel());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}