/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.primefaces.examples.push.browser;

import java.util.Map;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/browser")
public class BrowserStatsResource {
 
    @OnMessage(encoders = {JSONEncoder.class})
    public Map onMessage(Map data) {
        return data;
    }
}
