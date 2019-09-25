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

import blueprints.ConnAmbBp;
import blueprints.UrbMblBp;

/**
 * Servlet implementation class ConnAmb
 */
public class ConnAmb extends HttpServlet {
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
		
		boolean dataRat = false;
		boolean fcountry = false;
		boolean latenc = false;
		boolean date = false;
		boolean time = false;
		String fcount1 = null, fcount2 = null;
		
		//getting parameters from browser page
		String intent = request.getParameter("intent");
		String guided = request.getParameter("guided");
		String submit = request.getParameter("submit");
		String fromCountry = request.getParameter("fromCountry");
		String dataRate = request.getParameter("dataRate");
		String latency = request.getParameter("latency");
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
		
		if(latency != null)
			intent = intent + " #" + latency + " ";
		
		if(dataRate != null)
			intent = intent + " $" + dataRate + " ";
		
		if(fromCountry != null)
			intent = intent + " " + fromCountry + " ";
		
		if(submit != null)
			System.out.println("Submit choice: " + submit);
		else
			submit="nothing";
		
		//creation of blueprint and instantiation of some values
		ConnAmbBp amb = new ConnAmbBp();
		amb.setName("Connected Ambulance");
		amb.setDescription("Advance the emergency ambulance services with their healthcare stakeholders to help create improved experiences and outcomes for patients in their care");
		amb.setVersion(1.0);
		amb.setIdentity("BP Identity");
		amb.setSector("Health - Smart Cities");
		amb.setCountry("Greece");
		amb.setLocation("Greece");
		amb.setService("URLLC & eMBB");
		amb.setLatency(5);
		amb.setDataRate(25);
		amb.setReliability(99.99);
		amb.setAvailability(99.9);
		amb.setMobility(60);
		amb.setBroadband(25);
		amb.setCapacity(0.01);
		amb.setDensity(60000);
		amb.setSlicing("Y");
		amb.setSecurity("Y");
		amb.setDuration(80);
		
