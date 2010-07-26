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
package org.primefaces.examples.touch;

import java.util.List;

import javax.faces.event.ActionEvent;

import org.primefaces.examples.service.TwitterRSSService;
import org.primefaces.examples.service.TwitterService;

public class TwitterController {

	private TwitterService twitterService = new TwitterRSSService();
	
	private String username;
	
	private List<String> tweets;
	
	public void loadTweets(ActionEvent actionEvent) {
		tweets = twitterService.getTweets(username);
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<String> getTweets() {
		return tweets;
	}
	public void setTweets(List<String> tweets) {
		this.tweets = tweets;
	}
}
