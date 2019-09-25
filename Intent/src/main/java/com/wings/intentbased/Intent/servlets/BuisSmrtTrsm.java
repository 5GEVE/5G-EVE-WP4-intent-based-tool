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

import blueprints.BuisSmrtTrsmBp;

/**
 * Servlet implementation class BuisSmrtTrsm
 */
public class BuisSmrtTrsm extends HttpServlet {
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
		boolean band = false;
		boolean lat = false;
		boolean date = false;
		boolean time = false;
		String fcount1 = null, fcount2 = null;
		
		//getting parameters from browser page
		String guided = request.getParameter("guided");
		String submit = request.getParameter("submit");
		String intent = request.getParameter("intent");
		String fromCountry = request.getParameter("fromCountry");
		String latency = request.getParameter("latency");
		String bandwidth = request.getParameter("bandwidth");
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
		
		if(bandwidth != null)
			intent = intent + " $" + bandwidth + " ";
		
		if(latency != null)
			intent = intent + " #" + latency + " ";
		
		//creation of blueprint and instantiation of some values
		BuisSmrtTrsmBp bus = new BuisSmrtTrsmBp();
		bus.setName("Buiseness Augmented Booth");
		bus.setDescription("Implementation of an Augmented Fair Service with AR Interaction allowing enhanced physical experience of both exhibitors and visitors");
		bus.setVersion(1.0);
		bus.setIdentity("BP Identity");
		bus.setSector("Smart Tourism");
		bus.setService("URLLC & eMBB");
		bus.setCountry("Spain");
		bus.setLatency(100);
		bus.setDataRate(100);
		bus.setReliability(99);
		bus.setAvailability(99);
		bus.setMobility(10);
		bus.setBroadband(30000);
		bus.setCapacity(18.75);
		bus.setDensity(187500);
		bus.setSlicing("Y");
		bus.setSecurity("N");
		bus.setDuration(80);
		
