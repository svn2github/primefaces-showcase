package org.primefaces.examples.view;

import java.io.Serializable;
import java.util.logging.Logger;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import org.primefaces.examples.domain.Document;

public class DocumentsController implements Serializable {
	
	private static final Logger logger = Logger.getLogger(DocumentsController.class.getName());

	private TreeNode root;

	private Document selectedDocument;
	
	public DocumentsController() {
		root = new DefaultTreeNode("root", null);
		
		TreeNode documents = new DefaultTreeNode(new Document("Documents", "-", "Folder"), root);
		TreeNode pictures = new DefaultTreeNode(new Document("Pictures", "-", "Folder"), root);
		TreeNode music = new DefaultTreeNode(new Document("Music", "-", "Folder"), root);
		
		TreeNode work = new DefaultTreeNode(new Document("Work", "-", "Folder"), documents);
		TreeNode primefaces = new DefaultTreeNode(new Document("PrimeFaces", "-", "Folder"), documents);
		
		//Documents
		TreeNode expenses = new DefaultTreeNode("document", new Document("Expenses.doc", "30 KB", "Word Document"), work);
		TreeNode resume = new DefaultTreeNode("document", new Document("Resume.doc", "10 KB", "Word Document"), work);
		TreeNode refdoc = new DefaultTreeNode("document", new Document("RefDoc.pages", "40 KB", "Pages Document"), primefaces);
		
		//Pictures
		TreeNode barca = new DefaultTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
		TreeNode primelogo = new DefaultTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image"), pictures);
		TreeNode optimus = new DefaultTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image"), pictures);
		
		//Music
		TreeNode turkish = new DefaultTreeNode(new Document("Turkish", "-", "Folder"), music);
		
		TreeNode cemKaraca = new DefaultTreeNode(new Document("Cem Karaca", "-", "Folder"), turkish);
		TreeNode erkinKoray = new DefaultTreeNode(new Document("Erkin Koray", "-", "Folder"), turkish);
		TreeNode mogollar = new DefaultTreeNode(new Document("Mogollar", "-", "Folder"), turkish);
		
		TreeNode nemalacak = new DefaultTreeNode("mp3", new Document("Nem Alacak Felek Benim", "1500 KB", "Audio File"), cemKaraca);
		TreeNode resimdeki = new DefaultTreeNode("mp3", new Document("Resimdeki Gozyaslari", "2400 KB", "Audio File"), cemKaraca);
		
		TreeNode copculer = new DefaultTreeNode("mp3", new Document("Copculer", "2351 KB", "Audio File"), erkinKoray);
		TreeNode oylebirgecer = new DefaultTreeNode("mp3", new Document("Oyle bir Gecer", "1794 KB", "Audio File"), erkinKoray);
		
		TreeNode toprakana = new DefaultTreeNode("mp3", new Document("Toprak Ana", "1536 KB", "Audio File"), mogollar);
		TreeNode bisiyapmali = new DefaultTreeNode("mp3", new Document("Bisi Yapmali", "2730 KB", "Audio File"), mogollar);
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public Document getSelectedDocument() {
		return selectedDocument;
	}

	public void setSelectedDocument(Document selectedDocument) {
		this.selectedDocument = selectedDocument;
	}
}