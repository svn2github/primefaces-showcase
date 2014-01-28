/*
 * Copyright 2013 Jeanfrancois Arcand
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.primefaces.examples.view;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.push.Decoder;
import org.primefaces.push.Encoder;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PushEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PushEndpoint("/chat/{user}")
public class ChatResource {

    private final Logger logger = LoggerFactory.getLogger(ChatResource.class);

    @OnOpen
    public void onOpen(RemoteEndpoint r) {
        logger.info("OnOpen {}", r);
    }

    @OnClose
    public void onClose(RemoteEndpoint r) {
        logger.info("OnClose {}", r);
    }

    @OnMessage(decoders = {JSONDecoder.class}, encoders = {JSONEncoder.class})
    public Message onMessage(Message message) {
        logger.info("OnMessage {}", message);
        return message;
    }

    /**
     * Simple POJO
     */
    public final static class Message {
        public String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Message(String data) {
            this.data = data;
        }

        @Override
        public String toString(){
            return data;
        }
    }

    /**
     * A Simple {@link org.primefaces.push.Decoder} that decode a String into a {@link Message} object.
     */
    public final static class JSONDecoder implements Decoder<String, Message> {

        //@Override
        public Message decode(String s) {
            return new Message(s);
        }
    }

    /**
     * A Simple {@link org.primefaces.push.Encoder} that decode a {@link Message} into a simple JSON object.
     */
    public final static class JSONEncoder implements Encoder<Message, String> {

        //@Override
        public String encode(Message s) {
            return toJSON(s.getData());
        }

        private String toJSON(Object data) {
            try {
                StringBuilder jsonBuilder = new StringBuilder();
                jsonBuilder.append("{");

                if(isBean(data)) {
                    jsonBuilder.append("\"").append("data").append("\":").append(new JSONObject(data).toString());
                }
                else {
                    String json = new JSONObject().put("data", data).toString();

                    jsonBuilder.append(json.substring(1, json.length() - 1));
                }

                jsonBuilder.append("}");

                return jsonBuilder.toString();
            }

            catch(JSONException e) {
                System.out.println(e.getMessage());

                throw new RuntimeException(e);
            }

        }

        private boolean isBean(Object value) {
            if(value == null) {
                return false;
            }

            if(value instanceof Boolean || value instanceof String || value instanceof Number) {
                return false;
            }

            return true;
        }

    }

}
