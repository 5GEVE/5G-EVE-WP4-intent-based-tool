package com.wings.intentbased.Intent.services;

import blueprints.AGVBp;

public class AGVService {
	
	public void createBlueprint(String command) {
		
		AGVBp agv = new AGVBp();
		
		if(command.toLowerCase().contains(" Spain ") ||
		   command.toLowerCase().contains(" spanish ")) {
			agv.setCountry("Spain");
		}
		else if(command.toLowerCase().contains(" Greece ") ||
				command.toLowerCase().contains(" greek ")) {
			agv.setCountry("Greece");
		}
		else {
			
		}
		
	}
	
}
