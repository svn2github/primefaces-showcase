/*
 * Copyright 2009-2011 Prime Technology.
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
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.primefaces.examples.domain.Theme;

public class ThemeSwitcherBean {
        
    private Map<String, String> themes;
    
    private List<Theme> advancedThemes;
    
    private String theme;
    
    private GuestPreferences gp;

    public void setGp(GuestPreferences gp) {
        this.gp = gp;
    }
    
    public Map<String, String> getThemes() {
        return themes;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @PostConstruct
    public void init() {
        theme = gp.getTheme();
        
        advancedThemes = new ArrayList<Theme>();
        advancedThemes.add(new Theme("afterdark", "afterdark.png"));
        advancedThemes.add(new Theme("afternoon", "afternoon.png"));
        advancedThemes.add(new Theme("afterwork", "afterwork.png"));
        advancedThemes.add(new Theme("aristo", "aristo.png"));
        advancedThemes.add(new Theme("black-tie", "black-tie.png"));
        advancedThemes.add(new Theme("blitzer", "blitzer.png"));
        advancedThemes.add(new Theme("bluesky", "bluesky.png"));
        advancedThemes.add(new Theme("bootstrap", "bootstrap.png"));
        advancedThemes.add(new Theme("casablanca", "casablanca.png"));
        advancedThemes.add(new Theme("cruze", "cruze.png"));
        advancedThemes.add(new Theme("cupertino", "cupertino.png"));
        advancedThemes.add(new Theme("dark-hive", "dark-hive.png"));
        advancedThemes.add(new Theme("dot-luv", "dot-luv.png"));
        advancedThemes.add(new Theme("eggplant", "eggplant.png"));
        advancedThemes.add(new Theme("excite-bike", "excite-bike.png"));
        advancedThemes.add(new Theme("flick", "flick.png"));
        advancedThemes.add(new Theme("glass-x", "glass-x.png"));
        advancedThemes.add(new Theme("home", "home.png"));
        advancedThemes.add(new Theme("hot-sneaks", "hot-sneaks.png"));
        advancedThemes.add(new Theme("humanity", "humanity.png"));
        advancedThemes.add(new Theme("le-frog", "le-frog.png"));
        advancedThemes.add(new Theme("midnight", "midnight.png"));
        advancedThemes.add(new Theme("mint-choc", "mint-choc.png"));
        advancedThemes.add(new Theme("overcast", "overcast.png"));
        advancedThemes.add(new Theme("pepper-grinder", "pepper-grinder.png"));
        advancedThemes.add(new Theme("redmond", "redmond.png"));
        advancedThemes.add(new Theme("rocket", "rocket.png"));
        advancedThemes.add(new Theme("sam", "sam.png"));
        advancedThemes.add(new Theme("smoothness", "smoothness.png"));
        advancedThemes.add(new Theme("south-street", "south-street.png"));
        advancedThemes.add(new Theme("start", "start.png"));
        advancedThemes.add(new Theme("sunny", "sunny.png"));
        advancedThemes.add(new Theme("swanky-purse", "swanky-purse.png"));
        advancedThemes.add(new Theme("trontastic", "trontastic.png"));
        advancedThemes.add(new Theme("ui-darkness", "ui-darkness.png"));
        advancedThemes.add(new Theme("ui-lightness", "ui-lightness.png"));
        advancedThemes.add(new Theme("vader", "vader.png"));
        
        themes = new TreeMap<String, String>();
        themes.put("Afterdark", "afterdark");
        themes.put("Afternoon", "afternoon");
        themes.put("Afterwork", "afterwork");
        themes.put("Aristo", "aristo");
        themes.put("Black-Tie", "black-tie");
        themes.put("Blitzer", "blitzer");
        themes.put("Bluesky", "bluesky");
        themes.put("Bootstrap", "bootstrap");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put("Cruze", "cruze");
        themes.put("Dark-Hive", "dark-hive");
        themes.put("Dot-Luv", "dot-luv");
        themes.put("Eggplant", "eggplant");
        themes.put("Excite-Bike", "excite-bike");
        themes.put("Flick", "flick");
        themes.put("Glass-X", "glass-x");
        themes.put("Home", "home");
        themes.put("Hot-Sneaks", "hot-sneaks");
        themes.put("Humanity", "humanity");
        themes.put("Le-Frog", "le-frog");
        themes.put("Midnight", "midnight");
        themes.put("Mint-Choc", "mint-choc");
        themes.put("Overcast", "overcast");
        themes.put("Pepper-Grinder", "pepper-grinder");
        themes.put("Redmond", "redmond");
        themes.put("Rocket", "rocket");
        themes.put("Sam", "sam");
        themes.put("Smoothness", "smoothness");
        themes.put("South-Street", "south-street");
        themes.put("Start", "start");
        themes.put("Sunny", "sunny");
        themes.put("Swanky-Purse", "swanky-purse");
        themes.put("Trontastic", "trontastic");
        themes.put("UI-Darkness", "ui-darkness");
        themes.put("UI-Lightness", "ui-lightness");
        themes.put("Vader", "vader");
    }
    
    public void saveTheme() {
        gp.setTheme(theme);
    }

    public List<Theme> getAdvancedThemes() {
        return advancedThemes;
    }
}
