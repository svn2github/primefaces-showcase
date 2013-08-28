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

import java.util.List;
import java.util.logging.Logger;
import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;
import twitter4j.auth.AccessToken;

public class TwitterAPIService implements TwitterService {

    private static final Logger logger = Logger.getLogger(TwitterAPIService.class.getName());
    private static final String twitter_consumer_key = "9oplpu80IwpZQWkF4FusrA";
    private static final String twitter_consumer_secret = "s0ldhYYtIugvm0eUajrupXZ9py1MmVysL1jAmtYHg";
    private static final String access_token = "298190640-9gLIJv0lUD8WasidrgeeyOxfwN6EpAahumkgggDI";
    private static final String access_token_secret = "f6rOnnrHoSUEXlgUj7nOhanBZHgp8qhF0lAYbGFS7I";
    private List<Status> asyncTweets;   

    public List<Status> getTweets(String username) {
        List<Status> tweets = null;

        try {
            if (username != null && !username.equals("")) {
                Twitter twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer(twitter_consumer_key, twitter_consumer_secret);
                twitter.setOAuthAccessToken(new AccessToken(access_token, access_token_secret));
                Paging p = new Paging(1, 200);
                tweets = twitter.getUserTimeline(username,p);                                
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        return tweets;
    }

    public List<Status> asyncSearch(String query) {

        TwitterListener listener = new TwitterAdapter() {
            @Override
            public void searched(QueryResult queryResult) {
                asyncTweets = queryResult.getTweets();                   
            }

            @Override
            public void onException(TwitterException e, TwitterMethod method) {
                logger.severe(e.getMessage());                       
            }
        };

        AsyncTwitter twitter = new AsyncTwitterFactory().getInstance();
        twitter.setOAuthConsumer(twitter_consumer_key, twitter_consumer_secret);
        twitter.setOAuthAccessToken(new AccessToken(access_token, access_token_secret));
        twitter.addListener(listener);
        Query q = new Query(query);
        twitter.search(q);        
        return asyncTweets;
    }
}
