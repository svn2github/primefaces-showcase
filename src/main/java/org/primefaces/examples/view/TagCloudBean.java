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
package org.primefaces.examples.view;

import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

public class TagCloudBean {

    private TagCloudModel model;
    
    public TagCloudBean() {
        model = new DefaultTagCloudModel();
        model.addTag(new DefaultTagCloudItem("Transformers", "#", 1));
        model.addTag(new DefaultTagCloudItem("RIA", "#", 3));
        model.addTag(new DefaultTagCloudItem("AJAX", "#", 2));
        model.addTag(new DefaultTagCloudItem("jQuery", "#", 5));
        model.addTag(new DefaultTagCloudItem("NextGen", "#", 4));
        model.addTag(new DefaultTagCloudItem("JSF 2.0", "#", 2));
        model.addTag(new DefaultTagCloudItem("FCB", "#", 5));
        model.addTag(new DefaultTagCloudItem("Mobile", "#", 3));
        model.addTag(new DefaultTagCloudItem("Themes", "#", 4));
        model.addTag(new DefaultTagCloudItem("Rocks", "#", 1));
    }

    public TagCloudModel getModel() {
        return model;
    }
}
