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
package org.primefaces.examples.view.chat;

public class Response {

    private Message incomingMessage;
    private String extraMessage = "";

    public Response(){}

    public Response message(Message incomingMessage) {
        this.incomingMessage = incomingMessage;
        return this;
    }

    public Response extraMessage(String message) {
        this.extraMessage = extraMessage;
        return this;
    }


    public String getMessage() {
        return extraMessage;
    }

    public Message getIncomingMessage() {
        return incomingMessage;
    }

    public String toString() {
        return incomingMessage.toString() + " " + extraMessage;
    }
}
