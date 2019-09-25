<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ultra High Fidelity Media Bp Created</title>
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
    		<h1>Ultra High-Fidelity Media Blueprint Created</h1>
    		
    		<h5>If the Blueprint satisfies your needs please press confirm, otherwise press the edit button to modify the blueprint</h5>
	
	<table border="1" align="center">
		<tr>
			<th colspan="2">Blueprint Information</b></td>
		</tr>
		<tr>
			<td><b>Name</b></td>
			<td><i>${uhf.name }</i></td>
		</tr>
		<tr>
			<td><b>Description</b></td>
			<td><i>${uhf.description }</i></td>
		</tr>
		<tr>
			<td><b>Version</b></td>
			<td><i>${uhf.version }</i></td>
		</tr>
		<tr>
			<td><b>Identity</b></td>
			<td><i>${uhf.identity }</i></td>
		</tr>
		<tr>
			<td><b>Sector</b></td>
			<td><i>${uhf.sector }</i></td>
		</tr>
		<tr>
			<td><b>Launched from</b></td>
			<td><i>${uhf.fromCountry }</i></td>
		</tr>
		<tr>
			<td><b>Executed to</b></td>
			<td><i>${uhf.country }</i></td>
		</tr>
		<tr>
			<td><b>Date of Execution (dd/mm/yyyy)</b></td>
			<td><i>${uhf.day }/${uhf.month }/${uhf.year }</i>
		</tr>
		<tr>
			<td><b>Time of Execution</b></td>
			<td><i>${hour }:${minutes }</i>
		</tr>
		<!--  X<tr>
			<td><b>Allocated bandwidth per mobile cell</b></td>
			<td><i>${uhf.cellBand }Mbps</i></td>
		</tr> -->
		<tr>
			<td><b>Service</b></td>
			<td><i>${uhf.service }</i></td>
		</tr>
		<tr>
			<td><b>Latency</b></td>
			<td><i>${uhf.latency }ms</i></td>
		</tr>
		<tr>
			<td><b>Data Rate</b></td>
			<td><i>${uhf.dataRate }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Reliability</b></td>
			<td><i>${uhf.reliability }%</i></td>
		</tr>
		<tr>
			<td><b>Availability</b></td>
			<td><i>${uhf.availability }%</i></td>
		</tr>
		<tr>
			<td><b>Mobility</b></td>
			<td><i>${uhf.mobility }Km/h</i></td>
		</tr>
		<tr>
			<td><b>Broadband Connectivity</b></td>
			<td><i>${uhf.broadband }Mbps</i></td>
		</tr>
		<tr>
			<td><b>Capacity</b></td>
			<td><i>${uhf.capacity }Mbps/m2</i></td>
		</tr>
		<tr>
			<td><b>Device Density</b></td>
			<td><i>${uhf.density }Devices/Km2</i></td>
		</tr>
		<tr>
			<td><b>Network Slicing</b></td>
			<td><i>${uhf.slicing }</i></td>
		</tr>
		<tr>
			<td><b>Security</b></td>
			<td><i>${uhf.security }</i></td>
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
			<td><i>
				<ul>
					<li>Allocated bandwidth per mobile cell: 100-200Mbps</li>
					<li>Configuration must support a mix of SD 60% HD 25% and 4K 15% live channels</li>
					<li>90% live TV, 10% VOD</li>
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
		<form method="POST" action="UHF">
			<input type="hidden" name="guided" value="true"/>
			<input type="hidden" name="countryToChecked" value="${uhf.country }"/>
			<input type="hidden" name="countryFromChecked" value="${uhf.fromCountry }"/>
			<input type="hidden" name="cellBandChecked" value="${uhf.cellBand }"/>
			<input type="hidden" name="dayChecked" value="${uhf.day }"/>
			<input type="hidden" name="monthChecked" value="${uhf.month }"/>
			<input type="hidden" name="yearChecked" value="${uhf.year }"/>
			<input type="hidden" name="hourChecked" value="${uhf.hour }"/>
			<input type="hidden" name="minutesChecked" value="${uhf.minutes }"/>
			<input type="hidden" name="latencyChecked" value="${uhf.latency }"/>
			<input type="hidden" name="dataRateChecked" value="${uhf.dataRate }"/>
			<input type="hidden" name="reliabilityChecked" value="${uhf.reliability }"/>
			<input type="hidden" name="availabilityChecked" value="${uhf.availability }"/>
			<input type="hidden" name="mobilityChecked" value="${uhf.mobility }"/>
			<input type="hidden" name="broadbandChecked" value="${uhf.broadband }"/>
			<input type="hidden" name="capacityChecked" value="${uhf.capacity }"/>
			<input type="hidden" name="densityChecked" value="${uhf.density }"/>
			<input type="hidden" name="slicingChecked" value="${uhf.slicing }"/>
			<input type="hidden" name="securityChecked" value="${uhf.security }"/>
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