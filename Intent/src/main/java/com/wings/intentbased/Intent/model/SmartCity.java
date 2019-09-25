package com.wings.intentbased.Intent.model;

public class SmartCity {
	
	//constructor
	public SmartCity() {
		
	}
	
	//a function that checks the smart city services given in the command
	public String getSector(String command) {
		
		String sector1 = null;
		String sector2 = null;
		command = " " + command + " ";
		
		if(command.toLowerCase().contains(" health monitoring ")) {
			if(sector1 == null)
				sector1 = "health monitoring";
		}
		
		if(command.toLowerCase().contains(" smart home ") ||
		   command.toLowerCase().contains(" smart personalised spaces ") ||
		   command.toLowerCase().contains(" smart personalised space ")) {
			if(sector1 == null)
				sector1 = "smart home";
			else
				sector2 = "smart home";
		}
		
		if(command.toLowerCase().contains(" smart mobility ")) {
			if(sector1 == null)
				sector1 = "smart mobility";
			else
				sector2 = "smart mobility";
		}
		
		if(command.toLowerCase().contains(" eambulance ") ||
		   command.toLowerCase().contains(" connected ambulance ") ||
		   command.toLowerCase().contains(" ambulance ")) {
			if(sector1 == null)
				sector1 = "connected ambulance";
			else
				sector2 = "connected ambulance";
		}
		
		if(command.toLowerCase().contains(" smart turin ")) {
			if(sector1 == null)
				sector1 = "smart turin";
			else
				sector2 = "smart turin";
		}
		
		if(sector1 == null)
			return "no sector"; //no service provided
		else if(sector2 == null)
			return sector1; //returns service provided
		else
			return "conflict"; //multiple services provided
	}
}
