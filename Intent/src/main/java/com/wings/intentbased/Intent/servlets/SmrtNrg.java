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

import blueprints.SmrtNrgBp;

/**
 * Servlet implementation class SmrtNrg
 */
public class SmrtNrg extends HttpServlet {
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
		
		boolean devs = false;
		boolean dataR = false;
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
		String dataRate = request.getParameter("dataRate");
		String fromCountry = request.getParameter("fromCountry");
		String toCountry = request.getParameter("toCountry");
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
		
		if(dataRate != null)
			intent = intent + " $" + dataRate + " ";
		
		if(toCountry != null)
			intent = intent + " " + toCountry + " ";
		
		if(fromCountry != null)
			intent = intent + " " + fromCountry + " ";
		
		//creation of blueprint and instantiation of some values
		SmrtNrgBp nrg = new SmrtNrgBp();
		nrg.setName("Smart Energy");
		nrg.setDescription("Fault management for distributed electricity generation in smart grids");
		nrg.setVersion(1.0);
		nrg.setIdentity("BP Identity");
		nrg.setSector("Utilities");
		nrg.setService("URLLC/Critical mMTC");
		nrg.setLatency(5);
		nrg.setDataRate(1);
		nrg.setReliability(99.999);
		nrg.setAvailability(99.999);
		nrg.setMobility(0);
		nrg.setBroadband(10);
		nrg.setCapacity(0.1);
		nrg.setDensity(2000);
		nrg.setSlicing("Y");
		nrg.setSecurity("Y");
		nrg.setDuration(80);
		
