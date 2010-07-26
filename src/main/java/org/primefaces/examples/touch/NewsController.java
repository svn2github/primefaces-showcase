package org.primefaces.examples.touch;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.examples.domain.NewsEntry;
import org.primefaces.examples.domain.NewsGroup;
import org.primefaces.examples.service.NewsService;
import org.primefaces.examples.service.YAHOONewsService;

public class NewsController {

	private List<NewsGroup> news = new ArrayList<NewsGroup>();
	
	private NewsService newsService = new YAHOONewsService();
	
	private List<NewsEntry> entries;
	
	private NewsEntry entry;

	public NewsController() {
		news = this.newsService.fetchNews();
	}

	public List<NewsGroup> getNews() {
		return news;
	}
	
	public List<NewsEntry> getEntries() {
		return entries;
	}
	
	public NewsEntry getEntry() {
		return entry;
	}

	public void click(ActionEvent actionEvent) {
		String selectedGroupName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newsgroup");
	
		for(NewsGroup group : news) {
			if(group.getTitle().equals(selectedGroupName))
				entries = group.getEntries();
		}
	}
	
	public void detail(ActionEvent actionEvent) {
		int selectedEntryIndex = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newsdetailIndex"));
		
		entry = entries.get(selectedEntryIndex);
	}
}
