package org.primefaces.examples.domain;

import java.io.Serializable;
import java.util.List;

public class NewsGroup implements Serializable {

	private String title;
	
	private List<NewsEntry> entries;
	
	public NewsGroup() {
		
	}

	public NewsGroup(String title, List<NewsEntry> entries) {
		this.title = title;
		this.entries = entries;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<NewsEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<NewsEntry> entries) {
		this.entries = entries;
	}
}
