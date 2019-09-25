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

import blueprints.ArSmrtTrsmBp;

/**
 * Servlet implementation class ArSmrtTrsm
 */
public class ArSmrtTrsm extends HttpServlet {
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
			intent = intent + " " + nday+"/" + nmonth + "/" + nyear + " ";
		
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
		ArSmrtTrsmBp trsm = new ArSmrtTrsmBp();
		trsm.setName("AR Interaction");
		trsm.setDescription("Implementation of AR Technologies");
		trsm.setVersion(1.0);
		trsm.setIdentity("BP Identity");
		trsm.setSector("Smart Tourism");
		trsm.setService("eMBB");
		trsm.setCountry("Spain");
		trsm.setLatency(100);
		trsm.setDataRate(100);
		trsm.setReliability(99);
		trsm.setAvailability(99);
		trsm.setMobility(10);
		trsm.setBroadband(30000);
		trsm.setCapacity(18.75);
		trsm.setDensity(187500);
		trsm.setSlicing("Y");
		trsm.setSecurity("N");
		trsm.setDuration(80);
		
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
			
			trsm.setFromCountry(countryFrom);

			//trsm.setLatency(Integer.parseInt(latenc));
			//trsm.setBandwidth(Integer.parseInt(bandwdth));
			trsm.setDay(Integer.parseInt(dayCh));
			trsm.setMonth(Integer.parseInt(monthCh));
			trsm.setYear(Integer.parseInt(yearCh));
			trsm.setHour(Integer.parseInt(hourCh));
			trsm.setMinutes(Integer.parseInt(minutesCh));
			trsm.setLatency(Integer.parseInt(latencyCh));
			trsm.setDataRate(Integer.parseInt(dataRateCh));
			trsm.setReliability(Double.parseDouble(reliabilityCh));
			trsm.setAvailability(Double.parseDouble(availabilityCh));
			trsm.setBroadband(Double.parseDouble(broadbandCh));
			trsm.setMobility(Integer.parseInt(mobilityCh));
			trsm.setCapacity(Double.parseDouble(capacityCh));
			trsm.setDensity(Integer.parseInt(densityCh));
			trsm.setSlicing(slicingCh);
			trsm.setSecurity(securityCh);
			
			if(trsm.getMinutes()<10)
				request.setAttribute("minutes", "0" + trsm.getMinutes());
			else
				request.setAttribute("minutes", trsm.getMinutes());
			if(trsm.getHour()<10)
				request.setAttribute("hour", "0" + trsm.getHour());
			else
				request.setAttribute("hour", trsm.getHour());
			
			request.setAttribute("trsm", trsm);
			request.getRequestDispatcher("ArSmrtTrsmEdit.jsp").forward(request, response);
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
			
			trsm.setFromCountry(countryFrom);

