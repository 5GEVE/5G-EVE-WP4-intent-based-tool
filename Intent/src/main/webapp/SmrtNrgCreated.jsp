<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Smart Energy Bp Created</title>
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
    		<h1>Smart Energy Blueprint Created</h1>
    		
    		<h5>If the Blueprint satisfies your needs please press confirm, otherwise press the edit button to modify the blueprint</h5>
	
	<table border="1" align="center">
		<tr>
			<th colspan="2">Blueprint Information</b></td>
		</tr>
		<tr>
			<td><b>Name</b></td>
			<td><i>${nrg.name }</i></td>
		</tr>
		<tr>
			<td><b>Description</b></td>
			<td><i>${nrg.description }</i></td>
		</tr>
		<tr>
			<td><b>Version</b></td>
			<td><i>${nrg.version }</i></td>
		</tr>
		<tr>
			<td><b>Identity</b></td>
			<td><i>${nrg.identity }</i></td>
		</tr>
		<tr>
			<td><b>Sector</b></td>
			<td><i>${nrg.sector }</i></td>
		</tr>
		<tr>
			<td><b>Launched from</b></td>
			<td><i>${nrg.fromCountry }</i></td>
		</tr>
		<tr>
			<td><b>Executed to</b></td>
			<td><i>${nrg.country }</i></td>
		</tr>
		<tr>
			<td><b>Date of Execution (dd/mm/yyyy)</b></td>
			<td><i>${nrg.day }/${nrg.month }/${nrg.year }</i>
		</tr>
		<tr>
			<td><b>Time of Execution</b></td>
			<td><i>${hour }:${minutes }</i>
		</tr>
		<!--  <tr>
			<td><b>Number of devices/km2</b></td>
			<td><i>${nrg.density }</i></td>
		</tr>
		<tr>
			<td><b>Average data rate</b></td>
			<td><i>${nrg.dataRate } Mbps</i></td>
		</tr> -->
		<tr>
			<td><b>Service</b></td>
			<td><i>${nrg.service }</i></td>
		</tr>
		<tr>
			<td><b>Latency</b></td>
			<td><i>${nrg.latency }ms</i></td>
		</tr>
		<tr>
			<td><b>Data Rate</b></td>
			<td><i>${nrg.dataRate }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Reliability</b></td>
			<td><i>${nrg.reliability }%</i></td>
		</tr>
		<tr>
			<td><b>Availability</b></td>
			<td><i>${nrg.availability }%</i></td>
		</tr>
		<tr>
			<td><b>Broadband Connectivity</b></td>
			<td><i>${nrg.broadband }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Capacity</b></td>
			<td><i>${nrg.capacity }Mbps/m2</i></td>
		</tr>
		<tr>
			<td><b>Device Density</b></td>
			<td><i>${nrg.density }Devices/Km2</i></td>
		</tr>
		<tr>
			<td><b>Network Slicing</b></td>
			<td><i>${nrg.slicing }</i></td>
		</tr>
		<tr>
			<td><b>Security</b></td>
			<td><i>${nrg.security }</i></td>
		</tr>
		<!-- <tr>
			<td><b>Power Efficiency</b></td>
			<td><i>Battery lifetime in all equipment at least 10-15 years</i>
		</tr>
		<tr>
			<td><b>Coverage and penetration</b></td>
			<td><i>99.999% network availability</i></td>
		</tr>
		<tr>
			<td><b>Reliability</b></td>
			<td><i>99.999% reliability</i></td>
		</tr>
		<tr>
			<td><b>Security</b></td>
			<td><i>Strong</i></td>
		</tr>
		<tr>
			<td><b>Latency in fault detection</b></td>
			<td><i>5ms</i></td>
		</tr>
		<tr>
			<td><b>Restoration time including remote computing</b></td>
			<td><i>30sec</i></td>
		</tr>
		<tr>
			<td><b>Atomic Functional Components involved</b></td>
			<td><i>vEPC<br>RAN<br>MEC server (Edge PLC)<br>Compute Node</i>
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
		<form method="POST" action="SmrtNrg">
			<input type="hidden" name="guided" value="true"/>
			<input type="hidden" name="countryFromChecked" value="${nrg.fromCountry }"/>
			<input type="hidden" name="countryToChecked" value="${nrg.country }"/>
			<input type="hidden" name="numOfDevsChecked" value="${nrg.density }"/>
			<input type="hidden" name="dataRateChecked" value="${nrg.dataRate }"/>
			<input type="hidden" name="dayChecked" value="${nrg.day }"/>
			<input type="hidden" name="monthChecked" value="${nrg.month }"/>
			<input type="hidden" name="yearChecked" value="${nrg.year }"/>
			<input type="hidden" name="hourChecked" value="${nrg.hour }"/>
			<input type="hidden" name="minutesChecked" value="${nrg.minutes }"/>
			<input type="hidden" name="latencyChecked" value="${nrg.latency }"/>
			<input type="hidden" name="dataRateChecked" value="${nrg.dataRate }"/>
			<input type="hidden" name="reliabilityChecked" value="${nrg.reliability }"/>
			<input type="hidden" name="availabilityChecked" value="${nrg.availability }"/>
			<input type="hidden" name="mobilityChecked" value="${nrg.mobility }"/>
			<input type="hidden" name="broadbandChecked" value="${nrg.broadband }"/>
			<input type="hidden" name="capacityChecked" value="${nrg.capacity }"/>
			<input type="hidden" name="densityChecked" value="${nrg.density }"/>
			<input type="hidden" name="slicingChecked" value="${nrg.slicing }"/>
			<input type="hidden" name="securityChecked" value="${nrg.security }"/>
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