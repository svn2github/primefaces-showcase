/*
 * Copyright 2009-2012 Prime Teknoloji.
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
package org.primefaces.examples.push;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.BroadcasterLifeCyclePolicyListener;
import org.primefaces.push.PushRule;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.atmosphere.cpr.BroadcasterLifeCyclePolicy.EMPTY_DESTROY;

public class AllPathInfoRule implements PushRule {

    private final Logger logger = Logger.getLogger(AllPathInfoRule.class.getName());

    public boolean apply(AtmosphereResource resource) {
        String pathInfo = resource.getRequest().getPathInfo();
        if (pathInfo == null) {
            resource.setBroadcaster(BroadcasterFactory.getDefault().lookup("/*"));
            return true;
        }

        String[] decodedPath = pathInfo.split("/");
        AtomicBoolean isSet = new AtomicBoolean(false);
        for (String p : decodedPath) {
            final Broadcaster b = BroadcasterFactory.getDefault().lookup("/" + p, true);
            b.setBroadcasterLifeCyclePolicy(EMPTY_DESTROY);
            b.addBroadcasterLifeCyclePolicyListener(new BroadcasterLifeCyclePolicyListener() {

                public void onEmpty() {
                    logger.log(Level.FINEST, "onEmpty {}", b.getID());
                }

                public void onIdle() {
                    logger.log(Level.FINEST, "onIdle {}", b.getID());
                }

                public void onDestroy() {
                    logger.log(Level.FINEST, "onDestroy {}", b.getID());
                }
            });

            if (isSet.getAndSet(true)) {
                resource.setBroadcaster(b);
            }
        }
        return true;
    }
}
