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

import blueprints.HlthMntrBp;
import blueprints.SmrtMblBp;

/**
 * Servlet implementation class HlthMntr
 */
public class HlthMntr extends HttpServlet {
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
		boolean country = false;
		boolean date = false;
		boolean time = false;
		String fcount1 = null, fcount2 = null;
		String count1 = null, count2 = null;
		
		//getting parameters from browser page
		String guided = request.getParameter("guided");
		String submit = request.getParameter("submit");
		String intent = request.getParameter("intent");
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
		
		intent = intent + " ";
		
		if(submit == null)
			submit = "nothing";
		
		if(fromCountry != null)
			intent = intent + " " + fromCountry + " ";
		
		//creation of blueprint and instantiation of some values
		HlthMntrBp hlth = new HlthMntrBp();
		hlth.setName("Health Monitoring and Forecasting");
		hlth.setDescription("Monitoring and assessment of vital signs for inferring the physical condition of an individual");
		hlth.setVersion(1.0);
		hlth.setIdentity("BP Identity");
		hlth.setSector("Smart Cities");
		hlth.setService("Critical mMTC");
		hlth.setCountry("Greece");
		hlth.setLatency(5);
		hlth.setDataRate(25);
		hlth.setReliability(99.99);
		hlth.setAvailability(99.9);
		hlth.setMobility(50);
		hlth.setBroadband(25);
		hlth.setCapacity(0.01);
		hlth.setDensity(60000);
		hlth.setSlicing("Y");
		hlth.setSecurity("Y");
		hlth.setDuration(80);
		
