/*package com.wings.intentbased.Intent.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wings.intentbased.Intent.model.Command;
import com.wings.intentbased.Intent.services.CommandService;

@Path("/command")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommandResource {
	
	static public Command comm = new Command();
	
	@GET
	public String getCommand() {
		System.out.println(comm.getCommand());
		if(comm.getCommand() == null) {
			return "Command was not given!";
		}
		
		return "Command to execute: " + comm.getCommand();
	}
	
	//@PUT
	public String setCommand(String command) {
		CommandService comServ = new CommandService();
		comm.setCommand(command);
		System.out.println("Execute: " + comm.getCommand());
		String result = comServ.checkCommand(command);		
		System.out.print(result);
		
		return result;
	}
}*/