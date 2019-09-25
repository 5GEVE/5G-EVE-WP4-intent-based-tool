package blueprints;

public class HlthMntrBp extends Blueprint {
	
	String location;
	String service;
	
	//constructors
	public HlthMntrBp() {
		
	}
	
	public HlthMntrBp(String location, String service) {
		this.location = "Greece";
		this.service = "Critical mMTC";
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
