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

import org.primefaces.examples.view.chat.JSONDecoder;
import org.primefaces.examples.view.chat.JSONEncoder;
import org.primefaces.examples.view.chat.Message;
import org.primefaces.examples.view.chat.Response;
import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PushEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PushEndpoint("/{room}/{user}")
public class ChatResource {

    private final Logger logger = LoggerFactory.getLogger(ChatResource.class);

    // The Path takes the form of /{room}/{user}
    public final static int USER_PATH_SEGMENT = 2;
    public final static int ROOM_PATH_SEGMENT = 1;

    @OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus) {
        logger.info("OnOpen {}", r);

        // Publish the message to all connected user.
        eventBus.publish(new Message().setUser(r.pathSegments(USER_PATH_SEGMENT)).setMessage("is entering room " + r.pathSegments(ROOM_PATH_SEGMENT)));
    }

    @OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus) {
        eventBus.publish(r.pathSegments(ROOM_PATH_SEGMENT) + "/*", String.format("%s: disconnected", r.pathSegments(USER_PATH_SEGMENT)));
    }

    @OnMessage(decoders = {JSONDecoder.class}, encoders = {JSONEncoder.class})
    public Response onMessage(Message message) {
        return new Response().message(message);
    }

}

