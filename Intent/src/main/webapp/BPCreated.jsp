<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blueprint Created</title>
<link rel="stylesheet" href="DemoCSS.css">
</head>
<body>
	<img src="WebPages/wingslogo.png" alt="Wings Logo" class="center"/>
	
	<h1>Success</h1>
	<h3>${bp.name } Created</h3>
	<p><b><i>Blueprint information</i></b></p>
	<p><b>Name:</b> <i>${bp.name }</i></p>
	<p><b>Description:</b> <i>${bp.description }</i></p>
	<p><b>Version:</b> <i>${bp.version }</i></p>
	<p><b>Identity:</b> <i>${bp.identity }</i></p>
	<p><b>Country:</b> <i>${bp.country }</i></p>
	<p><b>Location of AGVs:</b> <i>${bp.location }</i></p>
	<p><b>Service:</b> <i>${bp.service }</i></p>
	
	<form method="POST" action="Expression">
		<input type="submit" value="New Intent"/>
	</form>
</body>
</html>