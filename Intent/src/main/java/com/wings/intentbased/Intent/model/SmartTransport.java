package com.wings.intentbased.Intent.model;

public class SmartTransport {
	
	//constructor
	public SmartTransport() {
		
	}
	
	//a function that checks the smart transport services given in the command
	public String getSector(String command) {
		
		String sector1 = null;
		String sector2 = null;
		command = " " + command + " ";
		
		if(command.toLowerCase().contains(" recognition service "))
			sector1 = "recognition service";
		
		if(command.toLowerCase().contains(" tracking service ")){
			if(sector1 == null)
				sector1 = "tracking service";
			else
				sector2 = "tracking service";
		}
		
		if(command.toLowerCase().contains(" urban mobility ")) {
			if(sector1 == null)
				sector1 = "urban mobility";
			else
				sector2 = "urban mobility";
		}
		
		if(sector1 == null)
			return "no sector"; //no service provided
		else if(sector2 == null)
			return sector1; //returns service provided
		else
			return "conflict"; //multiple services provided
	}
	
}
