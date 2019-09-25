package com.wings.intentbased.Intent.services;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pojo {

	int numRBs;
	String service;
	
	public String getService() {
		return service;
	}

	public int getNumRBs() {
		return numRBs;
	}
	
	public void setService(String service) {
		this.service = service;
	}

	public void setNumRBs(int numRBs) {
		this.numRBs = numRBs;
	}
	
	
}
