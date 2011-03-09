package org.primefaces.examples.domain;

import java.io.Serializable;

public class Vote implements Serializable {

	private String candidate;
	
	private int count;
	
	public Vote() {
		//NoOp
	}

	public Vote(String candidate, int count) {
		this.candidate = candidate;
		this.count = count;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void add(int count) {
		this.count = this.count + count;
	}
}