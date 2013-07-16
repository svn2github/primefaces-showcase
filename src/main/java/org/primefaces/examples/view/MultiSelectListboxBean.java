package org.primefaces.examples.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

public class MultiSelectListboxBean {
    
    private List<SelectItem> categories;
    
    private String selection;

    @PostConstruct
    public void init() {
        categories = new ArrayList<SelectItem>();
        SelectItemGroup option1 = new SelectItemGroup("Option 1");
        SelectItemGroup option2 = new SelectItemGroup("Option 2");
        SelectItemGroup option3 = new SelectItemGroup("Option 3");
        SelectItemGroup option4 = new SelectItemGroup("Option 4");
        
        SelectItemGroup option11 = new SelectItemGroup("Option 1.1");
        SelectItemGroup option12 = new SelectItemGroup("Option 1.2");
        SelectItemGroup option13 = new SelectItemGroup("Option 1.3");
        
        SelectItemGroup option21 = new SelectItemGroup("Option 2.1");
        SelectItemGroup option22 = new SelectItemGroup("Option 2.2");
        
        SelectItem option31 = new SelectItem("Option 3.1", "Option 3.1");
        SelectItem option32 = new SelectItem("Option 3.2", "Option 3.2");
        SelectItem option33 = new SelectItem("Option 3.3", "Option 3.3");
        SelectItem option34 = new SelectItem("Option 3.4", "Option 3.4");
        
        SelectItem option41 = new SelectItem("Option 4.1", "Option 4.1");
        
        SelectItem option111 = new SelectItem("Option 1.1.1");
        SelectItem option112 = new SelectItem("Option 1.1.2");
        option11.setSelectItems(new SelectItem[]{option111, option112});
        
        SelectItem option121 = new SelectItem("Option 1.2.1", "Option 1.2.1");
        SelectItem option122 = new SelectItem("Option 1.2.2", "Option 1.2.2");
        SelectItem option123 = new SelectItem("Option 1.2.3", "Option 1.2.3");
        option12.setSelectItems(new SelectItem[]{option121, option122, option123});
        
        option1.setSelectItems(new SelectItem[]{option11, option12, option13});
        option2.setSelectItems(new SelectItem[]{option21, option22});
        option3.setSelectItems(new SelectItem[]{option31, option32, option33, option34});
        option4.setSelectItems(new SelectItem[]{option41});
        
        categories.add(option1);
        categories.add(option2);
        categories.add(option3);
        categories.add(option4);
    }
    
    public List<SelectItem> getCategories() {
        return categories;
    }    

    public String getSelection() {
        return selection;
    }
    public void setSelection(String selection) {
        this.selection = selection;
    }
}
