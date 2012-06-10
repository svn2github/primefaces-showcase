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
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

public class MindMapBean implements Serializable {
    
    public MindmapNode root;
    
    public MindMapBean() {
        root = new DefaultMindmapNode("Root", null, "FACC2E");
        
        MindmapNode node1 = new DefaultMindmapNode("Node1", root, "FACC2E");
        MindmapNode node2 = new DefaultMindmapNode("Node2", root, "FACC2E");
        MindmapNode node3 = new DefaultMindmapNode("Node3", root, "FACC2E");
        MindmapNode node4 = new DefaultMindmapNode("Node4", root, "0080FF");
        
        MindmapNode node11 = new DefaultMindmapNode("Node11", node1, "FACC2E");
        MindmapNode node12 = new DefaultMindmapNode("Node12", node1, "FACC2E");
        
        
    }

    public MindmapNode getRoot() {
        return root;
    }
}
