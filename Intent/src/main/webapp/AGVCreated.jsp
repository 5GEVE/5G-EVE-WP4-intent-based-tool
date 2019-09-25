<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGV Bp Created</title>
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
    		<h1>AGV Blueprint Created</h1>
    		
    		<h5>If the Blueprint satisfies your needs please press confirm, otherwise press the edit button to modify the blueprint</h5>
	
	<table border="1" align="center">
		<tr>
			<th colspan="2">Blueprint Information</b></td>
		</tr>
		<tr>
			<td><b>Name</b></td>
			<td><i>${agv.name }</i></td>
		</tr>
		<tr>
			<td><b>Description</b></td>
			<td><i>${agv.description }</i></td>
		</tr>
		<tr>
			<td><b>Version</b></td>
			<td><i>${agv.version }</i></td>
		</tr>
		<tr>
			<td><b>Identity</b></td>
			<td><i>${agv.identity }</i></td>
		</tr>
		<tr>
			<td><b>Sector</b></td>
			<td><i>${agv.sector }</i></td>
		</tr>
		<tr>
			<td><b>Launched from</b></td>
			<td><i>${agv.fromCountry }</i></td>
		</tr>
		<tr>
			<td><b>Executed to</b></td>
			<td><i>${agv.country }</i></td>
		</tr>
		<tr>
			<td><b>Date of Execution (dd/mm/yyyy)</b></td>
			<td><i>${agv.day }/${agv.month }/${agv.year }</i>
		</tr>
		<tr>
			<td><b>Time of Execution</b></td>
			<td><i>${hour }:${minutes }</i>
		</tr>
		<tr>
			<td><b>Number of AGVs</b></td>
			<td><i>${agv.numOfAgvs }</i></td>
		</tr>
		<tr>
			<td><b>Location of AGVs</b></td>
			<td><i>${agv.location }</i></td>
		</tr>
		<!--  <tr>
			<td><b>Image of PLC Software</b></td>
			<td><i>${agv.image }</i></td>
		</tr>
		<tr>
			<td><b>Average data rate per AGV</b></td>
			<td><i>${agv.dataRate } Mbps</i></td>
		</tr>
		<tr>
			<td><b>SAP location</b></td>
			<td><i>${agv.sapLocation }</i></td>
		</tr> -->
		<tr>
			<td><b>Service</b></td>
			<td><i>${agv.service }</i></td>
		</tr>
		<tr>
			<td><b>Latency</b></td>
			<td><i>${agv.latency }ms</i></td>
		</tr>
		<tr>
			<td><b>Data Rate</b></td>
			<td><i>${agv.dataRate }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Reliability</b></td>
			<td><i>${agv.reliability }%</i></td>
		</tr>
		<tr>
			<td><b>Availability</b></td>
			<td><i>${agv.availability }%</i></td>
		</tr>
		<tr>
			<td><b>Mobility</b></td>
			<td><i>${agv.mobility }Km/h</i></td>
		</tr>
		<tr>
			<td><b>Broadband Connectivity</b></td>
			<td><i>${agv.broadband }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Capacity</b></td>
			<td><i>${agv.capacity }Mbps/m2</i></td>
		</tr>
		<tr>
			<td><b>Device Density</b></td>
			<td><i>${agv.density }Devices/Km2</i></td>
		</tr>
		<tr>
			<td><b>Network Slicing</b></td>
			<td><i>${agv.slicing }</i></td>
		</tr>
		<tr>
			<td><b>Security</b></td>
			<td><i>${agv.security }</i></td>
		</tr>
		<!-- <tr>
			<td><b>Atomic Functional Components involved</b></td>
			<td><i>vEPC<br>RAN<br>MEC server (Edge PLC)<br>Compute Node</i>
		</tr>
		<tr>
			<td><b>Connectivity Service</b></td>
			<td><i>L3VPN, GRE, 4G/5G</i></td>
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
			<td><i>Geographical location of the AGVs<br>sapRAN location<br>sapMEC location<br>Priority<br>Security</i></td>
		</tr>
		<tr>
			<td><b>Management and control capabilities of the tenant</b></td>
			<td><i>Provider managed</i></td>
		</tr>
		<tr>
			<td><b>SLA</b></td>
			<td><i>Sap1: N AGVs, message rate and message size<br>Bit rate of various connection points<br>Latency of saps<br>QoS (Service Availability and Reliability)</i></td>
		</tr>
		<tr>
			<td><b>Monitoring</b></td>
			<td><i>Sap1: bandwidth<br>Sap-Cp: latency<br>MEC server: cpu, memory, storage</i></td>
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
		<form method="POST" action="AGV">
			<input type="hidden" name="guided" value="true"/>
			<input type="hidden" name="countryFromChecked" value="${agv.fromCountry }"/>
			<input type="hidden" name="countryToChecked" value="${agv.country }"/>
			<input type="hidden" name="numOfAGVsChecked" value="${agv.numOfAgvs }"/>
			<input type="hidden" name="AGVsRateChecked" value="${agv.dataRate }"/>
			<input type="hidden" name="imageChecked" value="${agv.image }"/>
			<input type="hidden" name="dayChecked" value="${agv.day }"/>
			<input type="hidden" name="monthChecked" value="${agv.month }"/>
			<input type="hidden" name="yearChecked" value="${agv.year }"/>
			<input type="hidden" name="hourChecked" value="${agv.hour }"/>
			<input type="hidden" name="minutesChecked" value="${agv.minutes }"/>
			<input type="hidden" name="latencyChecked" value="${agv.latency }"/>
			<input type="hidden" name="dataRateChecked" value="${agv.dataRate }"/>
			<input type="hidden" name="reliabilityChecked" value="${agv.reliability }"/>
			<input type="hidden" name="availabilityChecked" value="${agv.availability }"/>
			<input type="hidden" name="mobilityChecked" value="${agv.mobility }"/>
			<input type="hidden" name="broadbandChecked" value="${agv.broadband }"/>
			<input type="hidden" name="capacityChecked" value="${agv.capacity }"/>
			<input type="hidden" name="densityChecked" value="${agv.density }"/>
			<input type="hidden" name="slicingChecked" value="${agv.slicing }"/>
			<input type="hidden" name="securityChecked" value="${agv.security }"/>
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