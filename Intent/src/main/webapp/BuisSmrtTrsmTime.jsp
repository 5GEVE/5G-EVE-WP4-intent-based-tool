<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Business Augmented Booth From Time</title>
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
    		<h1>Business Augmented Booth Blueprint</h1>
    		<br>
			${message }
			<h3>Please select the time you wish your experiment to run:</h3>	
	
			<form method="POST" action="BuisSmrtTrsm">
				<input type="hidden" name="intent" value='${intent }'/>
				Hour: <input type="number" name="hour" min="0" max="23" step="1" value='${hour }'/>
				Minutes: <input type="number" name="minutes" min="0" max="59" step="1" value='${minutes }'/>
				<br><br>
				<input type="submit" value="Continue"/>
			</form>
  		</div>
	</div>
	
	<div class="footer">
  			<p>5G European Validation platform for Extensive trials</p>
	</div>
	
</body>
</html>