package com.wings.intentbased.Intent.model;

public class SmartTourism {
	
	//constructor
	public SmartTourism() {
		
	}

	//a function that checks the smart tourism services given in the command
	public String getSector(String command) {
		
		String sector1 = null;
		String sector2 = null;
		command = " " + command + " ";
		
		if(command.toLowerCase().contains(" ar interaction "))
			sector1 = "ar interaction";
		
		if(command.toLowerCase().contains(" business augmented ") ||
		   command.toLowerCase().contains(" augmented fair ") ||
		   command.toLowerCase().contains(" augmented booth ") ||
		   command.toLowerCase().contains(" business booth ")) {
			if(sector1 == null)
				sector1 = "business booth";
			else
				sector2 = "business booth";
		}
		
		if(sector1 == null)
			return "no sector"; //no service provided
		else if(sector2 == null) 
			return sector1; //returns service provided
		else
			return "conflict"; //multiple services provided
	}
}
