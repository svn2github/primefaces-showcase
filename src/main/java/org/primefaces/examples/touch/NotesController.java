package org.primefaces.examples.touch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.examples.domain.Note;

public class NotesController implements Serializable {
	
	private Note note = new Note();

	private List<Note> notes = new ArrayList<Note>();

	public List<Note> getNotes() {
		return notes;
	}
	
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	
	public void addNote(ActionEvent actionEvent) {
		note.setId(String.valueOf(notes.size()));
		notes.add(note);
		
		note = new Note();
	}
	
	public void removeNote(ActionEvent actionEvent) {
		String noteId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("noteId");
		
		for(Note n : notes) {
			if(n.getId().equals(noteId)) {
				notes.remove(n);

				break;
			}
		}
	}
}