package org.primefaces.examples.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;

public class RatingController {

	private double rating1;

	private double rating2;

    private double rating3;
	
	private double rating4 = 3;
	
	public void handleRate(RateEvent rateEvent) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Double) rateEvent.getRating()).intValue());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public double getRating1() {
		return rating1;
	}

	public void setRating1(double rating1) {
		this.rating1 = rating1;
	}

	public double getRating2() {
		return rating2;
	}

	public void setRating2(double rating2) {
		this.rating2 = rating2;
	}

	public double getRating3() {
		return rating3;
	}

	public void setRating3(double rating3) {
		this.rating3 = rating3;
	}

    public double getRating4() {
        return rating4;
    }

    public void setRating4(double rating4) {
        this.rating4 = rating4;
    }
}
