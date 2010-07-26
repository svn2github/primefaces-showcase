package org.primefaces.examples.service;

import java.net.URL;
import java.util.logging.Logger;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class YAHOOWeatherService implements WeatherService {

	private static final Logger logger = Logger.getLogger(YAHOOWeatherService.class.getName());
	
	
	public String getConditions(String city, String unit) {

		try {
			URL feedSource = new URL("http://weather.yahooapis.com/forecastrss?p=" + city + "&u=" + unit);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));
			String value = ((SyndEntry) feed.getEntries().get(0)).getDescription().getValue();
			
			return value.split("<a href")[0];		//Remove links
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		
		return "Unable to retrieve conditions";
	}

}