		//cancel button is pressed
		if(guided!=null && submit.equalsIgnoreCase("cancel")) {
			request.getRequestDispatcher("IntentPage.jsp").forward(request, response);
		}
		//edit button is pressed
		//update of values according to edit values
		else if(guided != null && submit.equalsIgnoreCase("edit")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
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
			
			hlth.setFromCountry(countryFrom);
			hlth.setDay(Integer.parseInt(dayCh));
			hlth.setMonth(Integer.parseInt(monthCh));
			hlth.setYear(Integer.parseInt(yearCh));
			hlth.setHour(Integer.parseInt(hourCh));
			hlth.setMinutes(Integer.parseInt(minutesCh));
			hlth.setLatency(Integer.parseInt(latencyCh));
			hlth.setDataRate(Integer.parseInt(dataRateCh));
			hlth.setReliability(Double.parseDouble(reliabilityCh));
			hlth.setAvailability(Double.parseDouble(availabilityCh));
			hlth.setBroadband(Double.parseDouble(broadbandCh));
			hlth.setMobility(Integer.parseInt(mobilityCh));
			hlth.setCapacity(Double.parseDouble(capacityCh));
			hlth.setDensity(Integer.parseInt(densityCh));
			hlth.setSlicing(slicingCh);
			hlth.setSecurity(securityCh);
			
			if(hlth.getMinutes()<10)
				request.setAttribute("minutes", "0" + hlth.getMinutes());
			else
				request.setAttribute("minutes", hlth.getMinutes());
			if(hlth.getHour()<10)
				request.setAttribute("hour", "0" + hlth.getHour());
			else
				request.setAttribute("hour", hlth.getHour());
			
			request.setAttribute("hlth", hlth);
			request.getRequestDispatcher("HlthMntrEdit.jsp").forward(request, response);
		}
		//confirmation of blueprint
		//insert of experiment in database if slot available else redirection to edit page
		else if(guided != null && submit.equalsIgnoreCase("confirm")) {
			String countryTo = request.getParameter("countryToChecked");
			String countryFrom = request.getParameter("countryFromChecked");
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
			
			hlth.setFromCountry(countryFrom);
			hlth.setDay(Integer.parseInt(dayCh));
			hlth.setMonth(Integer.parseInt(monthCh));
			hlth.setYear(Integer.parseInt(yearCh));
			hlth.setHour(Integer.parseInt(hourCh));
			hlth.setMinutes(Integer.parseInt(minutesCh));
			hlth.setLatency(Integer.parseInt(latencyCh));
			hlth.setDataRate(Integer.parseInt(dataRateCh));
			hlth.setReliability(Double.parseDouble(reliabilityCh));
			hlth.setAvailability(Double.parseDouble(availabilityCh));
			hlth.setBroadband(Double.parseDouble(broadbandCh));
			hlth.setMobility(Integer.parseInt(mobilityCh));
			hlth.setCapacity(Double.parseDouble(capacityCh));
			hlth.setDensity(Integer.parseInt(densityCh));
			hlth.setSlicing(slicingCh);
			hlth.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				hlth.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				hlth.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				hlth.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					hlth.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					hlth.setMinutes(00);
				}
				else
				{
					hlth.setHour(23);
					hlth.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(hlth.getHour(), hlth.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						hlth.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						hlth.setMinutes(00);
					}
					else
					{
						hlth.setHour(23);
						hlth.setMinutes(59);
					}
				}
			}
	
			if(hlth.getMinutes()<10)
				request.setAttribute("minutes", "0" + hlth.getMinutes());
			else
				request.setAttribute("minutes", hlth.getMinutes());
			if(hlth.getHour()<10)
				request.setAttribute("hour", "0" + hlth.getHour());
			else
				request.setAttribute("hour", hlth.getHour());
			
			//-----------------COMMENT NEXT TWO LINES AND UNCOMMENT THE DATABASE COMMENTS WHEN HAVING A DATABASE----------------------
			
			request.setAttribute("hlth", hlth);
			request.getRequestDispatcher("HlthMntrConfirmed.jsp").forward(request, response);
			
			/*Database db = new Database();
			
			String query;
			if(hlth.getMinutes() < 10)
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + hlth.getDay() + "/" + hlth.getMonth() + "/" + hlth.getYear() +"' AND launchedTo='" + hlth.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(hlth.getHour() + ".0" + hlth.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(hlth.getHour() + ".0" + hlth.getMinutes()), hlth.getDuration()) + "' ORDER BY timeOfExecution;";
			else
				query = "SELECT name, dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + hlth.getDay() + "/" + hlth.getMonth() + "/" + hlth.getYear() +"' AND launchedTo='" + hlth.getCountry() + "' AND endTimeOfExecution < '" + Double.parseDouble(hlth.getHour() + "." + hlth.getMinutes()) + "' AND timeOfExecution >= '" + db.estimateEndTime(Double.parseDouble(hlth.getHour() + "." + hlth.getMinutes()), hlth.getDuration()) + "' ORDER BY timeOfExecution;";
			
			ResultSet rs = db.getResult(query);
			
			double dtime;
			if(hlth.getMinutes() < 10)
				dtime = Double.parseDouble(hlth.getHour() + ".0" + hlth.getMinutes());
			else
				dtime = Double.parseDouble(hlth.getHour() + "." + hlth.getMinutes());
			
			try {
				if(!rs.next() && db.checkAvailability(hlth.getDay() + "/" + hlth.getMonth() + "/" + hlth.getYear(), dtime, hlth.getDuration(), hlth.getCountry(), db) == true)
				{
					double endTime = db.estimateEndTime(dtime, hlth.getDuration());
					rs.beforeFirst();
					System.out.println("End time " + endTime);
					if(hlth.getMinutes() < 10)
						db.insertIntoDB(hlth.getName(), hlth.getDescription(), hlth.getVersion(), hlth.getIdentity(), hlth.getSector(), hlth.getFromCountry(), hlth.getCountry(), hlth.getDay() + "/" + hlth.getMonth() + "/" + hlth.getYear(), Double.parseDouble(hlth.getHour() + ".0" + hlth.getMinutes()), endTime, hlth.getDuration(), 0, hlth.getService(), hlth.getLatency(), hlth.getDataRate(), hlth.getReliability(), hlth.getAvailability(), hlth.getBroadband(), hlth.getMobility(), hlth.getCapacity(), hlth.getDensity(), hlth.getSlicing(), hlth.getSecurity());
					else
						db.insertIntoDB(hlth.getName(), hlth.getDescription(), hlth.getVersion(), hlth.getIdentity(), hlth.getSector(), hlth.getFromCountry(), hlth.getCountry(), hlth.getDay() + "/" + hlth.getMonth() + "/" + hlth.getYear(), Double.parseDouble(hlth.getHour() + "." + hlth.getMinutes()), endTime, hlth.getDuration(), 0, hlth.getService(), hlth.getLatency(), hlth.getDataRate(), hlth.getReliability(), hlth.getAvailability(), hlth.getBroadband(), hlth.getMobility(), hlth.getCapacity(), hlth.getDensity(), hlth.getSlicing(), hlth.getSecurity());
					request.setAttribute("hlth", hlth);
					request.getRequestDispatcher("HlthMntrConfirmed.jsp").forward(request, response);
				}
				else
				{
					
					String av1 = db.nextAvailable(hlth.getDay() + "/" + hlth.getMonth() + "/" + hlth.getYear(), Double.parseDouble(hlth.getHour() + "." + hlth.getMinutes()), hlth.getDuration(), "Greece", db);
					System.out.println("av1: " +av1);
					
					if(hlth.getMinutes() < 10)
						request.setAttribute("notAvailable", "Service not available in " + hlth.getCountry() + " at " + hlth.getHour() + ":0" + hlth.getMinutes() + " on " + hlth.getDay() + "/" + hlth.getMonth() + "/" + hlth.getYear() + "!");
					else
						request.setAttribute("notAvailable", "Service not available in " + hlth.getCountry() + " at " + hlth.getHour() + ":" + hlth.getMinutes() + " on " + hlth.getDay() + "/" + hlth.getMonth() + "/" + hlth.getYear() + "!");
					
					if(av1.equals("Not available!"))
					{
						request.setAttribute("suggestion", "Try another day!");
					}
					else
					{
						request.setAttribute("suggestion", "You can try in " + av1 + " on " + hlth.getDay() + "/" + hlth.getMonth() + "/" + hlth.getYear() + ".");
					}
					request.setAttribute("hlth", hlth);
					request.getRequestDispatcher("HlthMntrEdit.jsp").forward(request, response);
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
			
			hlth.setFromCountry(countryFrom);
			hlth.setDay(Integer.parseInt(dayCh));
			hlth.setMonth(Integer.parseInt(monthCh));
			hlth.setYear(Integer.parseInt(yearCh));
			hlth.setHour(Integer.parseInt(hourCh));
			hlth.setMinutes(Integer.parseInt(minutesCh));
			if(latencyCh != null)
				hlth.setLatency(Integer.parseInt(latencyCh));
			if(dataRateCh != null)
				hlth.setDataRate(Integer.parseInt(dataRateCh));
			if(reliabilityCh != null)
				hlth.setReliability(Double.parseDouble(reliabilityCh));
			if(availabilityCh != null)
				hlth.setAvailability(Double.parseDouble(availabilityCh));
			if(mobilityCh != null)
				hlth.setMobility(Integer.parseInt(mobilityCh));
			if(broadbandCh != null)
				hlth.setBroadband(Double.parseDouble(broadbandCh));
			if(capacityCh != null)
				hlth.setCapacity(Double.parseDouble(capacityCh));
			if(densityCh != null)
				hlth.setDensity(Integer.parseInt(densityCh));
			if(slicingCh != null)
				hlth.setSlicing(slicingCh);
			if(securityCh != null)
				hlth.setSecurity(securityCh);
			
			if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 0) {
				hlth.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				hlth.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
				hlth.setYear(Calendar.getInstance().get(Calendar.YEAR));
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
					hlth.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
					hlth.setMinutes(00);
				}
				else
				{
					hlth.setHour(23);
					hlth.setMinutes(59);
				}
			}
			else if(cd.dateCompare(Integer.parseInt(dayCh), Integer.parseInt(monthCh), Integer.parseInt(yearCh)) == 1) {
				
				if(ct.timeCompare(hlth.getHour(), hlth.getMinutes()) == 0) {
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<23) {
						hlth.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+1);
						hlth.setMinutes(00);
					}
					else
					{
						hlth.setHour(23);
						hlth.setMinutes(59);
					}
				}
			}
	
			if(hlth.getMinutes()<10)
				request.setAttribute("minutes", "0" + hlth.getMinutes());
			else
				request.setAttribute("minutes", hlth.getMinutes());
			if(hlth.getHour()<10)
				request.setAttribute("hour", "0" + hlth.getHour());
			else
				request.setAttribute("hour", hlth.getHour());
			
			request.setAttribute("hlth", hlth);
			request.getRequestDispatcher("HlthMntrCreated.jsp").forward(request, response);
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
				request.getRequestDispatcher("HlthMntrFromCountry.jsp").forward(request, response);
			}
			else if(fcount2 == null) {
				hlth.setFromCountry(fcount1);
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
				request.setAttribute("servlet", "HlthMntr");
				request.getRequestDispatcher("HlthMntrFromCountry.jsp").forward(request, response);
				
			}
			
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
					request.getRequestDispatcher("HlthMntrDate.jsp").forward(request, response);
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
							hlth.setDay(Integer.parseInt(day));
						}
						else hlth.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
					}
					else
						hlth.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
							hlth.setMonth(Integer.parseInt(month));
						}
						else
							hlth.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
					}
					else
					{
						hlth.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
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
							hlth.setYear(Integer.parseInt(year));
						}
						else
							hlth.setYear(Calendar.getInstance().get(Calendar.YEAR));
					}
					else
						hlth.setYear(Calendar.getInstance().get(Calendar.YEAR));
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String givda = ""+hlth.getYear()+"-"+hlth.getMonth()+"-"+hlth.getDay()+"";
					try {
						Date givdate = sdf.parse(givda);
						Date curdate = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
						
						day = Integer.toString(hlth.getDay());
						month = Integer.toString(hlth.getMonth());
						year = Integer.toString(hlth.getYear());
						
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
							request.getRequestDispatcher("HlthMntrDate.jsp").forward(request, response);
						}
						else if(cd.dateCompare(hlth.getDay(), hlth.getMonth(), hlth.getYear()) == 0) {
							
							for(int j=0; j<intent.length(); j++) {
								if(intent.charAt(j) == '/')
									intent = intent.substring(0, j) + ' ' + intent.substring(j+1);
							}
							
							request.setAttribute("day", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
							request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
							request.setAttribute("message", "Date given has wrong number of days.");
							request.setAttribute("intent", intent);
							request.getRequestDispatcher("HlthMntrDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("HlthMntrDate.jsp").forward(request, response);
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
					request.getRequestDispatcher("HlthMntrTime.jsp").forward(request, response);
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
							hlth.setHour(Integer.parseInt(hour));
						else
							hlth.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
					}
					else
						hlth.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

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
							hlth.setMinutes(Integer.parseInt(minutes));
						else
							hlth.setMinutes(00);	
					}
					
					if(cd.dateCompare(hlth.getDay(), hlth.getMonth(), hlth.getYear()) == 1) {
						if(ct.timeCompare(hlth.getHour(), hlth.getMinutes()) == 0) {
							if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 23) {
								hlth.setHour(00);
								hlth.setMinutes(00);
								hlth.setDay(hlth.getDay()+1);
								time = true;
							}
							else {
								hlth.setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1);
								hlth.setMinutes(00);
								time = true;
							}
						}
						else
						{
							time = true;
						}
					}
					else if(cd.dateCompare(hlth.getDay(), hlth.getMonth(), hlth.getYear()) == 2) {
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
						request.getRequestDispatcher("HltgMntrTime.jsp").forward(request, response);
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
					request.getRequestDispatcher("HlthMntrTime.jsp").forward(request, response);
				}
			}
			
			//redirection to blueprint page
			if(fcountry == true && date == true && time == true) {
				if(hlth.getMinutes()<10)
					request.setAttribute("minutes", "0" + hlth.getMinutes());
				else
					request.setAttribute("minutes", hlth.getMinutes());
				if(hlth.getHour()<10)
					request.setAttribute("hour", "0" + hlth.getHour());
				else
					request.setAttribute("hour", hlth.getHour());
				
				request.setAttribute("hlth", hlth);
				request.getRequestDispatcher("HlthMntrCreated.jsp").forward(request, response);
			}
		}
	}

}
