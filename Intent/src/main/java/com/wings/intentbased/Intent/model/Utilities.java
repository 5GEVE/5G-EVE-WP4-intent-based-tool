package com.wings.intentbased.Intent.model;

public class Utilities {
	
	//constructor
	public Utilities() {
		
	}
	
	//a function that checks the utilities services given in the command
	public String getSector(String command) {
		
		String sector1 = null;
		command = " " + command + " ";
		
		if(command.toLowerCase().contains(" energy management "))
			sector1 = "smart energy";
		
		if(command.toLowerCase().contains(" critical utilities ")) {
			if(sector1 == null)
				sector1 = "smart energy";
		}
		
		if(command.toLowerCase().contains(" smart energy "))
			sector1 = "smart energy";
		
		if(sector1 == null)
			return "no sector"; //no service provided
		else
			return sector1; //returns service provided
	}
}
