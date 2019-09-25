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

import blueprints.SmrtHomeBp;
import blueprints.SmrtTurinBp;

/**
 * Servlet implementation class SmrtHome
 */
public class SmrtHome extends HttpServlet {
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
		SmrtHomeBp hom = new SmrtHomeBp();
		hom.setName("Smart Personalised Spaces");
		hom.setDescription("Estimation of the preferences of the user with respect to the configuration of their indoor environment");
		hom.setVersion(1.0);
		hom.setIdentity("BP Identity");
		hom.setSector("Smart Cities");
		hom.setService("mMTC");
		hom.setCountry("Greece");
		hom.setLatency(5);
		hom.setDataRate(25);
		hom.setReliability(99.99);
		hom.setAvailability(99.9);
		hom.setMobility(0);
		hom.setBroadband(25);
		hom.setCapacity(0.01);
		hom.setDensity(60000);
		hom.setSlicing("Y");
		hom.setSecurity("Y");
		hom.setDuration(80);
		
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
			
			hom.setFromCountry(countryFrom);

			hom.setNumOfDevs(Integer.parseInt(numOfDevsChecked));
			hom.setDay(Integer.parseInt(dayCh));
			hom.setMonth(Integer.parseInt(monthCh));
			hom.setYear(Integer.parseInt(yearCh));
			hom.setHour(Integer.parseInt(hourCh));
			hom.setMinutes(Integer.parseInt(minutesCh));
			hom.setLatency(Integer.parseInt(latencyCh));
			hom.setDataRate(Integer.parseInt(dataRateCh));
			hom.setReliability(Double.parseDouble(reliabilityCh));
			hom.setAvailability(Double.parseDouble(availabilityCh));
			hom.setBroadband(Double.parseDouble(broadbandCh));
			hom.setMobility(Integer.parseInt(mobilityCh));
			hom.setCapacity(Double.parseDouble(capacityCh));
			hom.setDensity(Integer.parseInt(densityCh));
			hom.setSlicing(slicingCh);
			hom.setSecurity(securityCh);
			
			if(hom.getMinutes()<10)
				request.setAttribute("minutes", "0" + hom.getMinutes());
			else
				request.setAttribute("minutes", hom.getMinutes());
			if(hom.getHour()<10)
				request.setAttribute("hour", "0" + hom.getHour());
			else
				request.setAttribute("hour", hom.getHour());
			
			request.setAttribute("hom", hom);
			request.getRequestDispatcher("SmrtHomeEdit.jsp").forward(request, response);
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
			
			hom.setFromCountry(countryFrom);

