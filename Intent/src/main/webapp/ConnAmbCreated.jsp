<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connected Ambulance Bp Created</title>
<link rel="stylesheet" href="IntentCSS.css">
</head>
<body>
	
	<div class="header">
		<a href="https://wings-ict-solutions.eu/" target="_blank">
			<img src="WebPages/wingslogo.png" alt="Wings Logo" style="height:100px"/>		
		</a>	
	</div>
	
	<div class="topnav">
 		<a href="IntentPage.jsp">New Intent</a>
	 	<a href="GuidedSelection.jsp">Guided Selection</a>
  		<a href="https://www.5g-eve.eu/" target="_blank">5G-EVE project</a>
  		<a href="https://www.5g-eve.eu/wp-content/uploads/2018/11/5g-eve-d1.1-requirement-definition-analysis-from-participant-verticals.pdf" target="_blank">5G-EVE use cases</a>
  		<a href="HelpPage.jsp">Help</a>
	</div>
	
	
	<div class="row">
  		<div class="columnCreated">
    		<h1>Connected Ambulance Blueprint Created</h1>
    		
    		<h5>If the Blueprint satisfies your needs please press confirm, otherwise press the edit button to modify the blueprint</h5>
	
	<table border="1" align="center">
		<tr>
			<th colspan="2">Blueprint Information</b></td>
		</tr>
		<tr>
			<td><b>Name</b></td>
			<td><i>${amb.name }</i></td>
		</tr>
		<tr>
			<td><b>Description</b></td>
			<td><i>${amb.description }</i></td>
		</tr>
		<tr>
			<td><b>Version</b></td>
			<td><i>${amb.version }</i></td>
		</tr>
		<tr>
			<td><b>Identity</b></td>
			<td><i>${amb.identity }</i></td>
		</tr>
		<tr>
			<td><b>Sector</b></td>
			<td><i>${amb.sector }</i></td>
		</tr>
		<tr>
			<td><b>Launched from</b></td>
			<td><i>${amb.fromCountry }</i></td>
		</tr>
		<tr>
			<td><b>Executed to</b></td>
			<td><i>${amb.country }</i></td>
		</tr>
		<tr>
			<td><b>Date of Execution (dd/mm/yyyy)</b></td>
			<td><i>${amb.day }/${amb.month }/${amb.year }</i>
		</tr>
		<tr>
			<td><b>Time of Execution</b></td>
			<td><i>${hour }:${minutes }</i>
		</tr>
		<!--  <tr>
			<td><b>Camera view data rate</b></td>
			<td><i>${amb.dataRate }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Latency</b></td>
			<td><i>${amb.latency }ms</i></td>
		</tr> -->
		<tr>
			<td><b>Service</b></td>
			<td><i>${amb.service }</i></td>
		</tr>
		<tr>
			<td><b>Latency</b></td>
			<td><i>${amb.latency }ms</i></td>
		</tr>
		<tr>
			<td><b>Data Rate</b></td>
			<td><i>${amb.dataRate }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Reliability</b></td>
			<td><i>${amb.reliability }%</i></td>
		</tr>
		<tr>
			<td><b>Availability</b></td>
			<td><i>${amb.availability }%</i></td>
		</tr>
		<tr>
			<td><b>Mobility</b></td>
			<td><i>${amb.mobility }Km/h</i></td>
		</tr>
		<tr>
			<td><b>Broadband Connectivity</b></td>
			<td><i>${amb.broadband }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Capacity</b></td>
			<td><i>${amb.capacity }Mbps/m2</i></td>
		</tr>
		<tr>
			<td><b>Device Density</b></td>
			<td><i>${amb.density }Devices/Km2</i></td>
		</tr>
		<tr>
			<td><b>Network Slicing</b></td>
			<td><i>${amb.slicing }</i></td>
		</tr>
		<tr>
			<td><b>Security</b></td>
			<td><i>${amb.security }</i></td>
		</tr>
		<!--  <tr>
			<td><b>Reliability</b></td>
			<td><i>99.999%</i></td>
		</tr>
		<tr>
			<td><b>Network Availability</b></td>
			<td><i>99.999%</i></td>
		</tr>
		<tr>
			<td><b>Security</b></td>
			<td><i>Strong</i></td>
		</tr>
		<tr>
			<td><b>Atomic Functional Components involved</b></td>
			<td><i>RAN<br>MEC server (Edge PLC)<br>Compute Node</i>
		</tr>
		<tr>
			<td><b>Connectivity Service</b></td>
			<td><i>4G/5G</i></td>
		</tr>
		<tr>
			<td><b>External Interconnection</b></td>
			<td><i></i></td>
		</tr>
		<tr>
			<td><b>Internal Interconnection</b></td>
			<td><i></i></td>
		</tr>
		<tr>
			<td><b>Service Constraints</b></td>
			<td><i>
			<ul>
				<li><b>Network slicing to be flexible enough and guarantee the proper quality for creating an instance of an entire network virtually</b></li>
			</ul>
			</i></td>
		</tr>
		<tr>
			<td><b>Management and control capabilities of the tenant</b></td>
			<td><i>Provider managed</i></td>
		</tr>
		<tr>
			<td><b>SLA</b></td>
			<td><i></i></td>
		</tr>
		<tr>
			<td><b>Monitoring</b></td>
			<td><i></i></td>
		</tr>
		<tr>
			<td><b>Lifetime</b></td>
			<td><i>On-demand, without limitation</i></td>
		</tr>
		<tr>
			<td><b>Charging</b></td>
			<td><i>To be defined</i></td>
		</tr> -->
	</table>
		
		<br>
		<form method="POST" action="ConnAmb">
			<input type="hidden" name="guided" value="true"/>
			<input type="hidden" name="countryFromChecked" value="${amb.fromCountry }"/>
			<input type="hidden" name="latencyChecked" value="${amb.latency }"/>
			<input type="hidden" name="dataRateChecked" value="${amb.dataRate }"/>
			<input type="hidden" name="dayChecked" value="${amb.day }"/>
			<input type="hidden" name="monthChecked" value="${amb.month }"/>
			<input type="hidden" name="yearChecked" value="${amb.year }"/>
			<input type="hidden" name="hourChecked" value="${amb.hour }"/>
			<input type="hidden" name="minutesChecked" value="${amb.minutes }"/>
			<input type="hidden" name="latencyChecked" value="${amb.latency }"/>
			<input type="hidden" name="dataRateChecked" value="${amb.dataRate }"/>
			<input type="hidden" name="reliabilityChecked" value="${amb.reliability }"/>
			<input type="hidden" name="availabilityChecked" value="${amb.availability }"/>
			<input type="hidden" name="mobilityChecked" value="${amb.mobility }"/>
			<input type="hidden" name="broadbandChecked" value="${amb.broadband }"/>
			<input type="hidden" name="capacityChecked" value="${amb.capacity }"/>
			<input type="hidden" name="densityChecked" value="${amb.density }"/>
			<input type="hidden" name="slicingChecked" value="${amb.slicing }"/>
			<input type="hidden" name="securityChecked" value="${amb.security }"/>
			<input type="submit" name="submit" value="Cancel"/>
			<input type="submit" name="submit" value="Edit"/>
			<input type="submit" name="submit" value="Confirm"/>
		</form>
  	</div>
	</div>
	<div class="footerCreated">
  			<p>5G European Validation platform for Extensive trials</p>
	</div>
	
</body>
</html>