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

import blueprints.RecServBp;
import blueprints.UrbMblBp;

/**
 * Servlet implementation class RecServ
 */
public class RecServ extends HttpServlet {
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
		RecServBp rec = new RecServBp();
		rec.setName("5G EVE recognition service");
		rec.setDescription("Automatic identification of passenger mobility patterns and related transport modalities aimed at: 1. identifying different transportation modality demands 2. performing  spatial planning 3. enabling efficient trip planning and control");
		rec.setVersion(1.0);
		rec.setIdentity("BP Identity");
		rec.setSector("Smart Transport");
		rec.setCountry("Italy");
		rec.setLocation("Italy");
		rec.setService("URLLC");
		rec.setLatency(1);
		rec.setDataRate(100);
		rec.setReliability(99.95);
		rec.setAvailability(99.9);
		rec.setMobility(300);
		rec.setBroadband(5000);
		rec.setCapacity(3.56);
		rec.setDensity(100000);
		rec.setSlicing("Y");
		rec.setSecurity("Y");
		rec.setDuration(80);
		
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
			
			rec.setFromCountry(countryFrom);
			rec.setNumOfDevs(Integer.parseInt(numOfDevis));
			rec.setDay(Integer.parseInt(dayCh));
			rec.setMonth(Integer.parseInt(monthCh));
			rec.setYear(Integer.parseInt(yearCh));
			rec.setHour(Integer.parseInt(hourCh));
			rec.setMinutes(Integer.parseInt(minutesCh));
			rec.setLatency(Integer.parseInt(latencyCh));
			rec.setDataRate(Integer.parseInt(dataRateCh));
			rec.setReliability(Double.parseDouble(reliabilityCh));
			rec.setAvailability(Double.parseDouble(availabilityCh));
			rec.setBroadband(Double.parseDouble(broadbandCh));
			rec.setMobility(Integer.parseInt(mobilityCh));
			rec.setCapacity(Double.parseDouble(capacityCh));
			rec.setDensity(Integer.parseInt(densityCh));
			rec.setSlicing(slicingCh);
			rec.setSecurity(securityCh);
			
			if(rec.getMinutes()<10)
				request.setAttribute("minutes", "0" + rec.getMinutes());
			else
				request.setAttribute("minutes", rec.getMinutes());
			if(rec.getHour()<10)
				request.setAttribute("hour", "0" + rec.getHour());
			else
				request.setAttribute("hour", rec.getHour());
			
			request.setAttribute("rec", rec);
			request.getRequestDispatcher("RecServEdit.jsp").forward(request, response);
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
			