			hom.setNumOfDevs(Integer.parseInt(numOfDevsChecked));
			hom.setDay(Integer.parseInt(dayCh));
			hom.setMonth(Integer.parseInt(monthCh));
			hom.setYear(Integer.parseInt(yearCh));
			hom.setHour(Integer.parseInt(hourCh));
			hom.setMinutes(Integer.parseInt(minutesCh));
			hom.setLatency(Integer.parseInt(latencyCh));
			hom.setDataRate(Integer.parseInt(dataRateCh));
			hom.setReliability(Double.parseDouble(reliabilityCh));
			hom.setAvailability(Double.parseDouble(availabilityCh));
			hom.setBroadband(Double.parseDouble(broadbandCh));
			hom.setMobility(Integer.parseInt(mobilityCh));
			hom.setCapacity(Double.parseDouble(capacityCh));
			hom.setDensity(Integer.parseInt(densityCh));
			hom.setSlicing(slicingCh);
			hom.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				hom.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				hom.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				hom.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					hom.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					hom.setMinutes(00);
				}
				else
				{
					hom.setHour(23);
					hom.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(hom.getHour(), hom.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						hom.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						hom.setMinutes(00);
					}
					else
					{
						hom.setHour(23);
						hom.setMinutes(59);
					}
				}
			}
	
			if(hom.getMinutes()<10)
				request.setAttribute("minutes", "0" + hom.getMinutes());
			else
				request.setAttribute("minutes", hom.getMinutes());
			if(hom.getHour()<10)
				request.setAttribute("hour", "0" + hom.getHour());
			else
				request.setAttribute("hour", hom.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("hom", hom);
			request.getRequestDispatcher("SmrtHomeConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(hom.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + hom.getDay() + "/" + hom.getMonth() + "/" + hom.getYear() +"' AND launchedTo='" + hom.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(hom.getHour() + ".0" + hom.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(hom.getHour() + ".0" + hom.getMinutes()), hom.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + hom.getDay() + "/" + hom.getMonth() + "/" + hom.getYear() +"' AND launchedTo='" + hom.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(hom.getHour() + "." + hom.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(hom.getHour() + "." + hom.getMinutes()), hom.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(hom.getMinutes() < 10)
				dtime = Double.parseDouble(hom.getHour() + ".0" + hom.getMinutes());
			else
				dtime = Double.parseDouble(hom.getHour() + "." + hom.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(hom.getDay() + "/" + hom.getMonth() + "/" + hom.getYear(), dtime, hom.getDuration(), hom.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, hom.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(hom.getMinutes() < 10)
						db.insertIntoDB(hom.getName(), hom.getDescription(), hom.getVersion(), hom.getIdentity(), hom.getSector(), hom.getFromCountry(), hom.getCountry(), hom.getDay() + "/" + hom.getMonth() + "/" + hom.getYear(), Double.parseDouble(hom.getHour() + ".0" + hom.getMinutes()), endTime, hom.getDuration(), hom.getNumOfDevs(), hom.getService(), hom.getLatency(), hom.getDataRate(), hom.getReliability(), hom.getAvailability(), hom.getBroadband(), hom.getMobility(), hom.getCapacity(), hom.getDensity(), hom.getSlicing(), hom.getSecurity());
					else
						db.insertIntoDB(hom.getName(), hom.getDescription(), hom.getVersion(), hom.getIdentity(), hom.getSector(), hom.getFromCountry(), hom.getCountry(), hom.getDay() + "/" + hom.getMonth() + "/" + hom.getYear(), Double.parseDouble(hom.getHour() + "." + hom.getMinutes()), endTime, hom.getDuration(), hom.getNumOfDevs(), hom.getService(), hom.getLatency(), hom.getDataRate(), hom.getReliability(), hom.getAvailability(), hom.getBroadband(), hom.getMobility(), hom.getCapacity(), hom.getDensity(), hom.getSlicing(), hom.getSecurity());
					request.setAttribute("hom", hom);
					request.getRequestDispatcher("SmrtHomeConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(hom.getDay() + "/" + hom.getMonth() + "/" + hom.getYear(), Double.parseDouble(hom.getHour() + "." + hom.getMinutes()), hom.getDuration(), "Greece", db);
					System.out.println("av1: " +av1);
					
					if(hom.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + hom.getCountry() + " at " + hom.getHour() + ":0" + hom.getMinutes() + " on " + hom.getDay() + "/" + hom.getMonth() + "/" + hom.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + hom.getCountry() + " at " + hom.getHour() + ":" + hom.getMinutes() + " on " + hom.getDay() + "/" + hom.getMonth() + "/" + hom.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + hom.getDay() + "/" + hom.getMonth() + "/" + hom.getYear() + ".");
					}
					request.setAttribute("hom", hom);
					request.getRequestDispatcher("SmrtHomeEdit.jsp").forward(request, response);
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
			
			hom.setFromCountry(countryFrom);

			hom.setNumOfDevs(Integer.parseInt(numOfDevsChecked));
			hom.setDay(Integer.parseInt(dayCh));
			hom.setMonth(Integer.parseInt(monthCh));
			hom.setYear(Integer.parseInt(yearCh));
			hom.setHour(Integer.parseInt(hourCh));
			hom.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				hom.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				hom.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				hom.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				hom.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				hom.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				hom.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				hom.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				hom.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				hom.setSlicing(slicingCh);
			if(securityCh != null)
				hom.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				hom.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				hom.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				hom.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					hom.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					hom.setMinutes(00);
				}
				else
				{
					hom.setHour(23);
					hom.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(hom.getHour(), hom.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						hom.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						hom.setMinutes(00);
					}
					else
					{
						hom.setHour(23);
						hom.setMinutes(59);
					}
				}
			}
	
			if(hom.getMinutes()<10)
				request.setAttribute("minutes", "0" + hom.getMinutes());
			else
				request.setAttribute("minutes", hom.getMinutes());
			if(hom.getHour()<10)
				request.setAttribute("hour", "0" + hom.getHour());
			else
				request.setAttribute("hour", hom.getHour());
			
			request.setAttribute("hom", hom);
			request.getRequestDispatcher("SmrtHomeCreated.jsp").forward(request, response);
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
				request.getRequestDispatcher("SmrtHomeFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				hom.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "SmrtHome");
				request.getRequestDispatcher("SmrtHomeFromCountry.jsp").forward(request, response);
				
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
					request.getRequestDispatcher("SmrtHomeNumOfDevs.jsp").forward(request, response);
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
							hom.setNumOfDevs(Integer.parseInt(tmp));
							devs = true;
						}
						else {
							intent = intent.replace("#", " ");
							request.setAttribute("message", "Invalid number of devices given: " + tmp);
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("SmrtHomeNumOfDevs.jsp").forward(request, response);
						}
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("message", "Invalid number of devices given!");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("SmrtHomeNumOfDevs.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("SmrtHomeNumOfDevs.jsp").forward(request, response);
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
					request.getRequestDispatcher("SmrtHomeDate.jsp").forward(request, response);
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
							hom.setDay(Integer.parseInt(day));
						}
						else hom.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
					}
					else
						hom.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
							hom.setMonth(Integer.parseInt(month));
						}
						else
							hom.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
					}
					else
					{
						hom.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
							hom.setYear(Integer.parseInt(year));
						}
						else
							hom.setYear(Calendar.getInstance().get(Calendar.YEAR));
					}
					else
						hom.setYear(Calendar.getInstance().get(Calendar.YEAR));

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String givda = ""+hom.getYear()+"-"+hom.getMonth()+"-"+hom.getDay()+"";
					try {
						Date givdate = sdf.parse(givda);
						Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
						day = Integer.toString(hom.getDay());
						month = Integer.toString(hom.getMonth());
						year = Integer.toString(hom.getYear());
						
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
							request.getRequestDispatcher("SmrtHomeDate.jsp").forward(request, response);
						}
						else if(cd.dateCompare(hom.getDay(), hom.getMonth(), hom.getYear()) == 0) {
							
							for(int j=0; j<intent.length(); j++) {
								if(intent.charAt(j) == '/')
									intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
							}
							
							request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
							request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
							request.setAttribute("message", "Date given has wrong number of days.");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("SmrtHomeDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("SmrtHomeDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("SmrtHomeTime.jsp").forward(request, response);
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
							hom.setHour(Integer.parseInt(hour));
						else
							hom.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
					}
					else
						hom.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
							hom.setMinutes(Integer.parseInt(minutes));
						else
							hom.setMinutes(00);	
					}
					
					if(cd.dateCompare(hom.getDay(), hom.getMonth(), hom.getYear()) == 1) {
						if(ct.timeCompare(hom.getHour(), hom.getMinutes()) == 0) {
							if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
								hom.setHour(00);
								hom.setMinutes(00);
								hom.setDay(hom.getDay()+1);
								time = true;
							}
							else {
								hom.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
								hom.setMinutes(00);
								time = true;
							}
						}
						else
						{
							time = true;
						}
					}
					else if(cd.dateCompare(hom.getDay(), hom.getMonth(), hom.getYear()) == 2) {
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
						request.getRequestDispatcher("SmrtHomeTime.jsp").forward(request, response);
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
					request.getRequestDispatcher("SmrtHomeTime.jsp").forward(request, response);
				}
			}
			
			//redirection to blueprint page
			if(fcountry == true && devs == true && date == true && time == true) {
				if(hom.getMinutes()<10)
					request.setAttribute("minutes", "0" + hom.getMinutes());
				else
					request.setAttribute("minutes", hom.getMinutes());
				if(hom.getHour()<10)
					request.setAttribute("hour", "0" + hom.getHour());
				else
					request.setAttribute("hour", hom.getHour());
				
				request.setAttribute("hom", hom);
				request.getRequestDispatcher("SmrtHomeCreated.jsp").forward(request, response);
			}
		}
	}

}
