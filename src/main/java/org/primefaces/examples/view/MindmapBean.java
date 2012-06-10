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
        root = new DefaultMindmapNode("google.com", null, "CC0000");
        
        MindmapNode ips = new DefaultMindmapNode("IPs", root, "3399FF");
        MindmapNode ns = new DefaultMindmapNode("NS(s)", root, "3399FF");
        MindmapNode malware = new DefaultMindmapNode("Malware", root, "3399FF");
        
        //IPs
        new DefaultMindmapNode("1.1.1.1", ips, "00CC00");
        new DefaultMindmapNode("1.1.1.2", ips, "00CC00");
        new DefaultMindmapNode("1.1.1.3", ips, "00CC00");
        
        //NSs
        new DefaultMindmapNode("ns1.google.com", ns, "00CC00");
        new DefaultMindmapNode("ns2.google.com", ns, "00CC00");
        
        //Malware
        new DefaultMindmapNode("someMalware", malware, "00CC00");
    }

    public MindmapNode getRoot() {
        return root;
    }
    
    public void onNodeSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Node selected.", ((MindmapNode) event.getObject()).getData().toString());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
