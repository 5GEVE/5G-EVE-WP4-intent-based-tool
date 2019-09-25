package com.wings.intentbased.Intent.services;

public class DemoCommandService {
	
	public int checkCommand(String command) {
		
		int sector1 = 0;
		int sector2 = 0;
		
		command = " " + command + " ";
		
		if(command.toLowerCase().contains(" utilities ") ||
		   command.toLowerCase().contains(" energy ")) {	
			sector1 = 1;
		}
		
		if(command.toLowerCase().contains(" smart city ") ||
		   command.toLowerCase().contains(" air quality ") ||
		   command.toLowerCase().contains(" smart mobility ") ||
		   command.toLowerCase().contains(" smart home ")) {
			if(sector1 == 0)
				sector1 = 2;
			else
				sector2 = 2;
		}
		
		if(command.toLowerCase().contains(" health ")) {
			if(sector1 == 0)
				sector1 = 3;
			else
				sector2 = 3;
		}
		
		if(sector2 != 0)
			sector1 = 100;
		
		return sector1;
	}
}
