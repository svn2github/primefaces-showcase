package org.primefaces.examples.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;

public class RatingController {

	private Double rating1;

	private Double rating2;
	
	private Double rating3 = Double.valueOf(3);
	
	public void handleRate(RateEvent rateEvent) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", null);
		Double rating = rateEvent.getRating();
		
		if(rating != null)
			message.setDetail("You rated:" + rateEvent.getRating());
		else
			message.setDetail("You cleared your rate");
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public Double getRating1() {
		return rating1;
	}

	public void setRating1(Double rating1) {
		this.rating1 = rating1;
	}

	public Double getRating2() {
		return rating2;
	}

	public void setRating2(Double rating2) {
		this.rating2 = rating2;
	}

	public Double getRating3() {
		return rating3;
	}

	public void setRating3(Double rating3) {
		this.rating3 = rating3;
	}
}
