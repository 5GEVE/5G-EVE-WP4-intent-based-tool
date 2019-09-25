<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGV Edit</title>
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
    		<h1>AGV Blueprint Edit</h1>
    		
    		<h3>${notAvailable }</h3>
    		<h3>${suggestion }</h3>
    		
    		<form name="AGVEdit" method="POST" action="AGV">
    		
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
						<td><i><select name="countryFromChecked">
								<option selected hidden="{agv.fromCountry }">${agv.fromCountry }</option>
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
								<option selected hidden="{agv.country }">${agv.country }</option>
								<option value="Greece">Greece</option>
								<option value="Spain">Spain</option>
						</select></i></td>
					</tr>
					<tr>
						<td><b>Date of Execution (dd/mm/yyyy)</b></td>
						<td><i>
							Day: <input type="number" name="dayChecked" min="1" max="31" step="1" value='${agv.day }'/>
							<br>Month: <input type="number" name="monthChecked" min="1" max="12" step="1" value='${agv.month }'/>
							<br>Year: <input type="number" name="yearChecked" min="2019" max="2100" step="1" value='${agv.year }'/>
						</i></td>
					</tr>
					<tr>
						<td><b>Time of Execution</b></td>
						<td><i>
							Hour: <input type="number" name="hourChecked" min="0" max="23" step="1" value='${agv.hour }'/>
							<br>Minutes: <input type="number" name="minutesChecked" min="0" max="59" step="1" value='${agv.minutes }'/>
						</i></td>
					</tr>
					<tr>
						<td><b>Number of AGVs</b></td>
						<td><i><input type="number" name="numOfAGVsChecked" min="1" step="1" value="${agv.numOfAgvs }"></i></td>
					</tr>
					<tr>
						<td><b>Location of AGVs</b></td>
						<td><i>Will change according to experiment location</i></td>
					</tr>
					<!--  <tr>
						<td><b>Image of PLC Software</b></td>
						<td><i><select name="imageChecked">
								<option selected hidden="{agv.image }">${agv.image }</option>
								<option value="Image 1">Image 1</option>
								<option value="Image 2">Image 2</option>
								<option value="Image 3">Image 3</option>
							</select></i></i></td></i></td>
					</tr>
					<tr>
						<td><b>Average data rate per AGV</b></td>
						<td><i><input type="number" name="AGVsRateChecked" min="0" step="0.5" value=${agv.dataRate }>Mbps</i></td>
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
						<td><i><input type="number" name="latencyChecked" min="10" max="100" step="1" value="${agv.latency }">ms</i></td>
					</tr>
					<tr>
						<td><b>Data Rate</b></td>
						<td><i><input type="number" name="dataRateChecked" min="54" max="80" step="1" value="${agv.dataRate }">Mbps</i></td>
					</tr>
					<tr>
						<td><b>Reliability</b></td>
						<td><i><input type="number" name="reliabilityChecked" min="99" max="99.999" step="0.001" value="${agv.reliability }">%</i></td>
					</tr>
					<tr>
						<td><b>Availability</b></td>
						<td><i><input type="number" name="availabilityChecked" min="99" max="99.999" step="0.001" value="${agv.availability }">%</i></td>
					</tr>
					<tr>
						<td><b>Mobility</b></td>
						<td><i><input type="number" name="mobilityChecked" min="10" max="20" step="1" value="${agv.mobility }">Km/h</i></td>
					</tr>
					<tr>
						<td><b>Broadband Connectivity</b></td>
						<td><i><input type="number" name="broadbandChecked" min="108" max="6480" step="1" value="${agv.broadband }">Mbps</i></td>
					</tr>
					<tr>
						<td><b>Capacity</b></td>
						<td><i><input type="number" name="capacityChecked" min="1.3" max="3.2" step="0.01" value="${agv.capacity }">Mbps/m2</i></td>
					</tr>
					<tr>
						<td><b>Device Density</b></td>
						<td><i><input type="number" name="densityChecked" min="16000" max="40000" step="1" value="${agv.density }">Devices/Km2</i></td>
					</tr>
					<tr>
						<td><b>Network Slicing</b></td>
						<td><i><select name="slicingChecked">
								<option selected hidden="{agv.slicing }">${agv.slicing }</option>
								<option value="N">N</option>
								<option value="Y">Y</option>
						</select></i></td>
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
					</tr> -->
					<!--  <tr>
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
					</tr>	-->
					<!-- <tr>
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