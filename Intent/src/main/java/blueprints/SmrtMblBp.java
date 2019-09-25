package blueprints;

public class SmrtMblBp extends Blueprint {
	
	private String location;
	private String service;
	
	//constructors
	public SmrtMblBp() {
		
	}
	
	public SmrtMblBp(String location, String service) {
		this.location = location;
		this.service = service;
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

}