			//trsm.setLatency(Integer.parseInt(latenc));
			//trsm.setBandwidth(Integer.parseInt(bandwdth));
			trsm.setDay(Integer.parseInt(dayCh));
			trsm.setMonth(Integer.parseInt(monthCh));
			trsm.setYear(Integer.parseInt(yearCh));
			trsm.setHour(Integer.parseInt(hourCh));
			trsm.setMinutes(Integer.parseInt(minutesCh));
			trsm.setLatency(Integer.parseInt(latencyCh));
			trsm.setDataRate(Integer.parseInt(dataRateCh));
			trsm.setReliability(Double.parseDouble(reliabilityCh));
			trsm.setAvailability(Double.parseDouble(availabilityCh));
			trsm.setBroadband(Double.parseDouble(broadbandCh));
			trsm.setMobility(Integer.parseInt(mobilityCh));
			trsm.setCapacity(Double.parseDouble(capacityCh));
			trsm.setDensity(Integer.parseInt(densityCh));
			trsm.setSlicing(slicingCh);
			trsm.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				trsm.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				trsm.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				trsm.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					trsm.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					trsm.setMinutes(00);
				}
				else
				{
					trsm.setHour(23);
					trsm.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(trsm.getHour(), trsm.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						trsm.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						trsm.setMinutes(00);
					}
					else
					{
						trsm.setHour(23);
						trsm.setMinutes(59);
					}
				}
			}
	
			if(trsm.getMinutes()<10)
				request.setAttribute("minutes", "0" + trsm.getMinutes());
			else
				request.setAttribute("minutes", trsm.getMinutes());
			
			if(trsm.getHour()<10)
				request.setAttribute("hour", "0" + trsm.getHour());
			else
				request.setAttribute("hour", trsm.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("trsm", trsm);
			request.getRequestDispatcher("ArSmrtTrsmConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(trsm.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + trsm.getDay() + "/" + trsm.getMonth() + "/" + trsm.getYear() +"' AND launchedTo='" + trsm.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(trsm.getHour() + ".0" + trsm.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(trsm.getHour() + ".0" + trsm.getMinutes()), trsm.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + trsm.getDay() + "/" + trsm.getMonth() + "/" + trsm.getYear() +"' AND launchedTo='" + trsm.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(trsm.getHour() + "." + trsm.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(trsm.getHour() + "." + trsm.getMinutes()), trsm.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(trsm.getMinutes() < 10)
				dtime = Double.parseDouble(trsm.getHour() + ".0" + trsm.getMinutes());
			else
				dtime = Double.parseDouble(trsm.getHour() + "." + trsm.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(trsm.getDay() + "/" + trsm.getMonth() + "/" + trsm.getYear(), dtime, trsm.getDuration(), trsm.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, trsm.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(trsm.getMinutes() < 10)
						db.insertIntoDB(trsm.getName(), trsm.getDescription(), trsm.getVersion(), trsm.getIdentity(), trsm.getSector(), trsm.getFromCountry(), trsm.getCountry(), trsm.getDay() + "/" + trsm.getMonth() + "/" + trsm.getYear(), Double.parseDouble(trsm.getHour() + ".0" + trsm.getMinutes()), endTime, trsm.getDuration(), 0, trsm.getService(), trsm.getLatency(), trsm.getDataRate(), trsm.getReliability(), trsm.getAvailability(), trsm.getBroadband(), trsm.getMobility(), trsm.getCapacity(), trsm.getDensity(), trsm.getSlicing(), trsm.getSecurity());
					else
						db.insertIntoDB(trsm.getName(), trsm.getDescription(), trsm.getVersion(), trsm.getIdentity(), trsm.getSector(), trsm.getFromCountry(), trsm.getCountry(), trsm.getDay() + "/" + trsm.getMonth() + "/" + trsm.getYear(), Double.parseDouble(trsm.getHour() + "." + trsm.getMinutes()), endTime, trsm.getDuration(), 0, trsm.getService(), trsm.getLatency(), trsm.getDataRate(), trsm.getReliability(), trsm.getAvailability(), trsm.getBroadband(), trsm.getMobility(), trsm.getCapacity(), trsm.getDensity(), trsm.getSlicing(), trsm.getSecurity());
					request.setAttribute("trsm", trsm);
					request.getRequestDispatcher("ArSmrtTrsmConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(trsm.getDay() + "/" + trsm.getMonth() + "/" + trsm.getYear(), Double.parseDouble(trsm.getHour() + "." + trsm.getMinutes()), trsm.getDuration(), "Spain", db);
					System.out.println("av1: " +av1);
					
					if(trsm.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + trsm.getCountry() + " at " + trsm.getHour() + ":0" + trsm.getMinutes() + " on " + trsm.getDay() + "/" + trsm.getMonth() + "/" + trsm.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + trsm.getCountry() + " at " + trsm.getHour() + ":" + trsm.getMinutes() + " on " + trsm.getDay() + "/" + trsm.getMonth() + "/" + trsm.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + trsm.getDay() + "/" + trsm.getMonth() + "/" + trsm.getYear() + ".");
					}
					request.setAttribute("trsm", trsm);
					request.getRequestDispatcher("ArSmrtTrsmEdit.jsp").forward(request, response);
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
			
			trsm.setFromCountry(countryFrom);

			//trsm.setLatency(Integer.parseInt(latenc));
			//trsm.setBandwidth(Integer.parseInt(bandwdth));
			trsm.setDay(Integer.parseInt(dayCh));
			trsm.setMonth(Integer.parseInt(monthCh));
			trsm.setYear(Integer.parseInt(yearCh));
			trsm.setHour(Integer.parseInt(hourCh));
			trsm.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				trsm.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				trsm.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				trsm.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				trsm.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				trsm.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				trsm.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				trsm.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				trsm.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				trsm.setSlicing(slicingCh);
			if(securityCh != null)
				trsm.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				trsm.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				trsm.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				trsm.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					trsm.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					trsm.setMinutes(00);
				}
				else
				{
					trsm.setHour(23);
					trsm.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(trsm.getHour(), trsm.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						trsm.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						trsm.setMinutes(00);
					}
					else
					{
						trsm.setHour(23);
						trsm.setMinutes(59);
					}
				}
			}
	
			if(trsm.getMinutes()<10)
				request.setAttribute("minutes", "0" + trsm.getMinutes());
			else
				request.setAttribute("minutes", trsm.getMinutes());
			if(trsm.getHour()<10)
				request.setAttribute("hour", "0" + trsm.getHour());
			else
				request.setAttribute("hour", trsm.getHour());
			
			request.setAttribute("trsm", trsm);
			request.getRequestDispatcher("ArSmrtTrsmCreated.jsp").forward(request, response);
		}
		//values according to text intention
		//checks if command contains information about the country to run the experiment
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
				request.getRequestDispatcher("ArSmrtTrsmFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				trsm.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "ArSmrtTrsm");
				request.getRequestDispatcher("ArSmrtTrsmFromCountry.jsp").forward(request, response);
			
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
				request.getRequestDispatcher("ArSmrtTrsmLatency.jsp").forward(request, response);
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
						trsm.setLatency(Integer.parseInt(tmp));
						lat = true;
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("message", "Invalid number of latency given: " + tmp);
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("ArSmrtTrsmLatency.jsp").forward(request, response);
					}
				}
				else {
					intent = intent.replace("#", " ");
					request.setAttribute("message", "Invalid number of latency given!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("ArSmrtTrsmLatency.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("ArSmrtTrsmLatency.jsp").forward(request, response);
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
				request.getRequestDispatcher("ArSmrtTrsmBandwidth.jsp").forward(request, response);
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
						trsm.setBandwidth(Integer.parseInt(tmp));
						band = true;
					}
					else if(Integer.parseInt(tmp) < 50) {
						intent = intent.replace("$", " ");
						request.setAttribute("message", "Invalid bandwidth value provided: " + tmp);
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("ArSmrtTrsmBandwidth.jsp").forward(request, response);
					}
				}
				else{
					intent = intent.replace("$", " ");
					request.setAttribute("message", "Invalid bandwidth value provided!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("ArSmrtTrsmBandwidth.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("ArSmrtTrsmBandwidth.jsp").forward(request, response);
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
				request.getRequestDispatcher("ArSmrtTrsmDate.jsp").forward(request, response);
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
						trsm.setDay(Integer.parseInt(day));
					}
					else trsm.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					
				}
				else
					trsm.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
						trsm.setMonth(Integer.parseInt(month));
					}
					else
						trsm.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				}
				else
				{
					trsm.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
						trsm.setYear(Integer.parseInt(year));
					}
					else
						trsm.setYear(Calendar.getInstance().get(Calendar.YEAR));
				}
				else
					trsm.setYear(Calendar.getInstance().get(Calendar.YEAR));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String givda = ""+trsm.getYear()+"-"+trsm.getMonth()+"-"+trsm.getDay()+"";
				try {
					Date givdate = sdf.parse(givda);
					Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					
					day = Integer.toString(trsm.getDay());
					month = Integer.toString(trsm.getMonth());
					year = Integer.toString(trsm.getYear());
					
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
						request.getRequestDispatcher("ArSmrtTrsmDate.jsp").forward(request, response);
					}
					else if(cd.dateCompare(trsm.getDay(), trsm.getMonth(), trsm.getYear()) == 0) {
						
						for(int j=0; j<intent.length(); j++) {
							if(intent.charAt(j) == '/')
								intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
						}
						
						request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
						request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
						request.setAttribute("message", "Date given has wrong number of days.");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("ArSmrtTrsmDate.jsp").forward(request, response);
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
				request.getRequestDispatcher("ArSmrtTrsmDate.jsp").forward(request, response);
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
				request.getRequestDispatcher("ArSmrtTrsmTime.jsp").forward(request, response);
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
						trsm.setHour(Integer.parseInt(hour));
					else
						trsm.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
				}
				else
					trsm.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
						trsm.setMinutes(Integer.parseInt(minutes));
					else
						trsm.setMinutes(00);	
				}
				
				if(cd.dateCompare(trsm.getDay(), trsm.getMonth(), trsm.getYear()) == 1) {
					if(ct.timeCompare(trsm.getHour(), trsm.getMinutes()) == 0) {
						if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
							trsm.setHour(00);
							trsm.setMinutes(00);
							trsm.setDay(trsm.getDay()+1);
							time = true;
						}
						else {
							trsm.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
							trsm.setMinutes(00);
							time = true;
						}
					}
					else
					{
						System.out.println("Time3: "+trsm.getHour()+":"+trsm.getMinutes());
						time = true;
					}
				}
				else if(cd.dateCompare(trsm.getDay(), trsm.getMonth(), trsm.getYear()) == 2) {
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
					request.getRequestDispatcher("ArSmrtTrsmTime.jsp").forward(request, response);
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
				request.getRequestDispatcher("ArSmrtTrsmTime.jsp").forward(request, response);
			}
		}
		
		//redirection to blueprint page
		if(fcountry == true &&  date == true && time == true) { //lat == true && band == true &&
			if(trsm.getMinutes()<10)
				request.setAttribute("minutes", "0" + trsm.getMinutes());
			else
				request.setAttribute("minutes", trsm.getMinutes());
			if(trsm.getHour()<10)
				request.setAttribute("hour", "0" + trsm.getHour());
			else
				request.setAttribute("hour", trsm.getHour());
			
			request.setAttribute("trsm", trsm);
			request.getRequestDispatcher("ArSmrtTrsmCreated.jsp").forward(request, response);
		}
	}
}