		//cancel button is pressed
		if(guided!=null && submit.equalsIgnoreCase("cancel")) {
			request.getRequestDispatcher("IntentPage.jsp").forward(request, response);
		}
		//edit button is pressed
		//update of values according to edit values
		else if(guided != null && submit.equalsIgnoreCase("edit")) {
			String countryFrom = request.getParameter("countryFromChecked");
			//String latenc = request.getParameter("latencyChecked");
			//String bandwdth = request.getParameter("bandwidthChecked");
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
			
			bus.setFromCountry(countryFrom);

			//bus.setLatency(Integer.parseInt(latenc));
			//bus.setBandwidth(Integer.parseInt(bandwdth));
			bus.setDay(Integer.parseInt(dayCh));
			bus.setMonth(Integer.parseInt(monthCh));
			bus.setYear(Integer.parseInt(yearCh));
			bus.setHour(Integer.parseInt(hourCh));
			bus.setMinutes(Integer.parseInt(minutesCh));
			bus.setLatency(Integer.parseInt(latencyCh));
			bus.setDataRate(Integer.parseInt(dataRateCh));
			bus.setReliability(Double.parseDouble(reliabilityCh));
			bus.setAvailability(Double.parseDouble(availabilityCh));
			bus.setBroadband(Double.parseDouble(broadbandCh));
			bus.setMobility(Integer.parseInt(mobilityCh));
			bus.setCapacity(Double.parseDouble(capacityCh));
			bus.setDensity(Integer.parseInt(densityCh));
			bus.setSlicing(slicingCh);
			bus.setSecurity(securityCh);
			
			if(bus.getMinutes()<10)
				request.setAttribute("minutes", "0" + bus.getMinutes());
			else
				request.setAttribute("minutes", bus.getMinutes());
			if(bus.getHour()<10)
				request.setAttribute("hour", "0" + bus.getHour());
			else
				request.setAttribute("hour", bus.getHour());
			
			request.setAttribute("bus", bus);
			request.getRequestDispatcher("BuisSmrtTrsmEdit.jsp").forward(request, response);
		}
		//confirmation of blueprint
		//insert of experiment in database if slot available else redirection to edit page
		else if(guided != null && submit.equalsIgnoreCase("confirm")) {
			String countryFrom = request.getParameter("countryFromChecked");
			//String latenc = request.getParameter("latencyChecked");
			//String bandwdth = request.getParameter("bandwidthChecked");
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
			
			bus.setFromCountry(countryFrom);

			//bus.setLatency(Integer.parseInt(latenc));
			//bus.setBandwidth(Integer.parseInt(bandwdth));
			bus.setDay(Integer.parseInt(dayCh));
			bus.setMonth(Integer.parseInt(monthCh));
			bus.setYear(Integer.parseInt(yearCh));
			bus.setHour(Integer.parseInt(hourCh));
			bus.setMinutes(Integer.parseInt(minutesCh));
			bus.setLatency(Integer.parseInt(latencyCh));
			bus.setDataRate(Integer.parseInt(dataRateCh));
			bus.setReliability(Double.parseDouble(reliabilityCh));
			bus.setAvailability(Double.parseDouble(availabilityCh));
			bus.setBroadband(Double.parseDouble(broadbandCh));
			bus.setMobility(Integer.parseInt(mobilityCh));
			bus.setCapacity(Double.parseDouble(capacityCh));
			bus.setDensity(Integer.parseInt(densityCh));
			bus.setSlicing(slicingCh);
			bus.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				bus.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				bus.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				bus.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					bus.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					bus.setMinutes(00);
				}
				else
				{
					bus.setHour(23);
					bus.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(bus.getHour(), bus.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						bus.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						bus.setMinutes(00);
					}
					else
					{
						bus.setHour(23);
						bus.setMinutes(59);
					}
				}
			}
	
			if(bus.getMinutes()<10)
				request.setAttribute("minutes", "0" + bus.getMinutes());
			else
				request.setAttribute("minutes", bus.getMinutes());
			if(bus.getHour()<10)
				request.setAttribute("hour", "0" + bus.getHour());
			else
				request.setAttribute("hour", bus.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("bus", bus);
			request.getRequestDispatcher("BuisSmrtTrsmConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(bus.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + bus.getDay() + "/" + bus.getMonth() + "/" + bus.getYear() +"' AND launchedTo='" + bus.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(bus.getHour() + ".0" + bus.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(bus.getHour() + ".0" + bus.getMinutes()), bus.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + bus.getDay() + "/" + bus.getMonth() + "/" + bus.getYear() +"' AND launchedTo='" + bus.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(bus.getHour() + "." + bus.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(bus.getHour() + "." + bus.getMinutes()), bus.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(bus.getMinutes() < 10)
				dtime = Double.parseDouble(bus.getHour() + ".0" + bus.getMinutes());
			else
				dtime = Double.parseDouble(bus.getHour() + "." + bus.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(bus.getDay() + "/" + bus.getMonth() + "/" + bus.getYear(), dtime, bus.getDuration(), bus.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, bus.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(bus.getMinutes() < 10)
						db.insertIntoDB(bus.getName(), bus.getDescription(), bus.getVersion(), bus.getIdentity(), bus.getSector(), bus.getFromCountry(), bus.getCountry(), bus.getDay() + "/" + bus.getMonth() + "/" + bus.getYear(), Double.parseDouble(bus.getHour() + ".0" + bus.getMinutes()), endTime, bus.getDuration(), 0, bus.getService(), bus.getLatency(), bus.getDataRate(), bus.getReliability(), bus.getAvailability(), bus.getBroadband(), bus.getMobility(), bus.getCapacity(), bus.getDensity(), bus.getSlicing(), bus.getSecurity());
					else
						db.insertIntoDB(bus.getName(), bus.getDescription(), bus.getVersion(), bus.getIdentity(), bus.getSector(), bus.getFromCountry(), bus.getCountry(), bus.getDay() + "/" + bus.getMonth() + "/" + bus.getYear(), Double.parseDouble(bus.getHour() + "." + bus.getMinutes()), endTime, bus.getDuration(), 0, bus.getService(), bus.getLatency(), bus.getDataRate(), bus.getReliability(), bus.getAvailability(), bus.getBroadband(), bus.getMobility(), bus.getCapacity(), bus.getDensity(), bus.getSlicing(), bus.getSecurity());
					request.setAttribute("bus", bus);
					request.getRequestDispatcher("BuisSmrtTrsmConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(bus.getDay() + "/" + bus.getMonth() + "/" + bus.getYear(), Double.parseDouble(bus.getHour() + "." + bus.getMinutes()), bus.getDuration(), "Spain", db);
					System.out.println("av1: " +av1);
					
					if(bus.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + bus.getCountry() + " at " + bus.getHour() + ":0" + bus.getMinutes() + " on " + bus.getDay() + "/" + bus.getMonth() + "/" + bus.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + bus.getCountry() + " at " + bus.getHour() + ":" + bus.getMinutes() + " on " + bus.getDay() + "/" + bus.getMonth() + "/" + bus.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + bus.getDay() + "/" + bus.getMonth() + "/" + bus.getYear() + ".");
					}
					request.setAttribute("bus", bus);
					request.getRequestDispatcher("BuisSmrtTrsmEdit.jsp").forward(request, response);
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
			//String latenc = request.getParameter("latencyChecked");
			//String bandwdth = request.getParameter("bandwidthChecked");
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
			
			bus.setFromCountry(countryFrom);

			//bus.setLatency(Integer.parseInt(latenc));
			//bus.setBandwidth(Integer.parseInt(bandwdth));
			bus.setDay(Integer.parseInt(dayCh));
			bus.setMonth(Integer.parseInt(monthCh));
			bus.setYear(Integer.parseInt(yearCh));
			bus.setHour(Integer.parseInt(hourCh));
			bus.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				bus.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				bus.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				bus.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				bus.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				bus.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				bus.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				bus.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				bus.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				bus.setSlicing(slicingCh);
			if(securityCh != null)
				bus.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				bus.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				bus.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				bus.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					bus.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					bus.setMinutes(00);
				}
				else
				{
					bus.setHour(23);
					bus.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(bus.getHour(), bus.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						bus.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						bus.setMinutes(00);
					}
					else
					{
						bus.setHour(23);
						bus.setMinutes(59);
					}
				}
			}
	
			if(bus.getMinutes()<10)
				request.setAttribute("minutes", "0" + bus.getMinutes());
			else
				request.setAttribute("minutes", bus.getMinutes());
			if(bus.getHour()<10)
				request.setAttribute("hour", "0" + bus.getHour());
			else
				request.setAttribute("hour", bus.getHour());
			
			request.setAttribute("bus", bus);
			request.getRequestDispatcher("BuisSmrtTrsmCreated.jsp").forward(request, response);
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
				request.getRequestDispatcher("BuisSmrtTrsmFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				bus.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "BuisSmrtTrsm");
				request.getRequestDispatcher("BuisSmrtTrsmFromCountry.jsp").forward(request, response);
			
			}
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
				request.getRequestDispatcher("BuisSmrtTrsmLatency.jsp").forward(request, response);
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
					if(Integer.parseInt(tmp)>0 && Integer.parseInt(tmp)<=200) {
						bus.setLatency(Integer.parseInt(tmp));
						lat = true;
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("message", "Invalid number of latency given: " + tmp);
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("BuisSmrtTrsmLatency.jsp").forward(request, response);
					}
				}
				else {
					intent = intent.replace("#", " ");
					request.setAttribute("message", "Invalid number of latency given!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("BuisSmrtTrsmLatency.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("BuisSmrtTrsmLatency.jsp").forward(request, response);
			}
		}
		
		//checks if command contains information about the bandwidth
		if(lat == true) {
			
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
				request.setAttribute("message", "Multiple values for bandwidth provided. Please provide only one value!");
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("BuisSmrtTrsmBandwidth.jsp").forward(request, response);
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
					if(Integer.parseInt(tmp)>49) {
						bus.setBandwidth(Integer.parseInt(tmp));
						band = true;
					}
					else if(Integer.parseInt(tmp) < 50) {
						intent = intent.replace("$", " ");
						request.setAttribute("message", "Invalid bandwidth value provided: " + tmp);
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("BuisSmrtTrsmBandwidth.jsp").forward(request, response);
					}
				}
				else{
					intent = intent.replace("$", " ");
					request.setAttribute("message", "Invalid bandwidth value provided!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("BuisSmrtTrsmBandwidth.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("BuisSmrtTrsmBandwidth.jsp").forward(request, response);
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
				request.getRequestDispatcher("BuisSmrtTrsmDate.jsp").forward(request, response);
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
						bus.setDay(Integer.parseInt(day));
					}
					else bus.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					
				}
				else
					bus.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
						bus.setMonth(Integer.parseInt(month));
					}
					else
						bus.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				}
				else
				{
					bus.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
						bus.setYear(Integer.parseInt(year));
					}
					else
						bus.setYear(Calendar.getInstance().get(Calendar.YEAR));
				}
				else
					bus.setYear(Calendar.getInstance().get(Calendar.YEAR));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String givda = ""+bus.getYear()+"-"+bus.getMonth()+"-"+bus.getDay()+"";
				try {
					Date givdate = sdf.parse(givda);
					Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					
					day = Integer.toString(bus.getDay());
					month = Integer.toString(bus.getMonth());
					year = Integer.toString(bus.getYear());
					
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
						request.getRequestDispatcher("BuisSmrtTrsmDate.jsp").forward(request, response);
					}
					else if(cd.dateCompare(bus.getDay(), bus.getMonth(), bus.getYear()) == 0) {
						
						for(int j=0; j<intent.length(); j++) {
							if(intent.charAt(j) == '/')
								intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
						}
						
						request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
						request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
						request.setAttribute("message", "Date given has wrong number of days.");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("BuisSmrtTrsmDate.jsp").forward(request, response);
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
				request.getRequestDispatcher("BuisSmrtTrsmDate.jsp").forward(request, response);
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
				request.getRequestDispatcher("BuisSmrtTrsmTime.jsp").forward(request, response);
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
						bus.setHour(Integer.parseInt(hour));
					else
						bus.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
				}
				else
					bus.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
						bus.setMinutes(Integer.parseInt(minutes));
					else
						bus.setMinutes(00);	
				}
				
				if(cd.dateCompare(bus.getDay(), bus.getMonth(), bus.getYear()) == 1) {
					if(ct.timeCompare(bus.getHour(), bus.getMinutes()) == 0) {
						if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
							bus.setHour(00);
							bus.setMinutes(00);
							bus.setDay(bus.getDay()+1);
							time = true;
						}
						else {
							bus.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
							bus.setMinutes(00);
							time = true;
						}
					}
					else
					{
						time = true;
					}
				}
				else if(cd.dateCompare(bus.getDay(), bus.getMonth(), bus.getYear()) == 2) {
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
					request.getRequestDispatcher("BuisSmrtTrsmTime.jsp").forward(request, response);
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
				request.getRequestDispatcher("BuisSmrtTrsmTime.jsp").forward(request, response);
			}
		}
		
		//redirection to blueprint page
		if(fcountry == true && date == true && time == true) { //lat == true && band == true &&
			if(bus.getMinutes()<10)
				request.setAttribute("minutes", "0" + bus.getMinutes());
			else
				request.setAttribute("minutes", bus.getMinutes());
			if(bus.getHour()<10)
				request.setAttribute("hour", "0" + bus.getHour());
			else
				request.setAttribute("hour", bus.getHour());
			
			request.setAttribute("bus", bus);
			request.getRequestDispatcher("BuisSmrtTrsmCreated.jsp").forward(request, response);
		}
	}

}
