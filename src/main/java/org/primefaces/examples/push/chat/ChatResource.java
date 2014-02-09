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
package org.primefaces.examples.push.chat;

import org.atmosphere.config.service.Singleton;
import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PushEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PushEndpoint("/chat/{room}")
@Singleton
public class ChatResource {

    private final Logger logger = LoggerFactory.getLogger(ChatResource.class);

    @OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus) {
        logger.info("OnOpen {}", r);
        eventBus.encodeToJsonAndFire(String.format("%s connected", r.path()));
    }

    @OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus) {
        logger.info("OnClose {}", r);
        eventBus.encodeToJsonAndFire(String.format("%s disconnected", r.path()));
    }

    @OnMessage(decoders = {JSONDecoder.class}, encoders = {JSONEncoder.class})
    public Response onMessage(RemoteEndpoint r, Message message) {
        logger.info("OnMessage {}", message);
        return new Response().message(message)
                .message(String.format("Message will be delivered using %s from %s", r.transport().name(), r.address()));
    }

}

