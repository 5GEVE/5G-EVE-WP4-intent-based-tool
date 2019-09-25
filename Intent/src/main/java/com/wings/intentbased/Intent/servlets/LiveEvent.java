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
import blueprints.LiveEventBp;

/**
 * Servlet implementation class LiveEvent
 */
public class LiveEvent extends HttpServlet {
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
		
		boolean cellband = false;
		boolean uplink = false;
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
		String cellBand = request.getParameter("cellBand");
		String upLink = request.getParameter("upLink");
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
		
		if(cellBand != null)
			intent = intent + " $" + cellBand + " ";
		
		if(upLink != null)
			intent = intent + " #" + upLink + " ";
		
		if(fromCountry != null)
			intent = intent + " " + fromCountry + " ";
		
		//creation of blueprint and instantiation of some values
		LiveEventBp live = new LiveEventBp();
		live.setName("On-Site Live Event Experience");
		live.setDescription("TV events in on-site places like Stadiums, car Racing, Sport Events, etc");
		live.setVersion(1.0);
		live.setIdentity("BP Identity");
		live.setSector("Media & Entertainment");
		live.setService("eMBB");
		live.setCountry("Spain");
		live.setLatency(100);
		live.setDataRate(114);
		live.setReliability(99.99);
		live.setAvailability(99.99);
		live.setMobility(0);
		live.setBroadband(122);
		live.setCapacity(12150);
		live.setSlicing("Y");
		live.setSecurity("N");
		live.setDuration(80);
		
