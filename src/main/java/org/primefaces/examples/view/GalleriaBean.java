/*
 * Copyright 2010 Prime Technology.
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

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

public class GalleriaBean {

    private List<String> images;

    private String effect = "fade";

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        
        for(int i=1;i<=12;i++) {
            images.add("galleria" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
