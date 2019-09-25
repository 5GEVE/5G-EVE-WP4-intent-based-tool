package com.wings.intentbased.Intent.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class CompareDate {

	//constructor
	public CompareDate() {
		
	}
	
	/*a function that compares the current date with the date given in the command
	returns 0 if date given has passed compared to current date, 
	1 if date given is the same compared to current date
	2 if date given is after the current date*/
	public int dateCompare(int day, int month, int year) {
		
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		
		if(day > daysInMonth) {
			return 0;
		}
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String givda = year+"-"+month+"-"+day;
			try {
				Date givdate = sdf.parse(givda);
				Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				
				if(givdate.compareTo(curdate)<0) {
					return 0;
				}
				else if(givdate.compareTo(curdate) == 0)
					return 1;
				else
					return 2;
			} catch (ParseException e) {
				return 0;
			}
		}
	}
	
}
