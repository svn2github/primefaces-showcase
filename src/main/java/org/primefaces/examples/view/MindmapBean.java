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
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

public class MindmapBean implements Serializable {
    
    public MindmapNode root;
    
    public MindmapBean() {
        root = new DefaultMindmapNode("google.com", "FFCC00", false);
        
        MindmapNode ips = new DefaultMindmapNode("IPs", "6e9ebf", true);
        MindmapNode ns = new DefaultMindmapNode("NS(s)", "6e9ebf", true);
        MindmapNode malware = new DefaultMindmapNode("Malware", "6e9ebf", true);
        
        root.add(ips);
        root.add(ns);
        root.add(malware);
    }

    public MindmapNode getRoot() {
        return root;
    }
    
    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();
        
        //populate if not already loaded
        if(node.getChildren().isEmpty()) {
            Object data = node.getData();

            if(data.equals("NS(s)")) {
                for(int i = 0; i < 25; i++) {
                    node.add(new DefaultMindmapNode("ns" + i + ".google.com", "82c542"));
                }                
            }
            else if(data.equals("IPs")) {
                for(int i = 0; i < 18; i++) {
                    node.add(new DefaultMindmapNode("1.1.1."  + i, "fce24f"));
                } 

            }
            else if(data.equals("Malware")) {
                for(int i = 0; i < 18; i++) {
                    node.add(new DefaultMindmapNode("someMalware-"  + i, "3399ff", false));
                }
            }
        }
        
    }
}
