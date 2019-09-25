package com.wings.intentbased.Intent.model;

import java.util.Calendar;

public class CompareTime {

	//constructor
	public CompareTime() {
		
	}
	
	/*a function that compares time given in the command with the current time
	returns 0 if time given has passed compared to current time
	1 if time given is same or after the current time*/
	public int timeCompare(int hour, int minutes) {
		
		if(hour > Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
			return 1;
		else if(hour < Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
			return 0;
		else
		{
			if(minutes > Calendar.getInstance().get(Calendar.MINUTE))
				return 1;
			else
				return 0;
		}
		
	}
	
}
