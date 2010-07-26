package org.primefaces.examples.view;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ColorPickerConverter implements Converter {

	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		return submittedValue; //just return the rgb value as string
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (value == null)
			return null;
		else{
			return value.toString();
		}
	}
}
