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

import blueprints.ImMediaBp;
import blueprints.SmrtTurinBp;

/**
 * Servlet implementation class SmrtTurin
 */
public class SmrtTurin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		boolean devs = false;
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
		String numOfDevs = request.getParameter("numOfDevs");
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
		
		if(numOfDevs != null)
			intent = intent + " #" + numOfDevs + " ";
		
		if(fromCountry != null)
			intent = intent + " " + fromCountry + " ";
		
		//creation of blueprint and instantiation of some values
		SmrtTurinBp tur = new SmrtTurinBp();
		tur.setName("Smart Turin");
		tur.setDescription("Management of critical issues related to urban mobility");
		tur.setVersion(1.0);
		tur.setIdentity("BP Identity");
		tur.setSector("Smart Cities");
		tur.setService("mMTC");
		tur.setCountry("Italy");
		tur.setLatency(5);
		tur.setDataRate(1);
		tur.setReliability(99.99);
		tur.setAvailability(99.9);
		tur.setMobility(0);
		tur.setBroadband(10);
		tur.setCapacity(0.01);
		tur.setDensity(60000);
		tur.setSlicing("Y");
		tur.setSecurity("Y");
		tur.setDuration(80);
		
		//cancel button is pressed
		if(guided!=null && submit.equalsIgnoreCase("cancel")) {
			request.getRequestDispatcher("IntentPage.jsp").forward(request, response);
		}
		//edit button is pressed
		//update of values according to edit values
		else if(guided != null && submit.equalsIgnoreCase("edit")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			String numOfDevsChecked = request.getParameter("numOfDevsChecked");
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
			String slicingCh = request.getParameter("slicingChecked");
			String securityCh = request.getParameter("securityChecked");
			
			tur.setFromCountry(countryFrom);

			tur.setNumOfDevs(Integer.parseInt(numOfDevsChecked));
			tur.setDay(Integer.parseInt(dayCh));
			tur.setMonth(Integer.parseInt(monthCh));
			tur.setYear(Integer.parseInt(yearCh));
			tur.setHour(Integer.parseInt(hourCh));
			tur.setMinutes(Integer.parseInt(minutesCh));
			tur.setLatency(Integer.parseInt(latencyCh));
			tur.setDataRate(Integer.parseInt(dataRateCh));
			tur.setReliability(Double.parseDouble(reliabilityCh));
			tur.setAvailability(Double.parseDouble(availabilityCh));
			tur.setBroadband(Double.parseDouble(broadbandCh));
			tur.setMobility(Integer.parseInt(mobilityCh));
			tur.setCapacity(Double.parseDouble(capacityCh));
			tur.setDensity(Integer.parseInt(densityCh));
			tur.setSlicing(slicingCh);
			tur.setSecurity(securityCh);
			
			if(tur.getMinutes()<10)
				request.setAttribute("minutes", "0" + tur.getMinutes());
			else
				request.setAttribute("minutes", tur.getMinutes());
			if(tur.getHour()<10)
				request.setAttribute("hour", "0" + tur.getHour());
			else
				request.setAttribute("hour", tur.getHour());
			
			request.setAttribute("tur", tur);
			request.getRequestDispatcher("SmrtTurinEdit.jsp").forward(request, response);
		}
		//confirmation of blueprint
		//insert of experiment in database if slot available else redirection to edit page
		else if(guided != null && submit.equalsIgnoreCase("confirm")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			String numOfDevsChecked = request.getParameter("numOfDevsChecked");
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
			String slicingCh = request.getParameter("slicingChecked");
			String securityCh = request.getParameter("securityChecked");
			
			tur.setFromCountry(countryFrom);

			tur.setNumOfDevs(Integer.parseInt(numOfDevsChecked));
			tur.setDay(Integer.parseInt(dayCh));
			tur.setMonth(Integer.parseInt(monthCh));
			tur.setYear(Integer.parseInt(yearCh));
			tur.setHour(Integer.parseInt(hourCh));
			tur.setMinutes(Integer.parseInt(minutesCh));
			tur.setLatency(Integer.parseInt(latencyCh));
			tur.setDataRate(Integer.parseInt(dataRateCh));
			tur.setReliability(Double.parseDouble(reliabilityCh));
			tur.setAvailability(Double.parseDouble(availabilityCh));
			tur.setBroadband(Double.parseDouble(broadbandCh));
			tur.setMobility(Integer.parseInt(mobilityCh));
			tur.setCapacity(Double.parseDouble(capacityCh));
			tur.setDensity(Integer.parseInt(densityCh));
			tur.setSlicing(slicingCh);
			tur.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				tur.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				tur.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				tur.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					tur.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					tur.setMinutes(00);
				}
				else
				{
					tur.setHour(23);
					tur.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(tur.getHour(), tur.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						tur.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						tur.setMinutes(00);
					}
					else
					{
						tur.setHour(23);
						tur.setMinutes(59);
					}
				}
			}
	
			if(tur.getMinutes()<10)
				request.setAttribute("minutes", "0" + tur.getMinutes());
			else
				request.setAttribute("minutes", tur.getMinutes());
			if(tur.getHour()<10)
				request.setAttribute("hour", "0" + tur.getHour());
			else
				request.setAttribute("hour", tur.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("tur", tur);
			request.getRequestDispatcher("SmrtTurinConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(tur.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + tur.getDay() + "/" + tur.getMonth() + "/" + tur.getYear() +"' AND launchedTo='" + tur.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(tur.getHour() + ".0" + tur.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(tur.getHour() + ".0" + tur.getMinutes()), tur.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + tur.getDay() + "/" + tur.getMonth() + "/" + tur.getYear() +"' AND launchedTo='" + tur.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(tur.getHour() + "." + tur.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(tur.getHour() + "." + tur.getMinutes()), tur.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(tur.getMinutes() < 10)
				dtime = Double.parseDouble(tur.getHour() + ".0" + tur.getMinutes());
			else
				dtime = Double.parseDouble(tur.getHour() + "." + tur.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(tur.getDay() + "/" + tur.getMonth() + "/" + tur.getYear(), dtime, tur.getDuration(), tur.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, tur.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(tur.getMinutes() < 10)
						db.insertIntoDB(tur.getName(), tur.getDescription(), tur.getVersion(), tur.getIdentity(), tur.getSector(), tur.getFromCountry(), tur.getCountry(), tur.getDay() + "/" + tur.getMonth() + "/" + tur.getYear(), Double.parseDouble(tur.getHour() + ".0" + tur.getMinutes()), endTime, tur.getDuration(), tur.getNumOfDevs(), tur.getService(), tur.getLatency(), tur.getDataRate(), tur.getReliability(), tur.getAvailability(), tur.getBroadband(), tur.getMobility(), tur.getCapacity(), tur.getDensity(), tur.getSlicing(), tur.getSecurity());
					else
						db.insertIntoDB(tur.getName(), tur.getDescription(), tur.getVersion(), tur.getIdentity(), tur.getSector(), tur.getFromCountry(), tur.getCountry(), tur.getDay() + "/" + tur.getMonth() + "/" + tur.getYear(), Double.parseDouble(tur.getHour() + "." + tur.getMinutes()), endTime, tur.getDuration(), tur.getNumOfDevs(), tur.getService(), tur.getLatency(), tur.getDataRate(), tur.getReliability(), tur.getAvailability(), tur.getBroadband(), tur.getMobility(), tur.getCapacity(), tur.getDensity(), tur.getSlicing(), tur.getSecurity());
					request.setAttribute("tur", tur);
					request.getRequestDispatcher("SmrtTurinConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(tur.getDay() + "/" + tur.getMonth() + "/" + tur.getYear(), Double.parseDouble(tur.getHour() + "." + tur.getMinutes()), tur.getDuration(), "Italy", db);
					System.out.println("av1: " +av1);
					
					if(tur.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + tur.getCountry() + " at " + tur.getHour() + ":0" + tur.getMinutes() + " on " + tur.getDay() + "/" + tur.getMonth() + "/" + tur.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + tur.getCountry() + " at " + tur.getHour() + ":" + tur.getMinutes() + " on " + tur.getDay() + "/" + tur.getMonth() + "/" + tur.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + tur.getDay() + "/" + tur.getMonth() + "/" + tur.getYear() + ".");
					}
					request.setAttribute("tur", tur);
					request.getRequestDispatcher("SmrtTurinEdit.jsp").forward(request, response);
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
			String numOfDevsChecked = request.getParameter("numOfDevsChecked");
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
			String slicingCh = request.getParameter("slicingChecked");
			String securityCh = request.getParameter("securityChecked");
			
			tur.setFromCountry(countryFrom);

			tur.setNumOfDevs(Integer.parseInt(numOfDevsChecked));
			tur.setDay(Integer.parseInt(dayCh));
			tur.setMonth(Integer.parseInt(monthCh));
			tur.setYear(Integer.parseInt(yearCh));
			tur.setHour(Integer.parseInt(hourCh));
			tur.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				tur.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				tur.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				tur.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				tur.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				tur.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				tur.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				tur.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				tur.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				tur.setSlicing(slicingCh);
			if(securityCh != null)
				tur.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				tur.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				tur.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				tur.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					tur.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					tur.setMinutes(00);
				}
				else
				{
					tur.setHour(23);
					tur.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(tur.getHour(), tur.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						tur.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						tur.setMinutes(00);
					}
					else
					{
						tur.setHour(23);
						tur.setMinutes(59);
					}
				}
			}
	
			if(tur.getMinutes()<10)
				request.setAttribute("minutes", "0" + tur.getMinutes());
			else
				request.setAttribute("minutes", tur.getMinutes());
			if(tur.getHour()<10)
				request.setAttribute("hour", "0" + tur.getHour());
			else
				request.setAttribute("hour", tur.getHour());
			
			request.setAttribute("tur", tur);
			request.getRequestDispatcher("SmrtTurinCreated.jsp").forward(request, response);
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
				request.getRequestDispatcher("SmrtTurinFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				tur.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "SmrtTurin");
				request.getRequestDispatcher("SmrtTurinFromCountry.jsp").forward(request, response);
				
			}
				
			//checks if command contains information about the number of devices
			if(fcountry==true) {
					
				int num = 0;
					
				for(int i=0; i<intent.length(); i++) {
					if(intent.charAt(i) == '#')
						num++;
				}
					
				if(num > 1) {
					for(int i=0; i<intent.length(); i++) {
						if(intent.charAt(i) == '#')
							intent = intent.substring(0, i) + ' ' + intent.substring(i+1);
					}
					request.setAttribute("message", "Multiple values for devices provided. Please provide only one value!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("SmrtTurinNumOfDevs.jsp").forward(request, response);
				}
					
				if(intent.toLowerCase().indexOf("#")!=-1) {
					String tmp = "";
					int i = intent.indexOf("#");
					do
					{
						tmp += intent.charAt(i+1);
						i++;
						if(intent.charAt(i) == ' ' || Character.isDigit(intent.charAt(i)) == false)
							break;
					}while(intent.charAt(i) != ' ' || Character.isDigit(intent.charAt(i)) == false);
					tmp = tmp.replace(tmp.substring(tmp.length()-1), "");
					if(tmp.equalsIgnoreCase("") == false) {
						if(Integer.parseInt(tmp)>0) {
							tur.setNumOfDevs(Integer.parseInt(tmp));
							devs = true;
						}
						else {
							intent = intent.replace("#", " ");
							request.setAttribute("message", "Invalid number of devices given: " + tmp);
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("SmrtTurinNumOfDevs.jsp").forward(request, response);
						}
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("message", "Invalid number of devices given!");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("SmrtTurinNumOfDevs.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("SmrtTurinNumOfDevs.jsp").forward(request, response);
				}
			}
				
			//checks if command contains information about the date to run the experiment
			if(devs == true) {
				
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
					request.getRequestDispatcher("SmrtTurinDate.jsp").forward(request, response);
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
							tur.setDay(Integer.parseInt(day));
						}
						else tur.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
					}
					else
						tur.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
							tur.setMonth(Integer.parseInt(month));
						}
						else
							tur.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
					}
					else
					{
						tur.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
							tur.setYear(Integer.parseInt(year));
						}
						else
							tur.setYear(Calendar.getInstance().get(Calendar.YEAR));
					}
					else
						tur.setYear(Calendar.getInstance().get(Calendar.YEAR));

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String givda = ""+tur.getYear()+"-"+tur.getMonth()+"-"+tur.getDay()+"";
					try {
						Date givdate = sdf.parse(givda);
						Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
						day = Integer.toString(tur.getDay());
						month = Integer.toString(tur.getMonth());
						year = Integer.toString(tur.getYear());
						
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
							request.getRequestDispatcher("SmrtTurinDate.jsp").forward(request, response);
						}
						else if(cd.dateCompare(tur.getDay(), tur.getMonth(), tur.getYear()) == 0) {
							
							for(int j=0; j<intent.length(); j++) {
								if(intent.charAt(j) == '/')
									intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
							}
							
							request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
							request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
							request.setAttribute("message", "Date given has wrong number of days.");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("SmrtTurinDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("SmrtTurinDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("SmrtTurinTime.jsp").forward(request, response);
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
							tur.setHour(Integer.parseInt(hour));
						else
							tur.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
					}
					else
						tur.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
							tur.setMinutes(Integer.parseInt(minutes));
						else
							tur.setMinutes(00);	
					}
					
					if(cd.dateCompare(tur.getDay(), tur.getMonth(), tur.getYear()) == 1) {
						if(ct.timeCompare(tur.getHour(), tur.getMinutes()) == 0) {
							if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
								tur.setHour(00);
								tur.setMinutes(00);
								tur.setDay(tur.getDay()+1);
								time = true;
							}
							else {
								tur.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
								tur.setMinutes(00);
								time = true;
							}
						}
						else
						{
							time = true;
						}
					}
					else if(cd.dateCompare(tur.getDay(), tur.getMonth(), tur.getYear()) == 2) {
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
						request.getRequestDispatcher("SmrtTurinTime.jsp").forward(request, response);
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
					request.getRequestDispatcher("SmrtTurinTime.jsp").forward(request, response);
				}
			}
			
			//redirection to blueprint page
			if(fcountry == true && devs == true && date == true && time == true) {
				if(tur.getMinutes()<10)
					request.setAttribute("minutes", "0" + tur.getMinutes());
				else
					request.setAttribute("minutes", tur.getMinutes());
				if(tur.getHour()<10)
					request.setAttribute("hour", "0" + tur.getHour());
				else
					request.setAttribute("hour", tur.getHour());
				
				request.setAttribute("tur", tur);
				request.getRequestDispatcher("SmrtTurinCreated.jsp").forward(request, response);
			}
		}
		
	}

}
