package blueprints;

public class SmrtTurinBp extends Blueprint {
	
	private String location;
	private String service;
	private int numOfDevs;
	
	//constructors
	public SmrtTurinBp() {
		
	}
	
	public SmrtTurinBp(String location, String service, int numOfDevs) {
		this.location = location;
		this.service = service;
		this.numOfDevs = numOfDevs;
	}

	//getters - setters
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getNumOfDevs() {
		return numOfDevs;
	}

	public void setNumOfDevs(int numOfDevs) {
		this.numOfDevs = numOfDevs;
	}

}
