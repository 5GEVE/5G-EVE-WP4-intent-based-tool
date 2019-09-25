<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Smart Energy Edit </title>
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
    		<h1>Smart Energy Blueprint Edit</h1>
    		
    		<h3>${notAvailable }</h3>
    		<h3>${suggestion }</h3>
    		
    		<form name="SmrtNrgEdit" method="POST" action="SmrtNrg">
    		
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
						<td><i><select name="countryFromChecked">
								<option selected hidden="{nrg.fromCountry }">${nrg.fromCountry }</option>
								<option value="France">France</option>
								<option value="Greece">Greece</option>
								<option value="Italy">Italy</option>
								<option value="Spain">Spain</option>
							</select></i>
						</td>
					</tr>
					<tr>
						<td><b>Executed to</b></td>
						<td><i><select name="countryToChecked">
								<option selected hidden="{nrg.country }">${nrg.country }</option>
								<option value="France">France</option>
								<option value="Greece">Greece</option>
							</select></i></i></td>
					</tr>
					<tr>
						<td><b>Date of Execution (dd/mm/yyyy)</b></td>
						<td><i>
							Day: <input type="number" name="dayChecked" min="1" max="31" step="1" value='${nrg.day }'/>
							<br>Month: <input type="number" name="monthChecked" min="1" max="12" step="1" value='${nrg.month }'/>
							<br>Year: <input type="number" name="yearChecked" min="2019" max="2100" step="1" value='${nrg.year }'/>
						</i></td>
					</tr>
					<tr>
						<td><b>Time of Execution</b></td>
						<td><i>
							Hour: <input type="number" name="hourChecked" min="0" max="23" step="1" value='${nrg.hour }'/>
							<br>Minutes: <input type="number" name="minutesChecked" min="0" max="59" step="1" value='${nrg.minutes }'/>
						</i></td>
					</tr>
					<!--  <tr>
						<td><b>Number of devices/km2</b></td>
						<td><i><input type="number" name="numOfDevsChecked" min="1" max="2000" step="1" value="${nrg.density }"></i></td>
					</tr>
					<tr>
						<td><b>Average data rate</b></td>
						<td><i><input type="number" name="dataRateChecked" min="1" step="0.5" value=${nrg.dataRate }>Mbps</i></td>
					</tr> -->
					<tr>
						<td><b>Service</b></td>
						<td><i>${nrg.service }</i></td>
					</tr>
					<tr>
						<td><b>Latency</b></td>
						<td><i><input type="number" name="latencyChecked" min="5" max="30" step="1" value="${nrg.latency }">ms</i></td>
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
						<td><i><input type="number" name="densityChecked" min="1" max="2000" step="1" value="${nrg.density }">Devices/Km2</i></td>
					</tr>
					<tr>
						<td><b>Network Slicing</b></td>
						<td><i>${nrg.slicing }</i></td>
					</tr>
					<tr>
						<td><b>Security</b></td>
						<td><i>${nrg.security }</i></td>
					</tr>
					<!--  <tr>
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
    		
    		<input type="hidden" name="guided" value="true"/>
    		<br>
    		<input type="submit" value="Confirm"/>
    		</form>
    		
    	</div>
    </div>
	
	<div class="footerCreated">
  			<p>5G European Validation platform for Extensive trials</p>
	</div>

</body>
</html>