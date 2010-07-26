/*
 * Copyright 2009 Prime Technology.
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
package org.primefaces.examples.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class TwitterRSSService implements TwitterService {

	private static final Logger logger = Logger.getLogger(TwitterRSSService.class.getName());
	
	public List<String> getTweets(String username) {
		List<String> tweets = new ArrayList<String>();
		
		try {
			URL feedSource = new URL("http://twitter.com/statuses/user_timeline.rss?screen_name=" + username);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));
			for(Object f : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) f;
				tweets.add(entry.getTitle().substring(username.length() + 2));
			}
		}catch(Exception e) {
			logger.severe(e.getMessage());
		}
		
		return tweets;
	}
}