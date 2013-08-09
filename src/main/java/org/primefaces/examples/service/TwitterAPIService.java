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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterAPIService implements TwitterService {

    private static final Logger logger = Logger.getLogger(TwitterAPIService.class.getName());

    public List<String> getTweets(String username) {
        List<String> tweets = new ArrayList<String>();

        String twitter_consumer_key = "9oplpu80IwpZQWkF4FusrA";
        String twitter_consumer_secret = "s0ldhYYtIugvm0eUajrupXZ9py1MmVysL1jAmtYHg";        
        String access_token = "298190640-9gLIJv0lUD8WasidrgeeyOxfwN6EpAahumkgggDI";
        String access_token_secret = "f6rOnnrHoSUEXlgUj7nOhanBZHgp8qhF0lAYbGFS7I";

        try {
            if (username != null && !username.equals("")) {
                Twitter twitter = new TwitterFactory().getInstance();                
                twitter.setOAuthConsumer(twitter_consumer_key, twitter_consumer_secret);
                twitter.setOAuthAccessToken(new AccessToken(access_token, access_token_secret));
                List<Status> statuses = twitter.getUserTimeline(username);
                for (Status status : statuses) {
                    tweets.add(status.getText());
                }
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        return tweets;
    }
}
