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
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

public class TwitterRSSService implements TwitterService {

    private static final Logger logger = Logger.getLogger(TwitterRSSService.class.getName());

    public List<String> getTweets(String username) {
        List<String> tweets = new ArrayList<String>();

        try {
            if (username != null && !username.equals("")) {
                TwitterAPI twitterAPI = new TwitterAPI();
                JSONArray feed = twitterAPI.getUserTimeLine(username, "298190640-9gLIJv0lUD8WasidrgeeyOxfwN6EpAahumkgggDI", "f6rOnnrHoSUEXlgUj7nOhanBZHgp8qhF0lAYbGFS7I");

                for (int x = 0; x < feed.length(); x++) {
                    tweets.add(((JSONObject) feed.get(x)).getString("text"));
                }
            }

        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        return tweets;
    }
}