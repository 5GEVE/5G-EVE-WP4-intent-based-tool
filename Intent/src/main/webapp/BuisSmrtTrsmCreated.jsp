<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Business Augmented Booth Bp Created</title>
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
    		<h1>Business Augmented Booth Blueprint Created</h1>
    		
    		<h5>If the Blueprint satisfies your needs please press confirm, otherwise press the edit button to modify the blueprint</h5>
	
	<table border="1" align="center">
		<tr>
			<th colspan="2">Blueprint Information</b></td>
		</tr>
		<tr>
			<td><b>Name</b></td>
			<td><i>${bus.name }</i></td>
		</tr>
		<tr>
			<td><b>Description</b></td>
			<td><i>${bus.description }</i></td>
		</tr>
		<tr>
			<td><b>Version</b></td>
			<td><i>${bus.version }</i></td>
		</tr>
		<tr>
			<td><b>Identity</b></td>
			<td><i>${bus.identity }</i></td>
		</tr>
		<tr>
			<td><b>Sector</b></td>
			<td><i>${bus.sector }</i></td>
		</tr>
		<tr>
			<td><b>Launched from</b></td>
			<td><i>${bus.fromCountry }</i></td>
		</tr>
		<tr>
			<td><b>Executed to</b></td>
			<td><i>${bus.country }</i></td>
		</tr>
		<tr>
			<td><b>Date of Execution (dd/mm/yyyy)</b></td>
			<td><i>${bus.day }/${bus.month }/${bus.year }</i>
		</tr>
		<tr>
			<td><b>Time of Execution</b></td>
			<td><i>${hour }:${minutes }</i>
		</tr>
		<!--  <tr>
			<td><b>Latency</b></td>
			<td><i>${bus.latency }ms</i></td>
		</tr>
		<tr>
			<td><b>Bandwidth</b></td>
			<td><i>${bus.bandwidth }Mbps</i></td>
		</tr> -->
		<tr>
			<td><b>Service</b></td>
			<td><i>${bus.service }</i></td>
		</tr>
		<tr>
			<td><b>Latency</b></td>
			<td><i>${bus.latency }ms</i></td>
		</tr>
		<tr>
			<td><b>Data Rate</b></td>
			<td><i>${bus.dataRate }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Reliability</b></td>
			<td><i>${bus.reliability }%</i></td>
		</tr>
		<tr>
			<td><b>Availability</b></td>
			<td><i>${bus.availability }%</i></td>
		</tr>
		<tr>
			<td><b>Mobility</b></td>
			<td><i>${bus.mobility }Km/h</i></td>
		</tr>
		<tr>
			<td><b>Broadband Connectivity</b></td>
			<td><i>${bus.broadband }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Capacity</b></td>
			<td><i>${bus.capacity }Mbps/m2</i></td>
		</tr>
		<tr>
			<td><b>Device Density</b></td>
			<td><i>${bus.density }Devices/Km2</i></td>
		</tr>
		<tr>
			<td><b>Network Slicing</b></td>
			<td><i>${bus.slicing }</i></td>
		</tr>
		<tr>
			<td><b>Security</b></td>
			<td><i>${bus.security }</i></td>
		</tr>
		<!-- <tr>
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
				<li>Minimum bandwidth: 50Mbps</li>
				<li>Maximum latency: 200ms</li>
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
		</tr>  -->
	</table>
		
		<br>
		<form method="POST" action="BuisSmrtTrsm">
			<input type="hidden" name="guided" value="true"/>
			<input type="hidden" name="countryFromChecked" value="${bus.fromCountry }"/>
			<input type="hidden" name="latencyChecked" value="${bus.latency }"/>
			<input type="hidden" name="bandwidthChecked" value="${bus.bandwidth }"/>
			<input type="hidden" name="dayChecked" value="${bus.day }"/>
			<input type="hidden" name="monthChecked" value="${bus.month }"/>
			<input type="hidden" name="yearChecked" value="${bus.year }"/>
			<input type="hidden" name="hourChecked" value="${bus.hour }"/>
			<input type="hidden" name="minutesChecked" value="${bus.minutes }"/>
			<input type="hidden" name="latencyChecked" value="${bus.latency }"/>
			<input type="hidden" name="dataRateChecked" value="${bus.dataRate }"/>
			<input type="hidden" name="reliabilityChecked" value="${bus.reliability }"/>
			<input type="hidden" name="availabilityChecked" value="${bus.availability }"/>
			<input type="hidden" name="mobilityChecked" value="${bus.mobility }"/>
			<input type="hidden" name="broadbandChecked" value="${bus.broadband }"/>
			<input type="hidden" name="capacityChecked" value="${bus.capacity }"/>
			<input type="hidden" name="densityChecked" value="${bus.density }"/>
			<input type="hidden" name="slicingChecked" value="${bus.slicing }"/>
			<input type="hidden" name="securityChecked" value="${bus.security }"/>
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