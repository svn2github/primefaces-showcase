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

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;

import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class TreeBean implements Serializable {
	
	private TreeNode root;
    
    private TreeNode root2;
	
	private TreeNode selectedNode;
    
    private TreeNode selectedNode2;

	private TreeNode[] selectedNodes;
    
    private TreeNode[] selectedNodes2;

	public TreeBean() {
		root = new DefaultTreeNode("Root", null);
		TreeNode node0 = new DefaultTreeNode("Node 0", root);
		TreeNode node1 = new DefaultTreeNode("Node 1", root);
		TreeNode node2 = new DefaultTreeNode("Node 2", root);
		
		TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
		TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
		
		TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
		TreeNode node11 = new DefaultTreeNode("Node 1.1", node1);
		
		TreeNode node000 = new DefaultTreeNode("Node 0.0.0", node00);
		TreeNode node001 = new DefaultTreeNode("Node 0.0.1", node00);
		TreeNode node010 = new DefaultTreeNode("Node 0.1.0", node01);
		
		TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);
        
        root2 = new DefaultTreeNode("Root2", null);
		TreeNode item0 = new DefaultTreeNode("Item 0", root2);
		TreeNode item1 = new DefaultTreeNode("Item 1", root2);
        TreeNode item2 = new DefaultTreeNode("Item 2", root2);
		
		TreeNode item00 = new DefaultTreeNode("Item 0.0", item0);
	}

	public TreeNode getRoot() {
		return root;
	}

    public TreeNode getRoot2() {
        return root2;
    }
	
	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

    public TreeNode[] getSelectedNodes2() {
        return selectedNodes2;
    }

    public void setSelectedNodes2(TreeNode[] selectedNodes2) {
        this.selectedNodes2 = selectedNodes2;
    }

	public TreeNode getSelectedNode() {
		return selectedNode;
	}
	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

    public TreeNode getSelectedNode2() {
        return selectedNode2;
    }
    public void setSelectedNode2(TreeNode selectedNode2) {
        this.selectedNode2 = selectedNode2;
    }

    public void onNodeExpand(NodeExpandEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onNodeCollapse(NodeCollapseEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onNodeSelect(NodeSelectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

    public void onNodeUnselect(NodeUnselectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

    public void onDragDrop(TreeDragDropEvent event) {
        TreeNode dragNode = event.getDragNode();
        TreeNode dropNode = event.getDropNode();
        int dropIndex = event.getDropIndex();
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dragged " + dragNode.getData(), "Dropped on " + dropNode.getData() + " at " + dropIndex);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void displaySelectedSingle() {
        if(selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());

            FacesContext.getCurrentInstance().addMessage(null, message);
        }
	}
    
    public void displaySelectedMultiple() {
        if(selectedNodes != null && selectedNodes.length > 0) {
            StringBuilder builder = new StringBuilder();

            for(TreeNode node : selectedNodes) {
                builder.append(node.getData().toString());
                builder.append("<br />");
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, message);
        }
	}
    
    public void deleteNode() { 
        selectedNode.getParent().getChildren().remove(selectedNode);            
        selectedNode = null;  
    }  
}