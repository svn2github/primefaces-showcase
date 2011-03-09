/*
 * Copyright 2009 Prime Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.examples.domain;

import java.io.Serializable;

public class Birth implements Serializable {

	private int year;
	
	private int boys;
	
	private int girls;
	
	public Birth() {
		
	}
	
	public Birth(int year, int boys, int girls) {
		this.year = year;
		this.boys = boys;
		this.girls = girls;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getBoys() {
		return boys;
	}

	public void setBoys(int boys) {
		this.boys = boys;
	}

	public int getGirls() {
		return girls;
	}

	public void setGirls(int girls) {
		this.girls = girls;
	}
}
