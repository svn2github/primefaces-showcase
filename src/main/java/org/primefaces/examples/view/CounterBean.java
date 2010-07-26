package org.primefaces.examples.view;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

public class CounterBean implements Serializable{

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increment(ActionEvent actionEvent) {
		count++;
	}
}
