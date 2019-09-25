package com.wings.intentbased.Intent.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Command {
	
	//constructors
	private String command;
	
	public Command() {
		
	}
	
	public Command(String command) {
		this.command=command;
	}

	//getters - setters
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
