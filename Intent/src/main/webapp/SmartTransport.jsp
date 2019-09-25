<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Smart Transport Sector</title>
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
    		<h1>Smart Transport Sector</h1>
    		<br>
    		<h3>Please select the service you want to run:</h3>
	
			<form name="intentForm" method="POST" action="Expression">
				<input type="radio" name="intent" checked="checked" value=" recognition service "/>Recognition Service        
				<input type="radio" name="intent" value=" tracking service "/>Tracking Service      
				<input type="radio" name="intent" value=" urban mobility "/>Urban Mobility
			<br><br>
			<input type="submit" value="Continue"/>
			</form>
  		</div>
	</div>
 

	<div class="footer">
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