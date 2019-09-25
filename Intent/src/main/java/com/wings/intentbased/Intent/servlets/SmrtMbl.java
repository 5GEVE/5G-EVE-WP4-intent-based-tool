package com.wings.intentbased.Intent.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wings.intentbased.Intent.model.CompareDate;
import com.wings.intentbased.Intent.model.CompareTime;
import com.wings.intentbased.Intent.resources.Database;

import blueprints.SmrtMblBp;
import blueprints.SmrtTurinBp;

/**
 * Servlet implementation class SmrtMbl
 */
public class SmrtMbl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		boolean fcountry = false;
		boolean country = false;
		boolean date = false;
		boolean time = false;
		String fcount1 = null, fcount2 = null;
		String count1 = null, count2 = null;
		
		//getting parameters from browser page
		String guided = request.getParameter("guided");
		String submit = request.getParameter("submit");
		String intent = request.getParameter("intent");
		String fromCountry = request.getParameter("fromCountry");
		String nday = request.getParameter("day");
		String nmonth = request.getParameter("month");
		String nyear = request.getParameter("year");
		String nhour = request.getParameter("hour");
		String nminutes = request.getParameter("minutes");
		
		CompareDate cd = new CompareDate();
		CompareTime ct = new CompareTime();
		
		//extends intention according to information provided from browser page
		intent = intent + " ";
		
		if(nday!=null && nmonth!=null && nyear!=null)
			intent = intent + " " + nday + "/" + nmonth + "/" + nyear + " ";
		
		if(nhour!=null && nminutes!=null)
			intent = intent + " " + nhour + ":" + nminutes + " ";
		
		if(submit == null)
			submit = "nothing";
		
		if(fromCountry != null)
			intent = intent + " " + fromCountry + " ";
		
		//creation of blueprint and instantiation of some values
		SmrtMblBp mbl = new SmrtMblBp();
		mbl.setName("Smart Mobility");
		mbl.setDescription("Exploitation of knowledge on user preferences so as to provide personalised recommendations on an optimal route");
		mbl.setVersion(1.0);
		mbl.setIdentity("BP Identity");
		mbl.setSector("Smart Cities");
		mbl.setService("mMTC");
		mbl.setCountry("Greece");
		mbl.setLatency(5);
		mbl.setDataRate(25);
		mbl.setReliability(99.99);
		mbl.setAvailability(99.9);
		mbl.setMobility(50);
		mbl.setBroadband(25);
		mbl.setCapacity(0.01);
		mbl.setDensity(60000);
		mbl.setDuration(80);
		
		//cancel button is pressed
		if(guided!=null && submit.equalsIgnoreCase("cancel")) {
			request.getRequestDispatcher("IntentPage.jsp").forward(request, response);
		}
		//edit button is pressed
		//update of values according to edit values
		else if(guided != null && submit.equalsIgnoreCase("edit")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			String dayCh = request.getParameter("dayChecked");
			String monthCh = request.getParameter("monthChecked");
			String yearCh = request.getParameter("yearChecked");
			String hourCh = request.getParameter("hourChecked");
			String minutesCh = request.getParameter("minutesChecked");
			String latencyCh = request.getParameter("latencyChecked");
			String dataRateCh = request.getParameter("dataRateChecked");
			String reliabilityCh = request.getParameter("reliabilityChecked");
			String availabilityCh = request.getParameter("availabilityChecked");
			String mobilityCh = request.getParameter("mobilityChecked");
			String broadbandCh = request.getParameter("broadbandChecked");
			String capacityCh = request.getParameter("capacityChecked");
			String densityCh = request.getParameter("densityChecked");
			
			mbl.setFromCountry(countryFrom);
			mbl.setDay(Integer.parseInt(dayCh));
			mbl.setMonth(Integer.parseInt(monthCh));
			mbl.setYear(Integer.parseInt(yearCh));
			mbl.setHour(Integer.parseInt(hourCh));
			mbl.setMinutes(Integer.parseInt(minutesCh));
			mbl.setLatency(Integer.parseInt(latencyCh));
			mbl.setDataRate(Integer.parseInt(dataRateCh));
			mbl.setReliability(Double.parseDouble(reliabilityCh));
			mbl.setAvailability(Double.parseDouble(availabilityCh));
			mbl.setBroadband(Double.parseDouble(broadbandCh));
			mbl.setMobility(Integer.parseInt(mobilityCh));
			mbl.setCapacity(Double.parseDouble(capacityCh));
			mbl.setDensity(Integer.parseInt(densityCh));
			
			if(mbl.getMinutes()<10)
				request.setAttribute("minutes", "0" + mbl.getMinutes());
			else
				request.setAttribute("minutes", mbl.getMinutes());
			if(mbl.getHour()<10)
				request.setAttribute("hour", "0" + mbl.getHour());
			else
				request.setAttribute("hour", mbl.getHour());
			
			request.setAttribute("mbl", mbl);
			request.getRequestDispatcher("SmrtMblEdit.jsp").forward(request, response);
		}
		//confirmation of blueprint
		//insert of experiment in database if slot available else redirection to edit page
		else if(guided != null && submit.equalsIgnoreCase("confirm")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			String dayCh = request.getParameter("dayChecked");
			String monthCh = request.getParameter("monthChecked");
			String yearCh = request.getParameter("yearChecked");
			String hourCh = request.getParameter("hourChecked");
			String minutesCh = request.getParameter("minutesChecked");
			String latencyCh = request.getParameter("latencyChecked");
			String dataRateCh = request.getParameter("dataRateChecked");
			String reliabilityCh = request.getParameter("reliabilityChecked");
			String availabilityCh = request.getParameter("availabilityChecked");
			String mobilityCh = request.getParameter("mobilityChecked");
			String broadbandCh = request.getParameter("broadbandChecked");
			String capacityCh = request.getParameter("capacityChecked");
			String densityCh = request.getParameter("densityChecked");
			
			mbl.setFromCountry(countryFrom);
			mbl.setDay(Integer.parseInt(dayCh));
			mbl.setMonth(Integer.parseInt(monthCh));
			mbl.setYear(Integer.parseInt(yearCh));
			mbl.setHour(Integer.parseInt(hourCh));
			mbl.setMinutes(Integer.parseInt(minutesCh));
			mbl.setLatency(Integer.parseInt(latencyCh));
			mbl.setDataRate(Integer.parseInt(dataRateCh));
			mbl.setReliability(Double.parseDouble(reliabilityCh));
			mbl.setAvailability(Double.parseDouble(availabilityCh));
			mbl.setBroadband(Double.parseDouble(broadbandCh));
			mbl.setMobility(Integer.parseInt(mobilityCh));
			mbl.setCapacity(Double.parseDouble(capacityCh));
			mbl.setDensity(Integer.parseInt(densityCh));
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				mbl.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				mbl.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				mbl.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					mbl.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					mbl.setMinutes(00);
				}
				else
				{
					mbl.setHour(23);
					mbl.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(mbl.getHour(), mbl.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						mbl.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						mbl.setMinutes(00);
					}
					else
					{
						mbl.setHour(23);
						mbl.setMinutes(59);
					}
				}
			}
	
			if(mbl.getMinutes()<10)
				request.setAttribute("minutes", "0" + mbl.getMinutes());
			else
				request.setAttribute("minutes", mbl.getMinutes());
			if(mbl.getHour()<10)
				request.setAttribute("hour", "0" + mbl.getHour());
			else
				request.setAttribute("hour", mbl.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("mbl", mbl);
			request.getRequestDispatcher("SmrtMblConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(mbl.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + mbl.getDay() + "/" + mbl.getMonth() + "/" + mbl.getYear() +"' AND launchedTo='" + mbl.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(mbl.getHour() + ".0" + mbl.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(mbl.getHour() + ".0" + mbl.getMinutes()), mbl.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + mbl.getDay() + "/" + mbl.getMonth() + "/" + mbl.getYear() +"' AND launchedTo='" + mbl.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(mbl.getHour() + "." + mbl.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(mbl.getHour() + "." + mbl.getMinutes()), mbl.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(mbl.getMinutes() < 10)
				dtime = Double.parseDouble(mbl.getHour() + ".0" + mbl.getMinutes());
			else
				dtime = Double.parseDouble(mbl.getHour() + "." + mbl.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(mbl.getDay() + "/" + mbl.getMonth() + "/" + mbl.getYear(), dtime, mbl.getDuration(), mbl.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, mbl.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(mbl.getMinutes() < 10)
						db.insertIntoDB(mbl.getName(), mbl.getDescription(), mbl.getVersion(), mbl.getIdentity(), mbl.getSector(), mbl.getFromCountry(), mbl.getCountry(), mbl.getDay() + "/" + mbl.getMonth() + "/" + mbl.getYear(), Double.parseDouble(mbl.getHour() + ".0" + mbl.getMinutes()), endTime, mbl.getDuration(), 0, mbl.getService(), mbl.getLatency(), mbl.getDataRate(), mbl.getReliability(), mbl.getAvailability(), mbl.getBroadband(), mbl.getMobility(), mbl.getCapacity(), mbl.getDensity(), mbl.getSlicing(), mbl.getSecurity());
					else
						db.insertIntoDB(mbl.getName(), mbl.getDescription(), mbl.getVersion(), mbl.getIdentity(), mbl.getSector(), mbl.getFromCountry(), mbl.getCountry(), mbl.getDay() + "/" + mbl.getMonth() + "/" + mbl.getYear(), Double.parseDouble(mbl.getHour() + "." + mbl.getMinutes()), endTime, mbl.getDuration(), 0, mbl.getService(), mbl.getLatency(), mbl.getDataRate(), mbl.getReliability(), mbl.getAvailability(), mbl.getBroadband(), mbl.getMobility(), mbl.getCapacity(), mbl.getDensity(), mbl.getSlicing(), mbl.getSecurity());
					request.setAttribute("mbl", mbl);
					request.getRequestDispatcher("SmrtMblConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(mbl.getDay() + "/" + mbl.getMonth() + "/" + mbl.getYear(), Double.parseDouble(mbl.getHour() + "." + mbl.getMinutes()), mbl.getDuration(), "Greece", db);
					System.out.println("av1: " +av1);
					
					if(mbl.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + mbl.getCountry() + " at " + mbl.getHour() + ":0" + mbl.getMinutes() + " on " + mbl.getDay() + "/" + mbl.getMonth() + "/" + mbl.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + mbl.getCountry() + " at " + mbl.getHour() + ":" + mbl.getMinutes() + " on " + mbl.getDay() + "/" + mbl.getMonth() + "/" + mbl.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + mbl.getDay() + "/" + mbl.getMonth() + "/" + mbl.getYear() + ".");
					}
					request.setAttribute("mbl", mbl);
					request.getRequestDispatcher("SmrtMblEdit.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			// -------------------------------UNCOMMENT ABOVE LINES IN CASE OF DATABASE --------------------------------------
			
		}
		//getting values from guided selection
		else if(guided != null) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			String dayCh = request.getParameter("dayChecked");
			String monthCh = request.getParameter("monthChecked");
			String yearCh = request.getParameter("yearChecked");
			String hourCh = request.getParameter("hourChecked");
			String minutesCh = request.getParameter("minutesChecked");
			String latencyCh = request.getParameter("latencyChecked");
			String dataRateCh = request.getParameter("dataRateChecked");
			String reliabilityCh = request.getParameter("reliabilityChecked");
			String availabilityCh = request.getParameter("availabilityChecked");
			String mobilityCh = request.getParameter("mobilityChecked");
			String broadbandCh = request.getParameter("broadbandChecked");
			String capacityCh = request.getParameter("capacityChecked");
			String densityCh = request.getParameter("densityChecked");
			
			mbl.setFromCountry(countryFrom);
			mbl.setDay(Integer.parseInt(dayCh));
			mbl.setMonth(Integer.parseInt(monthCh));
			mbl.setYear(Integer.parseInt(yearCh));
			mbl.setHour(Integer.parseInt(hourCh));
			mbl.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				mbl.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				mbl.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				mbl.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				mbl.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				mbl.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				mbl.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				mbl.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				mbl.setDensity(Integer.parseInt(densityCh));
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				mbl.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				mbl.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				mbl.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					mbl.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					mbl.setMinutes(00);
				}
				else
				{
					mbl.setHour(23);
					mbl.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(mbl.getHour(), mbl.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						mbl.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						mbl.setMinutes(00);
					}
					else
					{
						mbl.setHour(23);
						mbl.setMinutes(59);
					}
				}
			}
	
			if(mbl.getMinutes()<10)
				request.setAttribute("minutes", "0" + mbl.getMinutes());
			else
				request.setAttribute("minutes", mbl.getMinutes());
			if(mbl.getHour()<10)
				request.setAttribute("hour", "0" + mbl.getHour());
			else
				request.setAttribute("hour", mbl.getHour());
			
			request.setAttribute("mbl", mbl);
			request.getRequestDispatcher("SmrtMblCreated.jsp").forward(request, response);
		}
		//values according to text intention
		//checks if command contains information about the country to run the experiment from
		else {
			
			if(intent.toLowerCase().contains(" from greece")) {
				fcount1 = "Greece";
			}
		
			if(intent.toLowerCase().contains(" from italy")) {
				if(fcount1 == null)
					fcount1 = "Italy";
				else {
					if(fcount2 == null)
						fcount2 = "Italy";
				}
			}
		
			if(intent.toLowerCase().contains(" from spain")) {
				if(fcount1 == null)
					fcount1 = "Spain";
				else {
					if(fcount2 == null)
						fcount2 = "Spain";
					else
						fcount2 = fcount2 + ", Spain";
				}
			}
		
			if(intent.toLowerCase().contains(" from france")) {
				if(fcount1 == null)
					fcount1 = "France";
				else {
					if(fcount2 == null)
						fcount2 = "France";
					else
						fcount2 = fcount2 + ", France";
				}
			}
			
			if(fcount1 == null) {
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("SmrtMblFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				mbl.setFromCountry(fcount1);
				fcountry = true;
			}
			else if(fcount2 != null) {
				fcount2 = fcount1 + ", " + fcount2 + ".";
				intent = intent.replaceAll("\\bfrom greece\\b", "");
				intent = intent.replaceAll("\\bfrom italy\\b", "");
				intent = intent.replaceAll("\\bfrom spain\\b", "");
				intent = intent.replaceAll("\\bfrom france\\b", "");
				request.setAttribute("message", "Conflict between countries!");
				request.setAttribute("message2", "You have selected the following countries to run the experiment from: " + fcount2);
				request.setAttribute("message3", "Only one Country acceptable!");
				request.setAttribute("intent", intent);
				request.setAttribute("servlet", "SmrtMbl");
				request.getRequestDispatcher("SmrtMblFromCountry.jsp").forward(request, response);
				
			}
			
			//checks if command contains information about the date to run the experiment
			if(fcountry == true) {
				
				int num = 0;
				String day = "", month = "", year = "";
				
				for(int i=0; i<intent.length(); i++) {
					if(intent.charAt(i) == '/')
						num++;
				}
				
				if(num == 1 || num > 2) {
					for(int i=0; i<intent.length(); i++) {
						if(intent.charAt(i) == '/')
							intent = intent.substring(0, i) + ' ' + intent.substring(i+1);
					}
					request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
					request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
					request.setAttribute("message", "Wrong value for date provided.");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("SmrtMblDate.jsp").forward(request, response);
				}
				
				if(num == 2 && intent.toLowerCase().indexOf("/")!=-1) {
					int i = intent.indexOf("/");
					do
					{
						day += intent.charAt(i-1);
						i--;
						if(intent.charAt(i) == ' ' || Character.isDigit(intent.charAt(i)) == false)
							break;
					}while(intent.charAt(i) != ' ' || Character.isDigit(intent.charAt(i)) == false);
					day = new StringBuffer(day).reverse().toString();
					day = day.substring(1, day.length());
					if(day.equalsIgnoreCase("") == false) {
						if(Integer.parseInt(day) > 0 && Integer.parseInt(day) < 32) {
							mbl.setDay(Integer.parseInt(day));
						}
						else mbl.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
					}
					else
						mbl.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					int k = intent.indexOf("/");
					do
					{
						month += intent.charAt(k+1);
						k++;
						if(intent.charAt(k) == ' ' || Character.isDigit(intent.charAt(k)) == false)
							break;
					}while(intent.charAt(k) != ' ' || Character.isDigit(intent.charAt(k)) == false);
					month = month.replace(month.substring(month.length()-1), "");
					if(month.equalsIgnoreCase("") == false) {
						if(Integer.parseInt(month)>0 && Integer.parseInt(month)<13) {
							mbl.setMonth(Integer.parseInt(month));
						}
						else
							mbl.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
					}
					else
					{
						mbl.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
					}
					
					String yearsub = intent.substring(intent.indexOf("/")+1, intent.length());
					int l = yearsub.indexOf("/");
					do
					{
						year += yearsub.charAt(l+1);
						l++;
						if(yearsub.charAt(l) == ' ' || Character.isDigit(yearsub.charAt(l)) == false)
							break;
					}while(intent.charAt(l) != ' ' || Character.isDigit(intent.charAt(l)) == false);
					year = year.replace(year.substring(year.length()-1), "");
					if(year.equalsIgnoreCase("") == false) {
						if(Integer.parseInt(year)>=Calendar.getInstance().get(Calendar.YEAR)) {
							mbl.setYear(Integer.parseInt(year));
						}
						else
							mbl.setYear(Calendar.getInstance().get(Calendar.YEAR));
					}
					else
						mbl.setYear(Calendar.getInstance().get(Calendar.YEAR));

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String givda = ""+mbl.getYear()+"-"+mbl.getMonth()+"-"+mbl.getDay()+"";
					try {
						Date givdate = sdf.parse(givda);
						Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
						day = Integer.toString(mbl.getDay());
						month = Integer.toString(mbl.getMonth());
						year = Integer.toString(mbl.getYear());
						
						if(givdate.compareTo(curdate)<0) {
							for(int j=0; j<intent.length(); j++) {
								if(intent.charAt(j) == '/')
									intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
							}
							
							request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
							request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
							request.setAttribute("message", "Date given has passed.");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("SmrtMblDate.jsp").forward(request, response);
						}
						else if(cd.dateCompare(mbl.getDay(), mbl.getMonth(), mbl.getYear()) == 0) {
							
							for(int j=0; j<intent.length(); j++) {
								if(intent.charAt(j) == '/')
									intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
							}
							
							request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
							request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
							request.setAttribute("message", "Date given has wrong number of days.");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("SmrtMblDate.jsp").forward(request, response);
						}
						else
							date=true;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
					request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("SmrtMblDate.jsp").forward(request, response);
				}
			}
			
			//checks if command contains information about the time to run the experiment
			if(date == true) {
				
				int num = 0;
				
				for(int i=0; i<intent.length(); i++) {
					if(intent.charAt(i) == ':')
						num++;
				}
				
				if(num > 1) {
					for(int i=0; i<intent.length(); i++) {
						if(intent.charAt(i) == ':')
							intent = intent.substring(0, i) + ' ' + intent.substring(i+1);
					}
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 23) {
						request.setAttribute("hour", Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
						request.setAttribute("minutes", "00");
					}
					else {
						request.setAttribute("hour", 23);
						request.setAttribute("minutes", "59");
					} 
					request.setAttribute("message", "Multiple values for time provided. Please provide only one value!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("SmrtMblTime.jsp").forward(request, response);
				}
				
				if(intent.toLowerCase().indexOf(":")!=-1) {
					String hour = "";
					String minutes = "";
					int i = intent.indexOf(":");
					do
					{
						hour += intent.charAt(i-1);
						i--;
						if(intent.charAt(i) == ' ' || Character.isDigit(intent.charAt(i)) == false)
							break;
					}while(intent.charAt(i) != ' ' || Character.isDigit(intent.charAt(i)) == false);
					hour = new StringBuffer(hour).reverse().toString();
					hour = hour.substring(1, hour.length());
					if(hour.equalsIgnoreCase("") == false) {
						if(Integer.parseInt(hour) > -1 && Integer.parseInt(hour) < 24)
							mbl.setHour(Integer.parseInt(hour));
						else
							mbl.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
					}
					else
						mbl.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

					int k = intent.indexOf(":");
					do {
						minutes += intent.charAt(k+1);
						k++;
						if(intent.charAt(k) == ' ' || Character.isDigit(intent.charAt(k)) == false)
							break;
					}while(intent.charAt(k) != ' ' || Character.isDigit(intent.charAt(k)) == false);
					minutes = minutes.replace(minutes.substring(minutes.length()-1), "");
					if(minutes.equalsIgnoreCase("") == false) {
						if(Integer.parseInt(minutes) > -1 && Integer.parseInt(minutes) < 60) 
							mbl.setMinutes(Integer.parseInt(minutes));
						else
							mbl.setMinutes(00);	
					}
					
					if(cd.dateCompare(mbl.getDay(), mbl.getMonth(), mbl.getYear()) == 1) {
						if(ct.timeCompare(mbl.getHour(), mbl.getMinutes()) == 0) {
							if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
								mbl.setHour(00);
								mbl.setMinutes(00);
								mbl.setDay(mbl.getDay()+1);
								time = true;
							}
							else {
								mbl.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
								mbl.setMinutes(00);
								time = true;
							}
						}
						else
						{
							time = true;
						}
					}
					else if(cd.dateCompare(mbl.getDay(), mbl.getMonth(), mbl.getYear()) == 2) {
						time = true;
					}
					else
					{
						if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 23) {
							request.setAttribute("hour", Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
							request.setAttribute("minutes", "00");
						}
						else {
							request.setAttribute("hour", 23);
							request.setAttribute("minutes", "59");
						}
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("SmrtMblTime.jsp").forward(request, response);
					}
					
				}
				else {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 23) {
						request.setAttribute("hour", Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
						request.setAttribute("minutes", "00");
					}
					else {
						request.setAttribute("hour", 23);
						request.setAttribute("minutes", "59");
					}
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("SmrtMblTime.jsp").forward(request, response);
				}
			}
			
			//redirection to blueprint page
			if(fcountry == true && date == true) {
				if(mbl.getMinutes()<10)
					request.setAttribute("minutes", "0" + mbl.getMinutes());
				else
					request.setAttribute("minutes", mbl.getMinutes());
				if(mbl.getHour()<10)
					request.setAttribute("hour", "0" + mbl.getHour());
				else
					request.setAttribute("hour", mbl.getHour());
				
				request.setAttribute("mbl", mbl);
				request.getRequestDispatcher("SmrtMblCreated.jsp").forward(request, response);
			}
		}
		
	}

}
