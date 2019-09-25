package com.wings.intentbased.Intent.services;

public class CommandService1 {
	
	/*a fucntion that checks if there is only one sector given in the intention or more
	and returns the number of the sector */
	public int checkCommand(String command) {
		
		int sector1 = 0;
		int sector2 = 0;
		
		command = " " + command + " ";
		
		if(command.toLowerCase().contains(" industry ") ||
		   command.toLowerCase().contains(" agvs ") ||
		   command.toLowerCase().contains(" autonomous vehicles ")) {
			
			sector1 = 1; //returns industry 4.0 identifier
		}
		
		if(command.toLowerCase().contains(" utilities ") ||
				command.toLowerCase().contains(" energy management ") ||
				command.toLowerCase().contains(" smart energy ")) {
			if(sector1 == 0)
				sector1 = 2; //returns utilities identifier
			else
				sector2 = 2; //conflict
		}
		
		if(command.toLowerCase().contains(" smart city") ||
				command.toLowerCase().contains(" smart cities ") ||
				command.toLowerCase().contains(" environment ") ||
				command.toLowerCase().contains(" health ") ||
				command.toLowerCase().contains(" health monitoring ") ||
				command.toLowerCase().contains(" smart mobility ") ||
				command.toLowerCase().contains(" smart home ") ||
				command.toLowerCase().contains(" smart personalised spaces ") ||
				command.toLowerCase().contains(" smart personalised space ") ||
				command.toLowerCase().contains(" flow of students ") ||
				command.toLowerCase().contains(" limited mobility ") ||
				command.toLowerCase().contains(" connected ambulance ") ||
				command.toLowerCase().contains(" eambulance ") ||
				command.toLowerCase().contains(" ambulance ") ||
				command.toLowerCase().contains(" smart turin ")) {
			if(sector1 == 0)
				sector1 = 3; //returns smart city identifier
			else
				sector2 = 3; //conflict
		}
		
		if(command.toLowerCase().contains(" media ") ||
				command.toLowerCase().contains(" entertainment ") ||
				command.toLowerCase().contains(" ultra high fidelity") ||
				command.toLowerCase().contains(" ultra-high fidelity ") ||
				command.toLowerCase().contains(" ultra high-fidelity ") ||
				command.toLowerCase().contains(" uhf ") ||
				command.toLowerCase().contains(" live event ") ||
				command.toLowerCase().contains(" on-site ") ||
				command.toLowerCase().contains(" on site ") ||
				command.toLowerCase().contains(" immersive and integrated ") ||
				command.toLowerCase().contains(" integrated ") ||
				command.toLowerCase().contains(" immersive ") ||
				command.toLowerCase().contains(" virtual visit ") ||
				command.toLowerCase().contains(" virtual 360 ") ||
				command.toLowerCase().contains(" video 360 ")) {
			if(sector1 == 0)
				sector1 = 4; //returns media and entertainment identifier
			else
				sector2 = 4; //conflict
		}
		
		if(command.toLowerCase().contains(" smart transport ") ||
		   command.toLowerCase().contains(" recognition service ") ||
		   command.toLowerCase().contains(" tracking service ") ||
		   command.toLowerCase().contains(" urban mobility ")) {
			if(sector1 == 0)
				sector1 = 5; //returns smart transport identifier
			else
				sector2 = 5; //conflict
		}
		
		if(command.toLowerCase().contains(" smart tourism ") ||
		   command.toLowerCase().contains(" ar interaction ") ||
		   command.toLowerCase().contains(" business augmented ") ||
		   command.toLowerCase().contains(" augmented fair ") ||
		   command.toLowerCase().contains(" augmented booth ") ||
		   command.toLowerCase().contains(" business booth ")) {
			if(sector1 == 0)
				sector1 = 6; //returns smart tourism identifier
			else
				sector2 = 6; //conflict
		}
		
		if(sector2 != 0)
			sector1 = 100; //conflict
		
		return sector1; //returns the identifier
	}

}
