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

import blueprints.Video360Bp;
import blueprints.Video360Bp;

/**
 * Servlet implementation class Video360
 */
public class Video360 extends HttpServlet {
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
		Video360Bp video = new Video360Bp();
		video.setName("Video 360");
		video.setDescription("Virtual visit over 5G");
		video.setVersion(1.0);
		video.setIdentity("BP Identity");
		video.setSector("Media & Entertainment");
		video.setService("URLLC & eMBB");
		video.setCountry("France");
		video.setLatency(10);
		video.setDataRate(80);
		video.setReliability(99);
		video.setAvailability(99);
		video.setMobility(0);
		video.setBroadband(80);
		video.setCapacity(12150);
		video.setDensity(4000);
		video.setSlicing("N");
		video.setSecurity("N");
		video.setDuration(80);
		
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
			
			video.setFromCountry(countryFrom);

			//video.setLatency(Integer.parseInt(latenc));
			//video.setBandwidth(Integer.parseInt(bandwdth));
			video.setDay(Integer.parseInt(dayCh));
			video.setMonth(Integer.parseInt(monthCh));
			video.setYear(Integer.parseInt(yearCh));
			video.setHour(Integer.parseInt(hourCh));
			video.setMinutes(Integer.parseInt(minutesCh));
			video.setLatency(Integer.parseInt(latencyCh));
			video.setDataRate(Integer.parseInt(dataRateCh));
			video.setReliability(Double.parseDouble(reliabilityCh));
			video.setAvailability(Double.parseDouble(availabilityCh));
			video.setBroadband(Double.parseDouble(broadbandCh));
			video.setMobility(Integer.parseInt(mobilityCh));
			video.setCapacity(Double.parseDouble(capacityCh));
			video.setDensity(Integer.parseInt(densityCh));
			video.setSlicing(slicingCh);
			video.setSecurity(securityCh);
			
			if(video.getMinutes()<10)
				request.setAttribute("minutes", "0" + video.getMinutes());
			else
				request.setAttribute("minutes", video.getMinutes());
			if(video.getHour()<10)
				request.setAttribute("hour", "0" + video.getHour());
			else
				request.setAttribute("hour", video.getHour());
			
			request.setAttribute("video", video);
			request.getRequestDispatcher("Video360Edit.jsp").forward(request, response);
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
			
			video.setFromCountry(countryFrom);

