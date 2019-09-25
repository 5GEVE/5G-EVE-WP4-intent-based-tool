package blueprints;

public class ConnAmbBp extends Blueprint {
	
	private String service;
	private String location;
	private int dataRate;
	private int latency;
	
	//constructors
	public ConnAmbBp() {
		
	}
	
	public ConnAmbBp(String service, String location, int dataRate, int latency) {
		this.service = service;
		this.location = location;
		this.dataRate = dataRate;
		this.latency = latency;
	}

	//getters - setters
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getDataRate() {
		return dataRate;
	}

	public void setDataRate(int dataRate) {
		this.dataRate = dataRate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getLatency() {
		return latency;
	}

	public void setLatency(int latency) {
		this.latency = latency;
	}
	
}
