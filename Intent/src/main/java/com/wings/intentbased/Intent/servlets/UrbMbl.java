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

import blueprints.UrbMblBp;

/**
 * Servlet implementation class UrbMbl
 */
public class UrbMbl extends HttpServlet {
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
		
		boolean numOfDevs = false;
		boolean fcountry = false;
		boolean date = false;
		boolean time = false;
		String fcount1 = null, fcount2 = null;
		
		//getting parameters from browser page
		String intent = request.getParameter("intent");
		String guided = request.getParameter("guided");
		String submit = request.getParameter("submit");
		String fromCountry = request.getParameter("fromCountry");
		String numOfDevices = request.getParameter("numOfDevices");
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
		
		if(numOfDevices != null)
			intent = intent + " #" + numOfDevices + " ";
		
		if(fromCountry != null)
			intent = intent + " " + fromCountry + " ";
		
		if(submit != null)
			System.out.println("Submit choice: " + submit);
		else
			submit="nothing";
		
		//creation of blueprint and instantiation of some values
		UrbMblBp urb = new UrbMblBp();
		urb.setName("5G EVE urban mobility service");
		urb.setDescription("Service enhancing multi-modality between railway network and other collective transportation utilities by providing useful infomobility services");
		urb.setVersion(1.0);
		urb.setIdentity("BP Identity");
		urb.setSector("Smart Transport");
		urb.setCountry("Italy");
		urb.setLocation("Italy");
		urb.setService("URLLC");
		urb.setLatency(1);
		urb.setDataRate(100);
		urb.setReliability(99.95);
		urb.setAvailability(99.9);
		urb.setMobility(300);
		urb.setBroadband(5000);
		urb.setCapacity(3.56);
		urb.setDensity(100000);
		urb.setSlicing("Y");
		urb.setSecurity("Y");
		urb.setDuration(80);
		
