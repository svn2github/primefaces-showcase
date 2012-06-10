/*
 * Copyright 2009-2012 Prime Teknoloji.
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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

public class MindmapBean implements Serializable {
    
    public MindmapNode root;
    
    public MindmapBean() {
        root = new DefaultMindmapNode("google.com", "CC0000", false);
        
        MindmapNode ips = new DefaultMindmapNode("IPs", "3399FF", false);
        MindmapNode ns = new DefaultMindmapNode("NS(s)", "3399FF", false);
        MindmapNode malware = new DefaultMindmapNode("Malware", "3399FF", false);
        
        root.add(ips);
        root.add(ns);
        root.add(malware);
        
        //IPs
        ips.add(new DefaultMindmapNode("1.1.1.1", "00CC00", true));
        ips.add(new DefaultMindmapNode("1.1.1.2", "00CC00", true));
        
        //NSs
        ns.add(new DefaultMindmapNode("ns1.google.com", "00CC00", true));
        ns.add(new DefaultMindmapNode("ns2.google.com", "00CC00", true));
        ns.add(new DefaultMindmapNode("ns3.google.com", "00CC00", true));
        
        //Malware
        malware.add(new DefaultMindmapNode("someMalware", "00CC00", true));
    }

    public MindmapNode getRoot() {
        return root;
    }
    
    public void onNodeSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Node selected.", ((MindmapNode) event.getObject()).getData().toString());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
