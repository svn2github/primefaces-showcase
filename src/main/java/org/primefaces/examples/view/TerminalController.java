package org.primefaces.examples.view;

import java.util.Date;

public class TerminalController {

	public String handleCommand(String command, String[] params) {
		if(command.equals("greet")) {
            if(params.length > 0)
                return "Hello " + params[0];
            else
                return "Hello Stranger";
        }
		else if(command.equals("date"))
			return new Date().toString();
		else
			return command + " not found";
	}
}
