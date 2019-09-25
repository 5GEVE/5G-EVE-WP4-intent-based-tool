<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Business Augmented Booth Bp Edit</title>
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
    		<h1>Business Augmented Booth Blueprint Edit</h1>
    		
    		<h3>${notAvailable }</h3>
    		<h3>${suggestion }</h3>
    		
    <form name="ArSmrtTrsmEdit" method="POST" action="BuisSmrtTrsm">
	
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
			<td><i><select name="countryFromChecked">
					<option selected hidden="{bus.fromCountry }">${bus.fromCountry }</option>
					<option value="France">France</option>
					<option value="Greece">Greece</option>
					<option value="Italy">Italy</option>
					<option value="Spain">Spain</option>
				</select></i>
			</td>
		</tr>
		<tr>
			<td><b>Executed to</b></td>
			<td><i>${bus.country }</i></td>
		</tr>
		<tr>
			<td><b>Date of Execution (dd/mm/yyyy)</b></td>
			<td><i>
				Day: <input type="number" name="dayChecked" min="1" max="31" step="1" value='${bus.day }'/>
				<br>Month: <input type="number" name="monthChecked" min="1" max="12" step="1" value='${bus.month }'/>
				<br>Year: <input type="number" name="yearChecked" min="2019" max="2100" step="1" value='${bus.year }'/>
			</i></td>
		</tr>
		<tr>
			<td><b>Time of Execution</b></td>
			<td><i>
				Hour: <input type="number" name="hourChecked" min="0" max="23" step="1" value='${bus.hour }'/>
				<br>Minutes: <input type="number" name="minutesChecked" min="0" max="59" step="1" value='${bus.minutes }'/>
			</i></td>
		</tr>
		<!-- <tr>
			<td><b>Latency</b></td>
			<td><i><input type="number" name="latencyChecked" min="1" max="200" step="1" value="${bus.latency }">ms</i></td>
		</tr>
		<tr>
			<td><b>Bandwidth</b></td>
			<td><i><input type="number" name="bandwidthChecked" min="50" step="1" value="${bus.bandwidth }">Mbps</i></td>
		</tr> -->
		<tr>
			<td><b>Service</b></td>
			<td><i>${bus.service }</i></td>
		</tr>
		<tr>
			<td><b>Latency</b></td>
			<td><i><input type="number" name="latencyChecked" min="100" max="200" step="1" value="${bus.latency }">ms</i></td>
		</tr>
		<tr>
			<td><b>Data Rate</b></td>
			<td><i><input type="number" name="dataRateChecked" min="1" max="100" step="1" value="${bus.dataRate }">Mbps</i></td>
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
			<td><i>${bus.mobility }Km/hour</i></td>
		</tr>
		<tr>
			<td><b>Broadband Connectivity</b></td>
			<td><i><input type="number" name="broadbandChecked" min="500" max="30000" step="1" value="${bus.broadband }">Mbps</i></td>
		</tr>
		<tr>
			<td><b>Capacity</b></td>
			<td><i><input type="number" name="capacityChecked" min="0.3125" max="18.75" step="0.0001" value="${bus.capacity }">Mbps/m2</i></td>
		</tr>
		<tr>
			<td><b>Device Density</b></td>
			<td><i>${bus.density }Devices/Km2</i></td>
		</tr>
		<tr>
			<td><b>Network Slicing</b></td>
			<td><i><select name="slicingChecked">
				<option selected hidden="{bus.slicing }">${bus.slicing }</option>
				<option value="N">N</option>
				<option value="Y">Y</option>
			</select></i></td>
		</tr>
		<tr>
			<td><b>Security</b></td>
			<td><i>${bus.security }</i></td>
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
		</tr> -->
	</table>
		
		
		<input type="hidden" name="guided" value="true"/>
		<br>
		<input type="submit" value="Confirm"/>
		</form>
  	</div>
	
	<div class="footerCreated">
  			<p>5G European Validation platform for Extensive trials</p>
	</div>

</body>
</html>