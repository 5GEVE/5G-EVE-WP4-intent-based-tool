<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ar Interaction Date</title>
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
    		<h1>AR Interaction Blueprint</h1>
    		<br>
			${message }
			<h3>Please select the date you wish your experiment to run:</h3>	
	
			<form method="POST" action="ArSmrtTrsm">
				<input type="hidden" name="intent" value='${intent }'/>
				Day: <input type="number" name="day" min="1" max="31" step="1" value='${day }'/>
				Month: <input type="number" name="month" min="1" max="12" step="1" value='${month }'/>
				Year:  <input type="number" name="year" min="2019" max="2100" step="1" value='${year }'/>
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