		//cancel button is pressed
		if(guided!=null && submit.equalsIgnoreCase("cancel")) {
			request.getRequestDispatcher("IntentPage.jsp").forward(request, response);
		}
		//edit button is pressed
		//update of values according to edit values
		else if(guided != null && submit.equalsIgnoreCase("edit")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			//String cellBandCh = request.getParameter("cellBandChecked");
			//String upLinkCh = request.getParameter("upLinkChecked");
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
			
			live.setFromCountry(countryFrom);

			//live.setCellBand(Integer.parseInt(cellBandCh));
			//live.setUpLink(Integer.parseInt(upLinkCh));
			live.setDay(Integer.parseInt(dayCh));
			live.setMonth(Integer.parseInt(monthCh));
			live.setYear(Integer.parseInt(yearCh));
			live.setHour(Integer.parseInt(hourCh));
			live.setMinutes(Integer.parseInt(minutesCh));
			live.setLatency(Integer.parseInt(latencyCh));
			live.setDataRate(Integer.parseInt(dataRateCh));
			live.setReliability(Double.parseDouble(reliabilityCh));
			live.setAvailability(Double.parseDouble(availabilityCh));
			live.setBroadband(Double.parseDouble(broadbandCh));
			live.setMobility(Integer.parseInt(mobilityCh));
			live.setCapacity(Double.parseDouble(capacityCh));
			live.setDensity(Integer.parseInt(densityCh));
			live.setSlicing(slicingCh);
			live.setSecurity(securityCh);
			
			if(live.getMinutes()<10)
				request.setAttribute("minutes", "0" + live.getMinutes());
			else
				request.setAttribute("minutes", live.getMinutes());
			if(live.getHour()<10)
				request.setAttribute("hour", "0" + live.getHour());
			else
				request.setAttribute("hour", live.getHour());
			
			request.setAttribute("live", live);
			request.getRequestDispatcher("LiveEventEdit.jsp").forward(request, response);
		}
		//confirmation of blueprint
		//insert of experiment in database if slot available else redirection to edit page
		else if(guided != null && submit.equalsIgnoreCase("confirm")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			//String cellBandCh = request.getParameter("cellBandChecked");
			//String upLinkCh = request.getParameter("upLinkChecked");
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
			
			live.setFromCountry(countryFrom);

			//live.setCellBand(Integer.parseInt(cellBandCh));
			//live.setUpLink(Integer.parseInt(upLinkCh));
			live.setDay(Integer.parseInt(dayCh));
			live.setMonth(Integer.parseInt(monthCh));
			live.setYear(Integer.parseInt(yearCh));
			live.setHour(Integer.parseInt(hourCh));
			live.setMinutes(Integer.parseInt(minutesCh));
			live.setLatency(Integer.parseInt(latencyCh));
			live.setDataRate(Integer.parseInt(dataRateCh));
			live.setReliability(Double.parseDouble(reliabilityCh));
			live.setAvailability(Double.parseDouble(availabilityCh));
			live.setBroadband(Double.parseDouble(broadbandCh));
			live.setMobility(Integer.parseInt(mobilityCh));
			live.setCapacity(Double.parseDouble(capacityCh));
			live.setDensity(Integer.parseInt(densityCh));
			live.setSlicing(slicingCh);
			live.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				live.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				live.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				live.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					live.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					live.setMinutes(00);
				}
				else
				{
					live.setHour(23);
					live.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(live.getHour(), live.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						live.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						live.setMinutes(00);
					}
					else
					{
						live.setHour(23);
						live.setMinutes(59);
					}
				}
			}
	
			if(live.getMinutes()<10)
				request.setAttribute("minutes", "0" + live.getMinutes());
			else
				request.setAttribute("minutes", live.getMinutes());
			if(live.getHour()<10)
				request.setAttribute("hour", "0" + live.getHour());
			else
				request.setAttribute("hour", live.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("live", live);
			request.getRequestDispatcher("LiveEventConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(live.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + live.getDay() + "/" + live.getMonth() + "/" + live.getYear() +"' AND launchedTo='" + live.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(live.getHour() + ".0" + live.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(live.getHour() + ".0" + live.getMinutes()), live.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + live.getDay() + "/" + live.getMonth() + "/" + live.getYear() +"' AND launchedTo='" + live.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(live.getHour() + "." + live.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(live.getHour() + "." + live.getMinutes()), live.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(live.getMinutes() < 10)
				dtime = Double.parseDouble(live.getHour() + ".0" + live.getMinutes());
			else
				dtime = Double.parseDouble(live.getHour() + "." + live.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(live.getDay() + "/" + live.getMonth() + "/" + live.getYear(), dtime, live.getDuration(), live.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, live.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(live.getMinutes() < 10)
						db.insertIntoDB(live.getName(), live.getDescription(), live.getVersion(), live.getIdentity(), live.getSector(), live.getFromCountry(), live.getCountry(), live.getDay() + "/" + live.getMonth() + "/" + live.getYear(), Double.parseDouble(live.getHour() + ".0" + live.getMinutes()), endTime, live.getDuration(), 0, live.getService(), live.getLatency(), live.getDataRate(), live.getReliability(), live.getAvailability(), live.getBroadband(), live.getMobility(), live.getCapacity(), live.getDensity(), live.getSlicing(), live.getSecurity());
					else
						db.insertIntoDB(live.getName(), live.getDescription(), live.getVersion(), live.getIdentity(), live.getSector(), live.getFromCountry(), live.getCountry(), live.getDay() + "/" + live.getMonth() + "/" + live.getYear(), Double.parseDouble(live.getHour() + "." + live.getMinutes()), endTime, live.getDuration(), 0, live.getService(), live.getLatency(), live.getDataRate(), live.getReliability(), live.getAvailability(), live.getBroadband(), live.getMobility(), live.getCapacity(), live.getDensity(), live.getSlicing(), live.getSecurity());
					request.setAttribute("live", live);
					request.getRequestDispatcher("LiveEventConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(live.getDay() + "/" + live.getMonth() + "/" + live.getYear(), Double.parseDouble(live.getHour() + "." + live.getMinutes()), live.getDuration(), "Spain", db);
					System.out.println("av1: " +av1);
					
					if(live.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + live.getCountry() + " at " + live.getHour() + ":0" + live.getMinutes() + " on " + live.getDay() + "/" + live.getMonth() + "/" + live.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + live.getCountry() + " at " + live.getHour() + ":" + live.getMinutes() + " on " + live.getDay() + "/" + live.getMonth() + "/" + live.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + live.getDay() + "/" + live.getMonth() + "/" + live.getYear() + ".");
					}
					request.setAttribute("live", live);
					request.getRequestDispatcher("LiveEventEdit.jsp").forward(request, response);
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
			//String cellBandCh = request.getParameter("cellBandChecked");
			//String upLinkCh = request.getParameter("upLinkChecked");
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
			
			live.setFromCountry(countryFrom);

			//live.setCellBand(Integer.parseInt(cellBandCh));
			//live.setUpLink(Integer.parseInt(upLinkCh));
			live.setDay(Integer.parseInt(dayCh));
			live.setMonth(Integer.parseInt(monthCh));
			live.setYear(Integer.parseInt(yearCh));
			live.setHour(Integer.parseInt(hourCh));
			live.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				live.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				live.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				live.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				live.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				live.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				live.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				live.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				live.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				live.setSlicing(slicingCh);
			if(securityCh != null)
				live.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				live.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				live.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				live.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					live.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					live.setMinutes(00);
				}
				else
				{
					live.setHour(23);
					live.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(live.getHour(), live.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						live.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						live.setMinutes(00);
					}
					else
					{
						live.setHour(23);
						live.setMinutes(59);
					}
				}
			}
	
			if(live.getMinutes()<10)
				request.setAttribute("minutes", "0" + live.getMinutes());
			else
				request.setAttribute("minutes", live.getMinutes());
			if(live.getHour()<10)
				request.setAttribute("hour", "0" + live.getHour());
			else
				request.setAttribute("hour", live.getHour());
			
			request.setAttribute("live", live);
			request.getRequestDispatcher("LiveEventCreated.jsp").forward(request, response);
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
				request.getRequestDispatcher("LiveEventFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				live.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "LiveEvent");
				request.getRequestDispatcher("LiveEventFromCountry.jsp").forward(request, response);
				
			}
			
			//checks if command contains information about the uplink/camera
			/*if(fcountry==true) {
					
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
					request.setAttribute("message", "Multiple values for uplink/camera provided. Please provide only one value!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("LiveEventUplink.jsp").forward(request, response);
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
						if(Integer.parseInt(tmp)>1 && Integer.parseInt(tmp)<11) {
							live.setUpLink(Integer.parseInt(tmp));
							uplink = true;
						}
						else {
							intent = intent.replace("#", " ");
							request.setAttribute("message", "Invalid number of uplink/camera given: " + tmp);
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("LiveEventUplink.jsp").forward(request, response);
						}
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("message", "Invalid number of uplink/camera given!");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("LiveEventUplink.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("LiveEventUplink.jsp").forward(request, response);
				}
				}
				
				//checks if command contains information about the bandwidth/mobile cell
				if(uplink == true) {
					
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
						request.setAttribute("message", "Multiple values for bandwidth/mobile cell provided. Please provide only one value!");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("LiveEventBandwidth.jsp").forward(request, response);
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
							if(Double.parseDouble(tmp)>39 && Integer.parseInt(tmp)<81) {
								live.setCellBand(Integer.parseInt(tmp));
								cellband = true;
							}
							else {
								intent = intent.replace("$", " ");
								request.setAttribute("intent", intent);
								request.getRequestDispatcher("LiveEventBandwidth.jsp").forward(request, response);
							}
						}
						else {
							intent = intent.replace("#", " ");
							request.setAttribute("message", "Invalid number of bandwidth/mobile cell given!");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("LiveEventBandwidth.jsp").forward(request, response);
						}
					}
					else
					{
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("LiveEventBandwidth.jsp").forward(request, response);
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
						request.getRequestDispatcher("LiveEventDate.jsp").forward(request, response);
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
								live.setDay(Integer.parseInt(day));
							}
							else live.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							
						}
						else
							live.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
								live.setMonth(Integer.parseInt(month));
							}
							else
								live.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
						}
						else
						{
							live.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
								live.setYear(Integer.parseInt(year));
							}
							else
								live.setYear(Calendar.getInstance().get(Calendar.YEAR));
						}
						else
							live.setYear(Calendar.getInstance().get(Calendar.YEAR));

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String givda = ""+live.getYear()+"-"+live.getMonth()+"-"+live.getDay()+"";
						try {
							Date givdate = sdf.parse(givda);
							Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							
							day = Integer.toString(live.getDay());
							month = Integer.toString(live.getMonth());
							year = Integer.toString(live.getYear());
							
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
								request.getRequestDispatcher("LiveEventDate.jsp").forward(request, response);
							}
							else if(cd.dateCompare(live.getDay(), live.getMonth(), live.getYear()) == 0) {
								
								for(int j=0; j<intent.length(); j++) {
									if(intent.charAt(j) == '/')
										intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
								}
								
								request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
								request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
								request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
								request.setAttribute("message", "Date given has wrong number of days.");
								request.setAttribute("intent", intent);
								request.getRequestDispatcher("LiveEventDate.jsp").forward(request, response);
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
						request.getRequestDispatcher("LiveEventDate.jsp").forward(request, response);
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
						request.getRequestDispatcher("LiveEventTime.jsp").forward(request, response);
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
								live.setHour(Integer.parseInt(hour));
							else
								live.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
						}
						else
							live.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
								live.setMinutes(Integer.parseInt(minutes));
							else
								live.setMinutes(00);	
						}
						
						if(cd.dateCompare(live.getDay(), live.getMonth(), live.getYear()) == 1) {
							if(ct.timeCompare(live.getHour(), live.getMinutes()) == 0) {
								if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
									live.setHour(00);
									live.setMinutes(00);
									live.setDay(live.getDay()+1);
									time = true;
								}
								else {
									live.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
									live.setMinutes(00);
									time = true;
								}
							}
							else
							{
								time = true;
							}
						}
						else if(cd.dateCompare(live.getDay(), live.getMonth(), live.getYear()) == 2) {
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
							request.getRequestDispatcher("LiveEventTime.jsp").forward(request, response);
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
						request.getRequestDispatcher("LiveEventTime.jsp").forward(request, response);
					}
				}
				
				//redirection to blueprint page
				if(fcountry == true && date == true && time == true) { //cellband == true && uplink == true && 
					if(live.getMinutes()<10)
						request.setAttribute("minutes", "0" + live.getMinutes());
					else
						request.setAttribute("minutes", live.getMinutes());
					if(live.getHour()<10)
						request.setAttribute("hour", "0" + live.getHour());
					else
					
					request.setAttribute("live", live);
					request.getRequestDispatcher("LiveEventCreated.jsp").forward(request, response);
				}
			}
	}

}
