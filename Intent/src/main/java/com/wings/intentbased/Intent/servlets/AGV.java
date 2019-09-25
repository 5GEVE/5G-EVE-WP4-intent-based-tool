package com.wings.intentbased.Intent.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wings.intentbased.Intent.model.CompareDate;
import com.wings.intentbased.Intent.model.CompareTime;
import com.wings.intentbased.Intent.resources.Database;
import com.wings.intentbased.Intent.yamlFiles.AGVToYaml;

import blueprints.AGVBp;

/**
 * Servlet implementation class AGV
 */
@WebServlet()
public class AGV extends HttpServlet {
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
		
		boolean numOfAgvs = false;
		boolean location = false;
		boolean image = false;
		boolean fcountry = false;
		boolean dataRate = false;
		boolean date = false;
		boolean time = false;
		String fcount1 = null, fcount2 = null;
		String count1 = null, count2 = null;
		
		//getting parameters from browser page
		String guided = request.getParameter("guided");
		String submit = request.getParameter("submit");
		
		String intent = request.getParameter("intent");
		String newIntent = request.getParameter("newIntent");
		String newIntentN = request.getParameter("newIntentN");
		String fromCountry = request.getParameter("fromCountry");
		String intentRate = request.getParameter("intentRate");
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
			submit="nothing";
		
		if(newIntent != null)
			intent = intent + " " + newIntent + " ";
		
		if(newIntentN != null)
			intent = intent + " " + "#" + newIntentN + " ";
		
		if(intentRate != null)
			intent = intent + " " + "$" + intentRate + " ";
		
		if(fromCountry != null)
			intent = intent + " " + fromCountry + " ";
		
		//creation of blueprint and instantiation of some values
		AGVBp agv = new AGVBp();
		agv.setName("AGV control and automation");
		agv.setDescription("Blueprint for AGVs using 5G network for Automation and Control purposes");
		agv.setVersion(1.0);
		agv.setIdentity("BP Identity");
		agv.setSector("Industry 4.0");
		agv.setSapLocation("SapLocation");
		agv.setService("URLLC");
		agv.setLatency(10);
		agv.setDataRate(54);
		agv.setReliability(99.9);
		agv.setAvailability(99.9);
		agv.setMobility(20);
		agv.setBroadband(6480);
		agv.setCapacity(2.16);
		agv.setDensity(40000);
		agv.setSlicing("Y");
		agv.setSecurity("N");
		agv.setDuration(80);
		
