package com.wings.intentbased.Intent.model;

public class Industry {
	
	//constructor
	public Industry() {
		
	}
	
	//a function that checks the industry 4.0 services given in the command
	public String getSector(String command) {
		
		String sector1 = null;
		command = " " + command + " ";
		
		if(command.toLowerCase().contains(" agvs ") || 
		   command.toLowerCase().contains(" autonomous vehicles "))
			sector1 = "agvs";
		
		if(sector1 == null)
			return "no sector"; //no service provided
		else
			return sector1; //returns service provided
	}
}
