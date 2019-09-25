package com.wings.intentbased.Intent.servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wings.intentbased.Intent.model.Industry;
import com.wings.intentbased.Intent.model.MediaEntertainment;
import com.wings.intentbased.Intent.model.SmartCity;
import com.wings.intentbased.Intent.model.SmartTourism;
import com.wings.intentbased.Intent.model.SmartTransport;
import com.wings.intentbased.Intent.model.Utilities;
import com.wings.intentbased.Intent.services.CommandService1;

import blueprints.AGVBp;

/**
 * Servlet implementation class Expression
 */
@WebServlet()
public class Expression extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	int command = 0;
	String message = "";
	
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //request.setAttribute("message", message);
        //request.getRequestDispatcher("ExpressionPage.html").forward(request, response);
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	//getting information from browser page
    	String intent = request.getParameter("intent");
    	intent = " " + intent + " ";
    	String newIntent = request.getParameter("newIntent");
    	if(newIntent != null)
    		intent += newIntent;
    	System.out.println("intention: " + intent);
    	
    	//gets the identifier for the sector
    	CommandService1 comm = new CommandService1();
    	command = comm.checkCommand(intent);
    	
    	switch (command) {
    		//industry sector and redirection to related service
    		case 1:
    			message = "Industry 4.0";
    			Industry ind = new Industry();
    			String sector1 = ind.getSector(intent);
    			if(sector1.equalsIgnoreCase("agvs")) {
    				/*HttpSession session = request.getSession(false);
    				session.setAttribute("intent", intent);
    				response.sendRedirect("AGVRedirect.jsp");
    				message += "agvs";*/
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("AGV").forward(request, response);
    			}
    			else if(sector1.equalsIgnoreCase("no sector")) {
    				//response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT );
    				//response.addHeader("Location","IndustrySector.jsp");
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("IndustrySector.jsp").forward(request, response);
    			}
    			break;
    		//utilities sector and redirection to related service
    		case 2:
    			message = "Utilities";
    			Utilities utl = new Utilities();
    			String sector2 = utl.getSector(intent);
    			if(sector2.equalsIgnoreCase("smart energy")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("SmrtNrg").forward(request, response);
    			}
    			else if(sector2.equalsIgnoreCase("no sector")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("UtilitiesSector.jsp").forward(request, response);
    			}
    			break;
    		
    		//smart cities sector and redirection to related service
    		case 3:
    			message = "Smart Cities";
    			SmartCity smc = new SmartCity();
    			String sector3 = smc.getSector(intent);
    			if(sector3.equalsIgnoreCase("health monitoring")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("HlthMntr").forward(request, response);
    			}
    			else if(sector3.equalsIgnoreCase("smart home")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("SmrtHome").forward(request, response);
    			}
    			else if(sector3.equalsIgnoreCase("connected ambulance")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("ConnAmb").forward(request, response);
    			}
    			else if(sector3.equalsIgnoreCase("smart turin")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("SmrtTurin").forward(request, response);
    			}
    			else if(sector3.equalsIgnoreCase("smart mobility")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("SmrtMbl").forward(request, response);
    			}
    			else if(sector3.equalsIgnoreCase("no sector")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("SmartCitySector.jsp").forward(request, response);
    			}
    			else {
    				message = "Conflict between Smart City sectors!";
    				request.setAttribute("message", message);
    		    	request.getRequestDispatcher("ExpressionPage.jsp").forward(request, response);
    			}
    			break;
    		
    		//media and entertainment sector and redirection to related service	
    		case 4:
    			message = "Media and entertainment";
    			MediaEntertainment media = new MediaEntertainment();
    			String sector4 = media.getSector(intent);
    			if(sector4.equalsIgnoreCase("ultra high fidelity")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("UHF").forward(request, response);
    			}
    			else if(sector4.equalsIgnoreCase("live event")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("LiveEvent").forward(request, response);
    			}
    			else if(sector4.equalsIgnoreCase("immersive and integrated")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("ImMedia").forward(request, response);
    			}
    			else if(sector4.equalsIgnoreCase("virtual 360")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("Video360").forward(request, response);
    			}
    			else if(sector4.equalsIgnoreCase("no sector")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("MediaSector.jsp").forward(request, response);
    			}
    			else {
    				message = "Conflict between Media and Entertainment sectors";
    				request.setAttribute("message", message);
    		    	request.getRequestDispatcher("ExpressionPage.jsp").forward(request, response);
    			}
    			break;
    		
    		//smart transport sector and redirection to related service	
    		case 5:
    			message = "Smart Transport";
    			SmartTransport transport = new SmartTransport();
    			String sector5 = transport.getSector(intent);
    			if(sector5.equalsIgnoreCase("recognition service")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("RecServ").forward(request, response);
    			}
    			else if(sector5.equalsIgnoreCase("tracking service")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("TrackServ").forward(request, response);
    			}
    			else if(sector5.equalsIgnoreCase("urban mobility")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("UrbMbl").forward(request, response);
    			}
    			else if(sector5.equalsIgnoreCase("no sector")) {
    				request.setAttribute("intent", intent);
    				request.getRequestDispatcher("SmartTransport.jsp").forward(request, response);
    			}
    			else {
    				message = "Conflict between Smart Transport sectors";
    				request.setAttribute("message", message);
    				request.getRequestDispatcher("ExpressionPage.jsp").forward(request, response);
    			}
    			break;
    		
    		//smart tourism sector and redirection to related service	
    		case 6:
    			 message = "Smart Tourism";
    			 SmartTourism tourism = new SmartTourism();
    			 String sector6 = tourism.getSector(intent);
    			 if(sector6.equalsIgnoreCase("ar interaction")) {
    				 request.setAttribute("intent", intent);
    				 request.getRequestDispatcher("ArSmrtTrsm").forward(request, response);
    			 }
    			 else if(sector6.equalsIgnoreCase("business booth")) {
    				 request.setAttribute("intent", intent);
    				 request.getRequestDispatcher("BuisSmrtTrsm").forward(request, response);
    			 }
    			 else if(sector6.equalsIgnoreCase("no sector")) {
    				 request.setAttribute("intent", intent);
    				 request.getRequestDispatcher("SmartTourism.jsp").forward(request, response);
    			 }
    			 else {
    				 message = "Conflict between Smart Tourism sectors";
    				 request.setAttribute("message", message);
    				 request.getRequestDispatcher("ExpressionPage.jsp").forward(request, response);
    			 }
    			break;
    		
    		//wrong intention provided	
    		case 0:
    			//message = "No intent given";
    			//request.setAttribute("message", message);
    			request.setAttribute("message", "Wrong intention or no intention given. For more information on how to express an intent press the Help button or use the Guided Selection.");
    			request.getRequestDispatcher("ExpressionPage.jsp").forward(request, response);
    			System.out.println(message);
    			break;
    		
    		//conflict between sectors	
    		case 100:
    			message = "Conflict between sectors";
    			request.setAttribute("message", message);
    			request.getRequestDispatcher("ExpressionPage.jsp").forward(request, response);
    			System.out.print(message);
    			break;
    	}
    	
     	/*request.setAttribute("message", message);
    	request.getRequestDispatcher("ExpressionPage.jsp").forward(request, response);*/
    }

}