		//cancel button is pressed
		if(guided != null && submit.equalsIgnoreCase("cancel")) {
			
			request.getRequestDispatcher("IntentPage.jsp").forward(request, response);
		}
		//edit button is pressed
		//update of values according to edit values
		else if(guided != null && submit.equalsIgnoreCase("edit")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			String numOfAGVs = request.getParameter("numOfAGVsChecked");
			//String agvsDataRate = request.getParameter("AGVsRateChecked");
			String imageCh = request.getParameter("imageChecked");
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
			
			agv.setCountry(countryTo);
			agv.setLocation(countryTo);
			agv.setFromCountry(countryFrom);
			agv.setNumOfAgvs(Integer.parseInt(numOfAGVs));
			//agv.setDataRate(Integer.parseInt(agvsDataRate));
			agv.setImage(imageCh);
			agv.setDay(Integer.parseInt(dayCh));
			agv.setMonth(Integer.parseInt(monthCh));
			agv.setYear(Integer.parseInt(yearCh));
			agv.setHour(Integer.parseInt(hourCh));
			agv.setMinutes(Integer.parseInt(minutesCh));
			agv.setLatency(Integer.parseInt(latencyCh));
			agv.setDataRate(Integer.parseInt(dataRateCh));
			agv.setReliability(Double.parseDouble(reliabilityCh));
			agv.setAvailability(Double.parseDouble(availabilityCh));
			agv.setBroadband(Double.parseDouble(broadbandCh));
			agv.setMobility(Integer.parseInt(mobilityCh));
			agv.setCapacity(Double.parseDouble(capacityCh));
			agv.setDensity(Integer.parseInt(densityCh));
			agv.setSlicing(slicingCh);
			agv.setSecurity(securityCh);
			
			if(agv.getMinutes()<10)
				request.setAttribute("minutes", "0" + agv.getMinutes());
			else
				request.setAttribute("minutes", agv.getMinutes());
			if(agv.getHour()<10)
				request.setAttribute("hour", "0" + agv.getHour());
			else
				request.setAttribute("hour", agv.getHour());
			
			request.setAttribute("agv", agv);
			request.getRequestDispatcher("AGVEdit.jsp").forward(request, response);
		}
		//confirmation of blueprint
		//insert of experiment in database if slot available else redirection to edit page
		else if(guided != null && submit.equalsIgnoreCase("confirm")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
			String numOfAGVs = request.getParameter("numOfAGVsChecked");
			//String agvsDataRate = request.getParameter("AGVsRateChecked");
			String imageCh = request.getParameter("imageChecked");
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
			
			agv.setCountry(countryTo);
			agv.setLocation(countryTo);
			agv.setFromCountry(countryFrom);
			agv.setNumOfAgvs(Integer.parseInt(numOfAGVs));
			//agv.setDataRate(Integer.parseInt(agvsDataRate));
			agv.setImage(imageCh);
			agv.setDay(Integer.parseInt(dayCh));
			agv.setMonth(Integer.parseInt(monthCh));
			agv.setYear(Integer.parseInt(yearCh));
			agv.setHour(Integer.parseInt(hourCh));
			agv.setMinutes(Integer.parseInt(minutesCh));
			agv.setLatency(Integer.parseInt(latencyCh));
			agv.setDataRate(Integer.parseInt(dataRateCh));
			agv.setReliability(Double.parseDouble(reliabilityCh));
			agv.setAvailability(Double.parseDouble(availabilityCh));
			agv.setBroadband(Double.parseDouble(broadbandCh));
			agv.setMobility(Integer.parseInt(mobilityCh));
			agv.setCapacity(Double.parseDouble(capacityCh));
			agv.setDensity(Integer.parseInt(densityCh));
			agv.setSlicing(slicingCh);
			agv.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				agv.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				agv.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				agv.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					agv.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					agv.setMinutes(00);
				}
				else
				{
					agv.setHour(23);
					agv.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(agv.getHour(), agv.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						agv.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						agv.setMinutes(00);
					}
					else
					{
						agv.setHour(23);
						agv.setMinutes(59);
					}
				}
			}
	
			if(agv.getMinutes()<10)
				request.setAttribute("minutes", "0" + agv.getMinutes());
			else
				request.setAttribute("minutes", agv.getMinutes());
			if(agv.getHour()<10)
				request.setAttribute("hour", "0" + agv.getHour());
			else
				request.setAttribute("hour", agv.getHour());
			
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("agv", agv);
			request.getRequestDispatcher("AGVConfirmed.jsp").forward(request, response);
			
			
			
			/*Database db = new Database();
			
			String query;
			if(agv.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear() +"' AND launchedTo='" + agv.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(agv.getHour() + ".0" + agv.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(agv.getHour() + ".0" + agv.getMinutes()), agv.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear() +"' AND launchedTo='" + agv.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(agv.getHour() + "." + agv.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(agv.getHour() + "." + agv.getMinutes()), agv.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(agv.getMinutes() < 10)
				dtime = Double.parseDouble(agv.getHour() + ".0" + agv.getMinutes());
			else
				dtime = Double.parseDouble(agv.getHour() + "." + agv.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear(), Double.parseDouble(agv.getHour() + "." + agv.getMinutes()), agv.getDuration(), agv.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, agv.getDuration());
					rs.beforeFirst();
					if(agv.getMinutes() < 10)
						db.insertIntoDB(agv.getName(), agv.getDescription(), agv.getVersion(), agv.getIdentity(), agv.getSector(), agv.getFromCountry(), agv.getCountry(), agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear(), Double.parseDouble(agv.getHour() + ".0" + agv.getMinutes()), endTime, agv.getDuration(), agv.getNumOfAgvs(), agv.getService(), agv.getLatency(), agv.getDataRate(), agv.getReliability(), agv.getAvailability(), agv.getBroadband(), agv.getMobility(), agv.getCapacity(), agv.getDensity(), agv.getSlicing(), agv.getSecurity());
					else
						db.insertIntoDB(agv.getName(), agv.getDescription(), agv.getVersion(), agv.getIdentity(), agv.getSector(), agv.getFromCountry(), agv.getCountry(), agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear(), Double.parseDouble(agv.getHour() + "." + agv.getMinutes()), endTime, agv.getDuration(), agv.getNumOfAgvs(), agv.getService(), agv.getLatency(), agv.getDataRate(), agv.getReliability(), agv.getAvailability(), agv.getBroadband(), agv.getMobility(), agv.getCapacity(), agv.getDensity(), agv.getSlicing(), agv.getSecurity());
					AGVToYaml agvYaml = new AGVToYaml();
					agvYaml.exportToYaml(agv);
					request.setAttribute("agv", agv);
					request.getRequestDispatcher("AGVConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear(), Double.parseDouble(agv.getHour() + "." + agv.getMinutes()), agv.getDuration(), "Greece", db);
				
					String av2 = db.nextAvailable(agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear(), Double.parseDouble(agv.getHour() + "." + agv.getMinutes()), agv.getDuration(), "Spain", db);
					
					if(agv.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + agv.getCountry() + " at " + agv.getHour() + ":0" + agv.getMinutes() + " on " + agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + agv.getCountry() + " at " + agv.getHour() + ":" + agv.getMinutes() + " on " + agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear() + "!");
					if(av1.equals("Not available!") && av2.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
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
						request.setAttribute("suggestion", "You can try in " + av1 + " or in " + av2 + " on " + agv.getDay() + "/" + agv.getMonth() + "/" + agv.getYear() + ".");
					}
					request.setAttribute("agv", agv);
					request.getRequestDispatcher("AGVEdit.jsp").forward(request, response);
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
			String numOfAGVs = request.getParameter("numOfAGVsChecked");
			//String agvsDataRate = request.getParameter("AGVsRateChecked");
			String imageCh = request.getParameter("imageChecked");
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
			
			agv.setCountry(countryTo);
			agv.setLocation(countryTo);
			agv.setFromCountry(countryFrom);
			agv.setNumOfAgvs(Integer.parseInt(numOfAGVs));
			//agv.setDataRate(Integer.parseInt(agvsDataRate));
			agv.setImage(imageCh);
			agv.setDay(Integer.parseInt(dayCh));
			agv.setMonth(Integer.parseInt(monthCh));
			agv.setYear(Integer.parseInt(yearCh));
			agv.setHour(Integer.parseInt(hourCh));
			agv.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				agv.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				agv.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				agv.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				agv.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				agv.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				agv.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				agv.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				agv.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				agv.setSlicing(slicingCh);
			if(securityCh != null)
				agv.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				System.out.println("Mpika1");
				agv.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				agv.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				agv.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					agv.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					agv.setMinutes(00);
				}
				else
				{
					agv.setHour(23);
					agv.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {				
				if(ct.timeCompare(agv.getHour(), agv.getMinutes()) == 0) {
					
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						agv.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						agv.setMinutes(00);
					}
					else
					{
						agv.setHour(23);
						agv.setMinutes(59);
					}
				}
			}
			
			if(agv.getMinutes()<10)
				request.setAttribute("minutes", "0" + agv.getMinutes());
			else
				request.setAttribute("minutes", agv.getMinutes());
			if(agv.getHour()<10)
				request.setAttribute("hour", "0" + agv.getHour());
			else
				request.setAttribute("hour", agv.getHour());
			
			request.setAttribute("agv", agv);
			request.getRequestDispatcher("AGVCreated.jsp").forward(request, response);
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
			else
				count2 = "Greece";
			}
		
			if(intent.toLowerCase().contains(" in france") ||
			   intent.toLowerCase().contains(" in the french ") ||
			   intent.toLowerCase().contains(" to france") ||
			   intent.toLowerCase().contains(" to the french ")) {
				if(count1 == null)
					count1 = "France";
				else
					count2 = "France";
			}
		
			if(intent.toLowerCase().contains(" in italy") ||
			   intent.toLowerCase().contains(" in the italian ") ||
			   intent.toLowerCase().contains(" to italy") ||
			   intent.toLowerCase().contains(" to the italian ")) {
				if(count1 == null)
					count1 = "Italy";
				else
					count2 = "Italy";
			}
		
			if(count1 == null) {
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("AGVCountry.jsp").forward(request, response);
			}
			else if(count2 == null && (count1.equalsIgnoreCase("greece") || count1.equalsIgnoreCase("spain"))) {
				agv.setCountry(count1);
				agv.setLocation(count1);
				location = true;
			}
			else if(count2 == null && (count1.equalsIgnoreCase("france") || count1.equalsIgnoreCase("italy"))) {
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
				request.getRequestDispatcher("AGVCountry.jsp").forward(request, response);
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
				request.getRequestDispatcher("AGVCountry.jsp").forward(request, response);
			}
			
			//checks if command contains information about the country to run the experiment from
			if(location == true) {
				if(intent.toLowerCase().contains(" from greece ")) {
					fcount1 = "Greece";
				}
		
				if(intent.toLowerCase().contains(" from italy ")) {
					if(fcount1 == null)
						fcount1 = "Italy";
					else {
						if(fcount2 == null)
							fcount2 = "Italy";
					}
				}
		
				if(intent.toLowerCase().contains(" from spain ")) {
					if(fcount1 == null)
						fcount1 = "Spain";
					else {
						if(fcount2 == null)
							fcount2 = "Spain";
						else
							fcount2 = fcount2 + ", Spain";
					}
				}
			
				if(intent.toLowerCase().contains(" from france ")) {
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
					request.getRequestDispatcher("AGVFromCountry.jsp").forward(request, response);
				}
				else if(fcount2 == null) {
					agv.setFromCountry(fcount1);
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
					request.setAttribute("servlet", "AGV");
					request.getRequestDispatcher("AGVFromCountry.jsp").forward(request, response);
				
				}
				
			}
		
			//checks if command contains information about the number of agvs	
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
				request.setAttribute("message", "Multiple values for number of AGVs provided. Please provide only one value!");
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("NumOfAgvs.jsp").forward(request, response);
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
						agv.setNumOfAgvs(Integer.parseInt(tmp));
						numOfAgvs = true;
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("NumOfAgvs.jsp").forward(request, response);
					}
				}
				else {
					intent = intent.replace("#", " ");
					request.setAttribute("message", "Invalid number of AGVs given!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("NumOfAgvs.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("NumOfAgvs.jsp").forward(request, response);
			}
		}
		//checks if command contains information about the data rate of the agvs
		/*
		if(numOfAgvs == true) {
			
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
				request.setAttribute("message", "Multiple values for AGVs data rate provided. Please provide only one value!");
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("AgvsDataRate.jsp").forward(request, response);
				
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
						agv.setDataRate(Double.parseDouble(tmp));
						dataRate = true;
					}
					else if(Double.parseDouble(tmp) == 0) {
						agv.setDataRate(80);
						dataRate = true;
					}
					else {
						intent = intent.replace("$", " ");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("AgvsDataRate.jsp").forward(request, response);
					}
				}
				else {
					intent = intent.replace("#", " ");
					request.setAttribute("message", "Invalid number of data rate given!");
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("AgvsDataRate.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("AgvsDataRate.jsp").forward(request, response);
			}
		}
		//checks if command contains information about the plc image 
		/*
		if(dataRate == true) {
			
			String image1 = null, image2 = null;
			
			if(intent.toLowerCase().contains(" image 1 ")) {
				image1 = "image 1";
			}
		
			if(intent.toLowerCase().contains(" image 2 ")) {
				if(image1 == null)
					image1 = "image 2";
				else
					image2 = "image 2";
			}
		
			if(intent.toLowerCase().contains(" image 3 ")) {
				if(image1 == null)
					image1 = "image 3";
				else
					image2 = "image 3";
			}
		
			if(image1 == null) {
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("AGVImage.jsp").forward(request, response);
			}
			else if(image2 == null) {
				agv.setImage(image1);
				image = true;
			}
			else{
				intent = intent.replaceAll("\\bimage 1\\b", "");
				intent = intent.replaceAll("\\bimage 2\\b", "");
				intent = intent.replaceAll("\\bimage 3\\b", "");
				request.setAttribute("message", "Multiple plc images given!");
				request.setAttribute("intent", intent);
				request.getRequestDispatcher("AGVImage.jsp").forward(request, response);
			}
		
		}*/
		
		//checks if command contains information about the date to run the experiment
		if(numOfAgvs == true) {
			
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
				request.getRequestDispatcher("AGVDate.jsp").forward(request, response);
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
						agv.setDay(Integer.parseInt(day));
					}
					else agv.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					
				}
				else
					agv.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
						agv.setMonth(Integer.parseInt(month));
					}
					else
						agv.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				}
				else
				{
					agv.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
						agv.setYear(Integer.parseInt(year));
					}
					else
						agv.setYear(Calendar.getInstance().get(Calendar.YEAR));
				}
				else
					agv.setYear(Calendar.getInstance().get(Calendar.YEAR));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String givda = ""+agv.getYear()+"-"+agv.getMonth()+"-"+agv.getDay()+"";
				try {
					Date givdate = sdf.parse(givda);
					Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					
					day = Integer.toString(agv.getDay());
					month = Integer.toString(agv.getMonth());
					year = Integer.toString(agv.getYear());
					
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
						request.getRequestDispatcher("AGVDate.jsp").forward(request, response);
					}
					else if(cd.dateCompare(agv.getDay(), agv.getMonth(), agv.getYear()) == 0) {
						
						for(int j=0; j<intent.length(); j++) {
							if(intent.charAt(j) == '/')
								intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
						}
						
						request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
						request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
						request.setAttribute("message", "Date given has wrong number of days.");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("AGVDate.jsp").forward(request, response);
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
				request.getRequestDispatcher("AGVDate.jsp").forward(request, response);
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
				request.getRequestDispatcher("AGVTime.jsp").forward(request, response);
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
						agv.setHour(Integer.parseInt(hour));
					else
						agv.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
				}
				else
					agv.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
						agv.setMinutes(Integer.parseInt(minutes));
					else
						agv.setMinutes(00);	
				}
				
				if(cd.dateCompare(agv.getDay(), agv.getMonth(), agv.getYear()) == 1) {
					if(ct.timeCompare(agv.getHour(), agv.getMinutes()) == 0) {
						if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
							agv.setHour(00);
							agv.setMinutes(00);
							agv.setDay(agv.getDay()+1);
							time = true;
						}
						else {
							agv.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
							agv.setMinutes(00);
							time = true;
						}
					}
					else
					{
						time = true;
					}
				}
				else if(cd.dateCompare(agv.getDay(), agv.getMonth(), agv.getYear()) == 2) {
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
					request.getRequestDispatcher("AGVTime.jsp").forward(request, response);
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
				request.getRequestDispatcher("AGVTime.jsp").forward(request, response);
			}
		}
		
		//redirection to blueprint page
		if(location == true && numOfAgvs == true && fcountry == true && date == true && time == true) {//dataRate && image
			if(agv.getMinutes()<10)
				request.setAttribute("minutes", "0" + agv.getMinutes());
			else
				request.setAttribute("minutes", agv.getMinutes());
			if(agv.getHour()<10)
				request.setAttribute("hour", "0" + agv.getHour());
			else
				request.setAttribute("hour", agv.getHour());
			
			request.setAttribute("agv", agv);
			request.getRequestDispatcher("AGVCreated.jsp").forward(request, response);
		}
	}
	}
}
