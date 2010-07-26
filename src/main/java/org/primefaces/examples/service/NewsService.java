package org.primefaces.examples.service;

import java.util.List;

import org.primefaces.examples.domain.NewsEntry;
import org.primefaces.examples.domain.NewsGroup;

public interface NewsService {

	public List<NewsGroup> fetchNews();
}
