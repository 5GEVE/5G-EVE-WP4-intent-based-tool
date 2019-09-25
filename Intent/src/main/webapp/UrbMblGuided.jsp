<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Urban Mobility Guided</title>
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
  		
  			<img src="WebPages/5G-Eve-logo.jpg" alt="5G Eve Logo" style="height:150px; display: block; margin-left: auto; margin-right: auto; margin-top:10px; margin-bottom: 2px"/>
    		<h1>Urban Mobility Guided Selection</h1>
    		<h2>Select the attributes you want your Blueprint to have</h2>	
  	</div>
	
	<form name="intentForm" method="POST" action="UrbMbl">
	<div class="row">
	
		<input type="hidden" name="guided" value='true'/>
  		
  		<div class="column" style="text-align: right">
  		
  				<h3>Select the country you want to run the experiment:</h3>
    			<input type="radio" name="countryToChecked" checked="checked" value="Italy"/>Italy
    			
    			<h3>Select the country you want to run the experiment from:</h3>
    			<input type="radio" name="countryFromChecked" checked="checked" value="France"/>France
    			<input type="radio" name="countryFromChecked" value="Greece"/>Greece
    			<input type="radio" name="countryFromChecked" value="Italy"/>Italy
    			<input type="radio" name="countryFromChecked" value="Spain"/>Spain
				
				
  		</div>
  
  		<div class="column" style="text-align: left">

				<h3>Select the date you want the experiment to be executed:</h3>
				Day: <input type="number" name="dayChecked" min="1" max="31" step="1" value='1'/>
				Month: <input type="number" name="monthChecked" min="1" max="12" step="1" value='1'/>
				Year: <input type="number" name="yearChecked" min="2019" max="2100" step="1" value='2019'/>
				
				<h3>Select the time you want the experiment to be executed:</h3>
				Hour: <input type="number" name="hourChecked" min="0" max="23" step="1" value='0'/>
				Minutes: <input type="number" name="minutesChecked" min="0" max="59" step="1" value='0'/>
			
 		 </div>
 		 
 	</div>
 		 
 	<div class="row" style="text-align: center">
 		
 		<h3>Number of Devices (smartphones):</h3>
		<input type="number" name="numOfDevsChecked" min="1" max="300" step="1" value="1">
				
		<h3>Make sure to have 5G EVE recognition service available and active!</h3>
 		
 		<input type="submit" value="Submit Selection"/>
 	</div>
  	</form>
	</div>
	
	<br><br>
	<div class="footerCreated">
  			<p>5G European Validation platform for Extensive trials</p>
	</div>
	
</body>
</html>