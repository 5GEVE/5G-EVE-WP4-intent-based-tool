package blueprints;

public class Video360Bp extends Blueprint {

	private String location;
	private String service;
	private int bandwidth;
	private int latency;
	
	//constructors
	public Video360Bp() {
		
	}
	
	public Video360Bp(String location, String service, int latency, int bandwidth) {
		this.location = location;
		this.service = service;
		this.latency = latency;
		this.bandwidth = bandwidth;
	}
	
	//getters - setters
	public int getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public int getLatency() {
		return latency;
	}

	public void setLatency(int latency) {
		this.latency = latency;
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
