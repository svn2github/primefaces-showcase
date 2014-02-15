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
package org.primefaces.examples.view.counter;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;

/**
 * A Simple {@link org.primefaces.push.Encoder} that decode a {@link org.primefaces.examples.view.chat.Message} into a simple JSON object.
 */
public final class StringToJSONEncoder implements Encoder<String, String> {

    //@Override
    public String encode(String s) {
        return toJSON(s);
    }

    private String toJSON(Object data) {
        try {
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");

            if (isBean(data)) {
                jsonBuilder.append("\"").append("data").append("\":").append(new JSONObject(data).toString());
            } else {
                String json = new JSONObject().put("data", data).toString();

                jsonBuilder.append(json.substring(1, json.length() - 1));
            }

            jsonBuilder.append("}");

            return jsonBuilder.toString();
        } catch (JSONException e) {
            System.out.println(e.getMessage());

            throw new RuntimeException(e);
        }

    }

    private boolean isBean(Object value) {
        if (value == null) {
            return false;
        }

        if (value instanceof Boolean || value instanceof String || value instanceof Number) {
            return false;
        }

        return true;
    }

}

