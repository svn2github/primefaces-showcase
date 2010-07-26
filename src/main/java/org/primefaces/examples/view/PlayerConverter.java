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

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.examples.domain.Player;

public class PlayerConverter implements Converter {

	private static List<Player> playerDB;
	
	static {
		playerDB = new ArrayList<Player>();
		
		playerDB.add(new Player("Messi", 10));
		playerDB.add(new Player("Ibrahimovic", 9));
		playerDB.add(new Player("Henry", 14));
		playerDB.add(new Player("Iniesta", 8));
		playerDB.add(new Player("Xavi", 6));
		playerDB.add(new Player("Puyol", 5));
	}
	
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		if(submittedValue.trim().equals(""))
			return null;
		else {
			int number = Integer.parseInt(submittedValue);
			
			for(Player p : playerDB) {
				if(p.getNumber() == number)
					return p;
			}
		}
		
		return null;
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if(value == null)
			return null;
		else
			return String.valueOf(((Player) value).getNumber());
	}
}