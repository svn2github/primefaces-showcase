package org.primefaces.examples.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

public class FileUploadController {
	
	private Logger logger = Logger.getLogger(FileUploadController.class.getName());

	public void handleFileUpload(FileUploadEvent event) {
		logger.log(Level.INFO, "Uploaded: {0}", event.getFile().getFileName());

		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}