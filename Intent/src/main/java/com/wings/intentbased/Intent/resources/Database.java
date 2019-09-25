package com.wings.intentbased.Intent.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class Database {
	
	//a function to insert new element in the database
	public void insertIntoDB(String name, String description, double version, String identity, String sector, String launchedFrom, String launchedTo, String dateOfExecution, double timeOfExecution, double endTimeOfExecution, double duration, int numberOfDevs, String service, int latency, int dataRate, double reliability, double availability, double broadbandConnectivity, int mobility, double capacity, int density, String networkSlicing, String security) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Error in forName");
			//e.printStackTrace();
		}
		
		try {
			
			String host = "jdbc:mysql://localhost:3306/5g_eve";
			String uName = "root";
			String uPass = "root";
			
			Connection con = DriverManager.getConnection(host, uName, uPass);
			
			String query = " insert into events (name, description, version, identity, sector, launchedFrom, launchedTo, dateOfExecution, timeOfExecution, endTimeOfExecution, Duration, numberOfDevs, service, latency, dataRate, reliability, availability, mobility, broadbandConnectivity, capacity, density, networkSlicing, security) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, description);
			preparedStmt.setDouble(3, version);
			preparedStmt.setString(4, identity);
			preparedStmt.setString(5, sector);
			preparedStmt.setString(6, launchedFrom);
			preparedStmt.setString(7, launchedTo);
			preparedStmt.setString(8, dateOfExecution);
			preparedStmt.setDouble(9, timeOfExecution);
			preparedStmt.setDouble(10, endTimeOfExecution);
			preparedStmt.setDouble(11, duration);
			preparedStmt.setInt(12, numberOfDevs);
			preparedStmt.setString(13, service);
			preparedStmt.setInt(14, latency);
			preparedStmt.setInt(15, dataRate);
			preparedStmt.setDouble(16, reliability);
			preparedStmt.setDouble(17, availability);
			preparedStmt.setInt(18, mobility);
			preparedStmt.setDouble(19, broadbandConnectivity);
			preparedStmt.setDouble(20, capacity);
			preparedStmt.setInt(21, density);
			preparedStmt.setString(22, networkSlicing);
			preparedStmt.setString(23, security);
			
			preparedStmt.execute();
			
			con.close();
			System.out.println("Event added successfully");
			
		}
		catch (SQLException e) {
			
			System.out.println("Error connecting");
		}
		
	}
	
	//returns a query result from the database
	public ResultSet getResult(String query) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Error in forName");
			//e.printStackTrace();
		}
		
		try {
			
			String host = "jdbc:mysql://localhost:3306/5g_eve";
			String uName = "root";
			String uPass = "root";
			
			Connection con = DriverManager.getConnection(host, uName, uPass);
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			return rs;
			
			//con.close();
			
		}
		catch (SQLException e) {
			
			System.out.println("Error connecting");
			return null;
		}
		
		
	}
	
	//a function that checks if the timeslot given is available
	public boolean checkAvailability (String date, double start, int duration, String country, Database db) {
		
		double endTime = db.estimateEndTime(start, duration);
		String query = "SELECT dateOfExecution, timeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + date +"' AND launchedTo='" + country + "' AND endTimeOfExecution > '" + start + "' AND timeOfExecution < '" + endTime + "' ORDER BY timeOfExecution;";
		System.out.println("Query inside: " + query);
		ResultSet rs = db.getResult(query);
		try {
			if(!rs.next() && endTime != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Empty");
			return false;
		}
	}
	
	//a function that returns the next available time for the service provided at a specific country
	public String nextAvailable (String date, double start, int duration, String country, Database db) {
		
		double endTime = 0;
		double startTime = start;
		double nextAvailTime = 0;
		//String query = "SELECT name, dateOfExecution, timeOfExecution, endTimeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + date +"' AND launchedTo='" + country + "' AND timeOfExecution >= '" + start + "' ORDER BY timeOfExecution;";
		String query;
		if(Calendar.getInstance().get(Calendar.MINUTE) < 10)
			query = "SELECT dateOfExecution, timeOfExecution, endTimeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + date +"' AND launchedTo='" + country + "' AND timeOfExecution > '" + Double.parseDouble(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ".0" + Calendar.getInstance().get(Calendar.MINUTE)) + "' ORDER BY timeOfExecution;";
		else
			query = "SELECT dateOfExecution, timeOfExecution, endTimeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + date +"' AND launchedTo='" + country + "' AND timeOfExecution > '" + Double.parseDouble(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "." + Calendar.getInstance().get(Calendar.MINUTE)) + "' ORDER BY timeOfExecution;";
		ResultSet rs = db.getResult(query);
		if(Calendar.getInstance().get(Calendar.MINUTE) < 10)
			startTime = Double.parseDouble(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ".0" + Calendar.getInstance().get(Calendar.MINUTE));
		else
			startTime = Double.parseDouble(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "." + Calendar.getInstance().get(Calendar.MINUTE));
		
		
		try {
			while(rs.next())
			{
				endTime = db.estimateEndTime(startTime, duration);
				if(endTime < rs.getDouble("timeOfExecution"))
				{
					nextAvailTime = startTime;
					break;
				}
				else 
				{
					startTime = rs.getDouble("endTimeOfExecution");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double ntime;
		if(Calendar.getInstance().get(Calendar.MINUTE) < 10)
			ntime = Double.parseDouble(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ".0" + Calendar.getInstance().get(Calendar.MINUTE));
		else
			ntime = Double.parseDouble(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "." + Calendar.getInstance().get(Calendar.MINUTE));
		
		String query2 = "SELECT dateOfExecution, timeOfExecution, endTimeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + date +"' AND launchedTo='" + country + "' AND timeOfExecution <= '" + ntime + "' AND endTimeOfExecution >= '" + ntime + "' ORDER BY timeOfExecution;";
		rs = db.getResult(query2);
		try {
			if(rs.next())
			{
				startTime = rs.getDouble("endTimeOfExecution");
				String query3 = "SELECT dateOfExecution, timeOfExecution, endTimeOfExecution, launchedTo FROM events WHERE dateOfExecution='" + date +"' AND launchedTo='" + country + "' AND timeOfExecution >= '" + ntime + "' ORDER BY timeOfExecution;";
				ResultSet rs1 = db.getResult(query3);
				try {
					while(rs1.next())
					{
						endTime = db.estimateEndTime(startTime, duration);
						if(endTime < rs1.getDouble("timeOfExecution"))
						{
							nextAvailTime = startTime;
							break;
						}
						else 
						{
							startTime = rs1.getDouble("endTimeOfExecution");
						}
					}
				} catch (SQLException e) {
					System.out.println("Den iparxei epomeno");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(nextAvailTime == 0 && db.estimateEndTime(startTime, duration) != 0)
			nextAvailTime = startTime;
		
		
		
		if(nextAvailTime != 0)
		{
			if((int) 100*(nextAvailTime - Math.floor(nextAvailTime)) < 10)
				return country + " at " + (int) Math.floor(nextAvailTime) + ":0" + ((int) Math.round(100*(nextAvailTime - Math.floor(nextAvailTime))));
			else
				return country + " at " + (int) Math.floor(nextAvailTime) + ":" + ((int) Math.round(100*(nextAvailTime - Math.floor(nextAvailTime))));
		}
		else
			return "Not available!";
	}
	
	//a function that estimates the time the experiment is going to end according to its duration
	public double estimateEndTime (double start, int duration) {
		int endHour = (int) Math.floor(start);
		int endMinutes = (int) (100*(start - Math.floor(start)));
		
		for (int i=0; i<duration; i++) {
			if((endMinutes%59 == 0) && endMinutes != 0)
			{
				endHour = endHour + 1;
				endMinutes = 0;
			}
			else
			{
				endMinutes = endMinutes + 1;
			}
			
			if(endHour == 24)
				break;
		}
		
		if(endHour == 24)
			return 0;
		else if(endMinutes < 10)
			return Double.parseDouble(endHour + ".0" + endMinutes);
		else
			return Double.parseDouble(endHour + "." + endMinutes);
	}
}
