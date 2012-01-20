package org.primefaces.examples.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ProgressBean implements Serializable {

	private static final long serialVersionUID = -5000470162366926811L;
	
	private Integer progress;

	public Integer getProgress() {
		if(progress == null) {
			progress = 0;
        }
		else {
			progress = progress + (int)(Math.random() * 35);
			
			if(progress > 100)
				progress = 100;
		}
		
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	
	public void onComplete() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Progress Completed", "Progress Completed"));
	}
    
    public void cancel() {
        progress = null;
    }
}