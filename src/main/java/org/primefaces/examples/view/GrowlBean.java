package org.primefaces.examples.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@ManagedBean
@RequestScoped
public class GrowlBean {

    private final static String CHANNEL = "/notify";
    
	private String text;
    
    private String summary;
    
    private String detail;
        
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
    
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

	public void save(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, new FacesMessage("Successful", "Hello " + text));
		context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
	}
    
    public void send() {
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(summary, detail));
    }
}
