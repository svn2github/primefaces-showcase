/*
 * Copyright 2009-2011 Prime Technology.
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.primefaces.model.timeline.DefaultTimeLine;
import org.primefaces.model.timeline.DefaultTimelineEvent;
import org.primefaces.model.timeline.Timeline;

public class TimelineBean implements Serializable {
 
    private List<Timeline> model;
    
    public TimelineBean() {
        model = new ArrayList<Timeline>();
        Calendar calendar = Calendar.getInstance();
        
        DefaultTimeLine primefaces = new DefaultTimeLine("primefaces", "PrimeFaces History");
        calendar.set(2008, 9, 29);
        
        primefaces.setFocusDate(calendar.getTime());
        primefaces.setInitialZoom(37);
        
        calendar.set(2008, 9, 29);
        primefaces.addEvent(new DefaultTimelineEvent("Project start", "PrimeFaces begins", calendar.getTime(), null, "/images/timeline/blue.png"));
        
        calendar.set(2009, 1, 26);
        primefaces.addEvent(new DefaultTimelineEvent("First Release", "PrimeFaces 0.8.0 Released", calendar.getTime(), null, "/images/timeline/orange.png"));
        
        calendar.set(2010, 1, 14);
        primefaces.addEvent(new DefaultTimelineEvent("1.0/2.0 Milestone", "PrimeFaces 1.0/2.0 Released", calendar.getTime(), null, "/images/timeline/green.png"));
        
        calendar.set(2011, 1, 15);
        primefaces.addEvent(new DefaultTimelineEvent("2.2.1 Milestone", "PrimeFaces 2.2.1 Released", calendar.getTime(), null, "/images/timeline/black.png"));
        
        model.add(primefaces);
    }

    public List<Timeline> getModel() {
        return model;
    }
}