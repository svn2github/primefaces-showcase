package org.primefaces.examples.view;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

public class CounterBean implements Serializable{

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increment() {
		count++;
	}
}
