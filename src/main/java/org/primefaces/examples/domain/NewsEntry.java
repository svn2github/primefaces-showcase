package org.primefaces.examples.domain;

import java.io.Serializable;

public class NewsEntry implements Serializable {
	
	private String title;
	
	private String content;
	
	private int index;

	public NewsEntry() {
		
	}
	
	public NewsEntry(int index, String title, String content) {
		this.index = index;
		this.content = content;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}