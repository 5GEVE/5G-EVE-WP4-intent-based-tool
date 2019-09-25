<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Help Page</title>
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
  		<div class="column">
    		<h1>Help Page</h1>
			<p>In order to express an intent you have to define the service you want to run (e.g recognition service) or the sector your service belongs to (e.g smart transport).</p>
			<p>In order to define the country you wish to run the experiment from you have to use the word <i>from</i> followed by one of the available countries: <i>France, Greece, Italy, Spain.</i></p>
			<p>In order to define the country you wish to run the experiment to you have to use the word <i>to</i> followed by the country of your choice: <i>France, Greece, Italy, Spain,</i> or the words <i>to the french/greek/italian/spanish site/facility</i>. In case the service is provided only in one country the choice will be made automatically, otherwise you will receive a warning message to provide a country, where the service is available.</p>
			<p>In case you want to provide some numbers (e.g number of agvs, number of devices, etc) you have to use the symbols <i># and $</i>. Note that the numbers cannot be decimals or less than zero. In case of a provided number is out of range a warning message will be received to provide a valid value. Below are two list provided information about the cases each symbol is used for:
			<ul>
				<li>#</li>
					<ul>
						<li>Number of AGVs (AGVs scenario)</li>
						<li>Number of devices (Recognition/Tracking/Urban mobility/Smart energy/Smart Turin/Smart Home scenarios)</li>
						<li>Latency (AR Interaction/Business Augmented Booth/Connected Ambulance/Virtual visit scenarios)</li>
						<li>Uplink (Immersive Media/On-Site Live Event Experience scenarios)
					</ul>
				
				<li>$</li>
					<ul>
						<li>Data Rate (AGVs/Smart energy/Connected Ambulance scenarios)</li>
						<li>Bandwidth (AR Interaction/Business Augmented Booth/Virtual visit/Immersive and Integrated Media/Ultra High-Fidelity Media/On-Site Live Event Experience scenarios)</li>
					</ul>
			</ul>
			<p>In order to provide the date you want the experiment to be executed you have to use the following format: dd/mm/yyyy. If the date provided has passed you will receive a warning message. Also if the number of days provided to a specific month are wrong you will receive a warning message too.</p>
			<p>In order to provide the time you want the experiment to be executed you have to use the following format: hh/mm, where hh varies from 0 to 23. In case you provide a wrong time the time will be filled automatically.</p>
			<p>After you provide your intent, in case there are some necessary fields missing, you will be guided to select some values for the required fields.</p>
			<p>When the selection is finished a table will be presented, providing information about the expected functionality of the experiment (Blueprint). You are provided with the choice to make changes to the Blueprint before finalizing your decision.</p>
		
			<br><br><br>
			<img src="WebPages/5G-Eve-logo.jpg" alt="5G Eve Logo" style="height:250px; display: block; margin-left: auto; margin-right: auto; margin-top:10px; margin-bottom: 2px"/>
  		</div>
  
  		<div class="column">
    		<h1>Guided Selection Helping notes</h1>
    		<p>In case of the guided selection you have to select the service you want to run from one of the available services.</p>
    		<p>After you made a selection you will be redirected to a page providing information about the necessary fields to be filled.</p>
    		<p>In case that only one choice is available, the field will be automatically filled, otherwise you will have to choose. Some values are also provided by default, but you can change them according to your needs.</p>
    		<p>When the selection is finished a table will be presented, providing information about the expected functionality of the experiment (Blueprint). You are provided with the choice to make changes to the Blueprint before finalizing your decision.</p>
    		<br><br>
    		<h3>Information on the extra fields</h3>
    		<p><u>Latency</u></p>
    		<p>Measures the duration between the transmission of a small data packet from the application layer at the source node and the successful reception at the application layer at the destination node plus equivalent time needed to carry the response back.</p>
    		<p><u>Speed (data rate)</u></p>
    		<p>It is set as the minimum user experienced data rate required for the user to get a quality experience of the targeted application/use case (it is also the required sustainable data rate).</p>
    		<p><u>Reliability</u></p>
    		<p>The amount of sent packets successfully delivered to the destination within the time constraint required by the targeted service, divided by the total number of sent packets. NOTE: the reliability rate is evaluated only when the network is available.</p>
    		<p><u>Availability</u></p>
    		<p>The network availability is characterized by its availability rate X, defined as follows: the network is available for the targeted communication in X% of the locations where the network is deployed and X% of the time. X% varies from 90% to 99.9999%.</p>
    		<p><u>Mobility</u></p>
    		<p>Mobility refers to the system’s ability to provide seamless service experience to users that are moving. In addition to mobile users, the identified 5G use cases show that 5G networks will have to support an increasingly large segment of static and nomadic users/devices.</p>
    		<p><u>Broadband Connectivity</u></p>
    		<p>High data rate provision during high traffic demand periods (It is also a measure of the peak data rate required).</p>
    		<p><u>Capacity</u></p>
    		<p>Capacity is measured in bit/s/m2 is defined as the total amount of traffic exchanged by all devices over the considered area. The KPI requirement on the minimum Traffic Volume Density/Area Capacity for a given use case is given by the product [required user experienced data rate] x [required connection density].</p>
    		<p><u>Device Density</u></p>
    		<p>Up to several hundred thousand simultaneous active connections per square kilometer shall be supported for massive sensor deployments. Here, active means the devices are exchanging data with the network.</p>
 		 </div>
  
	</div>
	
	<div class="footerCreated">
  			<p>5G European Validation platform for Extensive trials</p>
	</div>

</body>
</html>