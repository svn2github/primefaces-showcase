package org.primefaces.examples.touch;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.examples.domain.NewsEntry;
import org.primefaces.examples.domain.NewsGroup;
import org.primefaces.examples.service.NewsService;
import org.primefaces.examples.service.YAHOONewsService;

public class NewsController {

	private List<NewsGroup> groups = new ArrayList<NewsGroup>();
	
	private NewsService newsService = new YAHOONewsService();
	
	private NewsEntry selectedEntry;

    private NewsGroup selectedGroup;

	public NewsController() {
		groups = this.newsService.fetchNews();
	}

	public List<NewsGroup> getGroups() {
		return groups;
	}

    public NewsEntry getSelectedEntry() {
        return selectedEntry;
    }
    public void setSelectedEntry(NewsEntry selectedEntry) {
        this.selectedEntry = selectedEntry;
    }


    public NewsGroup getSelectedGroup() {
        return selectedGroup;
    }
    public void setSelectedGroup(NewsGroup selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
}