		//cancel button is pressed
		if(guided!=null && submit.equalsIgnoreCase("cancel")) {
			request.getRequestDispatcher("IntentPage.jsp").forward(request, response);
		}
		//edit button is pressed
		//update of values according to edit values
		else if(guided != null && submit.equalsIgnoreCase("edit")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			//String numOfDevis = request.getParameter("numOfDevsChecked");
			//String dataRat = request.getParameter("dataRateChecked");
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
			
			nrg.setCountry(countryTo);
			nrg.setFromCountry(countryFrom);

			//nrg.setDensity(Integer.parseInt(numOfDevis));
			//nrg.setDataRate(Double.parseDouble(dataRat));
			nrg.setDay(Integer.parseInt(dayCh));
			nrg.setMonth(Integer.parseInt(monthCh));
			nrg.setYear(Integer.parseInt(yearCh));
			nrg.setHour(Integer.parseInt(hourCh));
			nrg.setMinutes(Integer.parseInt(minutesCh));
			nrg.setLatency(Integer.parseInt(latencyCh));
			nrg.setDataRate(Integer.parseInt(dataRateCh));
			nrg.setReliability(Double.parseDouble(reliabilityCh));
			nrg.setAvailability(Double.parseDouble(availabilityCh));
			nrg.setBroadband(Double.parseDouble(broadbandCh));
			nrg.setMobility(Integer.parseInt(mobilityCh));
			nrg.setCapacity(Double.parseDouble(capacityCh));
			nrg.setDensity(Integer.parseInt(densityCh));
			nrg.setSlicing(slicingCh);
			nrg.setSecurity(securityCh);
			
			if(nrg.getMinutes()<10)
				request.setAttribute("minutes", "0" + nrg.getMinutes());
			else
				request.setAttribute("minutes", nrg.getMinutes());
			if(nrg.getHour()<10)
				request.setAttribute("hour", "0" + nrg.getHour());
			else
				request.setAttribute("hour", nrg.getHour());
			
			request.setAttribute("nrg", nrg);
			request.getRequestDispatcher("SmrtNrgEdit.jsp").forward(request, response);
		}
		//confirmation of blueprint
		//insert of experiment in database if slot available else redirection to edit page
		else if(guided != null && submit.equalsIgnoreCase("confirm")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			//String numOfDevis = request.getParameter("numOfDevsChecked");
			//String dataRat = request.getParameter("dataRateChecked");
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
			
			nrg.setCountry(countryTo);
			nrg.setFromCountry(countryFrom);

			//nrg.setDensity(Integer.parseInt(numOfDevis));
			//nrg.setDataRate(Double.parseDouble(dataRat));
			nrg.setDay(Integer.parseInt(dayCh));
			nrg.setMonth(Integer.parseInt(monthCh));
			nrg.setYear(Integer.parseInt(yearCh));
			nrg.setHour(Integer.parseInt(hourCh));
			nrg.setMinutes(Integer.parseInt(minutesCh));
			nrg.setLatency(Integer.parseInt(latencyCh));
			nrg.setDataRate(Integer.parseInt(dataRateCh));
			nrg.setReliability(Double.parseDouble(reliabilityCh));
			nrg.setAvailability(Double.parseDouble(availabilityCh));
			nrg.setBroadband(Double.parseDouble(broadbandCh));
			nrg.setMobility(Integer.parseInt(mobilityCh));
			nrg.setCapacity(Double.parseDouble(capacityCh));
			nrg.setDensity(Integer.parseInt(densityCh));
			nrg.setSlicing(slicingCh);
			nrg.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				nrg.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				nrg.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				nrg.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					nrg.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					nrg.setMinutes(00);
				}
				else
				{
					nrg.setHour(23);
					nrg.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(nrg.getHour(), nrg.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						nrg.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						nrg.setMinutes(00);
					}
					else
					{
						nrg.setHour(23);
						nrg.setMinutes(59);
					}
				}
			}
	
			if(nrg.getMinutes()<10)
				request.setAttribute("minutes", "0" + nrg.getMinutes());
			else
				request.setAttribute("minutes", nrg.getMinutes());
			if(nrg.getHour()<10)
				request.setAttribute("hour", "0" + nrg.getHour());
			else
				request.setAttribute("hour", nrg.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("nrg", nrg);
			request.getRequestDispatcher("SmrtNrgConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(nrg.getMinutes() < 10)
				query = "SELECT dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear() +"' AND launchedTo='" + nrg.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(nrg.getHour() + ".0" + nrg.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(nrg.getHour() + ".0" + nrg.getMinutes()), nrg.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear() +"' AND launchedTo='" + nrg.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(nrg.getHour() + "." + nrg.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(nrg.getHour() + "." + nrg.getMinutes()), nrg.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(nrg.getMinutes() < 10)
				dtime = Double.parseDouble(nrg.getHour() + ".0" + nrg.getMinutes());
			else
				dtime = Double.parseDouble(nrg.getHour() + "." + nrg.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear(), Double.parseDouble(nrg.getHour() + "." + nrg.getMinutes()), nrg.getDuration(), nrg.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, nrg.getDuration());
					rs.beforeFirst();
					if(nrg.getMinutes() < 10)
						db.insertIntoDB(nrg.getName(), nrg.getDescription(), nrg.getVersion(), nrg.getIdentity(), nrg.getSector(), nrg.getFromCountry(), nrg.getCountry(), nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear(), Double.parseDouble(nrg.getHour() + ".0" + nrg.getMinutes()), endTime, nrg.getDuration(), 0, nrg.getService(), nrg.getLatency(), nrg.getDataRate(), nrg.getReliability(), nrg.getAvailability(), nrg.getBroadband(), nrg.getMobility(), nrg.getCapacity(), nrg.getDensity(), nrg.getSlicing(), nrg.getSecurity());
					else
						db.insertIntoDB(nrg.getName(), nrg.getDescription(), nrg.getVersion(), nrg.getIdentity(), nrg.getSector(), nrg.getFromCountry(), nrg.getCountry(), nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear(), Double.parseDouble(nrg.getHour() + "." + nrg.getMinutes()), endTime, nrg.getDuration(), 0, nrg.getService(), nrg.getLatency(), nrg.getDataRate(), nrg.getReliability(), nrg.getAvailability(), nrg.getBroadband(), nrg.getMobility(), nrg.getCapacity(), nrg.getDensity(), nrg.getSlicing(), nrg.getSecurity());
					request.setAttribute("nrg", nrg);
					request.getRequestDispatcher("SmrtNrgConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear(), Double.parseDouble(nrg.getHour() + "." + nrg.getMinutes()), nrg.getDuration(), "France", db);
				
					String av2 = db.nextAvailable(nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear(), Double.parseDouble(nrg.getHour() + "." + nrg.getMinutes()), nrg.getDuration(), "Greece", db);
					
					if(nrg.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + nrg.getCountry() + " at " + nrg.getHour() + ":0" + nrg.getMinutes() + " on " + nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + nrg.getCountry() + " at " + nrg.getHour() + ":" + nrg.getMinutes() + " on " + nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear() + "!");
					if(av1.equals("Not available!") && av2.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day");
					}
					else if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "You can try in " + av2 + ".");
					}
					else if(av2.equals("Not available!"))
					{
						request.setAttribute("suggestion", "You can try in " + av1 + ".");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " or in " + av2 + " on " + nrg.getDay() + "/" + nrg.getMonth() + "/" + nrg.getYear() + ".");
					}
					request.setAttribute("nrg", nrg);
					request.getRequestDispatcher("SmrtNrgEdit.jsp").forward(request, response);
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
			//String numOfDevis = request.getParameter("numOfDevsChecked");
			//String dataRat = request.getParameter("dataRateChecked");
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
			
			nrg.setCountry(countryTo);
			nrg.setFromCountry(countryFrom);

			//nrg.setDensity(Integer.parseInt(numOfDevis));
			//nrg.setDataRate(Double.parseDouble(dataRat));
			nrg.setDay(Integer.parseInt(dayCh));
			nrg.setMonth(Integer.parseInt(monthCh));
			nrg.setYear(Integer.parseInt(yearCh));
			nrg.setHour(Integer.parseInt(hourCh));
			nrg.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				nrg.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				nrg.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				nrg.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				nrg.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				nrg.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				nrg.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				nrg.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				nrg.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				nrg.setSlicing(slicingCh);
			if(securityCh != null)
				nrg.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				nrg.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				nrg.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				nrg.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					nrg.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					nrg.setMinutes(00);
				}
				else
				{
					nrg.setHour(23);
					nrg.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(nrg.getHour(), nrg.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						nrg.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						nrg.setMinutes(00);
					}
					else
					{
						nrg.setHour(23);
						nrg.setMinutes(59);
					}
				}
			}
	
			if(nrg.getMinutes()<10)
				request.setAttribute("minutes", "0" + nrg.getMinutes());
			else
				request.setAttribute("minutes", nrg.getMinutes());
			if(nrg.getHour()<10)
				request.setAttribute("hour", "0" + nrg.getHour());
			else
				request.setAttribute("hour", nrg.getHour());
			
			request.setAttribute("nrg", nrg);
			request.getRequestDispatcher("SmrtNrgCreated.jsp").forward(request, response);
		}
		//values according to text intention
		//checks if command contains information about the country to run the experiment
		else {
			
			if(intent.toLowerCase().contains(" in spain") ||
					intent.toLowerCase().contains(" in the spanish ") ||
					intent.toLowerCase().contains(" to spain") ||
					intent.toLowerCase().contains(" to the spanish ")) {
				count1 = "Spain";
			}
		
			if(intent.toLowerCase().contains(" in greece") ||
				intent.toLowerCase().contains(" in the greek") ||
				intent.toLowerCase().contains(" to greece") ||
				intent.toLowerCase().contains(" to the greek")) {
				if(count1 == null)
					count1 = "Greece";
				else {
					if(count2 == null)
						count2 = "Greece";
				}
			}
		
			if(intent.toLowerCase().contains(" in france") ||
			   intent.toLowerCase().contains(" in the french ") ||
			   intent.toLowerCase().contains(" to france") ||
			   intent.toLowerCase().contains(" to the french ")) {
				if(count1 == null)
					count1 = "France";
				else {
					if(count2 == null)
						count2 = "France";
					else
						count2 = count2 + ", France";
				}
			}
		
			if(intent.toLowerCase().contains(" in italy") ||
			   intent.toLowerCase().contains(" in the italian ") ||
			   intent.toLowerCase().contains(" to italy") ||
			   intent.toLowerCase().contains(" to the italian ")) {
				if(count1 == null)
					count1 = "Italy";
				else {
					if(count2 == null)
						count2 = "Italy";
					else
						count2 = count2 + ", Italy";
				}
			}
		
			if(count1 == null) {
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("SmrtNrgCountry.jsp").forward(request, response);
			}
			else if(count2 == null && (count1.equalsIgnoreCase("greece") || count1.equalsIgnoreCase("france"))) {
				nrg.setCountry(count1);
				country = true;
			}
			else if(count2 == null && (count1.equalsIgnoreCase("spain") || count1.equalsIgnoreCase("italy"))) {
				intent = intent.replaceAll("\\bin spain\\b", "");
				intent = intent.replaceAll("\\bin the spanish\\b", "");
				intent = intent.replaceAll("\\bto spain\\b", "");
				intent = intent.replaceAll("\\bto the spanish\\b", "");
				intent = intent.replaceAll("\\bin greece\\b", "");
				intent = intent.replaceAll("\\bin the greek\\b", "");
				intent = intent.replaceAll("\\bto greece\\b", "");
				intent = intent.replaceAll("\\bto the greek\\b", "");
				intent = intent.replaceAll("\\bin france\\b", "");
				intent = intent.replaceAll("\\bin the french\\b", "");
				intent = intent.replaceAll("\\bto france\\b", "");
				intent = intent.replaceAll("\\bto the french\\b", "");
				intent = intent.replaceAll("\\bin italy\\b", "");
				intent = intent.replaceAll("\\bin the italian\\b", "");
				intent = intent.replaceAll("\\bto italy\\b", "");
				intent = intent.replaceAll("\\bto the italian\\b", "");
				request.setAttribute("message", "Service not supported in " + count1 + "!");
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("SmrtNrgCountry.jsp").forward(request, response);
			}
			else if(count2 != null) {
				count2 = count1 + ", " + count2 + ".";
				intent = intent.replaceAll("\\bin spain\\b", "");
				intent = intent.replaceAll("\\bin the spanish\\b", "");
				intent = intent.replaceAll("\\bto spain\\b", "");
				intent = intent.replaceAll("\\bto the spanish\\b", "");
				intent = intent.replaceAll("\\bin greece\\b", "");
				intent = intent.replaceAll("\\bin the greek\\b", "");
				intent = intent.replaceAll("\\bto greece\\b", "");
				intent = intent.replaceAll("\\bto the greek\\b", "");
				intent = intent.replaceAll("\\bin france\\b", "");
				intent = intent.replaceAll("\\bin the french\\b", "");
				intent = intent.replaceAll("\\bto france\\b", "");
				intent = intent.replaceAll("\\bto the french\\b", "");
				intent = intent.replaceAll("\\bin italy\\b", "");
				intent = intent.replaceAll("\\bin the italian\\b", "");
				intent = intent.replaceAll("\\bto italy\\b", "");
				intent = intent.replaceAll("\\bto the italian\\b", "");
				request.setAttribute("message", "Conflict between countries! You have selected the following countries to run the experiment to: " + count2 +" Only one country acceptable!");
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("SmrtNrgCountry.jsp").forward(request, response);
			}
			
			//checks if command contains information about the country to run the experiment from
			if(country == true) {
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
					request.getRequestDispatcher("SmrtNrgFromCountry.jsp").forward(request, response);
				}
				else if(fcount2 == null) {
					nrg.setFromCountry(fcount1);
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
					request.setAttribute("servlet", "SmrtNrg");
					request.getRequestDispatcher("SmrtNrgFromCountry.jsp").forward(request, response);
				
				}
				
				//checks if command contains information about the density of devices
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
						request.setAttribute("message", "Multiple values for devices/km2 provided. Please provide only one value!");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("SmrtNrgDensity.jsp").forward(request, response);
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
							if(Integer.parseInt(tmp)>0 && Integer.parseInt(tmp)<=2000) {
								nrg.setDensity(Integer.parseInt(tmp));
								devs = true;
							}
							else {
								intent = intent.replace("#", " ");
								request.setAttribute("message", "Invalid number of devices given: " + tmp);
								request.setAttribute("intent", intent);
								request.getRequestDispatcher("SmrtNrgDensity.jsp").forward(request, response);
							}
						}
						else {
							intent = intent.replace("#", " ");
							request.setAttribute("message", "Invalid number of devices/km2 given!");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("SmrtNrgDensity.jsp").forward(request, response);
						}
					}
					else
					{
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("SmrtNrgDensity.jsp").forward(request, response);
					}
				}
				
				//checks if command contains information about the data rate
				if(devs == true) {
					
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
						request.getRequestDispatcher("SmrtNrgDataRate.jsp").forward(request, response);
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
							if(Double.parseDouble(tmp)>0) {
								nrg.setDataRate(Double.parseDouble(tmp));
								dataR = true;
							}
							else if(Double.parseDouble(tmp) == 0) {
								nrg.setDataRate(50);
								dataR = true;
							}
							else {
								intent = intent.replace("$", " ");
								request.setAttribute("intent", intent);
								request.getRequestDispatcher("SmrtNrgDataRate.jsp").forward(request, response);
							}
						}
						else {
							intent = intent.replace("#", " ");
							request.setAttribute("message", "Invalid number of data rate given!");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("SmrtNrgDataRate.jsp").forward(request, response);
						}
					}
					else
					{
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("SmrtNrgDataRate.jsp").forward(request, response);
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
						request.getRequestDispatcher("SmrtNrgDate.jsp").forward(request, response);
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
								nrg.setDay(Integer.parseInt(day));
							}
							else nrg.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							
						}
						else
							nrg.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
								nrg.setMonth(Integer.parseInt(month));
							}
							else
								nrg.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
						}
						else
						{
							nrg.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
								nrg.setYear(Integer.parseInt(year));
							}
							else
								nrg.setYear(Calendar.getInstance().get(Calendar.YEAR));
						}
						else
							nrg.setYear(Calendar.getInstance().get(Calendar.YEAR));

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String givda = ""+nrg.getYear()+"-"+nrg.getMonth()+"-"+nrg.getDay()+"";
						try {
							Date givdate = sdf.parse(givda);
							Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							
							day = Integer.toString(nrg.getDay());
							month = Integer.toString(nrg.getMonth());
							year = Integer.toString(nrg.getYear());
							
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
								request.getRequestDispatcher("SmrtNrgDate.jsp").forward(request, response);
							}
							else if(cd.dateCompare(nrg.getDay(), nrg.getMonth(), nrg.getYear()) == 2) {
								
								for(int j=0; j<intent.length(); j++) {
									if(intent.charAt(j) == '/')
										intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
								}
								
								request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
								request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
								request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
								request.setAttribute("message", "Date given has wrong number of days.");
								request.setAttribute("intent", intent);
								request.getRequestDispatcher("SmrtNrgDate.jsp").forward(request, response);
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
						request.getRequestDispatcher("SmrtNrgDate.jsp").forward(request, response);
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
						request.getRequestDispatcher("SmrtNrgTime.jsp").forward(request, response);
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
								nrg.setHour(Integer.parseInt(hour));
							else
								nrg.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
						}
						else
							nrg.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
								nrg.setMinutes(Integer.parseInt(minutes));
							else
								nrg.setMinutes(00);	
						}
						
						if(cd.dateCompare(nrg.getDay(), nrg.getMonth(), nrg.getYear()) == 1) {
							if(ct.timeCompare(nrg.getHour(), nrg.getMinutes()) == 0) {
								if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
									nrg.setHour(00);
									nrg.setMinutes(00);
									nrg.setDay(nrg.getDay()+1);
									time = true;
								}
								else {
									nrg.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
									nrg.setMinutes(00);
									time = true;
								}
							}
							else
							{
								time = true;
							}
						}
						else if(cd.dateCompare(nrg.getDay(), nrg.getMonth(), nrg.getYear()) == 2) {
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
							request.getRequestDispatcher("SmrtNrgTime.jsp").forward(request, response);
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
						request.getRequestDispatcher("SmrtNrgTime.jsp").forward(request, response);
					}
				}
				
				//redirection to blueprint page
				if(country == true && fcountry == true && date == true && time == true) { //dataR == true && devs == true &&
					if(nrg.getMinutes()<10)
						request.setAttribute("minutes", "0" + nrg.getMinutes());
					else
						request.setAttribute("minutes", nrg.getMinutes());
					if(nrg.getHour()<10)
						request.setAttribute("hour", "0" + nrg.getHour());
					else
						request.setAttribute("hour", nrg.getHour());
					
					request.setAttribute("nrg", nrg);
					request.getRequestDispatcher("SmrtNrgCreated.jsp").forward(request, response);
				}
			}
		}
			
	}

}
