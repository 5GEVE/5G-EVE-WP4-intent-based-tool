<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get Country</title>
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
  		
  		<img src="WebPages/5G-Eve-logo.jpg" alt="5G Eve Logo" style="height:150px; display: block; margin-left: auto; margin-right: auto; margin-top:10px; margin-bottom: 2px"/>
    		<h1>AGV Blueprint</h1>
			
			<h2>${errorMessage }</h2>
			
			<h3>Please select the country you are running the experiment from:</h3><br>
			<form method="POST" action="${servlet }">
				<input type="hidden" name="intent" value='${intent }'/>
				<input type="radio" name="fromCountry" checked="checked" value="from Italy"/>Italy
				<input type="radio" name="fromCountry" value="from Greece"/>Greece
				<input type="radio" name="fromCountry" value="from France"/>France
    			<input type="radio" name="fromCountry" value="from Spain"/>Spain
				<br><br>
				<input type="submit" value="Continue"/>
			</form>
  		</div>
  
  		<div class="column">
    		<h1>AGV Guided Selection</h1>
    		<h2>Select the attributes you want your Blueprint to have</h3>
    		
    		<form name="intentForm" method="POST" action="AGV">
    			<input type="hidden" name="guided" value='true'/>
    			
    			<h3>Select the country you want to run the experiment:</h3>
    			<input type="radio" name="countryToChecked" checked="checked" value="Greece"/>Greece
    			<input type="radio" name="countryToChecked" value="Spain"/>Spain
    			
    			<h3>Select the country you want to run the experiment from:</h3>
    			<input type="radio" name="countryFromChecked" checked="checked" value="France"/>France
    			<input type="radio" name="countryFromChecked" value="Greece"/>Greece
    			<input type="radio" name="countryFromChecked" value="Italy"/>Italy
    			<input type="radio" name="countryFromChecked" value="Spain"/>Spain
				
				<h3>Number of AGVs:</h3>
				<input type="number" name="numOfAGVsChecked" min="1" step="1" value="1">
				
				<h3>Data Rate of AGVs (optional):</h3>
				<input type="number" name="AGVsRateChecked" min="0" step="0.5" value="80">Mbps
				
				<h3>Image of PLC software:</h3>
    			<input type="radio" name="imageChecked" checked="checked" value="Image 1"/>Image 1
    			<input type="radio" name="imageChecked" value="Image 2"/>Image 2
    			<input type="radio" name="imageChecked" value="Image 3"/>Image 3
				
				<br><br><input type="submit" value="Submit"/>
    		</form>
 		 </div>
  
	</div>
	
	<div class="footerCreated">
  			<p>5G European Validation platform for Extensive trials</p>
	</div>
	
</body>
</html>