			rec.setFromCountry(countryFrom);
			rec.setNumOfDevs(Integer.parseInt(numOfDevis));
			rec.setDay(Integer.parseInt(dayCh));
			rec.setMonth(Integer.parseInt(monthCh));
			rec.setYear(Integer.parseInt(yearCh));
			rec.setHour(Integer.parseInt(hourCh));
			rec.setMinutes(Integer.parseInt(minutesCh));
			rec.setLatency(Integer.parseInt(latencyCh));
			rec.setDataRate(Integer.parseInt(dataRateCh));
			rec.setReliability(Double.parseDouble(reliabilityCh));
			rec.setAvailability(Double.parseDouble(availabilityCh));
			rec.setBroadband(Double.parseDouble(broadbandCh));
			rec.setMobility(Integer.parseInt(mobilityCh));
			rec.setCapacity(Double.parseDouble(capacityCh));
			rec.setDensity(Integer.parseInt(densityCh));
			rec.setSlicing(slicingCh);
			rec.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				rec.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				rec.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				rec.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					rec.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					rec.setMinutes(00);
				}
				else
				{
					rec.setHour(23);
					rec.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(rec.getHour(), rec.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						rec.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						rec.setMinutes(00);
					}
					else
					{
						rec.setHour(23);
						rec.setMinutes(59);
					}
				}
			}
	
			if(rec.getMinutes()<10)
				request.setAttribute("minutes", "0" + rec.getMinutes());
			else
				request.setAttribute("minutes", rec.getMinutes());
			if(rec.getHour()<10)
				request.setAttribute("hour", "0" + rec.getHour());
			else
				request.setAttribute("hour", rec.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("rec", rec);
			request.getRequestDispatcher("RecServConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(rec.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + rec.getDay() + "/" + rec.getMonth() + "/" + rec.getYear() +"' AND launchedTo='" + rec.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(rec.getHour() + ".0" + rec.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(rec.getHour() + ".0" + rec.getMinutes()), rec.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + rec.getDay() + "/" + rec.getMonth() + "/" + rec.getYear() +"' AND launchedTo='" + rec.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(rec.getHour() + "." + rec.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(rec.getHour() + "." + rec.getMinutes()), rec.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(rec.getMinutes() < 10)
				dtime = Double.parseDouble(rec.getHour() + ".0" + rec.getMinutes());
			else
				dtime = Double.parseDouble(rec.getHour() + "." + rec.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(rec.getDay() + "/" + rec.getMonth() + "/" + rec.getYear(), dtime, rec.getDuration(), rec.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, rec.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(rec.getMinutes() < 10)
						db.insertIntoDB(rec.getName(), rec.getDescription(), rec.getVersion(), rec.getIdentity(), rec.getSector(), rec.getFromCountry(), rec.getCountry(), rec.getDay() + "/" + rec.getMonth() + "/" + rec.getYear(), Double.parseDouble(rec.getHour() + ".0" + rec.getMinutes()), endTime, rec.getDuration(), rec.getNumOfDevs(), rec.getService(), rec.getLatency(), rec.getDataRate(), rec.getReliability(), rec.getAvailability(), rec.getBroadband(), rec.getMobility(), rec.getCapacity(), rec.getDensity(), rec.getSlicing(), rec.getSecurity());
					else
						db.insertIntoDB(rec.getName(), rec.getDescription(), rec.getVersion(), rec.getIdentity(), rec.getSector(), rec.getFromCountry(), rec.getCountry(), rec.getDay() + "/" + rec.getMonth() + "/" + rec.getYear(), Double.parseDouble(rec.getHour() + "." + rec.getMinutes()), endTime, rec.getDuration(), rec.getNumOfDevs(), rec.getService(), rec.getLatency(), rec.getDataRate(), rec.getReliability(), rec.getAvailability(), rec.getBroadband(), rec.getMobility(), rec.getCapacity(), rec.getDensity(), rec.getSlicing(), rec.getSecurity());
					request.setAttribute("rec", rec);
					request.getRequestDispatcher("RecServConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(rec.getDay() + "/" + rec.getMonth() + "/" + rec.getYear(), Double.parseDouble(rec.getHour() + "." + rec.getMinutes()), rec.getDuration(), "Italy", db);
					System.out.println("av1: " +av1);
					
					if(rec.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + rec.getCountry() + " at " + rec.getHour() + ":0" + rec.getMinutes() + " on " + rec.getDay() + "/" + rec.getMonth() + "/" + rec.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + rec.getCountry() + " at " + rec.getHour() + ":" + rec.getMinutes() + " on " + rec.getDay() + "/" + rec.getMonth() + "/" + rec.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + rec.getDay() + "/" + rec.getMonth() + "/" + rec.getYear() + ".");
					}
					request.setAttribute("rec", rec);
					request.getRequestDispatcher("RecServEdit.jsp").forward(request, response);
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
			
			rec.setFromCountry(countryFrom);
			rec.setNumOfDevs(Integer.parseInt(numOfDevis));
			rec.setDay(Integer.parseInt(dayCh));
			rec.setMonth(Integer.parseInt(monthCh));
			rec.setYear(Integer.parseInt(yearCh));
			rec.setHour(Integer.parseInt(hourCh));
			rec.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				rec.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				rec.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				rec.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				rec.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				rec.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				rec.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				rec.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				rec.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				rec.setSlicing(slicingCh);
			if(securityCh != null)
				rec.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				rec.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				rec.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				rec.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					rec.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					rec.setMinutes(00);
				}
				else
				{
					rec.setHour(23);
					rec.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(rec.getHour(), rec.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						rec.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						rec.setMinutes(00);
					}
					else
					{
						rec.setHour(23);
						rec.setMinutes(59);
					}
				}
			}
	
			if(rec.getMinutes()<10)
				request.setAttribute("minutes", "0" + rec.getMinutes());
			else
				request.setAttribute("minutes", rec.getMinutes());
			if(rec.getHour()<10)
				request.setAttribute("hour", "0" + rec.getHour());
			else
				request.setAttribute("hour", rec.getHour());
			
			request.setAttribute("rec", rec);
			request.getRequestDispatcher("RecServCreated.jsp").forward(request, response);
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
				request.getRequestDispatcher("RecServFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				rec.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "RecServ");
				request.getRequestDispatcher("RecServFromCountry.jsp").forward(request, response);
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
					request.getRequestDispatcher("RecServNumOfDevs.jsp").forward(request, response);
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
							rec.setNumOfDevs(Integer.parseInt(tmp));
							numOfDevs = true;
						}
						else {
							if(Integer.parseInt(tmp)>300 || Integer.parseInt(tmp)<1) {
								request.setAttribute("message", "Not valid number(" + Integer.parseInt(tmp) + ") provided! Please select a number of devices between 1-300.");
							}
							intent = intent.replace("#", " ");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("RecServNumOfDevs.jsp").forward(request, response);
						}
					}
					else {
						intent = intent.replace("#", " ");
						request.setAttribute("message", "Invalid number of devices given!");
						request.setAttribute("intent", intent);
						request.getRequestDispatcher("RecServNumOfDevs.jsp").forward(request, response);
					}
				}
				else
				{
					request.setAttribute("intent", intent);
					request.getRequestDispatcher("RecServNumOfDevs.jsp").forward(request, response);
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
					request.getRequestDispatcher("RecServDate.jsp").forward(request, response);
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
							rec.setDay(Integer.parseInt(day));
						}
						else rec.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
					}
					else
						rec.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
							rec.setMonth(Integer.parseInt(month));
						}
						else
							rec.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
					}
					else
					{
						rec.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
							rec.setYear(Integer.parseInt(year));
						}
						else
							rec.setYear(Calendar.getInstance().get(Calendar.YEAR));
					}
					else
						rec.setYear(Calendar.getInstance().get(Calendar.YEAR));

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String givda = ""+rec.getYear()+"-"+rec.getMonth()+"-"+rec.getDay()+"";
					try {
						Date givdate = sdf.parse(givda);
						Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
						day = Integer.toString(rec.getDay());
						month = Integer.toString(rec.getMonth());
						year = Integer.toString(rec.getYear());
						
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
							request.getRequestDispatcher("RecServDate.jsp").forward(request, response);
						}
						else if(cd.dateCompare(rec.getDay(), rec.getMonth(), rec.getYear()) == 0) {
							
							for(int j=0; j<intent.length(); j++) {
								if(intent.charAt(j) == '/')
									intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
							}
							
							request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
							request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
							request.setAttribute("message", "Date given has wrong number of days.");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("RecServDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("RecServDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("RecServTime.jsp").forward(request, response);
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
							rec.setHour(Integer.parseInt(hour));
						else
							rec.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
					}
					else
						rec.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
							rec.setMinutes(Integer.parseInt(minutes));
						else
							rec.setMinutes(00);	
					}
					
					if(cd.dateCompare(rec.getDay(), rec.getMonth(), rec.getYear()) == 1) {
						if(ct.timeCompare(rec.getHour(), rec.getMinutes()) == 0) {
							if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
								rec.setHour(00);
								rec.setMinutes(00);
								rec.setDay(rec.getDay()+1);
								time = true;
							}
							else {
								rec.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
								rec.setMinutes(00);
								time = true;
							}
						}
						else
						{
							time = true;
						}
					}
					else if(cd.dateCompare(rec.getDay(), rec.getMonth(), rec.getYear()) == 2) {
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
						request.getRequestDispatcher("RecServTime.jsp").forward(request, response);
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
					request.getRequestDispatcher("RecServTime.jsp").forward(request, response);
				}
			}
			
			//redirection to blueprint page
			if(numOfDevs == true && fcountry == true && date == true && time == true) {
				if(rec.getMinutes()<10)
					request.setAttribute("minutes", "0" + rec.getMinutes());
				else
					request.setAttribute("minutes", rec.getMinutes());
				if(rec.getHour()<10)
					request.setAttribute("hour", "0" + rec.getHour());
				else
					request.setAttribute("hour", rec.getHour());
				
				request.setAttribute("rec", rec);
				request.getRequestDispatcher("RecServCreated.jsp").forward(request, response);
			}
		}
	}

}
