<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Health Monitoring and Forecasting Created</title>
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
    		<h1>Health Monitoring and Forecasting Blueprint Created</h1>
    		
    		<h5>If the Blueprint satisfies your needs please press confirm, otherwise press the edit button to modify the blueprint</h5>
	
	<table border="1" align="center">
		<tr>
			<th colspan="2">Blueprint Information</b></td>
		</tr>
		<tr>
			<td><b>Name</b></td>
			<td><i>${hlth.name }</i></td>
		</tr>
		<tr>
			<td><b>Description</b></td>
			<td><i>${hlth.description }</i></td>
		</tr>
		<tr>
			<td><b>Version</b></td>
			<td><i>${hlth.version }</i></td>
		</tr>
		<tr>
			<td><b>Identity</b></td>
			<td><i>${hlth.identity }</i></td>
		</tr>
		<tr>
			<td><b>Sector</b></td>
			<td><i>${hlth.sector }</i></td>
		</tr>
		<tr>
			<td><b>Launched from</b></td>
			<td><i>${hlth.fromCountry }</i></td>
		</tr>
		<tr>
			<td><b>Executed to</b></td>
			<td><i>${hlth.country }</i></td>
		</tr>
		<tr>
			<td><b>Date of Execution (dd/mm/yyyy)</b></td>
			<td><i>${hlth.day }/${hlth.month }/${hlth.year }</i>
		</tr>
		<tr>
			<td><b>Time of Execution</b></td>
			<td><i>${hour }:${minutes }</i>
		</tr>
		<tr>
			<td><b>Service</b></td>
			<td><i>${hlth.service }</i></td>
		</tr>
		<tr>
			<td><b>Latency</b></td>
			<td><i>${hlth.latency }ms</i></td>
		</tr>
		<tr>
			<td><b>Data Rate</b></td>
			<td><i>${hlth.dataRate }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Reliability</b></td>
			<td><i>${hlth.reliability }%</i></td>
		</tr>
		<tr>
			<td><b>Availability</b></td>
			<td><i>${hlth.availability }%</i></td>
		</tr>
		<tr>
			<td><b>Mobility</b></td>
			<td><i>${hlth.mobility }Km/h</i></td>
		</tr>
		<tr>
			<td><b>Broadband Connectivity</b></td>
			<td><i>${hlth.broadband }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Capacity</b></td>
			<td><i>${hlth.capacity }Mbps/m2</i></td>
		</tr>
		<tr>
			<td><b>Device Density</b></td>
			<td><i>${hlth.density }Devices/Km2</i></td>
		</tr>
		<tr>
			<td><b>Network Slicing</b></td>
			<td><i>${hlth.slicing }</i></td>
		</tr>
		<tr>
			<td><b>Security</b></td>
			<td><i>${hlth.security }</i></td>
		</tr>
		<!--  <tr>
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
			<td><i></i></td>
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
		<form method="POST" action="HlthMntr">
			<input type="hidden" name="guided" value="true"/>
			<input type="hidden" name="countryToChecked" value="${hlth.country }"/>
			<input type="hidden" name="countryFromChecked" value="${hlth.fromCountry }"/>
			<input type="hidden" name="dayChecked" value="${hlth.day }"/>
			<input type="hidden" name="monthChecked" value="${hlth.month }"/>
			<input type="hidden" name="yearChecked" value="${hlth.year }"/>
			<input type="hidden" name="hourChecked" value="${hlth.hour }"/>
			<input type="hidden" name="minutesChecked" value="${hlth.minutes }"/>
			<input type="hidden" name="latencyChecked" value="${hlth.latency }"/>
			<input type="hidden" name="dataRateChecked" value="${hlth.dataRate }"/>
			<input type="hidden" name="reliabilityChecked" value="${hlth.reliability }"/>
			<input type="hidden" name="availabilityChecked" value="${hlth.availability }"/>
			<input type="hidden" name="mobilityChecked" value="${hlth.mobility }"/>
			<input type="hidden" name="broadbandChecked" value="${hlth.broadband }"/>
			<input type="hidden" name="capacityChecked" value="${hlth.capacity }"/>
			<input type="hidden" name="densityChecked" value="${hlth.density }"/>
			<input type="hidden" name="slicingChecked" value="${hlth.slicing }"/>
			<input type="hidden" name="securityChecked" value="${hlth.security }"/>
			<input type="submit" name="submit" value="Cancel"/>
			<input type="submit" name="submit" value="Edit"/>
			<input type="submit" name="submit" value="Confirm"/>
		</form>
  	</div>
	
	<div class="footerCreated">
  			<p>5G European Validation platform for Extensive trials</p>
	</div>
	
</body>
</html>