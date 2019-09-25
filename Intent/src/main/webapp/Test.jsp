<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test Page</title>
<link rel="stylesheet" href="IntentCSS.css">
</head>
<body>	

	<script src="intentJS.js"></script>

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
  		
  			<img src="WebPages/5G-Eve-logo.jpg" alt="5G Eve Logo" style="height:150px; display: block; margin-left: auto; margin-right: auto; margin-top:10px; margin-bottom: 2px"/>
    		<h1>Guided Selection</h1>
    				
		<br>
  	</div>
	
	<div class="row">
	<div class="columnContainer">
		<div class="column">
		
		<button class="collapsible" style="display: block; float: right"><img src="WebPages/smart-transport.png" alt="Smart Transport Logo" style="height: 20px"/>  Smart Transport</button>
			<div class="content">
 	 			<ul>
 	 				<li><a href="RecServGuided.jsp" style="text-decoration:none; color: black;"><i>Recognition Service</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Automatic identification of passenger mobility patterns and related transport modalities aimed at: 1. identifying different transportation modality demands 2. performing  spatial planning 3. enabling efficient trip planning and control"></li>
                    <li><a href="TrackServGuided.jsp" style="text-decoration:none; color: black;"><i>Tracking Service</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Integration of 5G data and mobility data from different transport operators"></li>
                    <li><a href="UrbMblGuided.jsp" style="text-decoration:none; color: black;"><i>Urban mobility</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Service enhancing multi-modality between railway network and other collective transportation utilities by providing useful infomobility services"></li>
                </ul>
			</div>
			
			<button class="collapsible"><img src="WebPages/smart-tourism.png" alt="Smart Tourism Logo" style="height: 20px"/>  Smart Tourism</button>
			<div class="content">
 	 			<ul>
 	 				<li><a href="ArSmrtTrsmGuided.jsp" style="text-decoration:none; color: black;"><i>AR Interaction</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Implementation of AR Technologies"></li>
 	 				<li><a href="BuisSmrtTrsmGuided.jsp" style="text-decoration:none; color: black;"><i>Business Augmented Booth</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Implementation of an Augmented Fair Service with AR Interaction allowing enhanced physical experience of both exhibitors and visitors"></li>
                </ul>
			</div>
    		
    		<button class="collapsible"><img src="WebPages/industry-icon.png" alt="Industry Logo" style="height: 20px"/>  Industry 4.0</button>
			<div class="content">
 	 			<ul>
                    <li><a href="AGVsGuided.jsp" style="text-decoration:none; color: black;"><i>Autonomous vehicles in manufacturing environments - AGVs</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Service for AGVs using 5G network for Automation and Control purposes"></li>
                </ul>
			</div>
				
			
  		</div>
	
  		<div class="column">

    		
    		<button class="collapsible"><img src="WebPages/utilities.png" alt="Utilities Logo" style="height: 20px"/>  Utilities</button>
			<div class="content">
 	 			<ul>
 	 				<li><a href="SmrtNrgGuided.jsp" style="text-decoration:none; color: black;"><i>Smart Energy</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Fault management for distributed electricity generation in smart grids"></li>
                </ul>
			</div>
    		
    		<button class="collapsible"><img src="WebPages/smart-city-icon.png" alt="Smart City Logo" style="height: 20px"/>  Smart Cities</button>
			<div class="content">
 	 			<ul>
 	 				<li>Health</li>
                    	<ul>
                    		<li><a href="ConnAmbGuided.jsp" style="text-decoration:none; color: black;"><i>Connected Ambulance</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Advance the emergency ambulance services with their healthcare stakeholders to help create improved experiences and outcomes for patients in their care"></li>
                    		<li><a href="HlthMntrGuided.jsp" style="text-decoration:none; color: black;"><i>Health Monitoring and Forecasting</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Monitoring and assessment of vital signs for inferring the physical condition of an individual"></li>
                    	</ul><br>
                    	
                    <li>Safety and Environment</li>
                    	<ul>
                    		<li><a href="SmrtHomeGuided.jsp" style="text-decoration:none; color: black;"><i>Smart Personalised Spaces</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Estimation of the preferences of the user with respect to the configuration of their indoor environment"></li>
                    		<li><a href="SmrtMblGuided.jsp" style="text-decoration:none; color: black;"><i>Smart Mobility</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Exploitation of knowledge on user preferences so as to provide personalised recommendations on an optimal route"></li>
                    		<li><a href="SmrtTurinGuided.jsp" style="text-decoration:none; color: black;"><i>Smart Turin</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Management of critical issues related to urban mobility"></li>
                    	</ul>
                </ul>
			</div>
			
			<button class="collapsible"><img src="WebPages/video360.png" alt="Virtual 360 Logo" style="height: 20px"/>  Media & Entertainment</button>
			<div class="content">
 	 			<ul>
 	 				<li><a href="ImMediaGuided.jsp" style="text-decoration:none; color: black;"><i>Immersive and Integrated Media</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Live and on-demand channels for high quality TV (up to 4K video service)"></li>
                    <li><a href="LiveEventGuided.jsp" style="text-decoration:none; color: black;"><i>On-Site Live Event Experience</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="TV events in on-site places like Stadiums, car Racing, Sport Events, etc"></li>
                    <li><a href="UHFGuided.jsp" style="text-decoration:none; color: black;"><i>Ultra High-Fidelity Media</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Live and on-demand channels for high quality TV (up to 4K video service)"></li>
                    <li><a href="Video360Guided.jsp" style="text-decoration:none; color: black;"><i>Virtual Visit - Virtual 360</i></a> <img src="WebPages/help-icon.png" alt="Wings Logo" style="height:12px" title="Virtual Visit over 5G"></li>
                </ul>
			</div>
  
		</div>
		</div>
		</div>
		
		<div class="footerCreated">
  			<p>5G European Validation platform for Extensive trials</p>
		</div>
		
	<script>
		var coll = document.getElementsByClassName("collapsible");
		var i;

		for (i = 0; i < coll.length; i++) {
  			coll[i].addEventListener("click", function() {
    		this.classList.toggle("active");
    		var content = this.nextElementSibling;
    		if (content.style.maxHeight){
     	 		content.style.maxHeight = null;
    		} else {
      			content.style.maxHeight = content.scrollHeight + "px";
   			} 
  			});
		}
	</script>
	
</body>
</html>