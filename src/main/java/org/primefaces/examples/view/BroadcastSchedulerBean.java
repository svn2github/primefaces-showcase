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

import java.util.concurrent.TimeUnit;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

public class BroadcastSchedulerBean {
        
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void start() {
        if(!active) {
            PushContext context = PushContextFactory.getDefault().getPushContext();
            context.schedule("/schedule", "Ping", 30, TimeUnit.SECONDS);
            
            active = true;
        } 
    }
}
