<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Smart Turin Bp Edit</title>
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
    		<h1>Smart Turin Blueprint Edit</h1>
    		
    		<h3>${notAvailable }</h3>
    		<h3>${suggestion }</h3>
    		
    		<form name="ImMediaEdit" method="POST" action="SmrtTurin">
    		
    			<table border="1" align="center">
    			
					<tr>
						<th colspan="2">Blueprint Information</b></td>
					</tr>
					<tr>
						<td><b>Name</b></td>
						<td><i>${tur.name }</i></td>
					</tr>
					<tr>
						<td><b>Description</b></td>
						<td><i>${tur.description }</i></td>
					</tr>
					<tr>
						<td><b>Version</b></td>
						<td><i>${tur.version }</i></td>
					</tr>
					<tr>
						<td><b>Identity</b></td>
						<td><i>${tur.identity }</i></td>
					</tr>
					<tr>
						<td><b>Sector</b></td>
						<td><i>${tur.sector }</i></td>
					</tr>
					<tr>
						<td><b>Launched from</b></td>
						<td><i><select name="countryFromChecked">
								<option selected hidden="{tur.fromCountry }">${tur.fromCountry }</option>
								<option value="France">France</option>
								<option value="Greece">Greece</option>
								<option value="Italy">Italy</option>
								<option value="Spain">Spain</option>
							</select></i>
						</td>
					</tr>
					<tr>
						<td><b>Executed to</b></td>
						<td><i>${tur.country }</i></td>
					</tr>
					<tr>
						<td><b>Date of Execution (dd/mm/yyyy)</b></td>
						<td><i>
							Day: <input type="number" name="dayChecked" min="1" max="31" step="1" value='${tur.day }'/>
							<br>Month: <input type="number" name="monthChecked" min="1" max="12" step="1" value='${tur.month }'/>
							<br>Year: <input type="number" name="yearChecked" min="2019" max="2100" step="1" value='${tur.year }'/>
						</i></td>
					</tr>
					<tr>
						<td><b>Time of Execution</b></td>
						<td><i>
							Hour: <input type="number" name="hourChecked" min="0" max="23" step="1" value='${tur.hour }'/>
							<br>Minutes: <input type="number" name="minutesChecked" min="0" max="59" step="1" value='${tur.minutes }'/>
						</i></td>
					</tr>
					<tr>
						<td><b>Number of Devices</b></td>
						<td><i><input type="number" name="numOfDevsChecked" min="1" step="1" value=${tur.numOfDevs }></i></td>
					</tr>
					<tr>
						<td><b>Service</b></td>
						<td><i>${tur.service }</i></td>
					</tr>
					<tr>
						<td><b>Latency</b></td>
						<td><i><input type="number" name="latencyChecked" min="1" max="5" step="1" value="${tur.latency }">ms</i></td>
					</tr>
					<tr>
						<td><b>Data Rate</b></td>
						<td><i><input type="number" name="dataRateChecked" min="1" max="10" step="1" value="${tur.dataRate }">Mbps</i></td>
					</tr>
					<tr>
						<td><b>Reliability</b></td>
						<td><i><input type="number" name="reliabilityChecked" min="99.99" max="99.999" step="0.001" value="${tur.reliability }">%</i></td>
					</tr>
					<tr>
						<td><b>Availability</b></td>
						<td><i><input type="number" name="availabilityChecked" min="99.9" max="99.99" step="0.01" value="${tur.availability }">%</i></td>
					</tr>
					<tr>
						<td><b>Broadband Connectivity</b></td>
						<td><i><input type="number" name="broadbandChecked" min="10" max="100" step="1" value="${tur.broadband }">Mbps</i></td>
					</tr>
					<tr>
						<td><b>Capacity</b></td>
						<td><i><input type="number" name="capacityChecked" min="0.01" max="0.1" step="0.01" value="${tur.capacity }">Mbps/m2</i></td>
					</tr>
					<tr>
						<td><b>Device Density</b></td>
						<td><i><input type="number" name="densityChecked" min="1" max="60000" step="1" value="${tur.density }">Devices/Km2</i></td>
					</tr>
					<tr>
						<td><b>Network Slicing</b></td>
						<td><i><select name="slicingChecked">
							<option selected hidden="tur.slicing }">${tur.slicing }</option>
							<option value="N">N</option>
							<option value="Y">Y</option>
						</select></i></td>
					</tr>
					<tr>
						<td><b>Network Slicing</b></td>
						<td><i><select name="securityChecked">
							<option selected hidden="{tur.security }">${tur.slicing }</option>
							<option value="N">N</option>
							<option value="Y">Y</option>
						</select></i></td>
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