package org.primefaces.examples.view;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.TreeNode;

import org.primefaces.examples.domain.Document;
import org.primefaces.model.CheckboxTreeNode;

public class TreeCheckboxBean implements Serializable {
	
	private static final Logger logger = Logger.getLogger(TreeCheckboxBean.class.getName());

	private TreeNode root;
    
    private TreeNode root2;
    
    private TreeNode[] selectedNodes;
	
	public TreeCheckboxBean() {
		root = new CheckboxTreeNode(new Document("Files", "-", "Folder"), null);
		
		TreeNode documents = new CheckboxTreeNode(new Document("Documents", "-", "Folder"), root);
		TreeNode pictures = new CheckboxTreeNode(new Document("Pictures", "-", "Folder"), root);
		TreeNode movies = new CheckboxTreeNode(new Document("Movies", "-", "Folder"), root);
		
		TreeNode work = new CheckboxTreeNode(new Document("Work", "-", "Folder"), documents);
		TreeNode primefaces = new CheckboxTreeNode(new Document("PrimeFaces", "-", "Folder"), documents);
		
		//Documents
		TreeNode expenses = new CheckboxTreeNode("document", new Document("Expenses.doc", "30 KB", "Word Document"), work);
		TreeNode resume = new CheckboxTreeNode("document", new Document("Resume.doc", "10 KB", "Word Document"), work);
		TreeNode refdoc = new CheckboxTreeNode("document", new Document("RefDoc.pages", "40 KB", "Pages Document"), primefaces);
		
		//Pictures
		TreeNode barca = new CheckboxTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
		TreeNode primelogo = new CheckboxTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image"), pictures);
		TreeNode optimus = new CheckboxTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image"), pictures);
		
		//Movies
		TreeNode pacino = new CheckboxTreeNode(new Document("Al Pacino", "-", "Folder"), movies);
		TreeNode deniro = new CheckboxTreeNode(new Document("Robert De Niro", "-", "Folder"), movies);
		
		TreeNode scarface = new CheckboxTreeNode("mp3", new Document("Scarface", "15 GB", "Movie File"), pacino);
		TreeNode carlitosWay = new CheckboxTreeNode("mp3", new Document("Carlitos' Way", "24 GB", "Movie File"), pacino);
		
		TreeNode goodfellas = new CheckboxTreeNode("mp3", new Document("Goodfellas", "23 GB", "Movie File"), deniro);
		TreeNode untouchables = new CheckboxTreeNode("mp3", new Document("Untouchables", "17 GB", "Movie File"), deniro);
        
        
        root2 = new CheckboxTreeNode("Root", null);
		TreeNode node0 = new CheckboxTreeNode("Node 0", root2);
		TreeNode node1 = new CheckboxTreeNode("Node 1", root2);
		TreeNode node2 = new CheckboxTreeNode("Node 2", root2);
		
		TreeNode node00 = new CheckboxTreeNode("Node 0.0", node0);
		TreeNode node01 = new CheckboxTreeNode("Node 0.1", node0);
		
		TreeNode node10 = new CheckboxTreeNode("Node 1.0", node1);
		TreeNode node11 = new CheckboxTreeNode("Node 1.1", node1);
		
		TreeNode node000 = new CheckboxTreeNode("Node 0.0.0", node00);
		TreeNode node001 = new CheckboxTreeNode("Node 0.0.1", node00);
		TreeNode node010 = new CheckboxTreeNode("Node 0.1.0", node01);
		
		TreeNode node100 = new CheckboxTreeNode("Node 1.0.0", node10);

	}
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
    
    public TreeNode getRoot2() {
		return root2;
	}

	public void setRoot2(TreeNode root2) {
		this.root2 = root2;
	}
    
    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
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
}