package blueprints;

public class SmrtHomeBp extends Blueprint {

	private String location;
	private String service;
	private int numOfDevs;
	
	//constructors
	public SmrtHomeBp() {
		
	}
	
	public SmrtHomeBp(String location, String service, int numOfDevs) {
		this.location = location;
		this.service = service;
		this.numOfDevs = numOfDevs;
	}
	
	//getters - setters
	public int getNumOfDevs() {
		return numOfDevs;
	}

	public void setNumOfDevs(int numOfDevs) {
		this.numOfDevs = numOfDevs;
	}

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
}