			//video.setLatency(Integer.parseInt(latenc));
			//video.setBandwidth(Integer.parseInt(bandwdth));
			video.setDay(Integer.parseInt(dayCh));
			video.setMonth(Integer.parseInt(monthCh));
			video.setYear(Integer.parseInt(yearCh));
			video.setHour(Integer.parseInt(hourCh));
			video.setMinutes(Integer.parseInt(minutesCh));
			video.setLatency(Integer.parseInt(latencyCh));
			video.setDataRate(Integer.parseInt(dataRateCh));
			video.setReliability(Double.parseDouble(reliabilityCh));
			video.setAvailability(Double.parseDouble(availabilityCh));
			video.setBroadband(Double.parseDouble(broadbandCh));
			video.setMobility(Integer.parseInt(mobilityCh));
			video.setCapacity(Double.parseDouble(capacityCh));
			video.setDensity(Integer.parseInt(densityCh));
			video.setSlicing(slicingCh);
			video.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				video.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				video.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				video.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					video.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					video.setMinutes(00);
				}
				else
				{
					video.setHour(23);
					video.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(video.getHour(), video.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						video.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						video.setMinutes(00);
					}
					else
					{
						video.setHour(23);
						video.setMinutes(59);
					}
				}
			}
	
			if(video.getMinutes()<10)
				request.setAttribute("minutes", "0" + video.getMinutes());
			else
				request.setAttribute("minutes", video.getMinutes());
			if(video.getHour()<10)
				request.setAttribute("hour", "0" + video.getHour());
			else
				request.setAttribute("hour", video.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("video", video);
			request.getRequestDispatcher("Video360Confirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(video.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + video.getDay() + "/" + video.getMonth() + "/" + video.getYear() +"' AND launchedTo='" + video.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(video.getHour() + ".0" + video.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(video.getHour() + ".0" + video.getMinutes()), video.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + video.getDay() + "/" + video.getMonth() + "/" + video.getYear() +"' AND launchedTo='" + video.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(video.getHour() + "." + video.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(video.getHour() + "." + video.getMinutes()), video.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(video.getMinutes() < 10)
				dtime = Double.parseDouble(video.getHour() + ".0" + video.getMinutes());
			else
				dtime = Double.parseDouble(video.getHour() + "." + video.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(video.getDay() + "/" + video.getMonth() + "/" + video.getYear(), dtime, video.getDuration(), video.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, video.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(video.getMinutes() < 10)
						db.insertIntoDB(video.getName(), video.getDescription(), video.getVersion(), video.getIdentity(), video.getSector(), video.getFromCountry(), video.getCountry(), video.getDay() + "/" + video.getMonth() + "/" + video.getYear(), Double.parseDouble(video.getHour() + ".0" + video.getMinutes()), endTime, video.getDuration(), 0, video.getService(), video.getLatency(), video.getDataRate(), video.getReliability(), video.getAvailability(), video.getBroadband(), video.getMobility(), video.getCapacity(), video.getDensity(), video.getSlicing(), video.getSecurity());
					else
						db.insertIntoDB(video.getName(), video.getDescription(), video.getVersion(), video.getIdentity(), video.getSector(), video.getFromCountry(), video.getCountry(), video.getDay() + "/" + video.getMonth() + "/" + video.getYear(), Double.parseDouble(video.getHour() + "." + video.getMinutes()), endTime, video.getDuration(), 0, video.getService(), video.getLatency(), video.getDataRate(), video.getReliability(), video.getAvailability(), video.getBroadband(), video.getMobility(), video.getCapacity(), video.getDensity(), video.getSlicing(), video.getSecurity());
					request.setAttribute("video", video);
					request.getRequestDispatcher("Video360Confirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(video.getDay() + "/" + video.getMonth() + "/" + video.getYear(), Double.parseDouble(video.getHour() + "." + video.getMinutes()), video.getDuration(), "France", db);
					System.out.println("av1: " +av1);
					
					if(video.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + video.getCountry() + " at " + video.getHour() + ":0" + video.getMinutes() + " on " + video.getDay() + "/" + video.getMonth() + "/" + video.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + video.getCountry() + " at " + video.getHour() + ":" + video.getMinutes() + " on " + video.getDay() + "/" + video.getMonth() + "/" + video.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + video.getDay() + "/" + video.getMonth() + "/" + video.getYear() + ".");
					}
					request.setAttribute("video", video);
					request.getRequestDispatcher("Video360Edit.jsp").forward(request, response);
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
			
			video.setFromCountry(countryFrom);

			//video.setLatency(Integer.parseInt(latenc));
			//video.setBandwidth(Integer.parseInt(bandwdth));
			video.setDay(Integer.parseInt(dayCh));
			video.setMonth(Integer.parseInt(monthCh));
			video.setYear(Integer.parseInt(yearCh));
			video.setHour(Integer.parseInt(hourCh));
			video.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				video.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				video.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				video.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				video.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				video.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				video.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				video.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				video.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				video.setSlicing(slicingCh);
			if(securityCh != null)
				video.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				video.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				video.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				video.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					video.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					video.setMinutes(00);
				}
				else
				{
					video.setHour(23);
					video.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(video.getHour(), video.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						video.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						video.setMinutes(00);
					}
					else
					{
						video.setHour(23);
						video.setMinutes(59);
					}
				}
			}
	
			if(video.getMinutes()<10)
				request.setAttribute("minutes", "0" + video.getMinutes());
			else
				request.setAttribute("minutes", video.getMinutes());
			if(video.getHour()<10)
				request.setAttribute("hour", "0" + video.getHour());
			else
				request.setAttribute("hour", video.getHour());
			
			request.setAttribute("video", video);
			request.getRequestDispatcher("Video360Created.jsp").forward(request, response);
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
				request.getRequestDispatcher("Video360FromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				video.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "Video360");
				request.getRequestDispatcher("Video360FromCountry.jsp").forward(request, response);
			
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
				request.getRequestDispatcher("Video360Latency.jsp").forward(request, response);
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
						video.setLatency(Integer.parseInt(tmp));
						lat = true;
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("message", "Invalid number of latency given: " + tmp);
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("Video360Latency.jsp").forward(request, response);
					}
				}
				else {
					intent = intent.replace("#", " ");
					request.setAttribute("message", "Invalid number of latency given!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("Video360Latency.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("Video360Latency.jsp").forward(request, response);
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
				request.getRequestDispatcher("Video360Bandwidth.jsp").forward(request, response);
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
				System.out.println("tmp :" + tmp);
				if(tmp.equalsIgnoreCase("") == false) {
					if(Integer.parseInt(tmp)>49) {
						video.setBandwidth(Integer.parseInt(tmp));
						band = true;
					}
					else if(Integer.parseInt(tmp) < 50) {
						intent = intent.replace("$", " ");
						request.setAttribute("message", "Invalid bandwidth value provided: " + tmp);
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("Video360Bandwidth.jsp").forward(request, response);
					}
				}
				else{
					intent = intent.replace("$", " ");
					request.setAttribute("message", "Invalid bandwidth value provided!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("Video360Bandwidth.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("Video360Bandwidth.jsp").forward(request, response);
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
				request.getRequestDispatcher("Video360Date.jsp").forward(request, response);
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
						video.setDay(Integer.parseInt(day));
					}
					else video.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					
				}
				else
					video.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
						video.setMonth(Integer.parseInt(month));
					}
					else
						video.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				}
				else
				{
					video.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
						video.setYear(Integer.parseInt(year));
					}
					else
						video.setYear(Calendar.getInstance().get(Calendar.YEAR));
				}
				else
					video.setYear(Calendar.getInstance().get(Calendar.YEAR));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String givda = ""+video.getYear()+"-"+video.getMonth()+"-"+video.getDay()+"";
				try {
					Date givdate = sdf.parse(givda);
					Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					
					day = Integer.toString(video.getDay());
					month = Integer.toString(video.getMonth());
					year = Integer.toString(video.getYear());
					
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
						request.getRequestDispatcher("Video360Date.jsp").forward(request, response);
					}
					else if(cd.dateCompare(video.getDay(), video.getMonth(), video.getYear()) == 0) {
						
						for(int j=0; j<intent.length(); j++) {
							if(intent.charAt(j) == '/')
								intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
						}
						
						request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
						request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
						request.setAttribute("message", "Date given has wrong number of days.");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("Video360Date.jsp").forward(request, response);
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
				request.getRequestDispatcher("Video360Date.jsp").forward(request, response);
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
				request.getRequestDispatcher("Video360Time.jsp").forward(request, response);
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
						video.setHour(Integer.parseInt(hour));
					else
						video.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
				}
				else
					video.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
						video.setMinutes(Integer.parseInt(minutes));
					else
						video.setMinutes(00);	
				}
				
				if(cd.dateCompare(video.getDay(), video.getMonth(), video.getYear()) == 1) {
					if(ct.timeCompare(video.getHour(), video.getMinutes()) == 0) {
						if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
							video.setHour(00);
							video.setMinutes(00);
							video.setDay(video.getDay()+1);
							time = true;
						}
						else {
							video.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
							video.setMinutes(00);
							time = true;
						}
					}
					else
					{
						time = true;
					}
				}
				else if(cd.dateCompare(video.getDay(), video.getMonth(), video.getYear()) == 2) {
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
					request.getRequestDispatcher("Video360Time.jsp").forward(request, response);
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
				request.getRequestDispatcher("Video360Time.jsp").forward(request, response);
			}
		}
		
		//redirection to blueprint page
		if(fcountry == true && date == true && time == true) { //lat == true && band == true && 
			if(video.getMinutes()<10)
				request.setAttribute("minutes", "0" + video.getMinutes());
			else
				request.setAttribute("minutes", video.getMinutes());
			if(video.getHour()<10)
				request.setAttribute("hour", "0" + video.getHour());
			else
				request.setAttribute("hour", video.getHour());
			
			request.setAttribute("video", video);
			request.getRequestDispatcher("Video360Created.jsp").forward(request, response);
		}
	}

}