		//cancel button is pressed
		if(guided != null && submit.equalsIgnoreCase("cancel")) {
			request.getRequestDispatcher("IntentPage.jsp").forward(request, response);
		}
		//edit button is pressed
		//update of values according to edit values
		else if(guided != null && submit.equalsIgnoreCase("edit")) {
			String countryFrom = request.getParameter("countryFromChecked");
			//String dataRateCh = request.getParameter("dataRateChecked");
			//String latencyCh = request.getParameter("latencyChecked");
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
			
			amb.setFromCountry(countryFrom);
			//amb.setDataRate(Integer.parseInt(dataRateCh));
			//amb.setLatency(Integer.parseInt(latencyCh));
			amb.setDay(Integer.parseInt(dayCh));
			amb.setMonth(Integer.parseInt(monthCh));
			amb.setYear(Integer.parseInt(yearCh));
			amb.setHour(Integer.parseInt(hourCh));
			amb.setMinutes(Integer.parseInt(minutesCh));
			amb.setLatency(Integer.parseInt(latencyCh));
			amb.setDataRate(Integer.parseInt(dataRateCh));
			amb.setReliability(Double.parseDouble(reliabilityCh));
			amb.setAvailability(Double.parseDouble(availabilityCh));
			amb.setBroadband(Double.parseDouble(broadbandCh));
			amb.setMobility(Integer.parseInt(mobilityCh));
			amb.setCapacity(Double.parseDouble(capacityCh));
			amb.setDensity(Integer.parseInt(densityCh));
			amb.setSlicing(slicingCh);
			amb.setSecurity(securityCh);
			
			if(amb.getMinutes()<10)
				request.setAttribute("minutes", "0" + amb.getMinutes());
			else
				request.setAttribute("minutes", amb.getMinutes());
			if(amb.getHour()<10)
				request.setAttribute("hour", "0" + amb.getHour());
			else
				request.setAttribute("hour", amb.getHour());
			
			request.setAttribute("amb", amb);
			request.getRequestDispatcher("ConnAmbEdit.jsp").forward(request, response);
		}
		//confirmation of blueprint
		//insert of experiment in database if slot available else redirection to edit page
		else if(guided != null && submit.equalsIgnoreCase("confirm")) {
			String countryFrom = request.getParameter("countryFromChecked");
			//String dataRateCh = request.getParameter("dataRateChecked");
			//String latencyCh = request.getParameter("latencyChecked");
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
			
			amb.setFromCountry(countryFrom);
			//amb.setDataRate(Integer.parseInt(dataRateCh));
			//amb.setLatency(Integer.parseInt(latencyCh));
			amb.setDay(Integer.parseInt(dayCh));
			amb.setMonth(Integer.parseInt(monthCh));
			amb.setYear(Integer.parseInt(yearCh));
			amb.setHour(Integer.parseInt(hourCh));
			amb.setMinutes(Integer.parseInt(minutesCh));
			amb.setLatency(Integer.parseInt(latencyCh));
			amb.setDataRate(Integer.parseInt(dataRateCh));
			amb.setReliability(Double.parseDouble(reliabilityCh));
			amb.setAvailability(Double.parseDouble(availabilityCh));
			amb.setBroadband(Double.parseDouble(broadbandCh));
			amb.setMobility(Integer.parseInt(mobilityCh));
			amb.setCapacity(Double.parseDouble(capacityCh));
			amb.setDensity(Integer.parseInt(densityCh));
			amb.setSlicing(slicingCh);
			amb.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				amb.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				amb.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				amb.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					amb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					amb.setMinutes(00);
				}
				else
				{
					amb.setHour(23);
					amb.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(amb.getHour(), amb.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						amb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						amb.setMinutes(00);
					}
					else
					{
						amb.setHour(23);
						amb.setMinutes(59);
					}
				}
			}
	
			if(amb.getMinutes()<10)
				request.setAttribute("minutes", "0" + amb.getMinutes());
			else
				request.setAttribute("minutes", amb.getMinutes());
			if(amb.getHour()<10)
				request.setAttribute("hour", "0" + amb.getHour());
			else
				request.setAttribute("hour", amb.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("amb", amb);
			request.getRequestDispatcher("ConnAmbConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(amb.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + amb.getDay() + "/" + amb.getMonth() + "/" + amb.getYear() +"' AND launchedTo='" + amb.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(amb.getHour() + ".0" + amb.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(amb.getHour() + ".0" + amb.getMinutes()), amb.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + amb.getDay() + "/" + amb.getMonth() + "/" + amb.getYear() +"' AND launchedTo='" + amb.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(amb.getHour() + "." + amb.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(amb.getHour() + "." + amb.getMinutes()), amb.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(amb.getMinutes() < 10)
				dtime = Double.parseDouble(amb.getHour() + ".0" + amb.getMinutes());
			else
				dtime = Double.parseDouble(amb.getHour() + "." + amb.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(amb.getDay() + "/" + amb.getMonth() + "/" + amb.getYear(), dtime, amb.getDuration(), amb.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, amb.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(amb.getMinutes() < 10)
						db.insertIntoDB(amb.getName(), amb.getDescription(), amb.getVersion(), amb.getIdentity(), amb.getSector(), amb.getFromCountry(), amb.getCountry(), amb.getDay() + "/" + amb.getMonth() + "/" + amb.getYear(), Double.parseDouble(amb.getHour() + ".0" + amb.getMinutes()), endTime, amb.getDuration(), 0, amb.getService(), amb.getLatency(), amb.getDataRate(), amb.getReliability(), amb.getAvailability(), amb.getBroadband(), amb.getMobility(), amb.getCapacity(), amb.getDensity(), amb.getSlicing(), amb.getSecurity());
					else
						db.insertIntoDB(amb.getName(), amb.getDescription(), amb.getVersion(), amb.getIdentity(), amb.getSector(), amb.getFromCountry(), amb.getCountry(), amb.getDay() + "/" + amb.getMonth() + "/" + amb.getYear(), Double.parseDouble(amb.getHour() + "." + amb.getMinutes()), endTime, amb.getDuration(), 0, amb.getService(), amb.getLatency(), amb.getDataRate(), amb.getReliability(), amb.getAvailability(), amb.getBroadband(), amb.getMobility(), amb.getCapacity(), amb.getDensity(), amb.getSlicing(), amb.getSecurity());
					request.setAttribute("amb", amb);
					request.getRequestDispatcher("ConnAmbConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(amb.getDay() + "/" + amb.getMonth() + "/" + amb.getYear(), Double.parseDouble(amb.getHour() + "." + amb.getMinutes()), amb.getDuration(), "Greece", db);
					System.out.println("av1: " +av1);
					
					if(amb.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + amb.getCountry() + " at " + amb.getHour() + ":0" + amb.getMinutes() + " on " + amb.getDay() + "/" + amb.getMonth() + "/" + amb.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + amb.getCountry() + " at " + amb.getHour() + ":" + amb.getMinutes() + " on " + amb.getDay() + "/" + amb.getMonth() + "/" + amb.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + amb.getDay() + "/" + amb.getMonth() + "/" + amb.getYear() + ".");
					}
					request.setAttribute("amb", amb);
					request.getRequestDispatcher("ConnAmbEdit.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			// -------------------------------UNCOMMENT ABOVE LINES IN CASE OF DATABASE --------------------------------------
			
		}
		//getting values from guided selection
		else if(guided != null) {
			String countryFrom = request.getParameter("countryFromChecked");
			//String dataRateCh = request.getParameter("dataRateChecked");
			//String latencyCh = request.getParameter("latencyChecked");
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
			
			amb.setFromCountry(countryFrom);
			//amb.setDataRate(Integer.parseInt(dataRateCh));
			//amb.setLatency(Integer.parseInt(latencyCh));
			amb.setDay(Integer.parseInt(dayCh));
			amb.setMonth(Integer.parseInt(monthCh));
			amb.setYear(Integer.parseInt(yearCh));
			amb.setHour(Integer.parseInt(hourCh));
			amb.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				amb.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				amb.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				amb.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				amb.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				amb.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				amb.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				amb.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				amb.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				amb.setSlicing(slicingCh);
			if(securityCh != null)
				amb.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				amb.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				amb.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				amb.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					amb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					amb.setMinutes(00);
				}
				else
				{
					amb.setHour(23);
					amb.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(amb.getHour(), amb.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						amb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						amb.setMinutes(00);
					}
					else
					{
						amb.setHour(23);
						amb.setMinutes(59);
					}
				}
			}
	
			if(amb.getMinutes()<10)
				request.setAttribute("minutes", "0" + amb.getMinutes());
			else
				request.setAttribute("minutes", amb.getMinutes());
			if(amb.getHour()<10)
				request.setAttribute("hour", "0" + amb.getHour());
			else
				request.setAttribute("hour", amb.getHour());
			
			request.setAttribute("amb", amb);
			request.getRequestDispatcher("ConnAmbCreated.jsp").forward(request, response);
		}
		//values according to text intention
		//checks if command contains information about the country to run the experiment from
		else {
			
			if(intent.toLowerCase().contains(" from greece"))
				fcount1 = "Greece";
			
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
				request.getRequestDispatcher("ConnAmbFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				amb.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "ConnAmb");
				request.getRequestDispatcher("ConnAmbFromCountry.jsp").forward(request, response);
			}
			
			//checks if command contains information about the latency
			/*if(fcountry == true) {
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
					request.setAttribute("message", "Multiple values for latency provided. Please provide only one value!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("ConnAmbLatency.jsp").forward(request, response);
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
							amb.setLatency(Integer.parseInt(tmp));
							latenc = true;
						}
						else {
							if(Integer.parseInt(tmp)<1) {
								request.setAttribute("message", "Invalid number of latency given!");
							}
							intent = intent.replace("#", " ");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("ConnAmbLatency.jsp").forward(request, response);
						}
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("message", "Invalid number of latency given!");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("ConnAmbLatency.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("ConnAmbLatency.jsp").forward(request, response);
				}
			}
			
			//checks if command contains information about the data rate
			if(latenc == true) {
				int num = 0;
				
				for(int i=0; i<intent.length(); i++) {
					if(intent.charAt(i) == '$')
						num++;
				}
				
				if(num > 1) {
					for(int i=0; i<intent.length(); i++) {
						if(intent.charAt(i) == '$')
							intent = intent.substring(0, i) + ' ' + intent.substring(i+1);
					}
					request.setAttribute("message", "Multiple values for data rate provided. Please provide only one value!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("ConnAmbDataRate.jsp").forward(request, response);
				}
				
				if(intent.toLowerCase().indexOf("$")!=-1) {
					String tmp = "";
					int i = intent.indexOf("$");
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
							amb.setDataRate(Integer.parseInt(tmp));
							dataRat = true;
						}
						else {
							if(Integer.parseInt(tmp)>300 || Integer.parseInt(tmp)<1) {
								request.setAttribute("message", "Not valid number(" + Integer.parseInt(tmp) + ") provided! Please select a number of devices between 1-300.");
							}
							intent = intent.replace("$", " ");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("ConnAmbDataRate.jsp").forward(request, response);
						}
					}
					else {
						intent = intent.replace("$", " ");
						request.setAttribute("message", "Invalid number of devices given!");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("ConnAmbDataRate.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("ConnAmbDataRate.jsp").forward(request, response);
				}
			}*/
			
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
					request.getRequestDispatcher("ConnAmbDate.jsp").forward(request, response);
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
							amb.setDay(Integer.parseInt(day));
						}
						else amb.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
					}
					else
						amb.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
							amb.setMonth(Integer.parseInt(month));
						}
						else
							amb.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
					}
					else
					{
						amb.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
							amb.setYear(Integer.parseInt(year));
						}
						else
							amb.setYear(Calendar.getInstance().get(Calendar.YEAR));
					}
					else
						amb.setYear(Calendar.getInstance().get(Calendar.YEAR));
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String givda = ""+amb.getYear()+"-"+amb.getMonth()+"-"+amb.getDay()+"";
					try {
						Date givdate = sdf.parse(givda);
						Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
						day = Integer.toString(amb.getDay());
						month = Integer.toString(amb.getMonth());
						year = Integer.toString(amb.getYear());
						
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
							request.getRequestDispatcher("ConnAmbDate.jsp").forward(request, response);
						}
						else if(cd.dateCompare(amb.getDay(), amb.getMonth(), amb.getYear()) == 0) {
							
							for(int j=0; j<intent.length(); j++) {
								if(intent.charAt(j) == '/')
									intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
							}
							
							request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
							request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
							request.setAttribute("message", "Date given has wrong number of days.");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("ConnAmbDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("ConnAmbDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("ConnAmbTime.jsp").forward(request, response);
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
							amb.setHour(Integer.parseInt(hour));
						else
							amb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
					}
					else
						amb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
							amb.setMinutes(Integer.parseInt(minutes));
						else
							amb.setMinutes(00);	
					}
					
					if(cd.dateCompare(amb.getDay(), amb.getMonth(), amb.getYear()) == 1) {
						if(ct.timeCompare(amb.getHour(), amb.getMinutes()) == 0) {
							if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
								amb.setHour(00);
								amb.setMinutes(00);
								amb.setDay(amb.getDay()+1);
								time = true;
							}
							else {
								amb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
								amb.setMinutes(00);
								time = true;
							}
						}
						else
						{
							time = true;
						}
					}
					else if(cd.dateCompare(amb.getDay(), amb.getMonth(), amb.getYear()) == 2) {
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
						request.getRequestDispatcher("ConnAmbTime.jsp").forward(request, response);
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
					request.getRequestDispatcher("ConnAmbTime.jsp").forward(request, response);
				}
			}
			
			//redirection to blueprint page
			if(fcountry == true && date == true && time == true) { //dataRat == true && latenc == true &&
				if(amb.getMinutes()<10)
					request.setAttribute("minutes", "0" + amb.getMinutes());
				else
					request.setAttribute("minutes", amb.getMinutes());
				if(amb.getHour()<10)
					request.setAttribute("hour", "0" + amb.getHour());
				else
					request.setAttribute("hour", amb.getHour());
				
				request.setAttribute("amb", amb);
				request.getRequestDispatcher("ConnAmbCreated.jsp").forward(request, response);
			}
		}
		
		
	}

}
