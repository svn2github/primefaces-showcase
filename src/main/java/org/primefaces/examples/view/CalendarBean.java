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
package org.primefaces.examples.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.DateSelectEvent;

public class CalendarBean {

	private Date date1;
	
	private Date date2;
	
	private Date date3;

    private Date date4;

    private Date date5;

    private Date date6;

	private Date[] dates;

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

    public Date getDate4() {
		return date4;
	}

	public void setDate4(Date date4) {
		this.date4 = date4;
	}

    public Date getDate5() {
        return date5;
    }

    public void setDate5(Date date5) {
        this.date5 = date5;
    }

    public Date getDate6() {
        return date6;
    }

    public void setDate6(Date date6) {
        this.date6 = date6;
    }
    
	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}
	
	public List<Date> getSelectedDates() {
		if(dates != null)
			return Arrays.asList(dates);
		else
			return new ArrayList<Date>();
	}
	
	public void handleDateSelect(DateSelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getDate())));
	}
	
	public TimeZone getTimeZone() {
		return TimeZone.getDefault();
	}
}
