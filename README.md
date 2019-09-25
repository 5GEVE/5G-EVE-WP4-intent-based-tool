# 5G-EVE-WP4-intent-based-tool
Code to support the 5g-EVE intent-based interface tool

This project is a Maven project.

In order to install the intent-based interface tool you have to import it to your project and also import the librariies in the Necessary Libraries folder. Also uncomment the dependencies in the pom.xml file.

In this project Tomcat 7.0 Server is used.

In case you want to use a database connect it to your project and apply the necessary changes in the Database.java file. Also uncomment from the servlets the code corresponding to the database part.

After completing the above you have to run the Tomcat server to start the project and in a browser you can get to the starting page at the following link: http://localhost:8080/Intent/IntentPage.jsp
