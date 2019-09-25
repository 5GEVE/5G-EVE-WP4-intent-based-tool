package com.wings.intentbased.Intent.model;

public class MediaEntertainment {
	
	//constructor
	public MediaEntertainment() {
		
	}
	
	//a function that checks the media and entertainment services given in the command
	public String getSector(String command) {
		
		String sector1 = null;
		String sector2 = null;
		command = " " + command + " ";
		
		if(command.toLowerCase().contains(" ultra high fidelity ") ||
		   command.toLowerCase().contains(" ultra-high fidelity ") ||
		   command.toLowerCase().contains(" ultra high-fidelity ") ||
		   command.toLowerCase().contains(" uhf "))
			sector1 = "ultra high fidelity";
		
		if(command.toLowerCase().contains(" live event ") ||
		   command.toLowerCase().contains(" on-site ") ||
		   command.toLowerCase().contains(" on site ")) {
			if(sector1 == null)
				sector1 = "live event";
			else
				sector2 = "live event";
		}
		
		if(command.toLowerCase().contains(" immersive and integrated ") ||
		   command.toLowerCase().contains(" immersive ") ||
		   command.toLowerCase().contains(" integrated ")) {
			if(sector1 == null)
				sector1 = "immersive and integrated";
			else
				sector2 = "immersive and integrated";
		}
		
		if(command.toLowerCase().contains(" virtual visit ") ||
		   command.toLowerCase().contains(" virtual 360 ") ||
		   command.toLowerCase().contains(" video 360 ")) {
			if(sector1 == null)
				sector1 = "virtual 360";
			else
				sector2 = "virtual 360";
		}
		
		if(sector1 == null)
			return "no sector"; //no service provided
		else if(sector2 == null)
			return sector1; //returns service provided
		else
			return "conflict"; //multiple services provided
	}
}