		//cancel button is pressed
		if(guided != null && submit.equalsIgnoreCase("cancel")) {
			request.getRequestDispatcher("IntentPage.jsp").forward(request, response);
		}
		//edit button is pressed
		//update of values according to edit values
		else if(guided != null && submit.equalsIgnoreCase("edit")) {
			String countryFrom = request.getParameter("countryFromChecked");
			String numOfDevis = request.getParameter("numOfDevsChecked");
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
			
			urb.setFromCountry(countryFrom);
			urb.setNumOfDevs(Integer.parseInt(numOfDevis));
			urb.setDay(Integer.parseInt(dayCh));
			urb.setMonth(Integer.parseInt(monthCh));
			urb.setYear(Integer.parseInt(yearCh));
			urb.setHour(Integer.parseInt(hourCh));
			urb.setMinutes(Integer.parseInt(minutesCh));
			urb.setLatency(Integer.parseInt(latencyCh));
			urb.setDataRate(Integer.parseInt(dataRateCh));
			urb.setReliability(Double.parseDouble(reliabilityCh));
			urb.setAvailability(Double.parseDouble(availabilityCh));
			urb.setBroadband(Double.parseDouble(broadbandCh));
			urb.setMobility(Integer.parseInt(mobilityCh));
			urb.setCapacity(Double.parseDouble(capacityCh));
			urb.setDensity(Integer.parseInt(densityCh));
			urb.setSlicing(slicingCh);
			urb.setSecurity(securityCh);
			
			if(urb.getMinutes()<10)
				request.setAttribute("minutes", "0" + urb.getMinutes());
			else
				request.setAttribute("minutes", urb.getMinutes());
			if(urb.getHour()<10)
				request.setAttribute("hour", "0" + urb.getHour());
			else
				request.setAttribute("hour", urb.getHour());
			
			request.setAttribute("urb", urb);
			request.getRequestDispatcher("UrbMblEdit.jsp").forward(request, response);
		}
		//confirmation of blueprint
		//insert of experiment in database if slot available else redirection to edit page
		else if(guided != null && submit.equalsIgnoreCase("confirm")) {
			String countryFrom = request.getParameter("countryFromChecked");
			String numOfDevis = request.getParameter("numOfDevsChecked");
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
			
			urb.setFromCountry(countryFrom);
			urb.setNumOfDevs(Integer.parseInt(numOfDevis));
			urb.setDay(Integer.parseInt(dayCh));
			urb.setMonth(Integer.parseInt(monthCh));
			urb.setYear(Integer.parseInt(yearCh));
			urb.setHour(Integer.parseInt(hourCh));
			urb.setMinutes(Integer.parseInt(minutesCh));
			urb.setLatency(Integer.parseInt(latencyCh));
			urb.setDataRate(Integer.parseInt(dataRateCh));
			urb.setReliability(Double.parseDouble(reliabilityCh));
			urb.setAvailability(Double.parseDouble(availabilityCh));
			urb.setBroadband(Double.parseDouble(broadbandCh));
			urb.setMobility(Integer.parseInt(mobilityCh));
			urb.setCapacity(Double.parseDouble(capacityCh));
			urb.setDensity(Integer.parseInt(densityCh));
			urb.setSlicing(slicingCh);
			urb.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				urb.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				urb.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				urb.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					urb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					urb.setMinutes(00);
				}
				else
				{
					urb.setHour(23);
					urb.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(urb.getHour(), urb.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						urb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						urb.setMinutes(00);
					}
					else
					{
						urb.setHour(23);
						urb.setMinutes(59);
					}
				}
			}
	
			if(urb.getMinutes()<10)
				request.setAttribute("minutes", "0" + urb.getMinutes());
			else
				request.setAttribute("minutes", urb.getMinutes());
			if(urb.getHour()<10)
				request.setAttribute("hour", "0" + urb.getHour());
			else
				request.setAttribute("hour", urb.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("urb", urb);
			request.getRequestDispatcher("UrbMblConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(urb.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + urb.getDay() + "/" + urb.getMonth() + "/" + urb.getYear() +"' AND launchedTo='" + urb.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(urb.getHour() + ".0" + urb.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(urb.getHour() + ".0" + urb.getMinutes()), urb.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + urb.getDay() + "/" + urb.getMonth() + "/" + urb.getYear() +"' AND launchedTo='" + urb.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(urb.getHour() + "." + urb.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(urb.getHour() + "." + urb.getMinutes()), urb.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(urb.getMinutes() < 10)
				dtime = Double.parseDouble(urb.getHour() + ".0" + urb.getMinutes());
			else
				dtime = Double.parseDouble(urb.getHour() + "." + urb.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(urb.getDay() + "/" + urb.getMonth() + "/" + urb.getYear(), dtime, urb.getDuration(), urb.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, urb.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(urb.getMinutes() < 10)
						db.insertIntoDB(urb.getName(), urb.getDescription(), urb.getVersion(), urb.getIdentity(), urb.getSector(), urb.getFromCountry(), urb.getCountry(), urb.getDay() + "/" + urb.getMonth() + "/" + urb.getYear(), Double.parseDouble(urb.getHour() + ".0" + urb.getMinutes()), endTime, urb.getDuration(), urb.getNumOfDevs(), urb.getService(), urb.getLatency(), urb.getDataRate(), urb.getReliability(), urb.getAvailability(), urb.getBroadband(), urb.getMobility(), urb.getCapacity(), urb.getDensity(), urb.getSlicing(), urb.getSecurity());
					else
						db.insertIntoDB(urb.getName(), urb.getDescription(), urb.getVersion(), urb.getIdentity(), urb.getSector(), urb.getFromCountry(), urb.getCountry(), urb.getDay() + "/" + urb.getMonth() + "/" + urb.getYear(), Double.parseDouble(urb.getHour() + "." + urb.getMinutes()), endTime, urb.getDuration(), urb.getNumOfDevs(), urb.getService(), urb.getLatency(), urb.getDataRate(), urb.getReliability(), urb.getAvailability(), urb.getBroadband(), urb.getMobility(), urb.getCapacity(), urb.getDensity(), urb.getSlicing(), urb.getSecurity());
					request.setAttribute("urb", urb);
					request.getRequestDispatcher("UrbMblConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(urb.getDay() + "/" + urb.getMonth() + "/" + urb.getYear(), Double.parseDouble(urb.getHour() + "." + urb.getMinutes()), urb.getDuration(), "Italy", db);
					System.out.println("av1: " + av1);
					
					if(urb.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + urb.getCountry() + " at " + urb.getHour() + ":0" + urb.getMinutes() + " on " + urb.getDay() + "/" + urb.getMonth() + "/" + urb.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + urb.getCountry() + " at " + urb.getHour() + ":" + urb.getMinutes() + " on " + urb.getDay() + "/" + urb.getMonth() + "/" + urb.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + urb.getDay() + "/" + urb.getMonth() + "/" + urb.getYear() + ".");
					}
					request.setAttribute("urb", urb);
					request.getRequestDispatcher("UrbMblEdit.jsp").forward(request, response);
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
			String numOfDevis = request.getParameter("numOfDevsChecked");
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
			
			urb.setFromCountry(countryFrom);
			urb.setNumOfDevs(Integer.parseInt(numOfDevis));
			urb.setDay(Integer.parseInt(dayCh));
			urb.setMonth(Integer.parseInt(monthCh));
			urb.setYear(Integer.parseInt(yearCh));
			urb.setHour(Integer.parseInt(hourCh));
			urb.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				urb.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				urb.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				urb.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				urb.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				urb.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				urb.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				urb.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				urb.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				urb.setSlicing(slicingCh);
			if(securityCh != null)
				urb.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				urb.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				urb.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				urb.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					urb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					urb.setMinutes(00);
				}
				else
				{
					urb.setHour(23);
					urb.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(urb.getHour(), urb.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						urb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						urb.setMinutes(00);
					}
					else
					{
						urb.setHour(23);
						urb.setMinutes(59);
					}
				}
			}
	
			if(urb.getMinutes()<10)
				request.setAttribute("minutes", "0" + urb.getMinutes());
			else
				request.setAttribute("minutes", urb.getMinutes());
			if(urb.getHour()<10)
				request.setAttribute("hour", "0" + urb.getHour());
			else
				request.setAttribute("hour", urb.getHour());
			
			request.setAttribute("urb", urb);
			request.getRequestDispatcher("UrbMblCreated.jsp").forward(request, response);
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
				request.getRequestDispatcher("UrbMblFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				urb.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "UrbMbl");
				request.getRequestDispatcher("UrbMblFromCountry.jsp").forward(request, response);
			}
			
			//checks if command contains information about the number of devices
			if(fcountry == true) {
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
					request.getRequestDispatcher("UrbMblNumOfDevs.jsp").forward(request, response);
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
						if(Integer.parseInt(tmp)>0 && Integer.parseInt(tmp)<=300) {
							urb.setNumOfDevs(Integer.parseInt(tmp));
							numOfDevs = true;
						}
						else {
							if(Integer.parseInt(tmp)>300 || Integer.parseInt(tmp)<1) {
								request.setAttribute("message", "Not valid number(" + Integer.parseInt(tmp) + ") provided! Please select a number of devices between 1-300.");
							}
							intent = intent.replace("#", " ");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("UrbMblNumOfDevs.jsp").forward(request, response);
						}
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("message", "Invalid number of devices given!");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("UrbMblNumOfDevs.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("UrbMblNumOfDevs.jsp").forward(request, response);
				}
			}
			
			//checks if command contains information about the date to run the experiment
			if(numOfDevs == true) {
				
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
					request.getRequestDispatcher("UrbMblDate.jsp").forward(request, response);
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
							urb.setDay(Integer.parseInt(day));
						}
						else urb.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
					}
					else
						urb.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
							urb.setMonth(Integer.parseInt(month));
						}
						else
							urb.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
					}
					else
					{
						urb.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
							urb.setYear(Integer.parseInt(year));
						}
						else
							urb.setYear(Calendar.getInstance().get(Calendar.YEAR));
					}
					else
						urb.setYear(Calendar.getInstance().get(Calendar.YEAR));

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String givda = ""+urb.getYear()+"-"+urb.getMonth()+"-"+urb.getDay()+"";
					try {
						Date givdate = sdf.parse(givda);
						Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
						day = Integer.toString(urb.getDay());
						month = Integer.toString(urb.getMonth());
						year = Integer.toString(urb.getYear());
						
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
							request.getRequestDispatcher("UrbMblDate.jsp").forward(request, response);
						}
						else if(cd.dateCompare(urb.getDay(), urb.getMonth(), urb.getYear()) == 0) {
							
							for(int j=0; j<intent.length(); j++) {
								if(intent.charAt(j) == '/')
									intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
							}
							
							request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
							request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
							request.setAttribute("message", "Date given has wrong number of days.");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("UrbMblDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("UrbMblDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("UrbMblTime.jsp").forward(request, response);
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
							urb.setHour(Integer.parseInt(hour));
						else
							urb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
					}
					else
						urb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
							urb.setMinutes(Integer.parseInt(minutes));
						else
							urb.setMinutes(00);	
					}
					
					if(cd.dateCompare(urb.getDay(), urb.getMonth(), urb.getYear()) == 1) {
						if(ct.timeCompare(urb.getHour(), urb.getMinutes()) == 0) {
							if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
								urb.setHour(00);
								urb.setMinutes(00);
								urb.setDay(urb.getDay()+1);
								time = true;
							}
							else {
								urb.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
								urb.setMinutes(00);
								time = true;
							}
						}
						else
						{
							time = true;
						}
					}
					else if(cd.dateCompare(urb.getDay(), urb.getMonth(), urb.getYear()) == 2) {
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
						request.getRequestDispatcher("UrbMblTime.jsp").forward(request, response);
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
					request.getRequestDispatcher("UrbMblTime.jsp").forward(request, response);
				}
			}
			
			//redirection to blueprint page
			if(numOfDevs == true && fcountry == true && date == true) {
				if(urb.getMinutes()<10)
					request.setAttribute("minutes", "0" + urb.getMinutes());
				else
					request.setAttribute("minutes", urb.getMinutes());
				if(urb.getHour()<10)
					request.setAttribute("hour", "0" + urb.getHour());
				else
					request.setAttribute("hour", urb.getHour());
				
				request.setAttribute("urb", urb);
				request.getRequestDispatcher("UrbMblCreated.jsp").forward(request, response);
			}
		}
	}

}
